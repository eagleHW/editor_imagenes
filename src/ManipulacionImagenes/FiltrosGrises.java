/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManipulacionImagenes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author rae
 */
public class FiltrosGrises {
    
    private BibliotecaGrafica bg = new BibliotecaGrafica();
    
    public FiltrosGrises(){}
    
    public BufferedImage filtro_promedio(File file_imagen) throws IOException{
      
        BufferedImage imagen = ImageIO.read(file_imagen);
        
        int heigth = imagen.getHeight();
        int width = imagen.getWidth();
        
        int argb;
        int alpha;
        int red;
        int green;
        int blue;
        
        int promedio;
        
        for( int x = 0; x < width; x ++){     
            for(int y = 0; y < heigth; y++){
    
                argb = imagen.getRGB(x, y);
                
                alpha = bg.getAlphaNum(argb); 
                red = bg.getRedNum(argb);
                green = bg.getGreenNum(argb);
                blue = bg.getBlueNum(argb);
                
                promedio = (red + green + blue) / 3;
                
                imagen.setRGB(x, y, bg.getARGBNum(alpha,promedio,promedio,promedio));
                
            }
        }
        
        return imagen;
    }
    
    public BufferedImage filtro_luminicensia(File file_imagen) throws IOException{
      
        BufferedImage imagen = ImageIO.read(file_imagen);
        
        int heigth = imagen.getHeight();
        int width = imagen.getWidth();
        
        int argb;
        int alpha;
        int red;
        int green;
        int blue;
        
        int ponderacion;
        
        for( int x = 0; x < width; x ++){     
            for(int y = 0; y < heigth; y++){
    
                argb = imagen.getRGB(x, y);
                
                alpha = bg.getAlphaNum(argb); 
                red = bg.getRedNum(argb);
                green = bg.getGreenNum(argb);
                blue = bg.getBlueNum(argb);
                
                ponderacion = (int) ((red*0.3) + (green*0.59) + (blue*0.11));
                
                imagen.setRGB(x, y, bg.getARGBNum(alpha,ponderacion,ponderacion,ponderacion));
                
            }
        }
        
        return imagen;
    }

    public BufferedImage filtro_desaturacion(File file_imagen) throws IOException{
      
        BufferedImage imagen = ImageIO.read(file_imagen);
        
        int heigth = imagen.getHeight();
        int width = imagen.getWidth();
        
        int argb;
        int alpha;
        int red;
        int green;
        int blue;
        
        int promedio;
        
        for( int x = 0; x < width; x ++){     
            for(int y = 0; y < heigth; y++){
    
                argb = imagen.getRGB(x, y);
                
                alpha = bg.getAlphaNum(argb); 
                red = bg.getRedNum(argb);
                green = bg.getGreenNum(argb);
                blue = bg.getBlueNum(argb);
                
                promedio = (bg.getMax(red, green, blue) + bg.getMin(red, green, blue)) / 2;
                
                imagen.setRGB(x, y, bg.getARGBNum(alpha,promedio,promedio,promedio));
                
            }
        }
        
        return imagen;
    }

    public BufferedImage filtro_maximo(File file_imagen) throws IOException{
      
        BufferedImage imagen = ImageIO.read(file_imagen);
        
        int heigth = imagen.getHeight();
        int width = imagen.getWidth();
        
        int argb;
        int alpha;
        int red;
        int green;
        int blue;
        
        int maximo;
        
        for( int x = 0; x < width; x ++){     
            for(int y = 0; y < heigth; y++){
    
                argb = imagen.getRGB(x, y);
                
                alpha = bg.getAlphaNum(argb); 
                red = bg.getRedNum(argb);
                green = bg.getGreenNum(argb);
                blue = bg.getBlueNum(argb);
                
                maximo = bg.getMax(red, green, blue);
                
                imagen.setRGB(x, y, bg.getARGBNum(alpha,maximo,maximo,maximo));
                
            }
        }
        
        return imagen;
    }

    public BufferedImage filtro_minimo(File file_imagen) throws IOException{
      
        BufferedImage imagen = ImageIO.read(file_imagen);
        
        int heigth = imagen.getHeight();
        int width = imagen.getWidth();
        
        int argb;
        int alpha;
        int red;
        int green;
        int blue;
        
        int minimo;
        
        for( int x = 0; x < width; x ++){     
            for(int y = 0; y < heigth; y++){
    
                argb = imagen.getRGB(x, y);
                
                alpha = bg.getAlphaNum(argb); 
                red = bg.getRedNum(argb);
                green = bg.getGreenNum(argb);
                blue = bg.getBlueNum(argb);
                
                minimo = bg.getMin(red, green, blue);
                        
                imagen.setRGB(x, y, bg.getARGBNum(alpha,minimo,minimo,minimo));
                
            }
        }
        
        return imagen;
    }

    public BufferedImage filtro_verde(File file_imagen) throws IOException{
      
        BufferedImage imagen = ImageIO.read(file_imagen);
        
        int heigth = imagen.getHeight();
        int width = imagen.getWidth();
        
        int argb;
        int alpha;
        int green;
 
        for( int x = 0; x < width; x ++){     
            for(int y = 0; y < heigth; y++){
    
                argb = imagen.getRGB(x, y);
                
                alpha = bg.getAlphaNum(argb); 
                green = bg.getGreenNum(argb);
                
                imagen.setRGB(x, y, bg.getARGBNum(alpha,green,green,green));
                
            }
        }
        
        return imagen;
    }


}
