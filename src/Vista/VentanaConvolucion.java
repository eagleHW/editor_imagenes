/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;

import ManipulacionImagenes.Filtros;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author rae
 */
public class VentanaConvolucion extends JFrame {

    public VentanaConvolucion(PanelBasico panel_basico, BufferedImage imagen  ,int tam){
        
        super("Valores convolución");
        
        JPanel convolucion_panel = new JPanel(new GridBagLayout());
        JLabel convolucion_title_label = new JLabel("Elige los valores de la matriz de convolución");
        JTextField[][] convolucion_datos =  new JTextField[tam][tam];
        JLabel convolucion_factor_label = new JLabel("Factor : ",SwingConstants.CENTER);
        JTextField convolucion_factor_text = new JTextField("1");
        JButton boton = new JButton("Aceptar");
        
        boton.addActionListener((ActionEvent e) -> {
           
             Filtros filter = new Filtros();
             
             try {
             
             double[][] matriz_convolucion = new double[tam][tam];
             int factor = Integer.parseInt(convolucion_factor_text.getText());
             
             if(factor != 0 ){
             
                for(int i = 0; i < tam; i++){
                    for(int j = 0; j < tam; j++){

                        matriz_convolucion[i][j] = Double.parseDouble(convolucion_datos[i][j].getText()) ;        
                    }
                }

                panel_basico.poner_imagen_der(filter.filtro_convolucion(imagen,matriz_convolucion,factor)); 
                
                dispose();
             
             }else{
                 JOptionPane.showMessageDialog(null,"El factor no puede ser 0",
                                "Error en entrada",JOptionPane.ERROR_MESSAGE);
             }     
                 
            } catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"Algún campo contiene una entrada no valida",
                                "Error en entrada",JOptionPane.ERROR_MESSAGE);
            }
            
           
             
        });
        
        
        GridBagConstraints especif = new GridBagConstraints(); 
        
        especif.gridx = 0;
        especif.gridy = 0;
        especif.gridwidth = tam;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 1.0;
        especif.anchor = GridBagConstraints.CENTER;
        especif.insets = new Insets(10,5,10,5);
        
        convolucion_panel.add(convolucion_title_label,especif);
        
        for(int i = 1 ; i < tam+1 ; i++){
            for( int j = 0 ; j < tam; j++){
                
                especif.gridx = j;
                especif.gridy = i;
                especif.gridwidth = 1;
                especif.gridheight = 1;
                especif.weightx = 1.0;  
                especif.weighty = 1.0;
                especif.anchor = GridBagConstraints.CENTER;
                especif.fill = GridBagConstraints.HORIZONTAL;
                especif.insets = new Insets(5,5,5,5);
        
                convolucion_datos[i-1][j] = new JTextField("0");
                convolucion_panel.add(convolucion_datos[i-1][j], especif); 
         
            }
        }
        
        
        especif.gridx = 0;
        especif.gridy = tam+1;
        especif.gridwidth = tam;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 1.0;
        especif.anchor = GridBagConstraints.LINE_START;
        especif.fill = GridBagConstraints.NONE;
        especif.insets = new Insets(10,5,10,5);
        
        convolucion_panel.add(convolucion_factor_label, especif);
        
        especif.gridx = 1;
        especif.gridy = tam+1;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 1.0;
        especif.anchor = GridBagConstraints.CENTER;
        especif.fill = GridBagConstraints.HORIZONTAL;
        especif.insets = new Insets(10,5,10,5);
        
        convolucion_panel.add(convolucion_factor_text, especif);
        
        especif.gridx = 0;
        especif.gridy = tam+2;
        especif.gridwidth = tam;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 1.0;
        especif.anchor = GridBagConstraints.CENTER;
        especif.insets = new Insets(10,5,10,5);
        
        convolucion_panel.add(boton,especif);
        
        this.add(convolucion_panel);
        
        
        switch(tam){
            
            case 3:        
                this.setSize(330,220);
                break;
            case 5:
                this.setSize(425, 275);
                break;
            case 7:
                this.setSize(500, 340);
                break;
            case 9:
                this.setSize(520, 405);
                break;
                
        }
        
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        
    }
    
}
