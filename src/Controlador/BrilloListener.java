/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import ManipulacionImagenes.Filtros;
import Vista.Interfaz;
import Vista.PanelBrillo;

/**
 *
 * @author rae
 */
public class BrilloListener extends AutoaceptarListener{
    
    private Filtros filter = new Filtros();
    
    public BrilloListener(PanelBrillo panel_brillo, Interfaz ventana_principal, int valor_inicial){
      
        super.panel_editor = panel_brillo;
        super.ventana_principal = ventana_principal;
        super.valor = valor_inicial;
        
    }
    
    @Override
    public void aplicar_filtro() {
        
        panel_editor.poner_imagen_der(filter.filtro_brillo(ventana_principal.getImage(), valor));
    
    }
    
}
