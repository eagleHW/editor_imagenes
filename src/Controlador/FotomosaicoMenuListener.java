/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import ManipulacionImagenes.LectorArchivoFotomosaico;
import Vista.Interfaz;
import Vista.PanelFotomosaico;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author rae
 */
public class FotomosaicoMenuListener implements ActionListener{

    Interfaz ventana_principal;
    
    public FotomosaicoMenuListener(Interfaz ventana_principal){
        
        this.ventana_principal = ventana_principal;
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       
         switch(e.getActionCommand()){
             
             case "Fotomosaico":
             
                ventana_principal.remove(ventana_principal.getPanelPrincipal());
                ventana_principal.setPanelPrincipal(new PanelFotomosaico(ventana_principal));
                ventana_principal.add(ventana_principal.getPanelPrincipal());
                ventana_principal.enable_item_guardar(false);
                ventana_principal.actualizar_interfaz();
                 
                break;
                 
             case "Actualizar" :
                 
                 new LectorArchivoFotomosaico("fotograma/");
                 
                break; 
         }
        
    }
    
}
