/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;

import Controlador.ATTListener;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author rae
 */
public class PanelATT extends PanelEditorImagen{
    
    private Interfaz ventana_principal;
    private SpinnerNumberModel att_spinner_model;
    private String mensaje_label;
    private ATTListener listener;
    private PanelAutoaceptar panel_autoaceptar;
    
    private final int VALOR_INICIAL = 1;
    
    public PanelATT(Interfaz ventana_principal){
       
        this.setLayout(new GridLayout(1,1,0,0));
        
        this.ventana_principal = ventana_principal;
        this.att_spinner_model = new SpinnerNumberModel(VALOR_INICIAL,1,100,1);
        this.mensaje_label = "Número de filas : ";
        this.listener = new ATTListener(this,ventana_principal,VALOR_INICIAL);
        
        this.panel_autoaceptar = new PanelAutoaceptar(ventana_principal, att_spinner_model, mensaje_label, listener);
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
