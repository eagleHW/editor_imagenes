/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import ManipulacionImagenes.FiltrosRotacion;
import Vista.Interfaz;
import Vista.PanelRotacionMatriz;

/**
 *
 * @author rae
 */
public class RotacionMatrizListener extends AutoaceptarListener{
  
    private FiltrosRotacion fr = new FiltrosRotacion();

    public RotacionMatrizListener(PanelRotacionMatriz panel_rotacion_matriz,Interfaz ventana_principal, int valor_inicial){
        
        super.panel_editor = panel_rotacion_matriz;
        super.ventana_principal = ventana_principal;
        super.valor = valor_inicial;
        
    }
    
    @Override
    public void aplicar_filtro() {
    
        panel_editor.poner_imagen_der(fr.filtro_rotacion_matriz(ventana_principal.getImage(), valor));
        
    }
    
}
