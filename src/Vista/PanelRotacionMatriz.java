/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;

import Controlador.BlackLightListener;
import Controlador.RotacionMatrizListener;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author rae
 */
public class PanelRotacionMatriz extends PanelEditorImagen{

    private Interfaz ventana_principal;
    private SpinnerNumberModel rotacion_spinner_model;
    private String mensaje_label;
    private RotacionMatrizListener listener;
    private PanelAutoaceptar panel_autoaceptar;
    
    private final int VALOR_INICIAL = 0;
    
    public PanelRotacionMatriz(Interfaz ventana_principal){
        
         this.setLayout(new GridLayout(1,1,0,0));
        
        this.ventana_principal = ventana_principal;
        this.rotacion_spinner_model = new SpinnerNumberModel(VALOR_INICIAL,0,360,1);
        this.mensaje_label = "Grados : ";
        this.listener = new RotacionMatrizListener(this,ventana_principal,VALOR_INICIAL);
        
        this.panel_autoaceptar = new PanelAutoaceptar(ventana_principal, rotacion_spinner_model, mensaje_label, listener);
        this.add(panel_autoaceptar);
        
    }
    
       @Override
    public void poner_imagen_der(BufferedImage imagen) {  
       
        panel_autoaceptar.poner_imagen_der(imagen);
        
    }
    
    @Override
    public void poner_imagen_izq(BufferedImage imagen) {

    panel_autoaceptar.poner_imagen_izq(imagen);
        
    }
    
    public void boton_disponible(boolean disponible){
        
    panel_autoaceptar.boton_disponible(disponible);
     
     }
    
}