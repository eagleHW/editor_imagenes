/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;

import ManipulacionImagenes.Filtros;
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
public class PanelReduccionPorcentaje extends PanelEditorImagen{
    
    private Interfaz ventana_principal;
    private Filtros filter = new Filtros();
    
    private JPanel reduccion_panel_izq;
    private JPanel reduccion_panel_der;
   
    private JLabel reduccion_label_izq = new JLabel();
    private JLabel reduccion_label_der = new JLabel();
   
    private JScrollPane reduccion_scroll_label_izq = new JScrollPane(reduccion_label_izq);
    private JScrollPane reduccion_scroll_label_der = new JScrollPane(reduccion_label_der);
    
    JSlider reduccion_slider = new JSlider(SwingConstants.HORIZONTAL,0,100,1);
    
    public PanelReduccionPorcentaje(Interfaz ventana_principal){
    
        this.ventana_principal = ventana_principal;
        
        this.setLayout(new GridLayout(1,2,5,10));
        
        reduccion_panel_izq = new JPanel(new GridBagLayout()); 
        reduccion_panel_der = new JPanel(new BorderLayout());
            
        reduccion_panel_der.add(reduccion_scroll_label_der,BorderLayout.CENTER);
    
        reduccion_label_izq.setHorizontalAlignment(JLabel.CENTER);
        reduccion_label_der.setHorizontalAlignment(JLabel.CENTER);
        
        reduccion_label_izq.setIcon(new ImageIcon(ventana_principal.getImage()));
         
        reduccion_slider.addChangeListener((ChangeEvent e) -> {       
                            
                   poner_imagen_der(filter.filtro_reduccion_porcentaje(ventana_principal.getImage(),reduccion_slider.getValue()));   
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
        
            reduccion_panel_izq.add(reduccion_scroll_label_izq,especif);
            
            especif.gridx = 0;
            especif.gridy = 1;
            especif.gridwidth = 1;
            especif.gridheight = 1;
            especif.weightx = 1.0;  
            especif.weighty = 0.2;
            especif.insets = new Insets(20,10,0,0);
            especif.fill = GridBagConstraints.BOTH;
            especif.anchor = GridBagConstraints.CENTER;
            
            reduccion_panel_izq.add(reduccion_slider,especif);
        
            reduccion_slider.setPaintLabels(true);
            reduccion_slider.setPaintTicks(true);
            reduccion_slider.setMajorTickSpacing(10);
            
            this.add(reduccion_panel_izq);
            this.add(reduccion_panel_der);
            this.revalidate();
        
        
    }  

    @Override
    public void poner_imagen_der(BufferedImage imagen) {
        ventana_principal.setImageGuardar(imagen);  
        reduccion_label_der.setIcon(new ImageIcon(imagen));
        ventana_principal.actualizar_interfaz();

    }

    @Override
    public void poner_imagen_izq(BufferedImage imagen) {
       reduccion_slider.setValue(0);
       reduccion_label_izq.setIcon(new ImageIcon(imagen));
       reduccion_label_der.setIcon(null); 
    }
    

    
    
    
    
}
