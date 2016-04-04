
package Controlador;

import Vista.Interfaz;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
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
                 
                 ventana_principal.setFile(open_file.getSelectedFile());
                 ventana_principal.getPanelPrincipal().poner_imagen_izq(ventana_principal.getImage());
                 ventana_principal.enable_menu_filtros(true);
             
             }
            break;
            
        case "Guardar":
            JFileChooser save_file = new JFileChooser();  
            FileFilter filter = new FileNameExtensionFilter("JPEG file", "jpg", "jpeg");
            int confirmacion = 0;
            
            save_file.removeChoosableFileFilter(save_file.getFileFilter());
            save_file.addChoosableFileFilter(filter);
            
            user_selection = save_file.showSaveDialog(null);
            
            if(user_selection == JFileChooser.APPROVE_OPTION) {
            
                File fileToSave = save_file.getSelectedFile();
                 
                try {

                    if(Files.exists(fileToSave.toPath(), LinkOption.NOFOLLOW_LINKS)){
                        
                        confirmacion = JOptionPane.showConfirmDialog(null, "Ya existe un archivo con este nombre\n¿Deseas sobreescribirlo?",
                                "Advertencia", JOptionPane.YES_NO_OPTION );
                        
                        
                        
                        if(confirmacion == JOptionPane.YES_OPTION){
                            
                            ImageIO.write(ventana_principal.getImageGuardar(),"jpg",fileToSave);
                            
                        }
                        
                    }else{
                    
                        ImageIO.write(ventana_principal.getImageGuardar(),"jpg",fileToSave);
                        
                    }
                    
                } catch (IOException ex) {

                    JOptionPane.showMessageDialog(null, "Error al guardar la imagen", 
                                                            "Error", JOptionPane.ERROR_MESSAGE);

                    System.out.println("Error al guardar la imagen - ArchivoListener.java");

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
