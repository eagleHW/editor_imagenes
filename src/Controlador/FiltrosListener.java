
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
                      
                    ventana_principal.poner_imagen_der(bg.filtro_azar(ventana_principal.getFile()));
                
                break;
            case "RGB":
                    
                    new VentanaRGB(ventana_principal);
                   
                break;
            case "Brillo":
                  
                    new VentanaBrillo(ventana_principal);
                  
                break;
            case "Grises":
                 
                   // Para ver las acciones del subMenu Grises ir a GrisesListener.java
                 
                break;
            case "Mosaico":
        
                 //ventana_principal.poner_imagen_espera();      
                 ventana_principal.poner_imagen_der(bg.filtro_mosaico(ventana_principal.getFile(),5));
                
                break;
            case "Reduccion": 
   
                new VentanaReduccion(ventana_principal);
                
                //ventana_principal.poner_imagen_der(bg.filtro_reduccion(ventana_principal.getFile(),8));
                
                break;
            case "Warhol":
                
                    ventana_principal.warholizate();
                    //ventana_principal.repaint();
                
                break;
             
            default:
                System.out.println(e.getActionCommand());    
        
            }
           
        } catch (IOException ex) {
            Logger.getLogger(FiltrosListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
