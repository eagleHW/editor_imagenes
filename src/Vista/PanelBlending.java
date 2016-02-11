
package Vista;

import Controlador.BlendingMouseListener;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author rae
 */
public class PanelBlending extends PanelEditorImagen {
    
    private Interfaz ventana_principal;
    private Filtros filter = new Filtros();
    
    private JPanel blending_panel_izq;
    private JPanel blending_panel_der;
   
    private JLabel blending_label_izq_sup = new JLabel();
    private JLabel blending_label_izq_inf = new JLabel();
    private JLabel blending_label_der = new JLabel();
   
    private JScrollPane blending_scroll_label_izq_sup = new JScrollPane(blending_label_izq_sup);
    private JScrollPane blending_scroll_label_izq_inf = new JScrollPane(blending_label_izq_inf);
    private JScrollPane blending_scroll_label_der = new JScrollPane(blending_label_der);
    
    JSlider blending_slider = new JSlider(SwingConstants.HORIZONTAL,0,100,1);
    
    // Imagenes Blending
    BufferedImage blending_img_izq_sup, blending_img_izq_inf;
        
    
    public PanelBlending(Interfaz ventana_principal){
                   
            this.ventana_principal = ventana_principal;    
                        
            this.setLayout(new GridLayout(1,2,5,10));

            blending_panel_izq = new JPanel(new GridBagLayout()); 
            blending_panel_der = new JPanel(new BorderLayout());
            
            blending_panel_der.add(blending_scroll_label_der,BorderLayout.CENTER);
            
            blending_label_izq_sup.setHorizontalAlignment(JLabel.CENTER);
            blending_label_izq_inf.setHorizontalAlignment(JLabel.CENTER);
            blending_label_der.setHorizontalAlignment(JLabel.CENTER);
            
            blending_label_izq_inf.addMouseListener(new BlendingMouseListener(this));
            
            blending_label_izq_inf.setIcon(null);
            blending_label_izq_inf.setText("Click para agregar imagen");
            
            blending_img_izq_inf = null; // Eliminar cualquier imagen anterior que halla qeudado 
            blending_slider.setEnabled(false); // Desahbilitar el slider hasta que se carge una imagen
            
            try{
             
                blending_img_izq_sup = ImageIO.read(ventana_principal.getFile());
                //blending_img_inf = ImageIO.read(file_imagen);
                
                blending_label_izq_sup.setIcon(new ImageIcon(blending_img_izq_sup));
                //blending_label_inf.setIcon(new ImageIcon(blending_img_inf));
             
                blending_slider.addChangeListener((ChangeEvent e) -> {
                poner_imagen_der(filter.filtro_blending(blending_img_izq_sup, blending_img_izq_inf, blending_slider.getValue()));  
                });
                
            }catch (IOException ex) {
                System.out.println("Error al cargar imagen");
            }
        
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
            
            blending_panel_izq.add(blending_slider,especif);
        
            blending_slider.setPaintLabels(true);
            blending_slider.setPaintTicks(true);
            blending_slider.setMajorTickSpacing(10);
            
            this.add(blending_panel_izq);
            this.add(blending_panel_der);
            this.revalidate();
        
    }
    
    public void enable_blending_slider(){
        blending_slider.setEnabled(true);
    }
    
    public void poner_imagen_izq_inf_blending(File file_imagen){
        
        try{
          blending_img_izq_inf = ImageIO.read(file_imagen);
          blending_label_izq_inf.setIcon(new ImageIcon(blending_img_izq_inf));
          blending_label_izq_inf.setText("");
        }catch(IOException ex) {
            System.out.println("Problema al cargar la imagen");
        }
        
        blending_slider.setValue(0);
        
        revalidate();
        repaint();
        
    }
    
    public void poner_imagen_der(BufferedImage imagen){
        ventana_principal.setImageGuardar(imagen);  
        blending_label_der.setIcon(new ImageIcon(imagen));
    }

    public void poner_imagen_izq(BufferedImage imagen) {    
      
      blending_slider.setValue(0);
      blending_label_izq_sup.setIcon(new ImageIcon(imagen));
      blending_label_der.setIcon(null);
      
    }
        
        
}
