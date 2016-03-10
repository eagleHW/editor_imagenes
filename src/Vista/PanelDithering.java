/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;

import Controlador.DitheringListener;
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
public class PanelDithering extends PanelEditorImagen {
    
    private Interfaz ventana_principal;
    private BibliotecaGrafica bg = new BibliotecaGrafica();
    private Filtros filter = new Filtros();
   
    private final int VALOR_INICIAL_VENTANA = 3; 
    private final int VALOR_INICIAL_REDUCCION = 50;
    
    private JPanel dithering_panel_izq_inf_opciones = new JPanel();
    private JPanel dithering_panel_izq_inf = new JPanel();
    private JPanel dithering_panel_der = new JPanel();
    private JPanel dithering_panel_izq = new JPanel(); 
    
    private JLabel dithering_label_izq_sup = new JLabel();
    private JLabel dithering_label_izq_inf = new JLabel();
    private JLabel dithering_label_der = new JLabel();
    
    private JScrollPane dithering_scroll_label_izq_sup = new JScrollPane(dithering_label_izq_sup);
    private JScrollPane dithering_scroll_label_izq_inf = new JScrollPane(dithering_label_izq_inf);
    private JScrollPane dithering_scroll_label_der = new JScrollPane(dithering_label_der);
        
    private JButton dithering_boton = new JButton("Aceptar");
    
    private JLabel dithering_label_ventana = new JLabel("Tamaño de la ventana (n*n) :");
    private SpinnerModel dithering_spinner_model_ventana = new SpinnerNumberModel(VALOR_INICIAL_VENTANA,0,10,1);
    private JSpinner dithering_spinner_ventana = new JSpinner(dithering_spinner_model_ventana);
    
    private JLabel dithering_label_reduccion = new JLabel("Tamaño (%) :");
    private SpinnerModel dithering_spinner_model_reduccion = new SpinnerNumberModel(VALOR_INICIAL_REDUCCION,0,100,1);
    private JSpinner dithering_spinner_reduccion = new JSpinner(dithering_spinner_model_reduccion);
    
    //Imagenes Semitono
    private BufferedImage dithering_img_izq_inf, dithering_img_izq_inf_modificada;
    private BufferedImage[] coleccion_imagen;
    
    private DitheringListener dithering_listener;
    
    public PanelDithering(Interfaz ventana_principal){
        
        this.ventana_principal = ventana_principal;
        this.dithering_listener = new DitheringListener(this, ventana_principal);
        this.coleccion_imagen = set_coleccion_imagen();
        
        this.setLayout(new GridLayout(1,2,5,10));
        
        dithering_panel_izq = new JPanel(new GridBagLayout()); 
        dithering_panel_der = new JPanel(new BorderLayout());
        
        dithering_panel_der.add(dithering_scroll_label_der,BorderLayout.CENTER);
        
        dithering_label_izq_sup.setHorizontalAlignment(JLabel.CENTER);
        dithering_label_izq_inf.setHorizontalAlignment(JLabel.CENTER);
        dithering_label_der.setHorizontalAlignment(JLabel.CENTER);

        dithering_label_izq_sup.setIcon(new ImageIcon(ventana_principal.getImage()));
       
        try {
            File file_imagen = new File("images/dithering/1.jpg");
            dithering_img_izq_inf = ImageIO.read(file_imagen);
            dithering_label_izq_inf.setIcon(new ImageIcon(
                    filter.filtro_reduccion_porcentaje(dithering_img_izq_inf, VALOR_INICIAL_REDUCCION )));
        } catch (IOException ex) {
            System.out.println("Problemas al cargar la imagen");
        }
       
        dithering_spinner_ventana.setName("Ventana");
        dithering_spinner_reduccion.setName("Reduccion");
    
        dithering_spinner_ventana.addChangeListener(dithering_listener);
        dithering_spinner_reduccion.addChangeListener(dithering_listener);
        
        dithering_boton.addActionListener(dithering_listener);
        
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
            
        dithering_panel_izq.add(dithering_scroll_label_izq_sup,especif);
            
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
            
        dithering_panel_izq.add(dithering_panel_izq_inf,especif);
    
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
            
        dithering_panel_izq.add(dithering_boton,especif);
            
        dithering_panel_izq_inf.setLayout(new GridBagLayout());
        
        dithering_panel_izq_inf_opciones.setLayout(new GridLayout(4,1,0,-1));
        
        dithering_panel_izq_inf_opciones.add(dithering_label_ventana);
        dithering_panel_izq_inf_opciones.add(dithering_spinner_ventana);
        
        dithering_panel_izq_inf_opciones.add(dithering_label_reduccion);
        dithering_panel_izq_inf_opciones.add(dithering_spinner_reduccion);

        especif.gridx = 0;
        especif.gridy = 0;
        especif.gridwidth = 2;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 1.0;
        especif.fill = GridBagConstraints.BOTH;
        especif.anchor = GridBagConstraints.CENTER;
        especif.ipadx = 0;
             
        dithering_panel_izq_inf.add(dithering_scroll_label_izq_inf,especif);
            
        especif.gridx = 2;
        especif.gridy = 0;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 0.2;  
        especif.weighty = 1.0;
        especif.fill = GridBagConstraints.VERTICAL;
        especif.anchor = GridBagConstraints.CENTER;
            
            
        dithering_panel_izq_inf.add(dithering_panel_izq_inf_opciones,especif);
               
        this.add(dithering_panel_izq);
        this.add(dithering_panel_der);
        this.revalidate();
        
        
    }
    
        public BufferedImage get_image_der_inf(){
        return dithering_img_izq_inf;
    }
        
    public void cambiar_imagen_izq(BufferedImage imagen){
        dithering_img_izq_inf_modificada = imagen;
        dithering_label_izq_inf.setIcon(new ImageIcon(imagen));
        
    }
    
    public BufferedImage[] set_coleccion_imagen(){
        
        BufferedImage[] coleccion_imagen = new BufferedImage[10];
        
        String[] nombres_files = {"1.jpg","2.jpg","3.jpg","4.jpg","5.jpg","6.jpg","7.jpg","8.jpg","9.jpg","10.jpg"};
                
        try{
            
            for (int i = 0; i < nombres_files.length ; i++) {
                coleccion_imagen[i] = ImageIO.read(new File("images/dithering/" + nombres_files[i]));
                
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
        dithering_label_der.setIcon(new ImageIcon(imagen));
        ventana_principal.actualizar_interfaz();
    
    }

    @Override
    public void poner_imagen_izq(BufferedImage imagen) {
        
        dithering_label_izq_sup.setIcon(new ImageIcon(imagen));
        dithering_label_der.setIcon(null);
    
    }
    
}
