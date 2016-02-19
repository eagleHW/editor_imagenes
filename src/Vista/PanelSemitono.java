
package Vista;

import Controlador.SemitonoListener;
import ManipulacionImagenes.BibliotecaGrafica;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class PanelSemitono extends PanelEditorImagen {

    private Interfaz ventana_principal;
    private BibliotecaGrafica bg = new BibliotecaGrafica();
    
    private JPanel semitono_panel_izq_inf_opciones = new JPanel();
    private JPanel semitono_panel_izq_inf = new JPanel();
    private JPanel semitono_panel_der = new JPanel();
    private JPanel semitono_panel_izq = new JPanel(); 
    
    private JLabel semitono_label_izq_sup = new JLabel();
    private JLabel semitono_label_izq_inf = new JLabel();
    private JLabel semitono_label_der = new JLabel();
    
    private JScrollPane semitono_scroll_label_izq_sup = new JScrollPane(semitono_label_izq_sup);
    private JScrollPane semitono_scroll_label_izq_inf = new JScrollPane(semitono_label_izq_inf);
    private JScrollPane semitono_scroll_label_der = new JScrollPane(semitono_label_der);
        
    private JButton semitono_boton = new JButton("Aceptar");
    
    private JLabel semitono_label_ventana = new JLabel("Tamaño de la ventana (n*n) :");
    private SpinnerModel semitono_spinner_model_ventana = new SpinnerNumberModel(3,0,10,1);
    private JSpinner semitono_spinner_ventana = new JSpinner(semitono_spinner_model_ventana);
    
    private JLabel semitono_label_reduccion = new JLabel("Tamaño (%)");
    private SpinnerModel semitono_spinner_model_reduccion = new SpinnerNumberModel(100,0,100,1);
    private JSpinner semitono_spinner_reduccion = new JSpinner(semitono_spinner_model_reduccion);
    
    //Imagenes Semitono
    private BufferedImage semitono_img_izq_inf, semitono_img_izq_inf_modificada;
    private BufferedImage[] coleccion_imagen;
    
    private SemitonoListener semitono_listener;
    
    
    public PanelSemitono(Interfaz ventana_principal){
        
        this.ventana_principal = ventana_principal;
        this.semitono_listener = new SemitonoListener(this, ventana_principal);
        this.coleccion_imagen = set_coleccion_imagen();
        
        this.setLayout(new GridLayout(1,2,5,10));
        
        semitono_panel_izq = new JPanel(new GridBagLayout()); 
        semitono_panel_der = new JPanel(new BorderLayout());
        
        semitono_panel_der.add(semitono_scroll_label_der,BorderLayout.CENTER);
        
        semitono_label_izq_sup.setHorizontalAlignment(JLabel.CENTER);
        semitono_label_izq_inf.setHorizontalAlignment(JLabel.CENTER);
        semitono_label_der.setHorizontalAlignment(JLabel.CENTER);

        semitono_label_izq_sup.setIcon(new ImageIcon(ventana_principal.getImage()));
       
        try {
            File file_imagen = new File("images/points/1.jpg");
            semitono_img_izq_inf = ImageIO.read(file_imagen);
            semitono_label_izq_inf.setIcon(new ImageIcon(semitono_img_izq_inf));
        } catch (IOException ex) {
            System.out.println("Problemas al cargar la imagen");
        }
       
        semitono_spinner_ventana.setName("Ventana");
        semitono_spinner_reduccion.setName("Reduccion");
    
        semitono_spinner_ventana.addChangeListener(semitono_listener);
        semitono_spinner_reduccion.addChangeListener(semitono_listener);
        
        semitono_boton.addActionListener(semitono_listener);
        
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
            
        semitono_panel_izq.add(semitono_scroll_label_izq_sup,especif);
            
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
            
        semitono_panel_izq.add(semitono_panel_izq_inf,especif);
    
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
            
        semitono_panel_izq.add(semitono_boton,especif);
            
        semitono_panel_izq_inf.setLayout(new GridBagLayout());
        
        semitono_panel_izq_inf_opciones.setLayout(new GridLayout(4,1,0,-1));
        
        semitono_panel_izq_inf_opciones.add(semitono_label_ventana);
        semitono_panel_izq_inf_opciones.add(semitono_spinner_ventana);
        
        semitono_panel_izq_inf_opciones.add(semitono_label_reduccion);
        semitono_panel_izq_inf_opciones.add(semitono_spinner_reduccion);

        especif.gridx = 0;
        especif.gridy = 0;
        especif.gridwidth = 2;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 1.0;
        especif.fill = GridBagConstraints.BOTH;
        especif.anchor = GridBagConstraints.CENTER;
        especif.ipadx = 0;
             
        semitono_panel_izq_inf.add(semitono_scroll_label_izq_inf,especif);
            
        especif.gridx = 2;
        especif.gridy = 0;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 0.2;  
        especif.weighty = 1.0;
        especif.fill = GridBagConstraints.VERTICAL;
        especif.anchor = GridBagConstraints.CENTER;
            
            
        semitono_panel_izq_inf.add(semitono_panel_izq_inf_opciones,especif);
               
        this.add(semitono_panel_izq);
        this.add(semitono_panel_der);
        this.revalidate();
        
    }
    
    public BufferedImage get_image_der_inf(){
        return semitono_img_izq_inf;
    }
        
    public void cambiar_imagen_izq(BufferedImage imagen){
        semitono_img_izq_inf_modificada = imagen;
        semitono_label_izq_inf.setIcon(new ImageIcon(imagen));
        
    }
    
    public BufferedImage[] set_coleccion_imagen(){
        
        BufferedImage[] coleccion_imagen = new BufferedImage[16];
        
        String[] nombres_files = {"1.jpg","2.jpg","3.jpg","4.jpg","5.jpg","6.jpg","7.jpg","8.jpg","9.jpg","10.jpg"
                                 ,"11.jpg","12.jpg","13.jpg","14.jpg","15.jpg","16.jpg"};
                
        try{
            
            for (int i = 0; i < nombres_files.length ; i++) {
                coleccion_imagen[i] = ImageIO.read(new File("images/points/" + nombres_files[i]));
                
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
        semitono_label_der.setIcon(new ImageIcon(imagen));
        ventana_principal.actualizar_interfaz();
    
    }

    @Override
    public void poner_imagen_izq(BufferedImage imagen) {
  
        semitono_label_izq_sup.setIcon(new ImageIcon(imagen));
        semitono_label_der.setIcon(null);
    
    }
    
    
    
    
}
