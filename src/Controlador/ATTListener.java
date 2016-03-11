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
import java.awt.event.ItemEvent;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author rae
 */
public class ATTListener extends AutoaceptarListener {

    private Filtros filter = new Filtros();

    public ATTListener(PanelATT panel_att, Interfaz ventana_principal, int valor_inicial){
        
        super.panel_editor = panel_att;
        super.ventana_principal = ventana_principal;
        super.valor = valor_inicial;
        
    } 
 
    @Override
    public void aplicar_filtro() {
    
        panel_editor.poner_imagen_der(filter.filtro_att(ventana_principal.getImage(), valor));
        
    }
    
}
