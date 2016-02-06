
package Controlador;

import ManipulacionImagenes.BibliotecaGrafica;
import Vista.Interfaz;
import Vista.PanelBasico;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author rae
 */
public class MaximoListener implements ActionListener{

    Interfaz ventana_principal;
    
    public MaximoListener(Interfaz ventana_principal){
        this.ventana_principal = ventana_principal;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
        BibliotecaGrafica bg = new BibliotecaGrafica();
        PanelBasico panel_basico;
       
        ventana_principal.remove(ventana_principal.getPanelPrincipal());
        ventana_principal.setPanelPrincipal(new PanelBasico(ventana_principal));
        ventana_principal.add(ventana_principal.getPanelPrincipal());
        ventana_principal.actualizar_interfaz();
        
        panel_basico = (PanelBasico)ventana_principal.getPanelPrincipal();
        
        switch(e.getActionCommand()){
        
            case "3 x 3":
                
                panel_basico.poner_imagen_der(bg.filtro_maximo(ventana_principal.getImage(),3));
                
                break;
            
                
            case "5 x 5":
                    
                panel_basico.poner_imagen_der(bg.filtro_maximo(ventana_principal.getImage(),5));
                
                
                break;
            
            
        }
        
    }
    
}
