/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import ManipulacionImagenes.Filtros;
import Vista.Interfaz;
import Vista.PanelBasico;
import Vista.PanelReduccionPorcentaje;
import Vista.VentanaReduccion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author rae
 */
public class ReduccionListener implements ActionListener {

    private Interfaz ventana_principal;

    public ReduccionListener(Interfaz ventana_principal) {
          this.ventana_principal = ventana_principal;
    }
  
    @Override
    public void actionPerformed(ActionEvent e) {
        
        Filtros filter = new Filtros();
        
        ventana_principal.remove(ventana_principal.getPanelPrincipal());
        
        switch (e.getActionCommand()) {
            case "Proporcion":
                
                ventana_principal.setPanelPrincipal(new PanelBasico(ventana_principal));
                ventana_principal.add(ventana_principal.getPanelPrincipal());
                ventana_principal.actualizar_interfaz();
                new VentanaReduccion((PanelBasico)ventana_principal.getPanelPrincipal(), ventana_principal.getFile());
                
                break;
            case "Porcentaje":
                
                ventana_principal.setPanelPrincipal(new PanelReduccionPorcentaje(ventana_principal));
                ventana_principal.add(ventana_principal.getPanelPrincipal());
                ventana_principal.actualizar_interfaz();
                
                break;
            default:
                System.out.println("No cayo en ninguno");
        }
        
        
    
    }
    
}
