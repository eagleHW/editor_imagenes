package Controlador;

import ManipulacionImagenes.FiltrosRotacion;
import Vista.Interfaz;
import Vista.PanelBasico;
import Vista.VentanaConvolucion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rae
 */
public class ConvolucionListener implements ActionListener {
    
    private Interfaz ventana_principal;
    
    public ConvolucionListener(Interfaz ventana_principal){
        
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
        
        switch(e.getActionCommand()){
            
            case "3 x 3":
                
                    new VentanaConvolucion(panel_basico,ventana_principal.getImage(),3);
                
                break;
            case "5 x 5":
                
                    new VentanaConvolucion(panel_basico,ventana_principal.getImage(),5);
                
                break;
            case "7 x 7":
                
                    new VentanaConvolucion(panel_basico,ventana_principal.getImage(),7);
                
                break;
            case "9 x 9":
                
                    new VentanaConvolucion(panel_basico,ventana_principal.getImage(),9);
                
                break;
        }
    
    }
    
    
}
