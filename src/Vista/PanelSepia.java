
package Vista;

import ManipulacionImagenes.BibliotecaGrafica;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
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
public class PanelSepia extends PanelEditorImagen{
    
    private Interfaz ventana_principal;
    private BibliotecaGrafica bg = new BibliotecaGrafica();
    
    private JPanel sepia_panel_izq = new JPanel();
    private JPanel sepia_panel_der = new JPanel();
    
    private JLabel sepia_label_izq = new JLabel();
    private JLabel sepia_label_der = new JLabel();
    
    private JScrollPane sepia_scroll_label_izq = new JScrollPane(sepia_label_izq);
    private JScrollPane sepia_scroll_label_der = new JScrollPane(sepia_label_der);
    
    private JSlider sepia_slider = new JSlider(SwingConstants.HORIZONTAL,0,255,1);
    
    private BufferedImage sepia_img_izq;
    
    public PanelSepia(Interfaz ventana_principal){
   
        this.ventana_principal = ventana_principal;
        
            this.setLayout(new GridLayout(1,2,5,10));
                        
            sepia_panel_izq = new JPanel(new GridBagLayout()); 
            sepia_panel_der = new JPanel(new BorderLayout());
            
            sepia_panel_der.add(sepia_scroll_label_der,BorderLayout.CENTER);
            
            sepia_label_izq.setHorizontalAlignment(JLabel.CENTER);
            sepia_label_der.setHorizontalAlignment(JLabel.CENTER);
            
            try {
               
                sepia_img_izq = ImageIO.read(ventana_principal.getFile());
               
                sepia_label_izq.setIcon(new ImageIcon(sepia_img_izq));
                
            } catch (IOException ex) {
                System.out.println("Error al cargar imagen");
            }
            
             sepia_slider.addChangeListener((ChangeEvent e) -> {       
                    poner_imagen_der(bg.filtro_sepia(ventana_principal.getImage(),sepia_slider.getValue()));   
             });
             
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
        
            sepia_panel_izq.add(sepia_scroll_label_izq,especif);
            
            especif.gridx = 0;
            especif.gridy = 1;
            especif.gridwidth = 1;
            especif.gridheight = 1;
            especif.weightx = 1.0;  
            especif.weighty = 0.2;
            especif.insets = new Insets(20,10,0,0);
            especif.fill = GridBagConstraints.BOTH;
            especif.anchor = GridBagConstraints.CENTER;
            
            sepia_panel_izq.add(sepia_slider,especif);
        
            sepia_slider.setPaintLabels(true);
            sepia_slider.setPaintTicks(true);
            sepia_slider.setMajorTickSpacing(15);
            
            this.add(sepia_panel_izq);
            this.add(sepia_panel_der);
            this.revalidate();

        
    }
    
    @Override
    public void poner_imagen_der(BufferedImage imagen){
    
        ventana_principal.setImageGuardar(imagen);  
        sepia_label_der.setIcon(new ImageIcon(imagen));
        ventana_principal.actualizar_interfaz();
    
    }

    @Override
    public void poner_imagen_izq(BufferedImage imagen) {
       
       sepia_label_izq.setIcon(new ImageIcon(imagen));
       sepia_label_der.setIcon(null);
        
    }
   
    
}
