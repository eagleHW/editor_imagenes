
package Vista;

import ManipulacionImagenes.BibliotecaGrafica;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 *
 * @author rae
 */
public class PanelBasico extends PanelEditorImagen{
    
    private Interfaz ventana_principal;
    private BibliotecaGrafica bg = new BibliotecaGrafica();
    
    private JLabel img_izq = new JLabel();
    private JLabel img_der = new JLabel();
    private JScrollPane scroll_img_izq = new JScrollPane(img_izq);
    private JScrollPane scroll_img_der = new JScrollPane(img_der);
    
    private BufferedImage basico_img_izq;
    
    public PanelBasico(Interfaz ventana_principal){
                
            this.ventana_principal = ventana_principal;
        
            this.setLayout(new GridLayout(1,2,5,10));
            
            img_izq.setHorizontalAlignment(JLabel.CENTER);
            img_der.setHorizontalAlignment(JLabel.CENTER);
            
            if(ventana_principal.getImage() != null){
                
                img_izq.setIcon(new ImageIcon(ventana_principal.getImage()));
            
            }
      
            this.add(scroll_img_izq);
            this.add(scroll_img_der);
     
        
    }
    
    @Override
    public void poner_imagen_der(BufferedImage imagen){
        
        ventana_principal.setImageGuardar(imagen);  
        img_der.setIcon(new ImageIcon(imagen));
    
    }

    @Override
    public void poner_imagen_izq(BufferedImage imagen) {
    
        img_izq.setIcon(new ImageIcon(imagen));
        img_der.setIcon(null);
        
    }
    
}
