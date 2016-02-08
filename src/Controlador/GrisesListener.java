
package Controlador;

import ManipulacionImagenes.FiltrosGrises;
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
public class GrisesListener implements ActionListener{

    private Interfaz ventana_principal;
    
    public GrisesListener(Interfaz ventana_principal){
        
        this.ventana_principal = ventana_principal;
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    
        FiltrosGrises fg = new FiltrosGrises();
        PanelBasico panel_basico;
       
         ventana_principal.remove(ventana_principal.getPanelPrincipal());
         ventana_principal.setPanelPrincipal(new PanelBasico(ventana_principal));
         ventana_principal.add(ventana_principal.getPanelPrincipal());
         ventana_principal.actualizar_interfaz();
                
         panel_basico = (PanelBasico)ventana_principal.getPanelPrincipal();
         
        try {
       
        
            switch(e.getActionCommand()){
                case "Promedio":
       
                        panel_basico.poner_imagen_der(fg.filtro_promedio(ventana_principal.getFile()));
                    
                        break;
                case "Luminicensia":
                    
                        panel_basico.poner_imagen_der(fg.filtro_luminicensia(ventana_principal.getFile()));
                                        
                    
                    break;
                case "Desaturación":
                        
                        panel_basico.poner_imagen_der(fg.filtro_desaturacion(ventana_principal.getFile()));                  
                        
                    
                    break;
                case "Máximo":
                    
                        panel_basico.poner_imagen_der(fg.filtro_maximo(ventana_principal.getFile()));                   
                        
                    
                    break;
                case "Mínimo":
                    
                        panel_basico.poner_imagen_der(fg.filtro_minimo(ventana_principal.getFile()));                    
                        
   
                    break;
                case "Verde":
                    
                        panel_basico.poner_imagen_der(fg.filtro_verde(ventana_principal.getFile()));                    
                        
                   
                    break;
                default:
                    System.out.println(e.getActionCommand());

            }

        } catch (IOException ex) {
            Logger.getLogger(GrisesListener.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    
    }
    
}
