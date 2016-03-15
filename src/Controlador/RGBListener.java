/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import ManipulacionImagenes.Filtros;
import Vista.Interfaz;
import Vista.PanelEditorImagen;
import Vista.PanelRGB;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author rae
 */
public class RGBListener implements ChangeListener, ActionListener, ItemListener{

    private Interfaz ventana_principal;
    private PanelRGB panel_rgb;
    private boolean auto_aceptar = false;
    private Filtros filter = new Filtros();
    
    private int red;
    private int green;
    private int blue;
    
    public RGBListener(PanelRGB panel_rgb, Interfaz ventana_principal, int valor_inicial){
        
        this.panel_rgb = panel_rgb;
        this.ventana_principal = ventana_principal;
        this.red = valor_inicial;
        this.green = valor_inicial;
        this.blue = valor_inicial;
        
    }
    
    
    @Override
    public void stateChanged(ChangeEvent e) {
    
        JSpinner spinner = (JSpinner)e.getSource();
        SpinnerNumberModel spinner_number_model = (SpinnerNumberModel) spinner.getModel();
    
        switch(spinner.getName()){
            
            case "red":
                red = spinner_number_model.getNumber().intValue();
                System.out.println("RED : " + red);
                break;
            case "green":
                green = spinner_number_model.getNumber().intValue();
                break;
            case "blue":
                blue = spinner_number_model.getNumber().intValue();             
                break;
           
        }
        
        if(auto_aceptar){ 
           panel_rgb.poner_imagen_der(filter.filtro_rgb(ventana_principal.getImage(), red, green, blue));
        }
    
    }
   
    @Override
    public void actionPerformed(ActionEvent e) {
    
        panel_rgb.poner_imagen_der(filter.filtro_rgb(ventana_principal.getImage(), red, green, blue));
        
    
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
      
        auto_aceptar = ((JCheckBox)e.getSource()).isSelected();
        
    }
    
}
