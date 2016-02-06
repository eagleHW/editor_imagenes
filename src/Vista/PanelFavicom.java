
package Vista;

import ManipulacionImagenes.BibliotecaGrafica;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author rae
 */
public class PanelFavicom extends JPanel{
    
    Interfaz ventana_principal;
    BibliotecaGrafica bg = new BibliotecaGrafica();
    
    boolean favicom_bool = false;
    
    JPanel favicom_panel_izq_inf_radio = new JPanel();
    JPanel favicom_panel_izq_inf = new JPanel();
    JPanel favicom_panel_der = new JPanel();
    JPanel favicom_panel_izq = new JPanel(); 
    
    JLabel favicom_label_izq_sup = new JLabel();
    JLabel favicom_label_izq_inf = new JLabel();
    JLabel favicom_label_der = new JLabel();
    
    JScrollPane favicom_scroll_label_izq_sup = new JScrollPane(favicom_label_izq_sup);
    JScrollPane favicom_scroll_label_izq_inf = new JScrollPane(favicom_label_izq_inf);
    JScrollPane favicom_scroll_label_der = new JScrollPane(favicom_label_der);
    
    JRadioButton izq_sup = new JRadioButton("Esquina superior izquierda",true);
    JRadioButton der_sup = new JRadioButton("Esquina superior derecha",false);
    JRadioButton izq_inf = new JRadioButton("Esquina inferior izquierda",false);
    JRadioButton der_inf = new JRadioButton("Esquina inferior derecha",false);
    ButtonGroup opciones = new ButtonGroup();
    
    JButton favicom_boton = new JButton("Aceptar");
    
    JLabel favicom_spinner_label = new JLabel("Nivel de transparencia (%)");
    SpinnerModel favicom_spinner_model = new SpinnerNumberModel(0,0,100,1);
    JSpinner favicom_spinner = new JSpinner(favicom_spinner_model);
    
    //Imagenes Favicom
    BufferedImage favicom_img_izq_sup, favicom_img_izq_inf;
    
    public PanelFavicom(Interfaz ventana_principal){
        
    if(favicom_bool != true){

            this.ventana_principal = ventana_principal;
        
            this.setLayout(new GridLayout(1,2,5,10));

            favicom_bool = true;
            
            favicom_spinner_label.setHorizontalAlignment(JLabel.CENTER);
            
            favicom_panel_izq = new JPanel(new GridBagLayout()); 
            favicom_panel_der = new JPanel(new BorderLayout());
            
            favicom_panel_der.add(favicom_scroll_label_der,BorderLayout.CENTER);
            
            opciones.add(izq_sup);
            opciones.add(der_sup);
            opciones.add(izq_inf);
            opciones.add(der_inf);
            
            favicom_label_izq_sup.setHorizontalAlignment(JLabel.CENTER);
            favicom_label_izq_inf.setHorizontalAlignment(JLabel.CENTER);
            favicom_label_der.setHorizontalAlignment(JLabel.CENTER);
            
            try{
                
                favicom_img_izq_sup = ImageIO.read(ventana_principal.getFile());
                favicom_img_izq_inf = ImageIO.read(ventana_principal.getFile());
                        
                favicom_label_izq_sup.setIcon(new ImageIcon(favicom_img_izq_sup));
                favicom_label_izq_inf.setIcon(new ImageIcon(favicom_img_izq_inf));
                
            }catch(IOException ex) {
                System.out.println("Error al cargar imagen"); 
            }
            
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
            
            
            favicom_panel_izq.add(favicom_scroll_label_izq_sup,especif);
            
            especif.gridx = 0;
            especif.gridy = 1;
            especif.gridwidth = 1;
            especif.gridheight = 1;
            especif.weightx = 1.0;  
            especif.weighty = 0.5;
            especif.insets = new Insets(20,10,0,0);
            especif.fill = GridBagConstraints.BOTH;
            especif.anchor = GridBagConstraints.CENTER;
            especif.ipadx = 0;
            
            favicom_panel_izq.add(favicom_panel_izq_inf,especif);
        
            especif.gridx = 0;
            especif.gridy = 2;
            especif.gridwidth = 1;
            especif.gridheight = 1;
            especif.weightx = 1.0;  
            especif.weighty = 0.10;
            especif.insets = new Insets(20,10,20,0);
            especif.fill = GridBagConstraints.NONE;
            especif.anchor = GridBagConstraints.CENTER;
            
            
            favicom_panel_izq.add(favicom_boton,especif);
            
            // Aqui empieza la comfiguracion del subpanel inferior del lado izquierdo
            
            favicom_panel_izq_inf.setLayout(new GridBagLayout());
            
            
            favicom_panel_izq_inf_radio.setLayout(new GridLayout(6,1));
            favicom_panel_izq_inf_radio.add(favicom_spinner_label);
            favicom_panel_izq_inf_radio.add(favicom_spinner);
            favicom_panel_izq_inf_radio.add(izq_sup);
            favicom_panel_izq_inf_radio.add(der_sup);
            favicom_panel_izq_inf_radio.add(izq_inf);
            favicom_panel_izq_inf_radio.add(der_inf);
            
            
           
            especif.gridx = 0;
            especif.gridy = 0;
            especif.gridwidth = 2;
            especif.gridheight = 1;
            especif.weightx = 1.0;  
            especif.weighty = 1.0;
            //especif.insets = new Insets(20,10,0,0);
            especif.fill = GridBagConstraints.BOTH;
            especif.anchor = GridBagConstraints.CENTER;
            
             
            favicom_panel_izq_inf.add(favicom_scroll_label_izq_inf,especif);
            
            especif.gridx = 2;
            especif.gridy = 0;
            especif.gridwidth = 1;
            especif.gridheight = 1;
            especif.weightx = 0.1;  
            especif.weighty = 1.0;
            //especif.insets = new Insets(20,10,0,0);
            especif.fill = GridBagConstraints.VERTICAL;
            especif.anchor = GridBagConstraints.CENTER;
            
            
            favicom_panel_izq_inf.add(favicom_panel_izq_inf_radio,especif);
            
            
            this.add(favicom_panel_izq);
            this.add(favicom_panel_der);
            this.revalidate();
        
        }
        

        
    }
    
}
