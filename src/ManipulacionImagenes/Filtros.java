/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManipulacionImagenes;

import K3Tree.K3Tree;
import K3Tree.K3TreeNode;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import net.coobird.thumbnailator.Thumbnails;

/**
 *
 * @author rae
 */
public class Filtros {
    
    
    private Random random = new Random();
    private BibliotecaGrafica bg = new BibliotecaGrafica();

    public Filtros(){

    }

    public BufferedImage filtro_azar(File file_imagen) throws IOException{
         BufferedImage imagen = ImageIO.read(file_imagen);

         int heigth = imagen.getHeight();
         int width = imagen.getWidth();

         int red;
         int green;
         int blue;

        for( int x = 0; x < width; x ++){
            for(int y = 0; y < heigth; y++){

               red = random.nextInt(256) << 16;
               green = random.nextInt(256) << 8;
               blue = random.nextInt(256);
               imagen.setRGB(x, y, red | green | blue);

            }
        }
         return imagen;
    }

    public BufferedImage filtro_rgb(File file_image,int red,int green,int blue) throws IOException{

        BufferedImage imagen = ImageIO.read(file_image);
        return filtro_rgb(imagen,red,green,blue);
        
    }

    public BufferedImage filtro_rgb(BufferedImage imagen, int red, int green, int blue) throws IOException{
        
        int heigth = imagen.getHeight();
        int width = imagen.getWidth();

        int comp_rgb = bg.getARGBNum(255,red,green,blue);

        int rgb;

        for( int x = 0; x < width; x ++){
            for(int y = 0; y < heigth; y++){

                rgb = imagen.getRGB(x, y);
                imagen.setRGB(x, y, rgb & comp_rgb);

            }
        }
         return imagen;
        
    }
    
    public BufferedImage filtro_brillo(File file_image, int constante) throws IOException{

        BufferedImage imagen = ImageIO.read(file_image);

        int heigth = imagen.getHeight();
        int width = imagen.getWidth();

        int alpha;
        int red;
        int green;
        int blue;

        int argb;

        for( int x = 0; x < width; x ++){
            for(int y = 0; y < heigth; y++){

                argb = imagen.getRGB(x, y);

                alpha = bg.getAlphaNum(argb);

                red = bg.getRedNum(argb) + constante;
                green = bg.getGreenNum(argb) + constante;
                blue = bg.getBlueNum(argb) + constante;

                imagen.setRGB(x, y, bg.getARGBNum(alpha,red,green,blue));

            }
        }
         return imagen;

    }
 
    public BufferedImage filtro_mosaico(BufferedImage imagen){

        double[][] blur_matrix = {{1,1,1},{1,1,1},{1,1,1}};
        return filtro_convolucion(imagen,blur_matrix,9);

    }
    
    public BufferedImage filtro_reduccion_proporcion(File file_image, int tam_matrix) throws IOException{
        
        BufferedImage imagen_original = ImageIO.read(file_image);
        BufferedImage imagen_creada;
        
        double width = imagen_original.getWidth() / (tam_matrix * 1.0);
        double heigth = imagen_original.getHeight() / (tam_matrix * 1.0);
        
        int new_width = (int) Math.ceil(width);
        int new_heigth = (int) Math.ceil(heigth);
        
        imagen_creada = new BufferedImage(new_width,new_heigth,BufferedImage.TYPE_INT_RGB);
                                    
        int alpha,red,green,blue;
        int [][] matrix;
        int [][][] argbs_matrix;
        int argb = 0;
                
        for(int j = 0; j < imagen_original.getWidth(); j += tam_matrix){
            for(int i = 0; i < imagen_original.getHeight(); i += tam_matrix){
             
                matrix = bg.getEdgeCompMatrix(i,j,tam_matrix,tam_matrix,imagen_original);
                argbs_matrix = bg.getRGBMatrixs(matrix);
                
                red = bg.getAverage(argbs_matrix[0]);
                green = bg.getAverage(argbs_matrix[1]);
                blue = bg.getAverage(argbs_matrix[2]);
                
                argb = bg.getARGBNum(255,red,green,blue);
                     
                imagen_creada.setRGB(j/tam_matrix , i/tam_matrix , argb );
                
            }
            
        }
        
        return imagen_creada;
        
    }
   
