/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import ManipulacionImagenes.Filtros;
import Vista.Interfaz;
import Vista.PanelFotomosaico;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author rae
 */
public class FotomosaicoListener implements ActionListener, ChangeListener{

    int tamaño_ventana_x = 1;
    int tamaño_ventana_y = 1;
    int tamaño_resultado_x = 1;
    int tamaño_resultado_y = 1;
    
    private PanelFotomosaico panel_fotomosaico;
    private Interfaz ventana_principal;
    
    public FotomosaicoListener(PanelFotomosaico panel_fotomosaico,Interfaz ventana_principal){
       
        this.panel_fotomosaico = panel_fotomosaico;
        this.ventana_principal = ventana_principal;
        
    }
    
    @Override
    public void stateChanged(ChangeEvent e) {
   
        
        JSpinner spinner = (JSpinner)e.getSource();
        SpinnerNumberModel spinner_number_model = (SpinnerNumberModel) spinner.getModel();
                
        switch(spinner.getName()){
            
            case "tamaño_ventana_x":
    
                tamaño_ventana_x = spinner_number_model.getNumber().intValue();
                
                break;
                
            case "tamaño_ventana_y":
            
                tamaño_ventana_y = spinner_number_model.getNumber().intValue();
                
                break;
                
            case "tamaño_resultado_x":
                
                tamaño_resultado_x = spinner_number_model.getNumber().intValue();
                
                break;
            
            case "tamaño_resultado_y":
                
                tamaño_resultado_y = spinner_number_model.getNumber().intValue();
                
                break;
            default:
                
                System.out.println("Opcion desconocida - FotomosaicoListener.java");
                
        } 
        
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
   
         System.out.println("paso");
        
        Filtros filtros = new Filtros();
        filtros.filtro_fotomosaico(ventana_principal.getImage(), tamaño_ventana_x, tamaño_ventana_y, 
                                    tamaño_resultado_x, tamaño_resultado_y, "prueba1.html");
        
        System.out.println("salio");
                
    }

}
