package ManipulacionImagenes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Vista.Interfaz;
import javax.swing.UIManager;

/**
 *
 * @author rae
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         
       UIManager.put("OptionPane.yesButtonText", "Si");  
       new Interfaz(); // Se inicia la interfaz
        
    }
}
