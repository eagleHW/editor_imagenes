/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Vista.Interfaz;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
       
       switch(e.getActionCommand()){
           
            case "90":
                
                System.out.println("90");
                
               break;
            case "180":
                
                System.out.println("180");
                
               break;
            case "270":
                
                System.out.println("270");
                
                break;
           
       }
        
    }
    
}
