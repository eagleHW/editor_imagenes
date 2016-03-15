/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;

import Controlador.AutoaceptarListener;
import Controlador.ReduccionProporcionListener;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author rae
 */
public class PanelReduccionProporcion extends PanelEditorImagen {

    private Interfaz ventana_principal;
    
    private String[] proporciones = {"4:1", "16:1", "64:1"};
    private int[] valores = {2,4,8};
    
    private JPanel reduccion_proporcion_panel_izq = new JPanel();
    private JPanel reduccion_proporcion_panel_izq_inf = new JPanel();
    private JPanel reduccion_proporcion_panel_der = new JPanel();
    
    private JLabel reduccion_proporcion_label_izq = new JLabel();
    private JLabel reduccion_proporcion_label_der = new JLabel();

    private JScrollPane reduccion_proporcion_scroll_label_izq = new JScrollPane(reduccion_proporcion_label_izq);
    private JScrollPane reduccion_proporcion_scroll_label_der = new JScrollPane(reduccion_proporcion_label_der);
   
    private JLabel reduccion_proporcion_label_combobox = new JLabel("Proporción : ");
    JComboBox reduccion_proporcion_combobox = new JComboBox(proporciones);
    
    private JButton reduccion_proporcion_boton = new JButton("Aceptar");
    
    private ReduccionProporcionListener reduccion_proporcion_listener;
    
    public PanelReduccionProporcion(Interfaz ventana_principal){
        
        this.ventana_principal = ventana_principal;
        this.setLayout(new GridLayout(1,2,5,10));
        this.reduccion_proporcion_listener = new ReduccionProporcionListener(this,ventana_principal);
        
        reduccion_proporcion_panel_izq.setLayout(new GridBagLayout());
        reduccion_proporcion_panel_izq_inf.setLayout(new GridBagLayout());
        reduccion_proporcion_panel_der.setLayout(new BorderLayout());
            
        reduccion_proporcion_panel_der.add(reduccion_proporcion_scroll_label_der,BorderLayout.CENTER);
            
        reduccion_proporcion_label_izq.setHorizontalAlignment(JLabel.CENTER);
        reduccion_proporcion_label_der.setHorizontalAlignment(JLabel.CENTER);
        
        reduccion_proporcion_label_izq.setIcon(new ImageIcon(ventana_principal.getImage()));
        
        reduccion_proporcion_combobox.addItemListener(reduccion_proporcion_listener);
        reduccion_proporcion_boton.addActionListener(reduccion_proporcion_listener);
        
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
            
        reduccion_proporcion_panel_izq.add(reduccion_proporcion_scroll_label_izq,especif);
        
        especif.gridx = 0;
        especif.gridy = 1;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 0.05;
        especif.insets = new Insets(20,10,0,0);
        especif.fill = GridBagConstraints.BOTH;
        especif.anchor = GridBagConstraints.CENTER;
            
        reduccion_proporcion_panel_izq.add(reduccion_proporcion_panel_izq_inf,especif);
    
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
        
        reduccion_proporcion_panel_izq_inf.add(reduccion_proporcion_label_combobox,especif);
        
        especif.gridx = 0;
        especif.gridy = 1;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 1.0;
        especif.insets = new Insets(0,20,0,0);
        especif.fill = GridBagConstraints.HORIZONTAL;
        especif.anchor = GridBagConstraints.NORTH;
        
        reduccion_proporcion_panel_izq_inf.add(reduccion_proporcion_combobox,especif);
        
        especif.gridx = 1;
        especif.gridy = 1;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 1.0;
        especif.insets = new Insets(0,30,20,10);
        especif.fill = GridBagConstraints.HORIZONTAL;
        especif.anchor = GridBagConstraints.NORTH;
        
        reduccion_proporcion_panel_izq_inf.add(reduccion_proporcion_boton,especif);
        
        this.add(reduccion_proporcion_panel_izq);
        this.add(reduccion_proporcion_panel_der);
        this.revalidate();
        
    }

    @Override
    public void poner_imagen_der(BufferedImage imagen) {  
        
        ventana_principal.setImageGuardar(imagen);  
        reduccion_proporcion_label_der.setIcon(new ImageIcon(imagen));
        ventana_principal.actualizar_interfaz();
    
    }
    
    @Override
    public void poner_imagen_izq(BufferedImage imagen) {

        reduccion_proporcion_label_izq.setIcon(new ImageIcon(imagen));
        reduccion_proporcion_label_der.setIcon(null);
        
    }
    
    
     public void boton_disponible(boolean disponible){
    
        reduccion_proporcion_boton.setEnabled(disponible);
        
     }
    
}
