
package Vista;

import Controlador.WarholMouseListener;
import ManipulacionImagenes.BibliotecaGrafica;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author rae
 */
public class PanelWarhol extends PanelEditorImagen{
    
    private Interfaz ventana_principal;
    private BibliotecaGrafica bg = new BibliotecaGrafica();
    
    private JPanel warhol_panel_izq; 
    private JPanel warhol_panel_der; 
    
    private JLabel warhol_label_1 = new JLabel();
    private JLabel warhol_label_2 = new JLabel();
    private JLabel warhol_label_3 = new JLabel();
    private JLabel warhol_label_4 = new JLabel();
    private JLabel warhol_label_der = new JLabel();
    
    private JScrollPane scroll_label_1 = new JScrollPane(warhol_label_1);
    private JScrollPane scroll_label_2 = new JScrollPane(warhol_label_2);
    private JScrollPane scroll_label_3 = new JScrollPane(warhol_label_3);
    private JScrollPane scroll_label_4 = new JScrollPane(warhol_label_4);
    private JScrollPane warhol_scroll_label_der = new JScrollPane(warhol_label_der);

    private JButton button_generar = new JButton("Generar");
    
    // Imagenes Warhol
    private BufferedImage[] imgs = {null,null,null,null};
    
    public PanelWarhol(Interfaz ventana_principal){
        
            warhol_label_1.setName("0");
            warhol_label_2.setName("1");
            warhol_label_3.setName("2");
            warhol_label_4.setName("3"); 
             
            WarholMouseListener warhol_listener = new WarholMouseListener(this,ventana_principal);

            
            this.ventana_principal = ventana_principal;

            this.setLayout(new GridLayout(1,2,5,10));

            warhol_panel_izq = new JPanel(new GridLayout(2,2,5,10));
            warhol_panel_der = new JPanel(new BorderLayout());

            warhol_panel_der.add(warhol_scroll_label_der, BorderLayout.CENTER);
            warhol_panel_der.add(button_generar, BorderLayout.PAGE_START);
            
            warhol_label_1.setHorizontalAlignment(JLabel.CENTER);
            warhol_label_2.setHorizontalAlignment(JLabel.CENTER);
            warhol_label_3.setHorizontalAlignment(JLabel.CENTER);
            warhol_label_4.setHorizontalAlignment(JLabel.CENTER);
            warhol_label_der.setHorizontalAlignment(JLabel.CENTER);

            imgs[0] = ventana_principal.getImage();
            imgs[1] = ventana_principal.getImage();
            imgs[2] = ventana_principal.getImage();
            imgs[3] = ventana_principal.getImage();

            warhol_label_1.setIcon(new ImageIcon(imgs[0]));
            warhol_label_2.setIcon(new ImageIcon(imgs[1]));
            warhol_label_3.setIcon(new ImageIcon(imgs[2]));
            warhol_label_4.setIcon(new ImageIcon(imgs[3]));
      
            warhol_label_1.addMouseListener(warhol_listener);
            warhol_label_2.addMouseListener(warhol_listener);
            warhol_label_3.addMouseListener(warhol_listener);
            warhol_label_4.addMouseListener(warhol_listener);
              
            warhol_panel_izq.add(scroll_label_1);
            warhol_panel_izq.add(scroll_label_2);
            warhol_panel_izq.add(scroll_label_3);
            warhol_panel_izq.add(scroll_label_4);
            
            button_generar.addActionListener((ActionEvent e) -> {
                
                poner_imagen_der(bg.pegar_imagenes(imgs[0], imgs[1], imgs[2], imgs[3]));
                
            });
            
            this.add(warhol_panel_izq);
            this.add(warhol_panel_der);
            this.revalidate();

        
    }
    
    public void poner_imagen_warhol(int i, BufferedImage imagen){    
        JLabel[] labels = {warhol_label_1,warhol_label_2,warhol_label_3,warhol_label_4};
        imgs[i] = imagen; 
        labels[i].setIcon(new ImageIcon(imgs[i]));
    }
   
    @Override
    public void poner_imagen_der(BufferedImage imagen){
    
        ventana_principal.setImageGuardar(imagen);  
        warhol_label_der.setIcon(new ImageIcon(imagen));
    
    }

    @Override
    public void poner_imagen_izq(BufferedImage imagen) {
        
        imgs[0] = ventana_principal.getImage();
        imgs[1] = ventana_principal.getImage();
        imgs[2] = ventana_principal.getImage();
        imgs[3] = ventana_principal.getImage();

        warhol_label_1.setIcon(new ImageIcon(imgs[0]));
        warhol_label_2.setIcon(new ImageIcon(imgs[1]));
        warhol_label_3.setIcon(new ImageIcon(imgs[2]));
        warhol_label_4.setIcon(new ImageIcon(imgs[3]));
        
        warhol_label_der.setIcon(null);
        
        
    }
    
       
}
