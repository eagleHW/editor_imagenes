
package Vista;

import Controlador.WarholMouseListener;
import ManipulacionImagenes.BibliotecaGrafica;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author rae
 */
public class PanelWarhol extends JPanel{
    
    Interfaz ventana_principal;
    BibliotecaGrafica bg = new BibliotecaGrafica();
    
    boolean warhol_bool = false;
    
    JPanel warhol_panel_izq; 
    JPanel warhol_panel_der; 
    
    JLabel warhol_label_1 = new JLabel();
    JLabel warhol_label_2 = new JLabel();
    JLabel warhol_label_3 = new JLabel();
    JLabel warhol_label_4 = new JLabel();
    JLabel warhol_label_der = new JLabel();
    
    JScrollPane scroll_label_1 = new JScrollPane(warhol_label_1);
    JScrollPane scroll_label_2 = new JScrollPane(warhol_label_2);
    JScrollPane scroll_label_3 = new JScrollPane(warhol_label_3);
    JScrollPane scroll_label_4 = new JScrollPane(warhol_label_4);
    JScrollPane warhol_scroll_label_der = new JScrollPane(warhol_label_der);

    JButton button_generar = new JButton("Generar");
    
    // Imagenes Warhol
    BufferedImage img0,img1,img2,img3;
    
    public PanelWarhol(Interfaz ventana_principal){
        
         if(warhol_bool != true){

            warhol_label_1.setName("0");
            warhol_label_2.setName("1");
            warhol_label_3.setName("2");
            warhol_label_4.setName("3"); 
             
            WarholMouseListener warhol_listener = new WarholMouseListener(this,ventana_principal);

            warhol_bool = true;
            
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

            try {

              img0 = ImageIO.read(ventana_principal.getFile());
              img1 = ImageIO.read(ventana_principal.getFile());
              img2 = ImageIO.read(ventana_principal.getFile());
              img3 = ImageIO.read(ventana_principal.getFile());

              warhol_label_1.setIcon(new ImageIcon(img0));
              warhol_label_2.setIcon(new ImageIcon(img1));
              warhol_label_3.setIcon(new ImageIcon(img2));
              warhol_label_4.setIcon(new ImageIcon(img3));
      
              warhol_label_1.addMouseListener(warhol_listener);
              warhol_label_2.addMouseListener(warhol_listener);
              warhol_label_3.addMouseListener(warhol_listener);
              warhol_label_4.addMouseListener(warhol_listener);
              
              
            } catch (IOException ex) {
                System.out.println("Error al cargar imagen");
            }

            warhol_panel_izq.add(scroll_label_1);
            warhol_panel_izq.add(scroll_label_2);
            warhol_panel_izq.add(scroll_label_3);
            warhol_panel_izq.add(scroll_label_4);
            
            button_generar.addActionListener((ActionEvent e) -> {
                
                poner_imagen_der(bg.pegar_imagenes(img0, img1, img2, img3));
                
            });
            
            this.add(warhol_panel_izq);
            this.add(warhol_panel_der);
            this.revalidate();

        }
        
       
        
    }
    
    public void poner_imagen_warhol(int i, BufferedImage imagen){    
        JLabel[] imgs = {warhol_label_1,warhol_label_2,warhol_label_3,warhol_label_4};
        imgs[i].setIcon(new ImageIcon(imagen));
    }
   
    public void poner_imagen_der(BufferedImage imagen){
    
        ventana_principal.setImageGuardar(imagen);  
        warhol_label_der.setIcon(new ImageIcon(imagen));
    
    }
    
    
    
}