    public BufferedImage filtro_convolucion(BufferedImage imagen, double[][] valores, int factor){
        
      int heigth = imagen.getHeight();
      int width = imagen.getWidth();  
        
      BufferedImage imagen_nueva = new BufferedImage(width,heigth,BufferedImage.TYPE_INT_RGB);
        
      int tam_matrix = valores.length;
      
      int red,green,blue;
      int rgb;
      int[][] new_array;
      int[][][] rgb_arrays;
          
      for(int i = 0 ; i < heigth; i++){
          for(int j = 0; j < width; j++){
              
              new_array = bg.getCompMatrix(i,j,tam_matrix,tam_matrix,imagen);
              rgb_arrays = bg.getRGBMatrixs(new_array);
                            
              red = bg.convolucion(rgb_arrays[0], valores,factor);
              green = bg.convolucion(rgb_arrays[1], valores,factor);
              blue = bg.convolucion(rgb_arrays[2], valores,factor);
                 
              rgb = bg.getARGBNum(255,red,green,blue);
  
              try{
                   imagen_nueva.setRGB(j, i, rgb);     
              }catch(ArrayIndexOutOfBoundsException e){
                  System.out.println(j + "   " + i);
              }
              
          }
      }
        
      return imagen_nueva;
      
    }
    
    public BufferedImage filtro_blending(BufferedImage img1, BufferedImage img2, int porcentaje){
        
        BufferedImage imagen_creada = new BufferedImage(Math.max(img1.getWidth(), img2.getWidth()),
                Math.max(img1.getHeight(), img2.getHeight()),BufferedImage.TYPE_INT_RGB);
        
        BufferedImage minwidth = img1.getWidth() <= img2.getWidth() ? img1 : img2;
        BufferedImage minheight = img1.getHeight()<= img2.getHeight() ? img1 : img2;
        
        BufferedImage maxwidth = img1 == minwidth ? img2 : img1;
        BufferedImage maxheight = img1 == minheight ? img2 : img1;
        
        int red, green, blue;
        int rgb1, rgb2;
        
        boolean bool_eq_height = ( img1.getHeight() == img2.getHeight() ); // i >= minheight.getHeight() en el for
        boolean bool_eq_width = ( img1.getWidth() == img2.getWidth() ); // j >= minwidth.getWidth() en el for 
        
        double f_porcentaje = porcentaje / 100.0 ;
        
        for(int j = 0; j < imagen_creada.getWidth() ; j++){
            for(int i = 0; i < imagen_creada.getHeight(); i++){
                
                if( j < minwidth.getWidth() && i < minheight.getHeight() ){
            
                    rgb1 = img1.getRGB(j, i);
                    rgb2 = img2.getRGB(j, i);
                          
                    red =  (int) (bg.getRedNum(rgb1) * f_porcentaje + bg.getRedNum(rgb2) * (1- f_porcentaje));
                    green = (int) (bg.getGreenNum(rgb1) * f_porcentaje + bg.getGreenNum(rgb2) * (1- f_porcentaje));
                    blue = (int) (bg.getBlueNum(rgb1) * f_porcentaje + bg.getBlueNum(rgb2) * (1- f_porcentaje));
                    
                    imagen_creada.setRGB(j, i, bg.getARGBNum(255,red,green,blue));
                
                }
                
                
                if ( ( !bool_eq_height ) && j < maxheight.getWidth() && i >= minheight.getHeight() ){
                    
                    rgb1 = maxheight.getRGB(j, i);
        
                    red = (int)(bg.getRedNum(rgb1) * (1- f_porcentaje));
                    green = (int)(bg.getGreenNum(rgb1) * (1- f_porcentaje));
                    blue = (int)(bg.getBlueNum(rgb1) * (1- f_porcentaje));
                    
                    imagen_creada.setRGB(j, i, bg.getARGBNum(255,red,green,blue));
                    
                }
                
                if ( ( !bool_eq_width ) && j >= minwidth.getWidth() && i < maxwidth.getHeight() ){
                
                    rgb1 = maxwidth.getRGB(j, i);
                    
                     red = (int)(bg.getRedNum(rgb1) * (1- f_porcentaje));
                    green = (int)(bg.getGreenNum(rgb1) * (1- f_porcentaje));
                    blue = (int)(bg.getBlueNum(rgb1) * (1- f_porcentaje));
                    
                    imagen_creada.setRGB(j, i, bg.getARGBNum(255,red,green,blue));
                    
                    
                }
            }
        
            
        }
    
        return imagen_creada;
    
    }
    
