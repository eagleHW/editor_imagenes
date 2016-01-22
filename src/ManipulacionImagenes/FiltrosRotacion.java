/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManipulacionImagenes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author rae
 */
public class FiltrosRotacion {
    
    BibliotecaGrafica bg = new BibliotecaGrafica();
    
    public FiltrosRotacion(){}
    
    public BufferedImage filtro_rotacion90(File file_imagen){
        
        BufferedImage imagen_original;
        try {
            imagen_original = ImageIO.read(file_imagen);
       
            BufferedImage imagen_creada = new BufferedImage(imagen_original.getHeight(),
                                                            imagen_original.getWidth(),BufferedImage.TYPE_INT_ARGB);
            int heigth = imagen_original.getHeight();
            int width = imagen_original.getWidth();
        
            int argb;
        
            for(int x = 0; x < width; x++){ 
                for(int y = 0; y < heigth; y++){
                    
                    argb = imagen_original.getRGB(x, y);
                    imagen_creada.setRGB(x, y, argb);
                
                }
            }
        
        }  catch (IOException ex) {
            Logger.getLogger(FiltrosRotacion.class.getName()).log(Level.SEVERE, null, ex);
       }
       
        return null;
        
    }
}