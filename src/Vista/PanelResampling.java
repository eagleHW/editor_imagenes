/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;

import Controlador.ResamplingListener;
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
public class PanelResampling extends PanelEditorImagen {

    private Interfaz ventana_principal;
    
    private JPanel resampling_panel_izq = new JPanel();
    private JPanel resampling_panel_izq_inf = new JPanel();
    private JPanel resampling_panel_der = new JPanel();
    
    private JLabel resampling_label_izq = new JLabel();
    private JLabel resampling_label_der = new JLabel();

    private JScrollPane resampling_scroll_label_izq = new JScrollPane(resampling_label_izq);
    private JScrollPane resampling_scroll_label_der = new JScrollPane(resampling_label_der);
    
    private final int VALOR_INICIAL = 0;
    
    private JLabel resampling_label_spinner_width = new JLabel("Width : ");
    private SpinnerModel resampling_model_spinner_width = new SpinnerNumberModel(VALOR_INICIAL,0,1000,1);
    private JSpinner resampling_spinner_width = new JSpinner(resampling_model_spinner_width);
    
    private JLabel resampling_label_spinner_height = new JLabel("Height : ");
    private SpinnerModel resampling_model_spinner_height = new SpinnerNumberModel(VALOR_INICIAL,0,1000,1);
    private JSpinner resampling_spinner_height = new JSpinner(resampling_model_spinner_height);
    
    private JCheckBox resampling_checkbox = new JCheckBox(" Autoaceptar",false);
    private JButton resampling_boton = new JButton("Aceptar");
    
    private ResamplingListener resampling_listener;
    
    public PanelResampling(Interfaz ventana_principal){
        
        this.ventana_principal = ventana_principal;
        this.setLayout(new GridLayout(1,2,5,10));
        this.resampling_listener = new ResamplingListener(this,ventana_principal,VALOR_INICIAL);
        
        resampling_panel_izq.setLayout(new GridBagLayout());
        resampling_panel_izq_inf.setLayout(new GridBagLayout());
        resampling_panel_der.setLayout(new BorderLayout());
        
        resampling_panel_der.add(resampling_scroll_label_der,BorderLayout.CENTER);
        
        resampling_label_izq.setHorizontalAlignment(JLabel.CENTER);
        resampling_label_der.setHorizontalAlignment(JLabel.CENTER);
        
        resampling_label_izq.setIcon(new ImageIcon(ventana_principal.getImage()));
     
        resampling_spinner_width.setName("width");
        resampling_spinner_height.setName("height");

        resampling_checkbox.addItemListener(resampling_listener);
        resampling_spinner_width.addChangeListener(resampling_listener);
        resampling_spinner_height.addChangeListener(resampling_listener);
        resampling_boton.addActionListener(resampling_listener);
        
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
            
        resampling_panel_izq.add(resampling_scroll_label_izq,especif);
        
        especif.gridx = 0;
        especif.gridy = 1;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 0.05;
        especif.insets = new Insets(20,10,0,0);
        especif.fill = GridBagConstraints.BOTH;
        especif.anchor = GridBagConstraints.CENTER;
            
        resampling_panel_izq.add(resampling_panel_izq_inf,especif);
        
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
        
        resampling_panel_izq_inf.add(resampling_label_spinner_width,especif);
        
        especif.gridx = 1;
        especif.gridy = 0;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 0.05;
        especif.insets = new Insets(0,20,3,0);
        especif.fill = GridBagConstraints.BOTH;
        especif.anchor = GridBagConstraints.SOUTH;
        
        resampling_panel_izq_inf.add(resampling_label_spinner_height,especif);
        
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
        
        resampling_panel_izq_inf.add(resampling_spinner_width,especif);
        
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
        
        resampling_panel_izq_inf.add(resampling_spinner_height,especif);
        
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
        
        resampling_panel_izq_inf.add(resampling_boton,especif);
        
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
        
        resampling_panel_izq_inf.add(resampling_checkbox,especif);
        
        this.add(resampling_panel_izq);
        this.add(resampling_panel_der);
        this.revalidate();
        
    }
   
    @Override
    public void poner_imagen_der(BufferedImage imagen) {
   
        ventana_principal.setImageGuardar(imagen);  
        resampling_label_der.setIcon(new ImageIcon(imagen));
        ventana_principal.actualizar_interfaz();
    
        
    }

    @Override
    public void poner_imagen_izq(BufferedImage imagen) {
    
        resampling_label_izq.setIcon(new ImageIcon(imagen));
        resampling_label_der.setIcon(null);
        
    }

   
}