    public BufferedImage filtro_sepia(BufferedImage imagen, int constante){
       
        int height = imagen.getHeight();
        int width = imagen.getWidth();
 
        BufferedImage nueva_imagen = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        
        int red, green, blue;
        
         for(int j = 0; j < imagen.getWidth() ; j++){
            for(int i = 0; i < imagen.getHeight(); i++){
            
                red = bg.getRedNum(imagen.getRGB(j,i)) +  ( 2 * constante );
                green = bg.getGreenNum(imagen.getRGB(j, i)) + constante;
                blue = bg.getBlueNum(imagen.getRGB(j, i));
                
                nueva_imagen.setRGB(j, i, bg.getARGBNum(255,red,green,blue));
                
            
            }
         }
          
        return nueva_imagen;
    }

    public BufferedImage filtro_alto_contraste(BufferedImage imagen){
        
        int height = imagen.getHeight();
        int width = imagen.getWidth();
        
        int promedio;
        
        int LIMITE = 7750000;
        
        int pixel_negro = bg.getARGBNum(255,0,0,0);
        int pixel_blanco = bg.getARGBNum(255,255,255,255);
        int pixel;
        
        BufferedImage nueva_imagen = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        
        int red, green, blue;
        
         for(int j = 0; j < imagen.getWidth() ; j++){
            for(int i = 0; i < imagen.getHeight(); i++){
            
                red = bg.getRedNum(imagen.getRGB(j,i));
                green = bg.getGreenNum(imagen.getRGB(j, i));
                blue = bg.getBlueNum(imagen.getRGB(j, i));
                
                promedio = (red + green + blue) / 3;
                
                pixel = (65536 * promedio) + (256 * promedio) + promedio <= LIMITE ? pixel_negro : pixel_blanco ;
                nueva_imagen.setRGB(j, i, pixel);
                
            
            }
         }
          
        return nueva_imagen;
    }
        
