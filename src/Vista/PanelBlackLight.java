/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;

import ManipulacionImagenes.BibliotecaGrafica;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
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
public class PanelBlackLight extends PanelEditorImagen{

    private Interfaz ventana_principal;
    private BibliotecaGrafica bg = new BibliotecaGrafica();
    
    private JPanel blacklight_panel_izq = new JPanel();
    private JPanel blacklight_panel_der = new JPanel();
    
    private JLabel blacklight_label_izq = new JLabel();
    private JLabel blacklight_label_der = new JLabel();
    
    private JScrollPane blacklight_scroll_label_izq = new JScrollPane(blacklight_label_izq);
    private JScrollPane blacklight_scroll_label_der = new JScrollPane(blacklight_label_der);
    
    private JSlider blacklight_slider = new JSlider(SwingConstants.HORIZONTAL,1,7,1);
    
    private BufferedImage blacklight_img_izq;
    
    public PanelBlackLight(Interfaz ventana_principal){
        
        this.ventana_principal = ventana_principal;
        
        this.setLayout(new GridLayout(1,2,5,10));
        
        blacklight_panel_izq = new JPanel(new GridBagLayout()); 
        blacklight_panel_der = new JPanel(new BorderLayout());
            
        blacklight_panel_der.add(blacklight_scroll_label_der,BorderLayout.CENTER);
            
        blacklight_label_izq.setHorizontalAlignment(JLabel.CENTER);
        blacklight_label_der.setHorizontalAlignment(JLabel.CENTER);
        
        blacklight_label_izq.setIcon(new ImageIcon(ventana_principal.getImage()));
        
        blacklight_slider.addChangeListener((ChangeEvent e) -> {       
                    poner_imagen_der(bg.filtro_blacklight(ventana_principal.getImage(),blacklight_slider.getValue()));   
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
        
            blacklight_panel_izq.add(blacklight_scroll_label_izq,especif);
            
            especif.gridx = 0;
            especif.gridy = 1;
            especif.gridwidth = 1;
            especif.gridheight = 1;
            especif.weightx = 1.0;  
            especif.weighty = 0.2;
            especif.insets = new Insets(20,10,0,0);
            especif.fill = GridBagConstraints.BOTH;
            especif.anchor = GridBagConstraints.CENTER;
            
            blacklight_panel_izq.add(blacklight_slider,especif);
        
            blacklight_slider.setPaintLabels(true);
            blacklight_slider.setPaintTicks(true);
            blacklight_slider.setMajorTickSpacing(1);
            
            this.add(blacklight_panel_izq);
            this.add(blacklight_panel_der);
            this.revalidate();
        
    }
    
    
    @Override
    public void poner_imagen_der(BufferedImage imagen) {
   
        ventana_principal.setImageGuardar(imagen);  
        blacklight_label_der.setIcon(new ImageIcon(imagen));
        ventana_principal.actualizar_interfaz();
    
    }

    @Override
    public void poner_imagen_izq(BufferedImage imagen) {
       
       blacklight_slider.setValue(1);
       blacklight_label_izq.setIcon(new ImageIcon(imagen));
       blacklight_label_der.setIcon(null);
    
    }
    
}
