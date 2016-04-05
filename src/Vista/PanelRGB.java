/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;

import Controlador.RGBListener;
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
public class PanelRGB extends PanelEditorImagen{

    private Interfaz ventana_principal;
    
    private JPanel rgb_panel_izq = new JPanel();
    private JPanel rgb_panel_izq_inf = new JPanel();
    private JPanel rgb_panel_der = new JPanel();
    
    private JLabel rgb_label_izq = new JLabel();
    private JLabel rgb_label_der = new JLabel();

    private JScrollPane rgb_scroll_label_izq = new JScrollPane(rgb_label_izq);
    private JScrollPane rgb_scroll_label_der = new JScrollPane(rgb_label_der);
    
    private final int VALOR_INICIAL = 0;
    
    private JLabel rgb_label_spinner_red = new JLabel("Red : ");
    private SpinnerModel rgb_model_spinner_red = new SpinnerNumberModel(VALOR_INICIAL,0,255,1);
    private JSpinner rgb_spinner_red = new JSpinner(rgb_model_spinner_red);
    
    private JLabel rgb_label_spinner_green = new JLabel("Green : ");
    private SpinnerModel rgb_model_spinner_green = new SpinnerNumberModel(VALOR_INICIAL,0,255,1);
    private JSpinner rgb_spinner_green = new JSpinner(rgb_model_spinner_green);
    
    private JLabel rgb_label_spinner_blue = new JLabel("Blue : ");
    private SpinnerModel rgb_model_spinner_blue = new SpinnerNumberModel(VALOR_INICIAL,0,255,1);
    private JSpinner rgb_spinner_blue = new JSpinner(rgb_model_spinner_blue);
    
    private JCheckBox rgb_checkbox = new JCheckBox(" Autoaceptar",false);
    private JButton rgb_boton = new JButton("Aceptar");
    
    private RGBListener RGB_listener;
    
