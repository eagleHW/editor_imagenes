/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;

import Controlador.FotomosaicoListener;
import ManipulacionImagenes.BibliotecaGrafica;
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
public class PanelFotomosaico extends PanelEditorImagen {
    
    private Interfaz ventana_principal;
    private BibliotecaGrafica bg = new BibliotecaGrafica();
    
    private JLabel fotomosaico_label_der = new JLabel();
    private JLabel fotomosaico_label_izq = new JLabel();
    
    private JScrollPane fotomosaico_scroll_image_der = new JScrollPane(fotomosaico_label_der);
    private JScrollPane fotomosaico_scroll_image_izq = new JScrollPane(fotomosaico_label_izq);
    
    private JPanel fotomosaico_panel_izq = new JPanel();
    private JPanel fotomosaico_panel_der = new JPanel(); 
    private JPanel fotomosaico_panel_spinner = new JPanel();
    
    private JLabel fotomosaico_spinner_label_tamaño_ventana_x = new JLabel("Tamaño ventana X : ");
    private SpinnerModel fotomosaico_spinner_model_tamaño_ventana_x = new SpinnerNumberModel(1,1,20,1);
    private JSpinner fotomosaico_spinner_tamaño_ventana_x = new JSpinner(fotomosaico_spinner_model_tamaño_ventana_x);
    
    private JLabel fotomosaico_spinner_label_tamaño_ventana_y = new JLabel("Tamaño ventana Y : ");
    private SpinnerModel fotomosaico_spinner_model_tamaño_ventana_y = new SpinnerNumberModel(1,1,20,1);
    private JSpinner fotomosaico_spinner_tamaño_ventana_y = new JSpinner(fotomosaico_spinner_model_tamaño_ventana_y);
    
    private JLabel fotomosaico_spinner_label_tamaño_resultado_x = new JLabel("Tamaño resultado X : ");
    private SpinnerModel fotomosaico_spinner_model_tamaño_resultado_x = new SpinnerNumberModel(1,1,50,1);
    private JSpinner fotomosaico_spinner_tamaño_resultado_x = new JSpinner(fotomosaico_spinner_model_tamaño_resultado_x);
    
    private JLabel fotomosaico_spinner_label_tamaño_resultado_y = new JLabel("Tamaño resultado Y : ");
    private SpinnerModel fotomosaico_spinner_model_tamaño_resultado_y = new SpinnerNumberModel(1,1,50,1); 
    private JSpinner fotomosaico_spinner_tamaño_resultado_y = new JSpinner(fotomosaico_spinner_model_tamaño_resultado_y);
    
    private JButton fotomosaico_boton = new JButton("Generar");
    
    private FotomosaicoListener fotomosaico_listener;
    