    public BufferedImage filtro_maximo(BufferedImage imagen, int tam_matrix){
        
        int height = imagen.getHeight();
        int width = imagen.getWidth();
          
        BufferedImage nueva_imagen = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        
        int red, green, blue;
        int[][] new_array;
        int[][][] rgb_arrays;
        
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
             
              new_array = bg.getCompMatrix(i,j,tam_matrix,tam_matrix,imagen);
              rgb_arrays = bg.getRGBMatrixs(new_array);
               
              red = bg.getMatrixMax(rgb_arrays[0]);
              green = bg.getMatrixMax(rgb_arrays[1]);
              blue = bg.getMatrixMax(rgb_arrays[2]);       
               
              nueva_imagen.setRGB(j, i, bg.getARGBNum(255,red,green,blue));
                
            }
        } 
           
        return nueva_imagen;
    }
    
    public BufferedImage filtro_minimo(BufferedImage imagen, int tam_matrix){
        
        int height = imagen.getHeight();
        int width = imagen.getWidth();
          
        BufferedImage nueva_imagen = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        
        int red, green, blue;
        int[][] new_array;
        int[][][] rgb_arrays;
        
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
             
              new_array = bg.getCompMatrix(i,j,tam_matrix,tam_matrix,imagen);
              rgb_arrays = bg.getRGBMatrixs(new_array);
               
              red = bg.getMatrixMin(rgb_arrays[0]);
              green = bg.getMatrixMin(rgb_arrays[1]);
              blue = bg.getMatrixMin(rgb_arrays[2]);       
               
              nueva_imagen.setRGB(j, i, bg.getARGBNum(255,red,green,blue));
                
            }
        } 
           
        return nueva_imagen;
        
    }
   
    public BufferedImage filtro_blacklight(BufferedImage imagen, int constante){
        
        int height = imagen.getHeight();
        int width = imagen.getWidth();
          
        BufferedImage nueva_imagen = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        
        int red, green, blue;
        int rgb;
        int ponderacion;
        
        for(int i = 0; i < height ; i++){
            for (int j = 0; j < width; j++) {
             
                rgb = imagen.getRGB(j, i);
               
                red = bg.getRedNum(rgb); 
                green = bg.getGreenNum(rgb);
                blue = bg.getBlueNum(rgb);
                
                ponderacion = (int) ((red*0.3) + (green*0.59) + (blue*0.11));
                
                red = Math.abs(red - ponderacion) * constante ;
                green = Math.abs(green - ponderacion) * constante;
                blue = Math.abs(blue - ponderacion) * constante ;
               
                nueva_imagen.setRGB(j, i, bg.getARGBNum(255,red,green,blue));
                
            }
        }
        
        
        return nueva_imagen;
        
    }
    
    public BufferedImage filtro_oleo_bn(BufferedImage imagen){
        
        int height = imagen.getHeight();
        int width = imagen.getWidth();
        FiltrosGrises fg = new FiltrosGrises();
          
        BufferedImage nueva_imagen = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        BufferedImage gris_imagen = fg.filtro_promedio(imagen);
        
        int[][] ventana;
        int pixel;
        
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width ; j++) {
                
                ventana = bg.getCompMatrix(i,j,5,5,gris_imagen);
                pixel = bg.getMaxFrecuencia(bg.getRGBMatrixs(ventana)[0]);        
                nueva_imagen.setRGB(j, i, bg.getARGBNum(255,pixel,pixel,pixel));
                
            }
        }
        
        return nueva_imagen;
    }

    public BufferedImage filtro_oleo_color(BufferedImage imagen){
        
        int height = imagen.getHeight();
        int width = imagen.getWidth();
        
        double[][] blur_matrix = {{1,1},{1,1}};
        
        BufferedImage nueva_imagen = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        
        int[][] ventana;
        int[][][] rgb_matrixs;
        int red, green, blue;
        
         for (int i = 0; i < height; i++) {
            for (int j = 0; j < width ; j++) {
          
                ventana = bg.getCompMatrix(i, j, random.nextInt(5) + 1, random.nextInt(5) + 1 , imagen);
                rgb_matrixs = bg.getRGBMatrixs(ventana);
                
                red = bg.getMatrixMin(rgb_matrixs[0]);
                green = bg.getMatrixMin(rgb_matrixs[1]);
                blue = bg.getMatrixMin(rgb_matrixs[2]);
                
                nueva_imagen.setRGB(j, i, bg.getARGBNum(255, red, green, blue));
            }
         }
           
        return filtro_convolucion(nueva_imagen,blur_matrix,4);
         
    } 
    
    public BufferedImage filtro_reduccion_porcentaje(BufferedImage imagen, int porcentaje){
        
        if(porcentaje == 0){
            return new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        }
        
        int height = imagen.getHeight();
        int width = imagen.getWidth();
        
        int new_height =  (int) Math.ceil(height * (porcentaje / 100.0));
        int new_width = (int )Math.ceil(width * (porcentaje / 100.0));
        
        new_height = new_height == ((int) Math.ceil( (height-1) * (porcentaje / 100.0))) ? new_height + 1 : new_height;
        new_width = new_width == ((int) Math.ceil( (width-1) * (porcentaje / 100.0))) ? new_width + 1 : new_width;
            
        BufferedImage nueva_imagen = new BufferedImage(new_width,new_height,BufferedImage.TYPE_INT_RGB);
        
        int new_i;
        int new_j;
        
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                
                new_i = (int) Math.ceil(i * (porcentaje / 100.0));
                new_j = (int) Math.ceil(j * (porcentaje / 100.0));
            
                nueva_imagen.setRGB(new_j, new_i, imagen.getRGB(j, i) );
                    
            }
        }
    
        return nueva_imagen;
    
    }
    
    public BufferedImage filtro_blending_favicom(BufferedImage imagen,BufferedImage favicom, int posc_j, int posc_i, 
                                    int blending_porcentaje, int reduccion_porcentaje){
         
        int height = imagen.getHeight();
        int width = imagen.getWidth();
        
        BufferedImage nueva_imagen = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        BufferedImage r_favicom = filtro_reduccion_porcentaje(favicom, reduccion_porcentaje);
        double d_porcentaje_blending = blending_porcentaje / 100.0;  
        
        int red, green, blue;
        int rgb1, rgb2;
        
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++) {
             
                if ( ( j >= posc_j ) && ( j - posc_j  < r_favicom.getWidth() )  &&
                             (i >= posc_i) && ( i - posc_i < r_favicom.getHeight() )) {
                    
                    rgb1 = imagen.getRGB(j, i);
                    rgb2 = r_favicom.getRGB(j - posc_j, i - posc_i);

                    red =  (int) (bg.getRedNum(rgb1) * d_porcentaje_blending + bg.getRedNum(rgb2) * (1- d_porcentaje_blending));
                    green = (int) (bg.getGreenNum(rgb1) * d_porcentaje_blending + bg.getGreenNum(rgb2) * (1- d_porcentaje_blending));
                    blue = (int) (bg.getBlueNum(rgb1) * d_porcentaje_blending + bg.getBlueNum(rgb2) * (1- d_porcentaje_blending));

                    nueva_imagen.setRGB(j, i, bg.getARGBNum(255,red,green,blue));

                }else{
                    
                    nueva_imagen.setRGB(j, i, imagen.getRGB(j, i));
                    
                }
                
                     
            }    
        }
        
        return nueva_imagen;
    }
    
    public BufferedImage filtro_att(BufferedImage imagen, int num_filas){
        
        FiltrosGrises fg = new FiltrosGrises();
        
        int height = imagen.getHeight();
        int width = imagen.getWidth();
        
        int height_average_row_pixel = height / num_filas ;  
        int height_left_out_pixel = height - (height_average_row_pixel * num_filas) - 1;
        
        int height_row_pixel;
        
        int posc_i_inicial = 0;
        int num_black_pixels = 0;
        
        int pixel_negro = bg.getARGBNum(255,0,0,0);
              
        BufferedImage nueva_imagen = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);    
        bg.pasar_blanco(nueva_imagen);
        
        BufferedImage alto_contraste_imagen = filtro_alto_contraste(fg.filtro_promedio(imagen));
                
        for(int row = 0; row < num_filas; row++){
            
            height_row_pixel = height_left_out_pixel > 0 ? height_average_row_pixel + 1 : height_average_row_pixel;
            height_left_out_pixel--;
            
            for(int j = 0 ; j < width ; j++) { 
                
                for(int i = 0 ; i < height_row_pixel ; i++ ){
                                    
                    num_black_pixels = bg.getRedNum(alto_contraste_imagen.getRGB(j, i + posc_i_inicial)) == 0 ? 
                                                                                num_black_pixels + 1 : num_black_pixels;
                
                }    
                
                int value = posc_i_inicial + ( (height_row_pixel - num_black_pixels) / 2 );    
                
                for(int i = 0 ; i < height_row_pixel; i++ ){
                       
                    if ( num_black_pixels > 0 ) { 
                        nueva_imagen.setRGB(j, value + i, pixel_negro);
                        num_black_pixels--;
                                       
                    }  
                
                }
                        
             }        
            
             posc_i_inicial += height_row_pixel;
        }
    
        return nueva_imagen;
    
    }

    // La coleccion debe ser del mismo tamaño
    public BufferedImage filtro_semitono(BufferedImage imagen, int reduccion, int tam_matrix, BufferedImage[] coleccion_imagenes){
      
        FiltrosGrises fg = new FiltrosGrises();
        BufferedImage[] coleccion_imagenes_reducidas = new BufferedImage[coleccion_imagenes.length];
        
        int height = imagen.getHeight();
        int width = imagen.getWidth();
        
        int height_num_matrix = height / tam_matrix;
        int width_num_matrix = width / tam_matrix;
        
        for (int i = 0; i < coleccion_imagenes.length ; i++) {
        
            coleccion_imagenes_reducidas[i] = filtro_reduccion_porcentaje(coleccion_imagenes[i], reduccion);
            
        }
        
        int height_nueva_imagen = height_num_matrix *  coleccion_imagenes_reducidas[0].getHeight();
        int width_nueva_imagen = width_num_matrix * coleccion_imagenes_reducidas[0].getWidth();
        
        BufferedImage imagen_grises = fg.filtro_promedio(imagen);
        BufferedImage nueva_imagen = new BufferedImage(width_nueva_imagen, height_nueva_imagen, BufferedImage.TYPE_INT_RGB);
        BufferedImage imagen_arriba;
        
        int[][] ventana;
        int promedio;
         
        for (int i = 0; i < height - (height % tam_matrix); i += tam_matrix) {
            for (int j = 0; j < width - (width % tam_matrix); j += tam_matrix ) {
                
                ventana = bg.getEdgeCompMatrix(i, j, tam_matrix, tam_matrix, imagen_grises);
                promedio =  bg.getAverage(bg.getRGBMatrixs(ventana)[0]);        
                                
                imagen_arriba =  promedio == 256 ? coleccion_imagenes_reducidas[coleccion_imagenes.length - 1] 
                        : coleccion_imagenes_reducidas[  (int) (promedio/ (256.0/coleccion_imagenes.length))  ];
           
                bg.sobreponer_imagen((i / tam_matrix) * coleccion_imagenes_reducidas[0].getHeight(), 
                            (j / tam_matrix) * coleccion_imagenes_reducidas[0].getWidth(), imagen_arriba, nueva_imagen);                    
                
            }
        }
        
        return nueva_imagen;
    } 

    // La coleccion debe ser del mismo tamaño
    public BufferedImage filtro_letra(BufferedImage imagen, int reduccion, int tam_matrix, BufferedImage[] coleccion_imagenes){
        
        FiltrosGrises fg = new FiltrosGrises();
        BufferedImage[] coleccion_imagenes_reducidas = new BufferedImage[coleccion_imagenes.length];
        
        int height = imagen.getHeight();
        int width = imagen.getWidth();
        
        int height_num_matrix = height / tam_matrix;
        int width_num_matrix = width / tam_matrix;
        
        for (int i = 0; i < coleccion_imagenes.length ; i++) {
        
            coleccion_imagenes_reducidas[i] = filtro_reduccion_porcentaje(coleccion_imagenes[i], reduccion);
            
        }
        
        int height_nueva_imagen = height_num_matrix *  coleccion_imagenes_reducidas[0].getHeight();
        int width_nueva_imagen = width_num_matrix * coleccion_imagenes_reducidas[0].getWidth();
        
        BufferedImage imagen_grises = fg.filtro_promedio(imagen);
        BufferedImage nueva_imagen = new BufferedImage(width_nueva_imagen, height_nueva_imagen, BufferedImage.TYPE_INT_RGB);
        BufferedImage imagen_arriba;
        
        int[][] ventana;
        int promedio;
         
        for (int i = 0; i < height - (height % tam_matrix); i += tam_matrix) {
            for (int j = 0; j < width - (width % tam_matrix); j += tam_matrix ) {
                
                ventana = bg.getEdgeCompMatrix(i, j, tam_matrix, tam_matrix, imagen_grises);
                promedio =  bg.getAverage(bg.getRGBMatrixs(ventana)[0]);        
                                
                imagen_arriba =  promedio == 256 ? coleccion_imagenes_reducidas[coleccion_imagenes.length - 1] 
                        : coleccion_imagenes_reducidas[  (int) (promedio/ (256.0/coleccion_imagenes.length))  ];
           
                bg.sobreponer_imagen((i / tam_matrix) * coleccion_imagenes_reducidas[0].getHeight(), 
                            (j / tam_matrix) * coleccion_imagenes_reducidas[0].getWidth(), imagen_arriba, nueva_imagen);                    
                
            }
        }
        
        return nueva_imagen;
        
        
    }

    // La coleccion debe ser del mismo tamaño
    public BufferedImage filtro_dithering(BufferedImage imagen, int reduccion, int tam_matrix, BufferedImage[] coleccion_imagenes){
        
        FiltrosGrises fg = new FiltrosGrises();
        BufferedImage[] coleccion_imagenes_reducidas = new BufferedImage[coleccion_imagenes.length];
        
        int height = imagen.getHeight();
        int width = imagen.getWidth();
        
        int height_num_matrix = height / tam_matrix;
        int width_num_matrix = width / tam_matrix;
        
        for (int i = 0; i < coleccion_imagenes.length ; i++) {
        
            coleccion_imagenes_reducidas[i] = filtro_reduccion_porcentaje(coleccion_imagenes[i], reduccion);
            
        }
        
        int height_nueva_imagen = height_num_matrix *  coleccion_imagenes_reducidas[0].getHeight();
        int width_nueva_imagen = width_num_matrix * coleccion_imagenes_reducidas[0].getWidth();
        
        BufferedImage imagen_grises = fg.filtro_promedio(imagen);
        BufferedImage nueva_imagen = new BufferedImage(width_nueva_imagen, height_nueva_imagen, BufferedImage.TYPE_INT_RGB);
        BufferedImage imagen_arriba;
        
        int[][] ventana;
        int promedio;
         
        for (int i = 0; i < height - (height % tam_matrix); i += tam_matrix) {
            for (int j = 0; j < width - (width % tam_matrix); j += tam_matrix ) {
                
                ventana = bg.getEdgeCompMatrix(i, j, tam_matrix, tam_matrix, imagen_grises);
                promedio =  bg.getAverage(bg.getRGBMatrixs(ventana)[0]);        
                                
                imagen_arriba =  promedio == 256 ? coleccion_imagenes_reducidas[coleccion_imagenes.length - 1] 
                        : coleccion_imagenes_reducidas[  (int) (promedio/ (256.0/coleccion_imagenes.length))  ];
           
                bg.sobreponer_imagen((i / tam_matrix) * coleccion_imagenes_reducidas[0].getHeight(), 
                            (j / tam_matrix) * coleccion_imagenes_reducidas[0].getWidth(), imagen_arriba, nueva_imagen);                    
                
            }
        }
        
        return nueva_imagen;
        
    }

    public BufferedImage filtro_fotomosaico(BufferedImage imagen, int tam_ventana_x, int tam_ventana_y, int tam_resultado_x, int tam_resultado_y, String nombre_archivo){
        
        int width = imagen.getWidth();
        int height = imagen.getHeight();
        
        int new_width = ((int)Math.ceil(width/tam_ventana_x)) * tam_resultado_x;
        int new_height = ((int)Math.ceil(height/tam_ventana_y)) * tam_resultado_y;
        
        BufferedImage nueva_imagen = new BufferedImage(new_width,new_height,BufferedImage.TYPE_INT_RGB);
        
        int[][] new_array;
        int[][][] rgb_arrays;
        K3Tree arbol = null;
        K3TreeNode<LinkedList<String>> nodo_arbol = null;
        String path = "";
        BufferedImage imagen_reducida;
        
        int red;
        int blue;
        int green;
        
        try {
            arbol = cargar_arbol("hashtable.ser");
            path = new File("").getCanonicalPath();
        } catch (IOException ex) {
            System.out.println("Intente actuzalizar");
        } catch (ClassNotFoundException ex) {
            System.out.println("Clase no encontrada");
        }   
         
        try {
            
            for (int i = 0; i < height - (height % tam_ventana_y); i += tam_ventana_y) {
                for (int j = 0; j < width - (width % tam_ventana_x); j += tam_ventana_x ) {

                    new_array = bg.getCompMatrix(i, j, tam_ventana_y, tam_ventana_x, imagen);
                    rgb_arrays = bg.getRGBMatrixs(new_array);
                    red = bg.getAverage(rgb_arrays[0]);
                    green = bg.getAverage(rgb_arrays[1]);
                    blue = bg.getAverage(rgb_arrays[2]);
                    nodo_arbol = arbol.search_nearest_neighbour(red, green, blue); 

                    imagen_reducida =  Thumbnails.of(path + nodo_arbol.getAtributo().getFirst()).forceSize(tam_resultado_x, tam_resultado_y).asBufferedImage();
 
                    bg.sobreponer_imagen((i / tam_ventana_y) * tam_resultado_y, 
                                (j/tam_ventana_y) * tam_resultado_x, imagen_reducida, nueva_imagen);
                   

                }
            }
   
        } catch (IOException ex) {
                    Logger.getLogger(Filtros.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return nueva_imagen;
        
    }
    
    
    private K3Tree cargar_arbol(String nombre_archivo)throws IOException, ClassNotFoundException{
        
        FileInputStream fileIn = null;
        ObjectInputStream in = null;
        HashMap hash = null;
               
        try{
           
           fileIn = new FileInputStream(nombre_archivo);
           in = new ObjectInputStream(fileIn);
           hash = (HashMap) in.readObject();
           in.close();
           fileIn.close();
           
        } finally{
            try{
               in.close();
               fileIn.close();
            }catch(Exception e){
                // Ignorada a proposito
            }
            
        }  
        
        return  new K3Tree(hash.values());
        
    }
    
    
}
