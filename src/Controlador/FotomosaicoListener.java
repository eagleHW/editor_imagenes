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

    private int tamaño_ventana_x;
    private int tamaño_ventana_y;
    private int tamaño_resultado_x;
    private int tamaño_resultado_y;
    
    private PanelFotomosaico panel_fotomosaico;
    private Interfaz ventana_principal;
    private Filtros filtros = new Filtros();
        
    public FotomosaicoListener(PanelFotomosaico panel_fotomosaico,Interfaz ventana_principal, int valor_inicial){
       
        this.panel_fotomosaico = panel_fotomosaico;
        this.ventana_principal = ventana_principal;
        
        tamaño_ventana_x = valor_inicial;
        tamaño_ventana_y = valor_inicial;
        tamaño_resultado_x = valor_inicial;
        tamaño_resultado_y = valor_inicial;
        
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
        
        panel_fotomosaico.poner_imagen_der(filtros.filtro_fotomosaico(ventana_principal.getImage(),
                tamaño_ventana_x, tamaño_ventana_y, tamaño_resultado_x, tamaño_resultado_y, "prueba1.html"));
        
    }

}
