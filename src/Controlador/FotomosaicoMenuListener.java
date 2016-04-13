/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import ManipulacionImagenes.LectorArchivoFotomosaico;
import Vista.Interfaz;
import Vista.PanelFotomosaico;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 *
 * @author rae
 */
public class FotomosaicoMenuListener implements ActionListener{

    Interfaz ventana_principal;
    
    public FotomosaicoMenuListener(Interfaz ventana_principal){
        
        this.ventana_principal = ventana_principal;
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       
         switch(e.getActionCommand()){
             
             case "Fotomosaico":
             
                ventana_principal.remove(ventana_principal.getPanelPrincipal());
                ventana_principal.setPanelPrincipal(new PanelFotomosaico(ventana_principal));
                ventana_principal.add(ventana_principal.getPanelPrincipal());
                ventana_principal.enable_item_guardar(false);
                ventana_principal.actualizar_interfaz();
                 
                break;
                 
             case "Cargar Imágenes" :
              
                LectorArchivoFotomosaico lector = new LectorArchivoFotomosaico("fotograma/",ventana_principal);
                    
                lector.getDialog().addWindowListener(new WindowListener(){

                @Override
                public void windowOpened(WindowEvent e) {}

                @Override
                public void windowClosing(WindowEvent e) {}

                @Override
                public void windowClosed(WindowEvent e) {

                    lector.cancel(true);

                }

                @Override
                public void windowIconified(WindowEvent e) {
                
                    lector.cancel(true);
                    
                }

                @Override
                public void windowDeiconified(WindowEvent e) {}

                @Override
                public void windowActivated(WindowEvent e) {}

                @Override
                public void windowDeactivated(WindowEvent e) {}

                    }
               );
                    
                    
                lector.execute();
                lector.visible_frame();
                    

                break;                
         }
        
    }
    

    
    
    
    
}
