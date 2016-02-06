
package Vista;

import ManipulacionImagenes.BibliotecaGrafica;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author rae
 */
public class PanelBasico extends JPanel {
    
    Interfaz ventana_principal;
    BibliotecaGrafica bg = new BibliotecaGrafica();
    
    JLabel img_izq = new JLabel();
    JLabel img_der = new JLabel();
    JScrollPane scroll_img_izq = new JScrollPane(img_izq);
    JScrollPane scroll_img_der = new JScrollPane(img_der);
    
    BufferedImage basico_img_izq;
    
    public PanelBasico(Interfaz ventana_principal){
                
            this.ventana_principal = ventana_principal;
        
            this.setLayout(new GridLayout(1,2,5,10));
            
            img_izq.setHorizontalAlignment(JLabel.CENTER);
            img_der.setHorizontalAlignment(JLabel.CENTER);
            
            try {
                
            basico_img_izq = ImageIO.read(ventana_principal.getFile());
            img_izq.setIcon(new ImageIcon(basico_img_izq));
            
            } catch (IOException ex) {
                System.out.println("Error al cargar imagen");
            }
            
            this.add(scroll_img_izq);
            this.add(scroll_img_der);
     
        
    }
    
    public void poner_imagen_der(BufferedImage imagen){
    
        ventana_principal.setImageGuardar(imagen);  
        img_der.setIcon(new ImageIcon(imagen));
    
    }
    
}
