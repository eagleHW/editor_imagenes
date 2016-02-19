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
    
    private JPanel att_panel_izq = new JPanel();
    private JPanel att_panel_izq_inf = new JPanel();
    private JPanel att_panel_der = new JPanel();
    
    private JLabel att_label_izq = new JLabel();
    private JLabel att_label_der = new JLabel();

    private JScrollPane att_scroll_label_izq = new JScrollPane(att_label_izq);
    private JScrollPane att_scroll_label_der = new JScrollPane(att_label_der);
    
    private JLabel att_label_num_filas = new JLabel("Num. Filas : ");
    private SpinnerModel att_spinner_model_num_filas = new SpinnerNumberModel(1,1,100,1);
    private JSpinner  att_spinner_num_filas = new JSpinner(att_spinner_model_num_filas);
    
    private JButton att_boton = new JButton("Aceptar");
    
    private ATTListener att_listener;
    
    public PanelATT(Interfaz ventana_principal){
        
        this.ventana_principal = ventana_principal;
        this.setLayout(new GridLayout(1,2,5,10));
        this.att_listener = new ATTListener(this,ventana_principal);
        
        att_panel_izq.setLayout(new GridBagLayout());
        att_panel_izq_inf.setLayout(new GridBagLayout());
        att_panel_der.setLayout(new BorderLayout());
            
        att_panel_der.add(att_scroll_label_der,BorderLayout.CENTER);
            
        att_label_izq.setHorizontalAlignment(JLabel.CENTER);
        att_label_der.setHorizontalAlignment(JLabel.CENTER);
        
        att_label_izq.setIcon(new ImageIcon(ventana_principal.getImage()));
        
        att_spinner_num_filas.setName("Num_filas");
        
        att_spinner_num_filas.addChangeListener(att_listener);
        att_boton.addActionListener(att_listener);
        
        GridBagConstraints especif = new GridBagConstraints();
        
        especif.gridx = 0;
        especif.gridy = 0;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 1.0;
        especif.insets = new Insets(20,10,0,0);
        especif.fill = GridBagConstraints.BOTH;
        especif.anchor = GridBagConstraints.CENTER;
            
        att_panel_izq.add(att_scroll_label_izq,especif);
        
        especif.gridx = 0;
        especif.gridy = 1;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 0.2;
        especif.insets = new Insets(20,10,0,0);
        especif.fill = GridBagConstraints.BOTH;
        especif.anchor = GridBagConstraints.CENTER;
            
        att_panel_izq.add(att_panel_izq_inf,especif);
        
        especif.gridx = 0;
        especif.gridy = 0;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 0.10;
        especif.insets = new Insets(0,10,0,0);
        especif.fill = GridBagConstraints.NONE;
        especif.anchor = GridBagConstraints.EAST;
        
        att_panel_izq_inf.add(att_label_num_filas,especif);
        
        especif.gridx = 1;
        especif.gridy = 0;
        especif.gridwidth = 2;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 1.0;
        especif.insets = new Insets(0,0,0,10);
        especif.fill = GridBagConstraints.HORIZONTAL;
        especif.anchor = GridBagConstraints.CENTER;
        especif.ipady = 15;
        
        att_panel_izq_inf.add(att_spinner_num_filas,especif);
        
        especif.gridx = 3;
        especif.gridy = 0;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 1.0;
        especif.insets = new Insets(0,10,0,0);
        especif.fill = GridBagConstraints.HORIZONTAL;
        especif.anchor = GridBagConstraints.CENTER;
        
        att_panel_izq_inf.add(att_boton,especif);
        
        especif.ipady = 0;
        
        this.add(att_panel_izq);
        this.add(att_panel_der);
        this.revalidate();
        
    }

    @Override
    public void poner_imagen_der(BufferedImage imagen) {  
        
        ventana_principal.setImageGuardar(imagen);  
        att_label_der.setIcon(new ImageIcon(imagen));
        ventana_principal.actualizar_interfaz();
    
    }
    
    @Override
    public void poner_imagen_izq(BufferedImage imagen) {

        att_label_izq.setIcon(new ImageIcon(imagen));
        att_label_der.setIcon(null);
        
    }
    
    
}
