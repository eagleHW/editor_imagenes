/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import ManipulacionImagenes.Filtros;
import Vista.Interfaz;
import Vista.PanelReduccionProporcion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;

/**
 *
 * @author rae
 */
public class ReduccionProporcionListener implements ActionListener, ItemListener{
    
    private Interfaz ventana_principal;
    private PanelReduccionProporcion panel_reduccion_proporcion;
    
    private Filtros filter = new Filtros();
            
    private int seleccion = 0;
    private int[] valores = {2,4,8};
    
    public ReduccionProporcionListener(PanelReduccionProporcion panel_reduccion_proporcion, Interfaz ventana_principal){
        
        this.ventana_principal = ventana_principal;
        this.panel_reduccion_proporcion = panel_reduccion_proporcion;
                
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    
        System.out.println(valores[seleccion]);
        
        panel_reduccion_proporcion.poner_imagen_der(
                    filter.filtro_reduccion_proporcion(ventana_principal.getImage(), valores[seleccion]));
        
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
    
        seleccion = ((JComboBox)e.getSource()).getSelectedIndex();
        
    }
    
}
