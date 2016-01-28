package Controlador;

import Vista.Interfaz;
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
    
    Interfaz ventana_principal;
    
    public ConvolucionListener(Interfaz ventana_principal){
        
        this.ventana_principal = ventana_principal;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        ventana_principal.eliminar_imagen_der();
        ventana_principal.undo_warhol();
        
        switch(e.getActionCommand()){
            
            case "3 x 3":
                
                    new VentanaConvolucion(ventana_principal,3);
                
                break;
            case "5 x 5":
                
                    new VentanaConvolucion(ventana_principal,5);
                
                break;
            case "7 x 7":
                
                    new VentanaConvolucion(ventana_principal,7);
                
                break;
            case "9 x 9":
                
                    new VentanaConvolucion(ventana_principal,9);
                
                break;
        }
    
    }
    
    
}
