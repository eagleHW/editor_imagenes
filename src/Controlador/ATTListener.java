/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import ManipulacionImagenes.Filtros;
import Vista.Interfaz;
import Vista.PanelATT;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author rae
 */
public class ATTListener implements ChangeListener, ActionListener {

    private Interfaz ventana_principal;
    private PanelATT panel_att;
    private Filtros filter = new Filtros();
    
    private int num_filas = 1;
    
    public ATTListener(PanelATT panel_att, Interfaz ventana_principal){
        
        this.panel_att = panel_att;
        this.ventana_principal = ventana_principal;
        
    }
    
    @Override
    public void stateChanged(ChangeEvent e) {
        
        JSpinner spinner = (JSpinner)e.getSource();
        SpinnerNumberModel spinner_number_model = (SpinnerNumberModel) spinner.getModel();
        
        switch (spinner.getName()) {
        
            case "Num_filas" :
                    num_filas = spinner_number_model.getNumber().intValue();         
                break;
            default:
                  System.out.println("No cayo en ninguna opcion");
       
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    
        panel_att.poner_imagen_der(filter.filtro_att(ventana_principal.getImage(), num_filas));
        
    }
    
    
    
    
}
