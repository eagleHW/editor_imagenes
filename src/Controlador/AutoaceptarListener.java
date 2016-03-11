/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import ManipulacionImagenes.Filtros;
import Vista.Interfaz;
import Vista.PanelEditorImagen;
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
 * Clase Abstracta que nos dice los listener necesarios para tener funcionalidad en la 
 * vista PanelAutoaceptar e implementa la funcionalidad de los listener
 * 
 * @author rae
 */
public abstract class AutoaceptarListener implements ChangeListener, ActionListener, ItemListener{
   
    protected Interfaz ventana_principal;
    protected PanelEditorImagen panel_editor;
    protected int valor;
    protected boolean auto_aceptar = false;

    @Override
    public void stateChanged(ChangeEvent e) {
        
        JSpinner spinner = (JSpinner)e.getSource();
        SpinnerNumberModel spinner_number_model = (SpinnerNumberModel) spinner.getModel();
        
        if(auto_aceptar){
            valor = spinner_number_model.getNumber().intValue();   
            aplicar_filtro();
        }else{
            valor = spinner_number_model.getNumber().intValue();         
        }
                
    }
    
      @Override
    public void actionPerformed(ActionEvent e) {
    
        aplicar_filtro();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
   
        auto_aceptar = ((JCheckBox)e.getSource()).isSelected();
        
    }
    
    public abstract void aplicar_filtro();
    
}
