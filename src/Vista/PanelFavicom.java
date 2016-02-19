
package Vista;

import Controlador.FavicomListener;
import ManipulacionImagenes.BibliotecaGrafica;
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
public class PanelFavicom extends PanelEditorImagen{
    
    private Interfaz ventana_principal;
    private BibliotecaGrafica bg = new BibliotecaGrafica();
        
    private JPanel favicom_panel_izq_inf_opciones = new JPanel();
    private JPanel favicom_panel_izq_inf = new JPanel();
    private JPanel favicom_panel_der = new JPanel();
    private JPanel favicom_panel_izq = new JPanel(); 
    
    private JLabel favicom_label_izq_sup = new JLabel();
    private JLabel favicom_label_izq_inf = new JLabel();
    private JLabel favicom_label_der = new JLabel();
    
    private JScrollPane favicom_scroll_label_izq_sup = new JScrollPane(favicom_label_izq_sup);
    private JScrollPane favicom_scroll_label_izq_inf = new JScrollPane(favicom_label_izq_inf);
    private JScrollPane favicom_scroll_label_der = new JScrollPane(favicom_label_der);
        
    private JButton favicom_boton = new JButton("Aceptar");
    
    private JLabel favicom_spinner_label_transparencia = new JLabel("Nivel de transparencia (%)");
    private SpinnerModel favicom_spinner_model_transparencia = new SpinnerNumberModel(50,0,100,1);
    private JSpinner favicom_spinner_transparencia = new JSpinner(favicom_spinner_model_transparencia);
    
    private JLabel favicom_spinner_label_reduccion = new JLabel("Tamaño (%)");
    private SpinnerModel favicom_spinner_model_reduccion = new SpinnerNumberModel(100,0,100,1);
    private JSpinner favicom_spinner_reduccion = new JSpinner(favicom_spinner_model_reduccion);
    
    private JLabel favicom_spinner_label_posc_x = new JLabel("X : ");
    private SpinnerModel favicom_spinner_model_posc_x = new SpinnerNumberModel(0,0,1,1);
    private JSpinner favicom_spinner_posc_x = new JSpinner(favicom_spinner_model_posc_x); 
    
    private JLabel favicom_spinner_label_posc_y = new JLabel("Y : ");
    private SpinnerModel favicom_spinner_model_posc_y = new SpinnerNumberModel(1,0,1,1);
    private JSpinner favicom_spinner_posc_y = new JSpinner(favicom_spinner_model_posc_y); 
    
    //Imagenes Favicom
    private BufferedImage favicom_img_izq_inf, favicom_img_izq_inf_modificada;
    
    private FavicomListener favicom_listener;
    
