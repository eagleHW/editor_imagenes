/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;

import ManipulacionImagenes.BibliotecaGrafica;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author rae
 */
public class VentanaReduccion extends JFrame{
    
    private String[] proporciones = {"4:1", "8:1","16:1", "24:1", "32:1", "48:1", "64:1"};
    private int[] valores = {3,7,15,23,31,47,63};
    
    public VentanaReduccion(PanelBasico panel_basico, File file_image){
        super("Modificar tamaño");
        
        JPanel reduccion_panel = new JPanel(new GridBagLayout());
        JLabel reduccion_label = new JLabel("Introduce la proporción en la que reduciras la imagen");
        JComboBox reduccion_combox = new JComboBox(proporciones);
        JButton boton = new JButton("Aceptar");
        
        
        boton.addActionListener((ActionEvent e) -> {
           
             BibliotecaGrafica bg = new BibliotecaGrafica();
           
             // valor == tamaño matriz
             int valor = valores[reduccion_combox.getSelectedIndex()];
             
            try {
                panel_basico.poner_imagen_der(bg.filtro_reduccion(file_image,valor));
            } catch (IOException ex) {
                Logger.getLogger(VentanaRGB.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            dispose();
             
        });
        
        
        GridBagConstraints especif = new GridBagConstraints(); 
        
        // Se especifica layout de paneles
        
        especif.gridx = 0;
        especif.gridy = 0;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 1.0;
        especif.insets = new Insets(15,0,0,0);
        especif.anchor = GridBagConstraints.CENTER;
        
        reduccion_panel.add(reduccion_label,especif);
        
        especif.gridx = 0;
        especif.gridy = 1;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 1.0;
        especif.insets = new Insets(20,0,0,0);
        especif.ipadx = 40;
        
        reduccion_panel.add(reduccion_combox,especif);
       
        especif.ipadx = 0;  
        
        especif.gridx = 0;
        especif.gridy = 2;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 1.0;
        especif.insets = new Insets(20,0,15,0);
        especif.ipadx = 0;
        
        reduccion_panel.add(boton,especif);
        
        this.add(reduccion_panel);
        
        this.setVisible(true);
        this.setSize(500, 160);
        this.setLocationRelativeTo(null);
        
    }
    
}
