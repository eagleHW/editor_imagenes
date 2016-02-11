/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import ManipulacionImagenes.Filtros;
import Vista.Interfaz;
import Vista.PanelBasico;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author rae
 */
public class OleoListener implements ActionListener{

    private Interfaz ventana_principal;
    
    public OleoListener(Interfaz ventana_principal){
        this.ventana_principal = ventana_principal;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        Filtros filter = new Filtros();
        PanelBasico panel_basico;
    
        ventana_principal.remove(ventana_principal.getPanelPrincipal());
        ventana_principal.setPanelPrincipal(new PanelBasico(ventana_principal));
        ventana_principal.add(ventana_principal.getPanelPrincipal());
        ventana_principal.actualizar_interfaz();
                
        panel_basico = (PanelBasico)ventana_principal.getPanelPrincipal();
    
        switch(e.getActionCommand()){
        
            case "Gris":
                
                panel_basico.poner_imagen_der(filter.filtro_oleo_bn(ventana_principal.getImage()));
                
                break;
        
            case "Color":
           
                panel_basico.poner_imagen_der(ventana_principal.getImage());
                
                break;
      
            default:
                
                System.out.println("No cayo en ninguno");
              
        }
        
    }
    
    
    
    
}
