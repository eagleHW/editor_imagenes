/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import ManipulacionImagenes.Filtros;
import Vista.Interfaz;
import Vista.PanelReduccionPorcentaje;

/**
 *
 * @author rae
 */
public class ReduccionPorcentajeListener extends AutoaceptarListener{

    private Filtros filter = new Filtros();
    
    public ReduccionPorcentajeListener(PanelReduccionPorcentaje panel_reduccion_porcentaje, Interfaz ventana_principal, int valor_inicial){
        
        super.panel_editor = panel_reduccion_porcentaje;
        super.ventana_principal = ventana_principal;
        super.valor = valor_inicial;
        
        
    }
     
    @Override
    public void aplicar_filtro() {
    
        panel_editor.poner_imagen_der(filter.filtro_reduccion_porcentaje(ventana_principal.getImage(), valor));
        
    }
    
}
