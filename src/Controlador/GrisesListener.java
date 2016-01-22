
package Controlador;

import ManipulacionImagenes.BibliotecaGrafica;
import ManipulacionImagenes.FiltrosGrises;
import Vista.Interfaz;
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

    Interfaz ventana_principal;
    
    public GrisesListener(Interfaz ventana_principal){
        
        this.ventana_principal = ventana_principal;
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    
        FiltrosGrises fg = new FiltrosGrises();
       
         try {
       
        
            switch(e.getActionCommand()){
                case "Promedio":
                    
                        ventana_principal.poner_imagen_der(fg.filtro_promedio(ventana_principal.getFile()));                    
                        
                        break;
                case "Luminicensia":
                    
                        ventana_principal.poner_imagen_der(fg.filtro_luminicensia(ventana_principal.getFile()));                    
                        
                    
                    break;
                case "Desaturación":
                        
                        ventana_principal.poner_imagen_der(fg.filtro_desaturacion(ventana_principal.getFile()));                   
                        
                    
                    break;
                case "Máximo":
                    
                        ventana_principal.poner_imagen_der(fg.filtro_maximo(ventana_principal.getFile()));                    
                        
                    
                    break;
                case "Mínimo":
                    
                        ventana_principal.poner_imagen_der(fg.filtro_minimo(ventana_principal.getFile()));                    
                        
   
                    break;
                case "Verde":
                    
                        ventana_principal.poner_imagen_der(fg.filtro_verde(ventana_principal.getFile()));                    
                        
                    
                    break;
                default:
                    System.out.println(e.getActionCommand());

            }

        } catch (IOException ex) {
            Logger.getLogger(GrisesListener.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    
    }
    
}
