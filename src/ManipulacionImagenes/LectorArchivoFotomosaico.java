
package ManipulacionImagenes;

import K3Tree.K3TreeNode;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author rae
 */
public class LectorArchivoFotomosaico {
    
    private BibliotecaGrafica bg = new BibliotecaGrafica();
    private String path;
    private File dir; 
    private HashMap<String,K3TreeNode<LinkedList<String>>> hash_map = new HashMap(5000);
    
    
    public LectorArchivoFotomosaico(String path, boolean actualizar){
    
    this.path = path;
    
    hash_map = new HashMap(5000);
    leer_archivos(new File(path),hash_map);
    this.hash_map = hash_map;
    serializar_hashmap(hash_map,"hashtable.ser");
    
    System.out.println("Tamaño hashmap : " + hash_map.size());

            
    }
    
   
    //Hacer que funcione recursivamente
    private void  leer_archivos(File dir, HashMap<String,K3TreeNode<LinkedList<String>>> hash_map){
        
        int average_red, average_green, average_blue , average;
        K3TreeNode<LinkedList<String>> nuevo_elemento;
        BufferedImage imagen;
        String key;
        String path = "";
        
        int is_jpg = 0;        
        
        K3TreeNode[] nodos  = new K3TreeNode[1];
        
        
        try{
            
            File[] files = dir.listFiles();
          
            for( File file : files){
                
                if( file.isDirectory()){
                    System.out.println("Inicie : " + file.getName());
                    leer_archivos(file,hash_map);
                    System.out.println("Finalize : " + file.getName());
                    
                }else{
                    if(Files.probeContentType(file.toPath()).equals("image/jpeg")){
                   
                        is_jpg++;
                        
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
                        
                    }
                }
            }
                         
        }catch(IOException e){
            
            JOptionPane.showMessageDialog(null, "Error al cargar imagen para fotomosaico\n"
                    + path.substring(path.lastIndexOf("/fotograma")),"Error", JOptionPane.ERROR_MESSAGE);
            
            System.out.println("Problema al cargar imagen para fotomosaico - LectorArchivoFotomosaico.java");
            
        }
       
    }
    
    private boolean isJPG(File file) throws FileNotFoundException, IOException{
        
        DataInputStream input_stream = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
        try {
            if (input_stream.readInt() == 0xffd8ffe0) {
                return true;
            } else {
                return false;
            }
        } finally {
            input_stream.close();
        }
               
    }
    
    private void serializar_hashmap(HashMap tabla, String ruta_archivo){
        
        try{
            
            FileOutputStream fileOut =
            new FileOutputStream(ruta_archivo);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(tabla);
            out.close();
            fileOut.close();

        }catch(IOException e){
            System.out.println("Error al serializar objeto");
        }    
    }
    
    
}
