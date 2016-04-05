
package Vista;

import Controlador.BlendingListener;
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
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author rae
 */
public class PanelBlending extends PanelEditorImagen {
    
    private Interfaz ventana_principal;
    private Filtros filter = new Filtros();
    
    private JPanel blending_panel_izq;
    private JPanel blending_panel_der;
    private JPanel blending_panel_izq_inf;
    
    private JLabel blending_label_izq_sup = new JLabel();
    private JLabel blending_label_izq_inf = new JLabel();
    private JLabel blending_label_der = new JLabel();
   
    private JScrollPane blending_scroll_label_izq_sup = new JScrollPane(blending_label_izq_sup);
    private JScrollPane blending_scroll_label_izq_inf = new JScrollPane(blending_label_izq_inf);
    private JScrollPane blending_scroll_label_der = new JScrollPane(blending_label_der);
    
    private final int VALOR_INICIAL = 0;
    
    private JLabel blending_label_spinner = new JLabel("Blending (%) : ");
    private SpinnerModel blending_spinner_model = new SpinnerNumberModel(VALOR_INICIAL,0,100,1);
    private JSpinner blending_spinner = new JSpinner(blending_spinner_model);
    
    private JButton blending_boton = new JButton("Aceptar");
    
    private JCheckBox blending_checkbox = new JCheckBox(" Autoaceptar",false);
    
    // Imagenes Blending
    private BufferedImage blending_img_izq_sup, blending_img_izq_inf;
    
    private BlendingListener blending_listener;
    
    public PanelBlending(Interfaz ventana_principal){
                   
            this.ventana_principal = ventana_principal;    
            this.blending_listener = new BlendingListener(this,ventana_principal,VALOR_INICIAL);
            
            this.setLayout(new GridLayout(1,2,5,10));

            blending_panel_izq = new JPanel(new GridBagLayout()); 
            blending_panel_der = new JPanel(new BorderLayout());
            blending_panel_izq_inf = new JPanel(new GridBagLayout());
            
            blending_panel_der.add(blending_scroll_label_der,BorderLayout.CENTER);
            
            blending_label_izq_sup.setHorizontalAlignment(JLabel.CENTER);
            blending_label_izq_inf.setHorizontalAlignment(JLabel.CENTER);
            blending_label_der.setHorizontalAlignment(JLabel.CENTER);
            
            blending_label_izq_inf.setIcon(null);
            blending_label_izq_inf.setText("Click para agregar imagen");
            
            blending_img_izq_inf = null; // Eliminar cualquier imagen anterior que halla quedado 

            // Desahbilitar el slider hasta que se carge una imagen
            blending_boton.setEnabled(false); 
            blending_checkbox.setEnabled(false);
            
            blending_img_izq_sup = ventana_principal.getImage();
            blending_label_izq_sup.setIcon(new ImageIcon(ventana_principal.getImage()));
           
            blending_label_izq_inf.addMouseListener(blending_listener);
            blending_spinner.addChangeListener(blending_listener);
            blending_boton.addActionListener(blending_listener);
            blending_checkbox.addItemListener(blending_listener);
            
            GridBagConstraints especif = new GridBagConstraints(); 

            especif.gridx = 1;
            especif.gridy = 1;
            especif.gridwidth = 1;
            especif.gridheight = 1;
            especif.weightx = 1.0;  
            especif.weighty = 1.0;
            especif.insets = new Insets(20,10,0,0);
            especif.fill = GridBagConstraints.BOTH;
            especif.anchor = GridBagConstraints.CENTER;
        
            blending_panel_izq.add(blending_scroll_label_izq_sup,especif);
           
            especif.gridx = 1;
            especif.gridy = 2;
            especif.gridwidth = 1;
            especif.gridheight = 1;
            especif.weightx = 1.0;  
            especif.weighty = 1.0;
            especif.insets = new Insets(20,10,0,0);
            especif.fill = GridBagConstraints.BOTH;
            especif.anchor = GridBagConstraints.CENTER;
        
            
            blending_panel_izq.add(blending_scroll_label_izq_inf,especif);
        
            especif.gridx = 0;
            especif.gridy = 3;
            especif.gridwidth = 3;
            especif.gridheight = 1;
            especif.weightx = 1.0;  
            especif.weighty = 0.1;
            especif.insets = new Insets(20,10,0,0);
            especif.fill = GridBagConstraints.HORIZONTAL;
            especif.anchor = GridBagConstraints.NORTH;
            
            blending_panel_izq.add(blending_panel_izq_inf,especif);
        
            // Empieza la configuracion del panel izquierdo inferior

            especif.gridx = 0;
            especif.gridy = 0;
            especif.gridwidth = 3;
            especif.gridheight = 1;
            especif.weightx = 1.0;  
            especif.weighty = 0.05;
            especif.insets = new Insets(0,20,3,0);
            especif.fill = GridBagConstraints.BOTH;
            especif.anchor = GridBagConstraints.SOUTH;

            blending_panel_izq_inf.add(blending_label_spinner,especif);

            especif.gridx = 0;
            especif.gridy = 1;
            especif.gridwidth = 1;
            especif.gridheight = 1;
            especif.weightx = 2.0;  
            especif.weighty = 1.0;
            especif.insets = new Insets(0,20,0,0);
            especif.fill = GridBagConstraints.HORIZONTAL;
            especif.anchor = GridBagConstraints.NORTH;
            especif.ipady = 10;

            blending_panel_izq_inf.add(blending_spinner,especif);

            especif.gridx = 1;
            especif.gridy = 1;
            especif.gridwidth = 1;
            especif.gridheight = 1;
            especif.weightx = 1.0;  
            especif.weighty = 1.0;
            especif.insets = new Insets(0,30,0,0);
            especif.fill = GridBagConstraints.HORIZONTAL;
            especif.anchor = GridBagConstraints.NORTH;
            especif.ipady = 5;

            blending_panel_izq_inf.add(blending_boton,especif);

            especif.gridx = 2;
            especif.gridy = 1;
            especif.gridwidth = 1;
            especif.gridheight = 1;
            especif.weightx = 0.25;  
            especif.weighty = 1.0;
            especif.insets = new Insets(0,10,10,0);
            especif.fill = GridBagConstraints.HORIZONTAL;
            especif.anchor = GridBagConstraints.NORTH;

            blending_panel_izq_inf.add(blending_checkbox,especif);

            especif.ipady = 0;

            this.add(blending_panel_izq);
            this.add(blending_panel_der);
            this.revalidate();
        
    }
    
