/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import ManipulacionImagenes.Filtros;
import Vista.Interfaz;
import Vista.PanelSepia;

/**
 *
 * @author rae
 */
public class SepiaListener extends AutoaceptarListener{

    private Filtros filter = new Filtros();
    
    public SepiaListener(PanelSepia panel_sepia, Interfaz ventana_principal, int valor_inicial){
        
        super.panel_editor = panel_sepia;
        super.ventana_principal = ventana_principal;
        super.valor = valor_inicial;
            
    }
    
    @Override
    public void aplicar_filtro() {
        
        panel_editor.poner_imagen_der(filter.filtro_sepia(ventana_principal.getImage(), valor));
        
    }
    
}