    public PanelFavicom(Interfaz ventana_principal){
        
            this.ventana_principal = ventana_principal;
            this.favicom_listener = new FavicomListener(this,ventana_principal);
            
            this.setLayout(new GridLayout(1,2,5,10));
            
            favicom_spinner_label_posc_x.setText("X : (MAX_VALUE : " + 
                                        (ventana_principal.getImage().getWidth() - 1) +" )");
            
            favicom_spinner_label_posc_y.setText("Y : (MAX_VALUE : " + 
                                        (ventana_principal.getImage().getHeight() - 1) +" )");
            
            favicom_spinner_reduccion.setEnabled(false);
            favicom_spinner_transparencia.setEnabled(false);
            favicom_spinner_posc_x.setEnabled(false);
            favicom_spinner_posc_y.setEnabled(false);
            favicom_boton.setEnabled(false);
            
            
            favicom_panel_izq = new JPanel(new GridBagLayout()); 
            favicom_panel_der = new JPanel(new BorderLayout());
            
            favicom_panel_der.add(favicom_scroll_label_der,BorderLayout.CENTER);
                     
            favicom_label_izq_sup.setHorizontalAlignment(JLabel.CENTER);
            favicom_label_izq_inf.setHorizontalAlignment(JLabel.CENTER);
            favicom_label_der.setHorizontalAlignment(JLabel.CENTER);
                        
            favicom_label_izq_sup.setIcon(new ImageIcon(ventana_principal.getImage()));
            
            favicom_label_izq_inf.setIcon(null);
            favicom_label_izq_inf.setText("Click para agregar imagen");
            
            favicom_spinner_transparencia.setName("Transparencia");
            favicom_spinner_reduccion.setName("Reduccion");
            favicom_spinner_posc_x.setName("X");
            favicom_spinner_posc_y.setName("Y");
            
            favicom_label_izq_inf.addMouseListener(favicom_listener);
            
            favicom_spinner_transparencia.addChangeListener(favicom_listener);    
            favicom_spinner_reduccion.addChangeListener(favicom_listener);
            favicom_spinner_posc_x.addChangeListener(favicom_listener);
            favicom_spinner_posc_y.addChangeListener(favicom_listener);
    
            favicom_boton.addActionListener(favicom_listener);
                        
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
            
            
            favicom_panel_izq.add(favicom_scroll_label_izq_sup,especif);
            
            especif.gridx = 0;
            especif.gridy = 1;
            especif.gridwidth = 1;
            especif.gridheight = 1;
            especif.weightx = 1.0;  
            especif.weighty = 0.15;
            especif.insets = new Insets(20,10,0,0);
            especif.fill = GridBagConstraints.BOTH;
            especif.anchor = GridBagConstraints.CENTER;
            especif.ipady = 30;
            
            favicom_panel_izq.add(favicom_panel_izq_inf,especif);
    
            
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
            
            favicom_panel_izq.add(favicom_boton,especif);
            
            
            
            // Aqui empieza la comfiguracion del subpanel inferior del lado izquierdo
            
            favicom_panel_izq_inf.setLayout(new GridBagLayout());
            
            
            favicom_panel_izq_inf_opciones.setLayout(new GridLayout(8,1));
            favicom_panel_izq_inf_opciones.add(favicom_spinner_label_reduccion);
            favicom_panel_izq_inf_opciones.add(favicom_spinner_reduccion);
            favicom_panel_izq_inf_opciones.add(favicom_spinner_label_transparencia);
            favicom_panel_izq_inf_opciones.add(favicom_spinner_transparencia);
            favicom_panel_izq_inf_opciones.add(favicom_spinner_label_posc_x);
            favicom_panel_izq_inf_opciones.add(favicom_spinner_posc_x);
            favicom_panel_izq_inf_opciones.add(favicom_spinner_label_posc_y);
            favicom_panel_izq_inf_opciones.add(favicom_spinner_posc_y);
            

            especif.gridx = 0;
            especif.gridy = 0;
            especif.gridwidth = 2;
            especif.gridheight = 1;
            especif.weightx = 1.0;  
            especif.weighty = 1.0;
            especif.fill = GridBagConstraints.BOTH;
            especif.anchor = GridBagConstraints.CENTER;
            especif.ipadx = 0;
             
            favicom_panel_izq_inf.add(favicom_scroll_label_izq_inf,especif);
            
            especif.gridx = 2;
            especif.gridy = 0;
            especif.gridwidth = 1;
            especif.gridheight = 1;
            especif.weightx = 0.2;  
            especif.weighty = 1.0;
            especif.fill = GridBagConstraints.VERTICAL;
            especif.anchor = GridBagConstraints.CENTER;
            
            
            favicom_panel_izq_inf.add(favicom_panel_izq_inf_opciones,especif);
            
            
            this.add(favicom_panel_izq);
            this.add(favicom_panel_der);
            this.revalidate();
        
        
    }

    public void poner_imagen_izq_inf(File file_imagen){
        
        try{
          favicom_img_izq_inf = ImageIO.read(file_imagen);
          favicom_label_izq_inf.setIcon(new ImageIcon(favicom_img_izq_inf));
          favicom_label_izq_inf.setText("");
        }catch(IOException ex) {
            System.out.println("Problema al cargar la imagen");
        }
        
        favicom_spinner_transparencia.setValue(0);
        favicom_spinner_reduccion.setValue(100);
        
        favicom_spinner_model_posc_x = new SpinnerNumberModel(0,0, 
                                    ventana_principal.getImage().getWidth()-1 ,1);
        favicom_spinner_posc_x.setModel(favicom_spinner_model_posc_x); 

        
        favicom_spinner_model_posc_y = new SpinnerNumberModel(0,0, 
                                    ventana_principal.getImage().getHeight()-1 ,1);
        favicom_spinner_posc_y.setModel(favicom_spinner_model_posc_y);

        
        revalidate();
        repaint();
        
    }
    
    public void cambiar_imagen_izq(BufferedImage imagen){
        
        favicom_img_izq_inf_modificada = imagen;
        favicom_label_izq_inf.setIcon(new ImageIcon(imagen));
        
    }
       
    @Override
    public void poner_imagen_der(BufferedImage imagen) {
        
        ventana_principal.setImageGuardar(imagen);  
        favicom_label_der.setIcon(new ImageIcon(imagen));
        ventana_principal.actualizar_interfaz();
   
    }
    
    @Override
    public void poner_imagen_izq(BufferedImage imagen) {
     
        favicom_label_izq_sup.setIcon(new ImageIcon(imagen));
        favicom_label_der.setIcon(null);
        favicom_spinner_model_reduccion.setValue(new Integer(100));
        favicom_spinner_model_transparencia.setValue(new Integer(0));
        
        favicom_spinner_label_posc_x.setText("X : (MAX_VALUE : " + 
                                        (imagen.getWidth() - 1) +" )");
            
        favicom_spinner_label_posc_y.setText("Y : (MAX_VALUE : " + 
                                        (imagen.getHeight() - 1) +" )");
        
        
    }

    public BufferedImage get_inf_image(){
        return favicom_img_izq_inf;
    }
    
    public void enable_options(){
        favicom_spinner_reduccion.setEnabled(true);
        favicom_spinner_transparencia.setEnabled(true);
        favicom_spinner_posc_x.setEnabled(true);
        favicom_spinner_posc_y.setEnabled(true);
        favicom_boton.setEnabled(true);
    }
    
    
}
