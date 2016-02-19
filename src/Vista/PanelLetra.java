
package Vista;

import Controlador.LetraListener;
import Controlador.SemitonoListener;
import ManipulacionImagenes.BibliotecaGrafica;
import ManipulacionImagenes.Filtros;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author rae
 */
public class PanelLetra extends PanelEditorImagen{

    private Interfaz ventana_principal;
    private BibliotecaGrafica bg = new BibliotecaGrafica();
    private Filtros filter = new Filtros();
    
    private final int VALOR_INICIAL_VENTANA = 3; 
    private final int VALOR_INICIAL_REDUCCION = 50;
    
    private JPanel letra_panel_izq_inf_opciones = new JPanel();
    private JPanel letra_panel_izq_inf = new JPanel();
    private JPanel letra_panel_der = new JPanel();
    private JPanel letra_panel_izq = new JPanel(); 
    
    private JLabel letra_label_izq_sup = new JLabel();
    private JLabel letra_label_izq_inf = new JLabel();
    private JLabel letra_label_der = new JLabel();
    
    private JScrollPane letra_scroll_label_izq_sup = new JScrollPane(letra_label_izq_sup);
    private JScrollPane letra_scroll_label_izq_inf = new JScrollPane(letra_label_izq_inf);
    private JScrollPane letra_scroll_label_der = new JScrollPane(letra_label_der);
        
    private JButton letra_boton = new JButton("Aceptar");
    
    private JLabel letra_label_ventana = new JLabel("Tamaño de la ventana (n*n) :");
    private SpinnerModel letra_spinner_model_ventana = new SpinnerNumberModel(VALOR_INICIAL_VENTANA,0,10,1);
    private JSpinner letra_spinner_ventana = new JSpinner(letra_spinner_model_ventana);
    
    private JLabel letra_label_reduccion = new JLabel("Tamaño (%)");
    private SpinnerModel letra_spinner_model_reduccion = new SpinnerNumberModel(VALOR_INICIAL_REDUCCION,0,100,1);
    private JSpinner letra_spinner_reduccion = new JSpinner(letra_spinner_model_reduccion);
    
    //Imagenes Semitono
    private BufferedImage letra_img_izq_inf, letra_img_izq_inf_modificada;
    private BufferedImage[] coleccion_imagen;
    
    private LetraListener letra_listener;
    
