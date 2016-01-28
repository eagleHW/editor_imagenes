
package Controlador;

import ManipulacionImagenes.BibliotecaGrafica;
import Vista.Interfaz;
import Vista.VentanaBrillo;
import Vista.VentanaConvolucion;
import Vista.VentanaRGB;
import Vista.VentanaReduccion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rae
 */
public class FiltrosListener implements ActionListener{
    
    Interfaz ventana_principal;
    
    public FiltrosListener(Interfaz ventana_principal){
     
        this.ventana_principal = ventana_principal;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
        BibliotecaGrafica bg = new BibliotecaGrafica();
        
        try{
            switch(e.getActionCommand()){
            case "Azar":
   
                    ventana_principal.eliminar_imagen_der();
                    ventana_principal.undo_warhol();
                    ventana_principal.poner_imagen_der(bg.filtro_azar(ventana_principal.getFile()));
                
                break;
            case "RGB":
                
                    ventana_principal.eliminar_imagen_der();
                    ventana_principal.undo_warhol();
                    new VentanaRGB(ventana_principal);
                   
                break;
            case "Brillo":
                  
                    ventana_principal.eliminar_imagen_der();
                    ventana_principal.undo_warhol();
                    new VentanaBrillo(ventana_principal);
                  
                break;
            case "Grises":
                 
                   // Para ver las acciones del subMenu Grises ir a GrisesListener.java
                 
                break;
            case "Mosaico":
        
                 //ventana_principal.poner_imagen_espera();
                 ventana_principal.eliminar_imagen_der();
                 ventana_principal.undo_warhol();
                 ventana_principal.poner_imagen_der(bg.filtro_mosaico(ventana_principal.getFile(),5));
                
                break;
            case "Reduccion": 
                
                 ventana_principal.eliminar_imagen_der();
                 ventana_principal.undo_warhol();
                new VentanaReduccion(ventana_principal);
                
                break;
            case "Warhol":
                
                 ventana_principal.eliminar_imagen_der();
                 ventana_principal.warholizate();
                
                break;
             
            default:
                System.out.println(e.getActionCommand());    
        
            }
           
        } catch (IOException ex) {
            Logger.getLogger(FiltrosListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
