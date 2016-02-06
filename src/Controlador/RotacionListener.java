/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import ManipulacionImagenes.FiltrosGrises;
import ManipulacionImagenes.FiltrosRotacion;
import Vista.Interfaz;
import Vista.PanelBasico;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rae
 */
public class RotacionListener implements ActionListener {

    Interfaz ventana_principal;
    
    public RotacionListener(Interfaz ventana_principal){
        this.ventana_principal = ventana_principal;
    }
        
        
    @Override
    public void actionPerformed(ActionEvent e) {
       
        FiltrosRotacion fr = new FiltrosRotacion();        
        PanelBasico panel_basico;
       
        ventana_principal.remove(ventana_principal.getPanelPrincipal());
        ventana_principal.setPanelPrincipal(new PanelBasico(ventana_principal));
        ventana_principal.add(ventana_principal.getPanelPrincipal());
        ventana_principal.actualizar_interfaz();
                
         panel_basico = (PanelBasico)ventana_principal.getPanelPrincipal();
        
        try{
        
            switch(e.getActionCommand()){

                 case "90":

                     panel_basico.poner_imagen_der(fr.filtro_rotacion90(ventana_principal.getFile()));

                    break;
                 case "180":
                     
                     panel_basico.poner_imagen_der(fr.filtro_rotacion180(ventana_principal.getFile()));

         
                    break;
                 case "270":

                     panel_basico.poner_imagen_der(fr.filtro_rotacion270(ventana_principal.getFile()));

                     break;

            }
        
        }catch(IOException ex) {
            Logger.getLogger(GrisesListener.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       
    }
    
}
