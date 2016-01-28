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
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
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
import javax.swing.event.ChangeListener;

/**
 *
 * @author rae
 */
public class VentanaRGB extends JFrame {
   
    Interfaz ventana_principal;
    boolean warhol = false;
    int num;
    BufferedImage imagen_warhol;
    
    public VentanaRGB(Interfaz ventana_principal){
        
        super("Modificar RGB");
        this.ventana_principal = ventana_principal;
        cargar_elementos();
        
    }
    
    public VentanaRGB(Interfaz ventana_principal, int num, BufferedImage imagen_warhol){
        
        super("Modificar RGB");
        this.ventana_principal = ventana_principal;
        this.num = num;
        this.imagen_warhol = imagen_warhol;
        warhol = true;
        
        cargar_elementos();
        
    }
    
    private void cargar_elementos(){

        // Red Slider
        
        JPanel red_panel = new JPanel(new GridBagLayout()); 
        JSlider red_slider = new JSlider(SwingConstants.HORIZONTAL,0,255,1);        
        JLabel red_label = new JLabel("R :     0");
        
        red_slider.setPaintLabels(true);
        red_slider.setPaintTicks(true);
        red_slider.setMajorTickSpacing(85);
         
        red_slider.addChangeListener((ChangeEvent e) -> {
            red_label.setText("R :    " + red_slider.getValue());  
        });
  
        
        //Green Slider
        
        JPanel green_panel = new JPanel(new GridBagLayout());
        JSlider green_slider = new JSlider(SwingConstants.HORIZONTAL,0,255,1);
        JLabel green_label = new JLabel("G :    0");
        
        green_slider.setPaintLabels(true);
        green_slider.setPaintTicks(true);
        green_slider.setMajorTickSpacing(85);
          
        green_slider.addChangeListener((ChangeEvent e) -> {
            green_label.setText("G :    " + green_slider.getValue());  
        });
  
        
        //Blue Slider
        
        JPanel blue_panel = new JPanel(new GridBagLayout());
        JSlider blue_slider = new JSlider(SwingConstants.HORIZONTAL,0,255,1);
        JLabel blue_label = new JLabel("B :    0");
        
        blue_slider.setPaintLabels(true);
        blue_slider.setPaintTicks(true);
        blue_slider.setMajorTickSpacing(85);
        
        blue_slider.addChangeListener((ChangeEvent e) -> {
            blue_label.setText("B :    " + blue_slider.getValue());  
        });
  
        
        // Boton
      
        JPanel boton_panel = new JPanel(new GridBagLayout());
        JButton boton = new JButton("Aceptar");
        
        // Inicio controlador boton
        
         boton.addActionListener((ActionEvent e) -> {
           
             BibliotecaGrafica bg = new BibliotecaGrafica();
             
             int red = red_slider.getValue();
             int green = green_slider.getValue();
             int blue = blue_slider.getValue();
                
            try {
                if(!warhol){
                    ventana_principal.poner_imagen_der(bg.filtro_rgb(ventana_principal.getFile(),red,green,blue));
                }else{
                    ventana_principal.poner_imagen_warhol(num,bg.filtro_rgb(imagen_warhol,red,green,blue) );
                }
                          
            } catch (IOException ex) {
                System.out.println("Error al cargar imagen");
            }
            
            dispose();
             
        });
        
         // Fin controlador boton
        
        GridBagConstraints especif = new GridBagConstraints(); 

        //Se especifica el layout de los paneles
       
        especif.gridx = 0;
        especif.gridy = 0;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 1.0;
        especif.insets = new Insets(20,10,0,0);
        especif.fill = GridBagConstraints.HORIZONTAL;
        
        red_panel.add(red_slider,especif);
        
        especif.insets = new Insets(0,10,0,0);
        
        green_panel.add(green_slider,especif);
        blue_panel.add(blue_slider,especif);
        
        especif.fill = GridBagConstraints.NONE;
        especif.anchor = GridBagConstraints.CENTER;
        especif.weightx = .25; 
        especif.gridx = 1;

        red_panel.add(red_label,especif);
        green_panel.add(green_label,especif);
        blue_panel.add(blue_label,especif);
        
        // Se especifica el layout del boton
        
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
      
        this.add(red_panel,especif);
        
        especif.gridy = 1;
        
        this.add(green_panel,especif);
       
        especif.gridy = 2; 
        
        this.add(blue_panel,especif);
            
        especif.gridy = 3;
        
        this.add(boton_panel,especif);
        
        this.setVisible(true);
        this.setSize(500, 300);
        this.setLocationRelativeTo(null);
        //this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
    
}