    public void enable_aceptar(){
         blending_boton.setEnabled(true); 
         blending_checkbox.setEnabled(true);
         blending_label_izq_inf.setToolTipText("Click para reemplazar la imagen");
    }
      
    public void poner_imagen_der(BufferedImage imagen){
        ventana_principal.setImageGuardar(imagen);  
        blending_label_der.setIcon(new ImageIcon(imagen));
        blending_spinner_model.setValue(new Integer(VALOR_INICIAL));
    }

    public void poner_imagen_izq(BufferedImage imagen) {    
      
      blending_label_izq_sup.setIcon(new ImageIcon(imagen));
      blending_label_der.setIcon(null);
      
    }

    public void poner_imagen_izq_inf_blending(File file_imagen){
        
        try{
          blending_img_izq_inf = ImageIO.read(file_imagen);
          blending_label_izq_inf.setIcon(new ImageIcon(blending_img_izq_inf));
          blending_label_izq_inf.setText("");
          blending_label_der.setIcon(null);
          blending_spinner_model.setValue(new Integer(VALOR_INICIAL));

        }catch(IOException ex) {
            
            JOptionPane.showMessageDialog(null, "Error al cargar la imagen", 
                                                    "Error", JOptionPane.ERROR_MESSAGE);
            
            System.out.println("Problema al cargar la imagen - PanelBlending.java");
        }
        
        revalidate();
        repaint();
        
    }
    
    public BufferedImage getBlending_img_izq_inf() {
        return blending_img_izq_inf;
    }
        
    
    
}
