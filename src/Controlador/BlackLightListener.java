/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import ManipulacionImagenes.Filtros;
import Vista.Interfaz;
import Vista.PanelBlackLight;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author rae
 */
public class BlackLightListener extends AutoaceptarListener {

    private Filtros filter = new Filtros();
    
    public BlackLightListener(PanelBlackLight panel_blacklight, Interfaz ventana_principal, int valor_inicial){
        
        super.panel_editor = panel_blacklight;
        super.ventana_principal = ventana_principal;
        super.valor = valor_inicial;
        
    }
    
    @Override
    public void aplicar_filtro() {
    
        panel_editor.poner_imagen_der(filter.filtro_blacklight(ventana_principal.getImage(), valor));
        
    }

   
    
}
