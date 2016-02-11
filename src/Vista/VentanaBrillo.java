
package Vista;

import ManipulacionImagenes.Filtros;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author rae
 */
public class VentanaBrillo extends JFrame{
    
    public VentanaBrillo(PanelBasico panel_basico, File file_image){
        
        super("Modificar brillo");
        
        // Brillo Slider
        
        JPanel brillo_panel = new JPanel(new GridBagLayout());
        JSlider brillo_slider = new JSlider(SwingConstants.HORIZONTAL,-255,255,1);
        JLabel brillo_label = new JLabel("Brillo :   0");
        
        brillo_slider.setPaintLabels(true);
        brillo_slider.setPaintTicks(true);
        brillo_slider.setMajorTickSpacing(85);
        
        brillo_slider.addChangeListener((ChangeEvent e) -> {
            brillo_label.setText("Brillo :   " + brillo_slider.getValue());  
        });
        
        // Boton
        
        JPanel boton_panel = new JPanel(new GridBagLayout());
        JButton boton = new JButton("Aceptar");
        
          // Inicio controlador boton
        
         boton.addActionListener((ActionEvent e) -> {
           
             Filtros filter = new Filtros();
             
             int brillo = brillo_slider.getValue();
                
            try {
                panel_basico.poner_imagen_der(filter.filtro_brillo(file_image,brillo));
            } catch (IOException ex) {
                Logger.getLogger(VentanaRGB.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            dispose();
             
        });
        
          // Fin controlador boton
        
        GridBagConstraints especif = new GridBagConstraints(); 
        
        // Se especifica layout de paneles
        
        especif.gridx = 0;
        especif.gridy = 0;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 1.0;
        especif.insets = new Insets(20,10,0,0);
        especif.fill = GridBagConstraints.HORIZONTAL;
        
        brillo_panel.add(brillo_slider,especif);
        
        especif.fill = GridBagConstraints.NONE;
        especif.anchor = GridBagConstraints.CENTER;
        especif.weightx = .25; 
        especif.gridx = 1;

        brillo_panel.add(brillo_label,especif);
        
        // Se especifica layout del boton
        
        especif = new GridBagConstraints(); 
        
        especif.gridx = 0;
        especif.gridy = 0;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 1.0;
        especif.anchor = GridBagConstraints.CENTER;
        especif.insets = new Insets(0,0,10,0);
        
        boton_panel.add(boton,especif);
        
        // Se especifica el layout de la ventana principal
        
        this.setLayout(new GridBagLayout()); 
        especif = new GridBagConstraints(); 
        
        especif.gridx = 0;
        especif.gridy = 0;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 1.0;
        especif.weighty = 1.0;
        especif.fill = GridBagConstraints.HORIZONTAL;
      
        this.add(brillo_panel,especif);
        
        especif.gridy = 1;
        
        this.add(boton_panel,especif);
       
        this.setVisible(true);
        this.setSize(500, 160);
        this.setLocationRelativeTo(null);
        //this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
    }
    
    
}