    public PanelLetra(Interfaz ventana_principal){
        
        this.ventana_principal = ventana_principal;
        this.letra_listener = new LetraListener(this, ventana_principal);  //Listener
        this.coleccion_imagen = set_coleccion_imagen();                       // Metodo set_coleccion_imagen
        
        this.setLayout(new GridLayout(1,2,5,10));
        
        letra_panel_izq = new JPanel(new GridBagLayout()); 
        letra_panel_der = new JPanel(new BorderLayout());
        
        letra_panel_der.add(letra_scroll_label_der,BorderLayout.CENTER);
        
        letra_label_izq_sup.setHorizontalAlignment(JLabel.CENTER);
        letra_label_izq_inf.setHorizontalAlignment(JLabel.CENTER);
        letra_label_der.setHorizontalAlignment(JLabel.CENTER);

        letra_label_izq_sup.setIcon(new ImageIcon(ventana_principal.getImage()));
       
        try {
            File file_imagen = new File("images/letters/M.jpg");
            letra_img_izq_inf = ImageIO.read(file_imagen);
            letra_label_izq_inf.setIcon(new ImageIcon(
                    filter.filtro_reduccion_porcentaje(letra_img_izq_inf, VALOR_INICIAL_REDUCCION )));
        } catch (IOException ex) {
            System.out.println("Problemas al cargar la imagen");
        }
       
        letra_spinner_ventana.setName("Ventana");
        letra_spinner_reduccion.setName("Reduccion");
    
        letra_spinner_ventana.addChangeListener(letra_listener);
        letra_spinner_reduccion.addChangeListener(letra_listener);
        
        letra_boton.addActionListener(letra_listener);
        
        GridBagConstraints especif = new GridBagConstraints();
        
        especif.gridx = 0;
        especif.gridy = 0;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 1.0;
        especif.insets = new Insets(20,10,0,0);
        especif.fill = GridBagConstraints.BOTH;
        especif.anchor = GridBagConstraints.CENTER;
            
        letra_panel_izq.add(letra_scroll_label_izq_sup,especif);
            
        especif.gridx = 0;
        especif.gridy = 1;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 0.4;
        especif.insets = new Insets(20,10,0,0);
        especif.fill = GridBagConstraints.BOTH;
        especif.anchor = GridBagConstraints.CENTER;
        especif.ipady = 30;
            
        letra_panel_izq.add(letra_panel_izq_inf,especif);
    
        especif.gridx = 0;
        especif.gridy = 2;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 0.05;
        especif.insets = new Insets(0,10,20,0);
        especif.fill = GridBagConstraints.HORIZONTAL;
        especif.anchor = GridBagConstraints.CENTER;
        especif.ipady = 0;
        especif.ipadx = 20;
            
        letra_panel_izq.add(letra_boton,especif);
            
        letra_panel_izq_inf.setLayout(new GridBagLayout());
        
        letra_panel_izq_inf_opciones.setLayout(new GridLayout(4,1,0,-1));
        
        letra_panel_izq_inf_opciones.add(letra_label_ventana);
        letra_panel_izq_inf_opciones.add(letra_spinner_ventana);
        
        letra_panel_izq_inf_opciones.add(letra_label_reduccion);
        letra_panel_izq_inf_opciones.add(letra_spinner_reduccion);

        especif.gridx = 0;
        especif.gridy = 0;
        especif.gridwidth = 2;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 1.0;
        especif.fill = GridBagConstraints.BOTH;
        especif.anchor = GridBagConstraints.CENTER;
        especif.ipadx = 0;
             
        letra_panel_izq_inf.add(letra_scroll_label_izq_inf,especif);
            
        especif.gridx = 2;
        especif.gridy = 0;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 0.2;  
        especif.weighty = 1.0;
        especif.fill = GridBagConstraints.VERTICAL;
        especif.anchor = GridBagConstraints.CENTER;
            
            
        letra_panel_izq_inf.add(letra_panel_izq_inf_opciones,especif);
               
        this.add(letra_panel_izq);
        this.add(letra_panel_der);
        this.revalidate();
        
    }
   
    public BufferedImage get_image_der_inf(){
        return letra_img_izq_inf;
    }
        
    public void cambiar_imagen_izq(BufferedImage imagen){
        letra_img_izq_inf_modificada = imagen;
        letra_label_izq_inf.setIcon(new ImageIcon(imagen));
        
    }
    
    public BufferedImage[] set_coleccion_imagen(){
        
        BufferedImage[] coleccion_imagen = new BufferedImage[16];
        
        String[] nombres_files = {"M.jpg","N.jpg","H.jpg","#.jpg","Q.jpg","U.jpg","A.jpg","0.jpg","O.jpg","Y.jpg"
                                 ,"2.jpg","$.jpg","%.jpg","+.jpg","_.jpg","blanco.jpg"};
                
        try{
            
            for (int i = 0; i < nombres_files.length ; i++) {
                coleccion_imagen[i] = ImageIO.read(new File("images/letters/" + nombres_files[i]));
                
            }      
            
        }catch(IOException ex){
        
            System.out.println("Error al cargar la imagen");
        
        }
        
        return coleccion_imagen;
        
    }
    
    public BufferedImage[] get_coleccion_imagen(){
        
        return coleccion_imagen;
    }
    
    @Override
    public void poner_imagen_der(BufferedImage imagen) {
        ventana_principal.setImageGuardar(imagen);  
        letra_label_der.setIcon(new ImageIcon(imagen));
        ventana_principal.actualizar_interfaz();
    
    }

    @Override
    public void poner_imagen_izq(BufferedImage imagen) {
        letra_label_izq_sup.setIcon(new ImageIcon(imagen));
        letra_label_der.setIcon(null);
    }
    
}
