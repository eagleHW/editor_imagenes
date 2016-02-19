/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import ManipulacionImagenes.Filtros;
import Vista.Interfaz;
import Vista.PanelLetra;
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
public class LetraListener implements ChangeListener, ActionListener{
    
    private Interfaz ventana_principal;
    private PanelLetra panel_letra;
    private Filtros filter = new Filtros();
            
    private int reduccion = 50;
    private int ventana = 3;
    
    public LetraListener(PanelLetra panel_letra, Interfaz ventana_principal){
    
        this.panel_letra = panel_letra;
        this.ventana_principal = ventana_principal;
        
    }

      @Override
    public void stateChanged(ChangeEvent e) {
        
        JSpinner spinner = (JSpinner)e.getSource();
        SpinnerNumberModel spinner_number_model = (SpinnerNumberModel) spinner.getModel();
        
        switch (spinner.getName()) {
            case "Reduccion":
                reduccion =  spinner_number_model.getNumber().intValue();
                panel_letra.cambiar_imagen_izq(filter.filtro_reduccion_porcentaje(panel_letra.get_image_der_inf(), reduccion));
                break; 
            case "Ventana" :
                ventana  = spinner_number_model.getNumber().intValue();
                break;
            default:
                System.out.println("No cayo en ninguna opcion");
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        panel_letra.poner_imagen_der(filter.filtro_letra(ventana_principal.getImage(), reduccion, ventana,
                        panel_letra.get_coleccion_imagen()));
        
    
    }
    
    
    
    
    
}