    public PanelRGB(Interfaz ventana_principal){
        
        this.ventana_principal = ventana_principal;
        this.setLayout(new GridLayout(1,2,5,10));
        this.RGB_listener = new RGBListener(this,ventana_principal,VALOR_INICIAL);
        
        rgb_panel_izq.setLayout(new GridBagLayout());
        rgb_panel_izq_inf.setLayout(new GridBagLayout());
        rgb_panel_der.setLayout(new BorderLayout());
        
        rgb_panel_der.add(rgb_scroll_label_der,BorderLayout.CENTER);
        
        rgb_label_izq.setHorizontalAlignment(JLabel.CENTER);
        rgb_label_der.setHorizontalAlignment(JLabel.CENTER);
        
        rgb_label_izq.setIcon(new ImageIcon(ventana_principal.getImage()));
     
        rgb_spinner_red.setName("red");
        rgb_spinner_green.setName("green");
        rgb_spinner_blue.setName("blue");
        
        rgb_checkbox.addItemListener(RGB_listener);
        rgb_spinner_red.addChangeListener(RGB_listener);
        rgb_spinner_green.addChangeListener(RGB_listener);
        rgb_spinner_blue.addChangeListener(RGB_listener);
        rgb_boton.addActionListener(RGB_listener);
        
        GridBagConstraints especif = new GridBagConstraints();
        
        especif.gridx = 0;
        especif.gridy = 0;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 0.15;  
        especif.weighty = 1.0;
        especif.insets = new Insets(20,10,0,0);
        especif.fill = GridBagConstraints.BOTH;
        especif.anchor = GridBagConstraints.CENTER;
            
        rgb_panel_izq.add(rgb_scroll_label_izq,especif);
        
        especif.gridx = 0;
        especif.gridy = 1;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 0.05;
        especif.insets = new Insets(20,10,0,0);
        especif.fill = GridBagConstraints.BOTH;
        especif.anchor = GridBagConstraints.CENTER;
            
        rgb_panel_izq.add(rgb_panel_izq_inf,especif);
        
        // Empieza la configuracion del panel inferior izquierdo
        
        especif.gridx = 0;
        especif.gridy = 0;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 0.05;
        especif.insets = new Insets(0,20,3,0);
        especif.fill = GridBagConstraints.BOTH;
        especif.anchor = GridBagConstraints.SOUTH;
        
        rgb_panel_izq_inf.add(rgb_label_spinner_red,especif);
        
        especif.gridx = 1;
        especif.gridy = 0;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 0.05;
        especif.insets = new Insets(0,20,3,0);
        especif.fill = GridBagConstraints.BOTH;
        especif.anchor = GridBagConstraints.SOUTH;
        
        rgb_panel_izq_inf.add(rgb_label_spinner_green,especif);
        
        especif.gridx = 2;
        especif.gridy = 0;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 0.05;
        especif.insets = new Insets(0,20,3,0);
        especif.fill = GridBagConstraints.BOTH;
        especif.anchor = GridBagConstraints.SOUTH;
        
        rgb_panel_izq_inf.add(rgb_label_spinner_blue,especif);
        
        especif.gridx = 0;
        especif.gridy = 1;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 2.0;  
        especif.weighty = 1.0;
        especif.insets = new Insets(0,20,20,0);
        especif.fill = GridBagConstraints.HORIZONTAL;
        especif.anchor = GridBagConstraints.NORTH;
        especif.ipady = 10;
        
        rgb_panel_izq_inf.add(rgb_spinner_red,especif);
        
        especif.gridx = 1;
        especif.gridy = 1;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 2.0;  
        especif.weighty = 1.0;
        especif.insets = new Insets(0,20,0,0);
        especif.fill = GridBagConstraints.HORIZONTAL;
        especif.anchor = GridBagConstraints.NORTH;
        especif.ipady = 10;
        
        rgb_panel_izq_inf.add(rgb_spinner_green,especif);
        
        especif.gridx = 2;
        especif.gridy = 1;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 2.0;  
        especif.weighty = 1.0;
        especif.insets = new Insets(0,20,0,0);
        especif.fill = GridBagConstraints.HORIZONTAL;
        especif.anchor = GridBagConstraints.NORTH;
        especif.ipady = 10;
        
        rgb_panel_izq_inf.add(rgb_spinner_blue,especif);
        
        especif.gridx = 3;
        especif.gridy = 1;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 2.0;  
        especif.weighty = 1.0;
        especif.insets = new Insets(0,40,0,0);
        especif.fill = GridBagConstraints.HORIZONTAL;
        especif.anchor = GridBagConstraints.NORTH;
        especif.ipady = 0;
        
        rgb_panel_izq_inf.add(rgb_boton,especif);
        
        especif.gridx = 4;
        especif.gridy = 1;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 2.0;  
        especif.weighty = 1.0;
        especif.insets = new Insets(0,10,0,0);
        especif.fill = GridBagConstraints.HORIZONTAL;
        especif.anchor = GridBagConstraints.NORTH;
        especif.ipady = 0;
        
        rgb_panel_izq_inf.add(rgb_checkbox,especif);
        
        this.add(rgb_panel_izq);
        this.add(rgb_panel_der);
        this.revalidate();
        
    }
    
    
    @Override
    public void poner_imagen_der(BufferedImage imagen) {
    
        ventana_principal.setImageGuardar(imagen);  
        rgb_label_der.setIcon(new ImageIcon(imagen));
        ventana_principal.actualizar_interfaz();
    
    }

    @Override
    public void poner_imagen_izq(BufferedImage imagen) {
    
        rgb_label_izq.setIcon(new ImageIcon(imagen));
        rgb_model_spinner_red.setValue(new Integer(VALOR_INICIAL));
        rgb_model_spinner_green.setValue(new Integer(VALOR_INICIAL));
        rgb_model_spinner_blue.setValue(new Integer(VALOR_INICIAL));
        rgb_label_der.setIcon(null);
    
    }

    
    
    
}
