
package Controlador;

import ManipulacionImagenes.Filtros;
import Vista.Interfaz;
import Vista.PanelBasico;
import Vista.PanelBlackLight;
import Vista.PanelBlending;
import Vista.PanelFavicom;
import Vista.PanelSepia;
import Vista.PanelWarhol;
import Vista.VentanaBrillo;
import Vista.VentanaRGB;
import Vista.VentanaReduccion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rae
 */
public class FiltrosListener implements ActionListener{
    
    private Interfaz ventana_principal;
    
    public FiltrosListener(Interfaz ventana_principal){
     
        this.ventana_principal = ventana_principal;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
        Filtros filter = new Filtros();
        
        try{
            switch(e.getActionCommand()){
            case "Azar":
   
                    ventana_principal.remove(ventana_principal.getPanelPrincipal());
                    ventana_principal.setPanelPrincipal(new PanelBasico(ventana_principal));
                    ventana_principal.add(ventana_principal.getPanelPrincipal());
                    ventana_principal.actualizar_interfaz();
                    ventana_principal.getPanelPrincipal().poner_imagen_der(filter.filtro_azar(ventana_principal.getFile()));
                
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
                   ventana_principal.getPanelPrincipal().poner_imagen_der(filter.filtro_mosaico(ventana_principal.getImage()));
                    
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
                
                ventana_principal.remove(ventana_principal.getPanelPrincipal());
                ventana_principal.setPanelPrincipal(new PanelFavicom(ventana_principal));
                ventana_principal.add(ventana_principal.getPanelPrincipal());
                ventana_principal.actualizar_interfaz();
                
                break;
            
            case "Sepia":
                
                ventana_principal.remove(ventana_principal.getPanelPrincipal());
                ventana_principal.setPanelPrincipal(new PanelSepia(ventana_principal));
                ventana_principal.add(ventana_principal.getPanelPrincipal());
                ventana_principal.actualizar_interfaz();
                
                break;
            
            case "Alto Contraste":
                
                ventana_principal.remove(ventana_principal.getPanelPrincipal());
                ventana_principal.setPanelPrincipal(new PanelBasico(ventana_principal));
                ventana_principal.add(ventana_principal.getPanelPrincipal());
                ventana_principal.actualizar_interfaz();
                ventana_principal.getPanelPrincipal().poner_imagen_der(filter.filtro_alto_contraste(ventana_principal.getImage()));
                   
                break;
                
            case "BlackLight":
                
                ventana_principal.remove(ventana_principal.getPanelPrincipal());
                ventana_principal.setPanelPrincipal(new PanelBlackLight(ventana_principal));
                ventana_principal.add(ventana_principal.getPanelPrincipal());
                ventana_principal.actualizar_interfaz();
                
                break;
            default:
                System.out.println(e.getActionCommand());    
        
            }
           
        } catch (IOException ex) {
            Logger.getLogger(FiltrosListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
