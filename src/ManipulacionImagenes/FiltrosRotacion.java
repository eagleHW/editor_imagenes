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
    
    public BufferedImage filtro_rotacion90(File file_imagen) throws IOException{
        
        BufferedImage imagen_original;
        
            imagen_original = ImageIO.read(file_imagen);
       
            BufferedImage imagen_creada = new BufferedImage(imagen_original.getHeight(),
                                                            imagen_original.getWidth(),BufferedImage.TYPE_INT_RGB);
            int heigth = imagen_original.getHeight();
            int width = imagen_original.getWidth();
        
            int rgb;
        
            for(int x = 0; x < width; x++){ 
                for(int y = 0; y < heigth; y++){
                    
                    rgb = imagen_original.getRGB(x, y);
                    imagen_creada.setRGB(y, (width-1) - x , rgb);
                
                }
            }
            
            return imagen_creada;
    
    }
    
    public BufferedImage filtro_rotacion180(File file_imagen) throws IOException{
        
         BufferedImage imagen_original;
        
            imagen_original = ImageIO.read(file_imagen);
       
            BufferedImage imagen_creada = new BufferedImage(imagen_original.getWidth(),
                                                            imagen_original.getHeight(),BufferedImage.TYPE_INT_RGB);
            int heigth = imagen_original.getHeight();
            int width = imagen_original.getWidth();
        
            int rgb;
        
            for(int x = 0; x < width; x++){ 
                for(int y = 0; y < heigth; y++){
                    
                    rgb = imagen_original.getRGB(x, y);
                    imagen_creada.setRGB(x, (heigth -1) - y , rgb);
                
                }
            }
            
            return imagen_creada;
        
    }

    public BufferedImage filtro_rotacion270(File file_imagen) throws IOException{
    
          BufferedImage imagen_original;
        
            imagen_original = ImageIO.read(file_imagen);
       
            BufferedImage imagen_creada = new BufferedImage(imagen_original.getHeight(),
                                                            imagen_original.getWidth(),BufferedImage.TYPE_INT_RGB);
            int heigth = imagen_original.getHeight();
            int width = imagen_original.getWidth();
        
            int rgb;
        
            for(int x = 0; x < width; x++){ 
                for(int y = 0; y < heigth; y++){
                    
                    rgb = imagen_original.getRGB(x, y);
                    imagen_creada.setRGB(  (heigth -1) - y  , x , rgb);
                
                }
            }
            
            return imagen_creada;
        
    
    }
    
}