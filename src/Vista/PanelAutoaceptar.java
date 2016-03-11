/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;

import Controlador.AutoaceptarListener;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.util.EventListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author rae
 */
public class PanelAutoaceptar extends PanelEditorImagen{

    private Interfaz ventana_principal;
    
    private JPanel auto_aceptar_panel_izq = new JPanel();
    private JPanel auto_aceptar_panel_izq_inf = new JPanel();
    private JPanel auto_aceptar_panel_der = new JPanel();
    
    private JLabel auto_aceptar_label_izq = new JLabel();
    private JLabel auto_aceptar_label_der = new JLabel();

    private JScrollPane auto_aceptar_scroll_label_izq = new JScrollPane(auto_aceptar_label_izq);
    private JScrollPane auto_aceptar_scroll_label_der = new JScrollPane(auto_aceptar_label_der);
    
    private JCheckBox auto_aceptar_checkbox = new JCheckBox(" Autoaceptar",false);
    
    private JLabel auto_aceptar_label_spinner;
    private JSpinner auto_aceptar_spinner;
    
    private JButton auto_aceptar_boton = new JButton("Aceptar");
    
    private AutoaceptarListener listener;
    
    public PanelAutoaceptar(Interfaz ventana_principal, SpinnerNumberModel spinner_model, 
                                            String mensaje_label, AutoaceptarListener listener ){
        
        this.ventana_principal = ventana_principal;
        this.auto_aceptar_spinner = new JSpinner(spinner_model);
        this.auto_aceptar_label_spinner = new JLabel(mensaje_label);
        this.listener = listener;        
        this.setLayout(new GridLayout(1,2,5,10));
        
        auto_aceptar_panel_izq.setLayout(new GridBagLayout());
        auto_aceptar_panel_izq_inf.setLayout(new GridBagLayout());
        auto_aceptar_panel_der.setLayout(new BorderLayout());
            
        auto_aceptar_panel_der.add(auto_aceptar_scroll_label_der,BorderLayout.CENTER);
            
        auto_aceptar_label_izq.setHorizontalAlignment(JLabel.CENTER);
        auto_aceptar_label_der.setHorizontalAlignment(JLabel.CENTER);
        
        auto_aceptar_label_izq.setIcon(new ImageIcon(ventana_principal.getImage()));
        
        auto_aceptar_checkbox.addItemListener(listener);
        auto_aceptar_spinner.addChangeListener(listener);
        auto_aceptar_boton.addActionListener(listener);
        
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
            
        auto_aceptar_panel_izq.add(auto_aceptar_scroll_label_izq,especif);
        
        especif.gridx = 0;
        especif.gridy = 1;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 0.05;
        especif.insets = new Insets(20,10,0,0);
        especif.fill = GridBagConstraints.BOTH;
        especif.anchor = GridBagConstraints.CENTER;
            
        auto_aceptar_panel_izq.add(auto_aceptar_panel_izq_inf,especif);
    
        // Empieza la configuracion del panel inferior izquierdo
        
        especif.gridx = 0;
        especif.gridy = 0;
        especif.gridwidth = 3;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 0.05;
        especif.insets = new Insets(0,20,3,0);
        especif.fill = GridBagConstraints.BOTH;
        especif.anchor = GridBagConstraints.SOUTH;
        
        auto_aceptar_panel_izq_inf.add(auto_aceptar_label_spinner,especif);
        
        especif.gridx = 0;
        especif.gridy = 1;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 2.0;  
        especif.weighty = 1.0;
        especif.insets = new Insets(0,20,0,0);
        especif.fill = GridBagConstraints.HORIZONTAL;
        especif.anchor = GridBagConstraints.NORTH;
        especif.ipady = 10;
        
        auto_aceptar_panel_izq_inf.add(auto_aceptar_spinner,especif);
        
        especif.gridx = 1;
        especif.gridy = 1;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 1.0;
        especif.insets = new Insets(0,30,0,0);
        especif.fill = GridBagConstraints.HORIZONTAL;
        especif.anchor = GridBagConstraints.NORTH;
        especif.ipady = 5;
        
        auto_aceptar_panel_izq_inf.add(auto_aceptar_boton,especif);
        
        especif.gridx = 2;
        especif.gridy = 1;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 0.25;  
        especif.weighty = 1.0;
        especif.insets = new Insets(0,10,20,0);
        especif.fill = GridBagConstraints.HORIZONTAL;
        especif.anchor = GridBagConstraints.NORTH;
        
        auto_aceptar_panel_izq_inf.add(auto_aceptar_checkbox,especif);
        
        especif.ipady = 0;
        
        this.add(auto_aceptar_panel_izq);
        this.add(auto_aceptar_panel_der);
        this.revalidate();
        
    }

    @Override
    public void poner_imagen_der(BufferedImage imagen) {  
        
        ventana_principal.setImageGuardar(imagen);  
        auto_aceptar_label_der.setIcon(new ImageIcon(imagen));
        ventana_principal.actualizar_interfaz();
    
    }
    
    @Override
    public void poner_imagen_izq(BufferedImage imagen) {

        auto_aceptar_label_izq.setIcon(new ImageIcon(imagen));
        auto_aceptar_label_der.setIcon(null);
        
    }
    
    
     public void boton_disponible(boolean disponible){
    
        auto_aceptar_boton.setEnabled(disponible);
        
     }
}