    public PanelFotomosaico(Interfaz ventana_principal){
    
        this.ventana_principal = ventana_principal;        
        this.fotomosaico_listener = new FotomosaicoListener(this,ventana_principal);
        
        this.setLayout(new GridLayout(1,2,5,10));
    
        fotomosaico_panel_izq.setLayout(new GridBagLayout());
        fotomosaico_panel_der.setLayout(new BorderLayout());
        fotomosaico_panel_spinner.setLayout(new GridBagLayout());
        
        fotomosaico_panel_der.add(fotomosaico_scroll_image_der);
        
        fotomosaico_label_izq.setIcon(new ImageIcon(ventana_principal.getImage()));
        
        fotomosaico_label_izq.setHorizontalAlignment(JLabel.CENTER);
        fotomosaico_label_der.setHorizontalAlignment(JLabel.CENTER);
        
        fotomosaico_spinner_tamaño_ventana_x.setName("tamaño_ventana_x");
        fotomosaico_spinner_tamaño_ventana_y.setName("tamaño_ventana_y");
        fotomosaico_spinner_tamaño_resultado_x.setName("tamaño_resultado_x");
        fotomosaico_spinner_tamaño_resultado_y.setName("tamaño_resultado_y");
        
        fotomosaico_spinner_tamaño_ventana_x.addChangeListener(fotomosaico_listener);
        fotomosaico_spinner_tamaño_ventana_y.addChangeListener(fotomosaico_listener);
        fotomosaico_spinner_tamaño_resultado_x.addChangeListener(fotomosaico_listener);
        fotomosaico_spinner_tamaño_resultado_y.addChangeListener(fotomosaico_listener);
        
        fotomosaico_boton.addActionListener(fotomosaico_listener);
        
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
        fotomosaico_panel_izq.add(fotomosaico_scroll_image_izq,especif);
        
        especif.gridx = 0;
        especif.gridy = 1;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 0.25;
        especif.insets = new Insets(20,10,0,0);
        especif.fill = GridBagConstraints.BOTH;
        especif.anchor = GridBagConstraints.CENTER;
        fotomosaico_panel_izq.add(fotomosaico_panel_spinner,especif);
        
        // Empieza la configuracion del panel de spinners
        
        especif.gridx = 0;
        especif.gridy = 0;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 0.15;
        especif.insets = new Insets(20,10,0,10);
        especif.fill = GridBagConstraints.HORIZONTAL;
        especif.anchor = GridBagConstraints.CENTER;
        fotomosaico_panel_spinner.add(fotomosaico_spinner_label_tamaño_ventana_x,especif);
        
        especif.gridx = 0;
        especif.gridy = 1;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 1.0;
        especif.insets = new Insets(20,10,0,10);
        especif.fill = GridBagConstraints.HORIZONTAL;
        especif.anchor = GridBagConstraints.CENTER;  
        fotomosaico_panel_spinner.add(fotomosaico_spinner_tamaño_ventana_x,especif);
        
        especif.gridx = 0;
        especif.gridy = 2;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 0.15;
        especif.insets = new Insets(20,10,0,10);
        especif.fill = GridBagConstraints.HORIZONTAL;
        especif.anchor = GridBagConstraints.CENTER; 
        fotomosaico_panel_spinner.add(fotomosaico_spinner_label_tamaño_ventana_y,especif);
        
        especif.gridx = 0;
        especif.gridy = 3;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 1.0;
        especif.insets = new Insets(20,10,0,10);
        especif.fill = GridBagConstraints.HORIZONTAL;
        especif.anchor = GridBagConstraints.CENTER;  
        fotomosaico_panel_spinner.add(fotomosaico_spinner_tamaño_ventana_y,especif);
        
        especif.gridx = 1;
        especif.gridy = 0;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 0.15;
        especif.insets = new Insets(20,10,0,10);
        especif.fill = GridBagConstraints.HORIZONTAL;
        especif.anchor = GridBagConstraints.CENTER;  
        fotomosaico_panel_spinner.add(fotomosaico_spinner_label_tamaño_resultado_x,especif);
        
        especif.gridx = 1;
        especif.gridy = 1;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 1.0;
        especif.insets = new Insets(20,10,0,10);
        especif.fill = GridBagConstraints.HORIZONTAL;
        especif.anchor = GridBagConstraints.CENTER;  
        fotomosaico_panel_spinner.add(fotomosaico_spinner_tamaño_resultado_x,especif);
        
        especif.gridx = 1;
        especif.gridy = 2;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 0.15;
        especif.insets = new Insets(20,10,0,10);
        especif.fill = GridBagConstraints.HORIZONTAL;
        especif.anchor = GridBagConstraints.CENTER;  
        fotomosaico_panel_spinner.add(fotomosaico_spinner_label_tamaño_resultado_y,especif);
        
        especif.gridx = 1;
        especif.gridy = 3;
        especif.gridwidth = 1;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 1.0;
        especif.insets = new Insets(20,10,0,10);
        especif.fill = GridBagConstraints.HORIZONTAL;
        especif.anchor = GridBagConstraints.CENTER;  
        fotomosaico_panel_spinner.add(fotomosaico_spinner_tamaño_resultado_y,especif);
        
        especif.gridx = 0;
        especif.gridy = 4;
        especif.gridwidth = 2;
        especif.gridheight = 1;
        especif.weightx = 1.0;  
        especif.weighty = 1.0;
        especif.insets = new Insets(20,10,20,10);
        especif.fill = GridBagConstraints.NONE;
        especif.anchor = GridBagConstraints.CENTER;  
        fotomosaico_panel_spinner.add(fotomosaico_boton,especif);
        
        this.add(fotomosaico_panel_izq);
        this.add(fotomosaico_panel_der);
        this.revalidate();
        
    }

    @Override
    public void poner_imagen_der(BufferedImage imagen) {
   
        ventana_principal.setImageGuardar(imagen);  
        fotomosaico_label_der.setIcon(new ImageIcon(imagen));
        ventana_principal.actualizar_interfaz();
    
    }

    @Override
    public void poner_imagen_izq(BufferedImage imagen) {
        fotomosaico_label_izq.setIcon(new ImageIcon(imagen));
        fotomosaico_label_der.setIcon(null);
    }
    
    
    
    
    
}
