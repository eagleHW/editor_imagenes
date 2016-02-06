
package Controlador;

import ManipulacionImagenes.BibliotecaGrafica;
import Vista.Interfaz;
import Vista.PanelBasico;
import Vista.PanelBlending;
import Vista.PanelWarhol;
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
   
                    ventana_principal.remove(ventana_principal.getPanelPrincipal());
                    ventana_principal.setPanelPrincipal(new PanelBasico(ventana_principal));
                    ventana_principal.add(ventana_principal.getPanelPrincipal());
                    ventana_principal.actualizar_interfaz();
                    ((PanelBasico)ventana_principal.getPanelPrincipal()).poner_imagen_der(bg.filtro_azar(ventana_principal.getFile()));
                
                break;
            case "RGB":
                
                    ventana_principal.remove(ventana_principal.getPanelPrincipal());
                    ventana_principal.setPanelPrincipal(new PanelBasico(ventana_principal));
                    ventana_principal.add(ventana_principal.getPanelPrincipal());
                    ventana_principal.actualizar_interfaz();
                    new VentanaRGB((PanelBasico)ventana_principal.getPanelPrincipal(), ventana_principal.getFile());
                   
                break;
            case "Brillo":
                
                    ventana_principal.remove(ventana_principal.getPanelPrincipal());
                    ventana_principal.setPanelPrincipal(new PanelBasico(ventana_principal));
                    ventana_principal.add(ventana_principal.getPanelPrincipal());
                    ventana_principal.actualizar_interfaz();
                    new VentanaBrillo((PanelBasico)ventana_principal.getPanelPrincipal(), ventana_principal.getFile());
                  
                break;
            case "Grises":
                 
                   // Para ver las acciones del subMenu Grises ir a GrisesListener.java
                 
                break;
            case "Mosaico":
        
                   ventana_principal.remove(ventana_principal.getPanelPrincipal());
                   ventana_principal.setPanelPrincipal(new PanelBasico(ventana_principal));
                   ventana_principal.add(ventana_principal.getPanelPrincipal());
                   ventana_principal.actualizar_interfaz();
                   ((PanelBasico)ventana_principal.getPanelPrincipal()).poner_imagen_der(bg.filtro_mosaico(ventana_principal.getFile(),5));
                    
                break;
            case "Reduccion": 
                
                 ventana_principal.remove(ventana_principal.getPanelPrincipal());
                 ventana_principal.setPanelPrincipal(new PanelBasico(ventana_principal));
                 ventana_principal.add(ventana_principal.getPanelPrincipal());
                 ventana_principal.actualizar_interfaz();
                 new VentanaReduccion((PanelBasico)ventana_principal.getPanelPrincipal(), ventana_principal.getFile());
           
                break;
            case "Warhol":
                
                //System.out.println(ventana_principal.getPanelPrincipal().getClass().getName());

                ventana_principal.remove(ventana_principal.getPanelPrincipal());
                ventana_principal.setPanelPrincipal(new PanelWarhol(ventana_principal));
                ventana_principal.add(ventana_principal.getPanelPrincipal());
                ventana_principal.actualizar_interfaz();
                                  
                break;
            case "Rotacion": 
                 // Para ver las acciones del subMenu Rotacion ir a RotacionListener.java
                break;
            
            case "Blending":
              
                ventana_principal.remove(ventana_principal.getPanelPrincipal());
                ventana_principal.setPanelPrincipal(new PanelBlending(ventana_principal));
                ventana_principal.add(ventana_principal.getPanelPrincipal());
                ventana_principal.actualizar_interfaz();
                
                break;
            case "Favicom":
                
                ventana_principal.undo_all();
                ventana_principal.eliminar_imagen_der();
                ventana_principal.do_favicom();
                
                break;
            
            case "Sepia":
                
                ventana_principal.undo_all();
                ventana_principal.eliminar_imagen_der();
                ventana_principal.do_sepia();
                
                break;
            
            case "Alto Contraste":
                
                ventana_principal.undo_all();
                ventana_principal.eliminar_imagen_der();
                ventana_principal.poner_imagen_der(bg.filtro_alto_contraste(ventana_principal.getImage()));
               
                break;
            default:
                System.out.println(e.getActionCommand());    
        
            }
           
        } catch (IOException ex) {
            Logger.getLogger(FiltrosListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
