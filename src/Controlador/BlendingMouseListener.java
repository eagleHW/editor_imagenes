
package Controlador;

import Vista.PanelBlending;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author rae
 */
public class BlendingMouseListener implements MouseListener{

    PanelBlending panel_blending;
    int user_selection; // Para verificar si el usuario escogio un archivo. 
    
    public BlendingMouseListener(PanelBlending panel_blending){
        this.panel_blending = panel_blending;     
    }
   
    @Override
    public void mouseClicked(MouseEvent e) {
    
        JFileChooser open_file = new JFileChooser();   
        FileFilter imageFilter = new FileNameExtensionFilter(
                    "Image files", ImageIO.getReaderFileSuffixes());
        open_file.removeChoosableFileFilter(open_file.getFileFilter());
        open_file.addChoosableFileFilter(imageFilter);
            
        user_selection = open_file.showOpenDialog(null);
            
         if(user_selection == JFileChooser.APPROVE_OPTION) {
            
             panel_blending.poner_imagen_izq_inf_blending(open_file.getSelectedFile());
             panel_blending.enable_blending_slider();
             System.out.println(open_file.getSelectedFile());
            
         }
    
        
         
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
}
