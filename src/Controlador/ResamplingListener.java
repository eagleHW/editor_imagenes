/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import ManipulacionImagenes.Filtros;
import Vista.Interfaz;
import Vista.PanelResampling;
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
public class ResamplingListener implements ChangeListener,ActionListener,ItemListener{

    private Interfaz ventana_principal;
    private PanelResampling panel_resampling;
    private int width;
    private int height; 
    private boolean auto_aceptar = false;
    
    private Filtros filter = new Filtros();
    
    public ResamplingListener(PanelResampling panel_resampling, Interfaz ventana_principal, int valor_inicial){
        
        this.ventana_principal = ventana_principal;
        this.panel_resampling = panel_resampling;
        width = valor_inicial;
        height = valor_inicial;
        
    }
    
    @Override
    public void stateChanged(ChangeEvent e) {
        
        JSpinner spinner = (JSpinner)e.getSource();
        SpinnerNumberModel spinner_number_model = (SpinnerNumberModel) spinner.getModel();
        
        switch(spinner.getName()){
            
            case "width":
                width = spinner_number_model.getNumber().intValue();
                break;
            case "height":
                height = spinner_number_model.getNumber().intValue();
                break;
            
        }
        
        if(auto_aceptar){ 
           panel_resampling.poner_imagen_der(filter.filtro_resampling(ventana_principal.getImage(), width, height));
        }
    
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
          panel_resampling.poner_imagen_der(filter.filtro_resampling(ventana_principal.getImage(), width, height));
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
         auto_aceptar = ((JCheckBox)e.getSource()).isSelected();
    }
    
}
