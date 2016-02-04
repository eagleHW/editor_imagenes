
package Controlador;

import ManipulacionImagenes.BibliotecaGrafica;
import Vista.Interfaz;
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
        
        ventana_principal.eliminar_imagen_der();
        ventana_principal.undo_all(); 
        
        
        switch(e.getActionCommand()){
        
            case "3 x 3":
                
                ventana_principal.poner_imagen_der(bg.filtro_maximo(ventana_principal.getImage(),3));
                
                break;
            
                
            case "5 x 5":
                    
                ventana_principal.poner_imagen_der(bg.filtro_maximo(ventana_principal.getImage(),5));
                
                
                break;
            
            
        }
        
    }
    
}
