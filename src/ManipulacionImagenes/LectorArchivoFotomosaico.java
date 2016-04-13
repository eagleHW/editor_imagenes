
package ManipulacionImagenes;

import K3Tree.K3TreeNode;
import Vista.Interfaz;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

/**
 *
 * @author rae
 */
public class LectorArchivoFotomosaico extends SwingWorker<Void,Integer>{
    
    private BibliotecaGrafica bg = new BibliotecaGrafica();
    private String path;
    private File dir; 
    private HashMap<String,K3TreeNode<LinkedList<String>>> hash_map;
    private JProgressBar progreso = new JProgressBar(0,100);
    private JDialog dialog;
    private int numero_archivos;
    private int numero_archivos_procesados = 0;
    
    public LectorArchivoFotomosaico(String path, Interfaz ventana_principal){
    
        this.path = path;
        this.dialog = new JDialog(ventana_principal, "Carga Imágenes", true);
        
    }
    
    private void  leer_archivos(File dir, HashMap<String,K3TreeNode<LinkedList<String>>> hash_map){
        
        int average_red, average_green, average_blue , average;
        K3TreeNode<LinkedList<String>> nuevo_elemento;
        BufferedImage imagen;
        String key;
        String path = "";
        
               
        try{
            
            File[] files = dir.listFiles();
          
            for( File file : files){
    
                if(this.isCancelled()){
                    break;
                }
                
                if( file.isDirectory()){
                    System.out.println("Inicie : " + file.getName());
                    leer_archivos(file,hash_map);
                    System.out.println("Finalize : " + file.getName());
                    
                }else{
                    if(Files.probeContentType(file.toPath()).equals("image/jpeg")){
                                           
                        imagen = ImageIO.read(file);
                        
                        average = bg.getAverageRGB(imagen,5);
                        average_red = bg.getRedNum(average);
                        average_green = bg.getGreenNum(average);
                        average_blue = bg.getBlueNum(average);
        
                        key = "( " + average_red + "," + average_green + "," + average_blue + ")";
                        
                        path = file.getCanonicalPath();
                        if(hash_map.containsKey(key)){
                                                       
                            hash_map.get(key).getAtributo().add(path.substring(path.lastIndexOf("/fotograma")));
                            
                        }else{
                            
                            nuevo_elemento = new K3TreeNode(average_red,average_green,average_blue, new LinkedList());
                            nuevo_elemento.getAtributo().add(path.substring(path.lastIndexOf("/fotograma")));
                            hash_map.put(key, nuevo_elemento);
                            
                        }         
                        
                        numero_archivos_procesados++;
                        publish(numero_archivos_procesados);
                        System.out.println("hola");
                        
                    }
                }
            }
                         
        }catch(IOException e){
            
            JOptionPane.showMessageDialog(null, "Error al cargar imagen para fotomosaico\n"
                    + path.substring(path.lastIndexOf("/fotograma")),"Error", JOptionPane.ERROR_MESSAGE);
            
            System.out.println("Problema al cargar imagen para fotomosaico - LectorArchivoFotomosaico.java");
            
            numero_archivos_procesados--;
            
        }
       
    }
    
    private int contar_archivos(File dir){
        
        int numero_archivos = 0;
        
         try{
            
            File[] files = dir.listFiles();
          
            for( File file : files){
                
                if(this.isCancelled()){
                    break;
                }
                
                if( file.isDirectory()){
                    numero_archivos += contar_archivos(file);
                }else{
                    if(Files.probeContentType(file.toPath()).equals("image/jpeg")){ 
                        numero_archivos++;
                    }
                }
                
            }
                         
        }catch(IOException e){
            numero_archivos--;
        }
        
        return numero_archivos;
    }
    
    private void serializar_hashmap(HashMap tabla, String ruta_archivo){
        
        try{
            if(!this.isCancelled()){
                
                FileOutputStream fileOut = new FileOutputStream(ruta_archivo);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(tabla);
                out.close();
                fileOut.close();
            }
        }catch(IOException e){
            System.out.println("Error al serializar objeto");
        }    
    }

    @Override
    protected Void doInBackground() throws Exception {
        
        numero_archivos = contar_archivos(new File(path));;  
        hash_map = new HashMap(10000);
        leer_archivos(new File(path),hash_map);
        serializar_hashmap(hash_map,"hashtable.ser");
        progreso.setValue(100);
        return null;
        
    }
    
    @Override
    protected void process(List<Integer> chunks) {
        
        int val = chunks.get(0);
        progreso.setValue((val * 100)/ numero_archivos );
       
   } 

    private void set_progressbar_frame(){
          
       dialog.setLayout(new GridBagLayout());
       dialog.setVisible(false);
       dialog.setSize(new Dimension(300, 100));
       dialog.setLocationRelativeTo(null);
       dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       progreso.setVisible(true);
       progreso.setValue(0);
       progreso.setStringPainted(true);
      
       GridBagConstraints especif = new GridBagConstraints();
       
       especif.gridx = 0;
       especif.gridy = 0;
       especif.gridwidth = 1;
       especif.gridheight = 1;
       especif.weightx = 0.15;  
       especif.weighty = 1.0;
       especif.insets = new Insets(0,15,0,15);
       especif.fill = GridBagConstraints.HORIZONTAL;
       especif.anchor = GridBagConstraints.CENTER;
            
       dialog.add(progreso,especif);
       
   }
    
    public void visible_frame(){

        set_progressbar_frame();
        dialog.setVisible(true);
        
    }
    
    public JDialog getDialog( ){
    
    return dialog;
    
    
    }
     
}
