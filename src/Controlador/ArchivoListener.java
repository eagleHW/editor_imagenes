
package Controlador;

import Vista.Interfaz;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author rae
 */
public class ArchivoListener implements ActionListener{
    
    private final Interfaz ventana_principal;
    
    public ArchivoListener(Interfaz ventana_principal){
        this.ventana_principal = ventana_principal;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        int user_selection; // Para verificar si el usuario escogio un archivo.
        
    switch(e.getActionCommand()){
       
        case "Nueva imagen":
       
            // FileFilter filter = new FileNameExtensionFilter("JPEG file", "jpg", "jpeg");
            // fileChooser.addChoosableFileFilter(filter);
            JFileChooser open_file = new JFileChooser();   
            FileFilter imageFilter = new FileNameExtensionFilter(
                    "Image files", ImageIO.getReaderFileSuffixes());
            open_file.removeChoosableFileFilter(open_file.getFileFilter());
            open_file.addChoosableFileFilter(imageFilter);
            
            user_selection = open_file.showOpenDialog(null);
            
             if(user_selection == JFileChooser.APPROVE_OPTION) {
                
                 ventana_principal.poner_imagen_izq(open_file.getSelectedFile());
                 System.out.println(open_file.getSelectedFile());
             
             }
            break;
            
        case "Guardar":
            JFileChooser save_file = new JFileChooser();  
            FileFilter filter = new FileNameExtensionFilter("JPEG file", "jpg", "jpeg");

            save_file.removeChoosableFileFilter(save_file.getFileFilter());
            save_file.addChoosableFileFilter(filter);
            
            user_selection = save_file.showSaveDialog(null);
            
            if(user_selection == JFileChooser.APPROVE_OPTION) {
            
                File fileToSave = save_file.getSelectedFile();
                 
        try {
                ImageIO.write(ventana_principal.getImageGuardar(),"jpg",fileToSave);
       
        } catch (IOException ex) {
          Logger.getLogger(ArchivoListener.class.getName()).log(Level.SEVERE, null, ex);
        }
                 
            }    
            break;
            
        case "Salir" :
            System.exit(0);
            break;
        
        default :  
       
          
    }
        
        

    
    }
    
    
}
