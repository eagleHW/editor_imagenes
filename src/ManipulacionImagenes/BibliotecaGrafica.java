
package ManipulacionImagenes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author rae
 */
public class BibliotecaGrafica {

    Random random = new Random();

    public BibliotecaGrafica(){

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

        int comp_rgb = getARGBNum(255,red,green,blue);

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

                alpha = getAlphaNum(argb);

                red = getRedNum(argb) + constante;
                green = getGreenNum(argb) + constante;
                blue = getBlueNum(argb) + constante;

                imagen.setRGB(x, y, getARGBNum(alpha,red,green,blue));

            }
        }
         return imagen;

    }
 
    public BufferedImage filtro_mosaico(File file_image, int tam_matrix) throws IOException{

        double[][] blur_matrix = {{1,1,1},{1,1,1},{1,1,1}};
        return filtro_convolucion(file_image,blur_matrix,9);

    }
    
    public BufferedImage filtro_reduccion(File file_image, int tam_matrix) throws IOException{
        
        BufferedImage imagen_original = ImageIO.read(file_image);
        BufferedImage imagen_creada;
        
        double width = imagen_original.getWidth() / (tam_matrix * 1.0);
        double heigth = imagen_original.getHeight() / (tam_matrix * 1.0);
        
        int new_width = (int) Math.ceil(width);
        int new_heigth = (int) Math.ceil(heigth);
        
        imagen_creada = new BufferedImage(new_width,new_heigth,BufferedImage.TYPE_INT_RGB);
                                    //Dividir
        int alpha,red,green,blue;
        int [][] matrix;
        int [][][] argbs_matrix;
        int argb = 0;
                
        for(int j = 0; j < imagen_original.getWidth(); j += tam_matrix){
            for(int i = 0; i < imagen_original.getHeight(); i += tam_matrix){
             
                matrix = getEdgeCompMatrix(i,j,tam_matrix,tam_matrix,imagen_original);
                argbs_matrix = getRGBMatrixs(matrix);
                
                red = getAverage(argbs_matrix[0]);
                green = getAverage(argbs_matrix[1]);
                blue = getAverage(argbs_matrix[2]);
                
                argb = getARGBNum(255,red,green,blue);
                     
                imagen_creada.setRGB(j/tam_matrix , i/tam_matrix , argb );
                
            }
            
        }
        
        return imagen_creada;
        
    }
   
    public BufferedImage filtro_convolucion(File file_image, double[][] valores, int factor) throws IOException{
        
      BufferedImage imagen_original = ImageIO.read(file_image);
      BufferedImage imagen_modificada = ImageIO.read(file_image);
        
      int heigth = imagen_original.getHeight();
      int width = imagen_original.getWidth();
      
      int tam_matrix = valores.length;
      
      int red,green,blue;
      int rgb;
      int[][] new_array;
      int[][][] rgb_arrays;
          
      for(int i = 0 ; i < heigth; i++){
          for(int j = 0; j < width; j++){
              
              new_array = getCompMatrix(i,j,tam_matrix,tam_matrix,imagen_original);
              rgb_arrays = getRGBMatrixs(new_array);
                            
              red = convolucion(rgb_arrays[0], valores,factor);
              green = convolucion(rgb_arrays[1], valores,factor);
              blue = convolucion(rgb_arrays[2], valores,factor);
                 
              rgb = getARGBNum(255,red,green,blue);
  
              try{
                   imagen_modificada.setRGB(j, i, rgb);     
              }catch(ArrayIndexOutOfBoundsException e){
                  System.out.println(j + "   " + i);
              }
              
          }
      }
        
      return imagen_modificada;
      
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
                          
                    red =  (int) (getRedNum(rgb1) * f_porcentaje + getRedNum(rgb2) * (1- f_porcentaje));
                    green = (int) (getGreenNum(rgb1) * f_porcentaje + getGreenNum(rgb2) * (1- f_porcentaje));
                    blue = (int) (getBlueNum(rgb1) * f_porcentaje + getBlueNum(rgb2) * (1- f_porcentaje));
                    
                    imagen_creada.setRGB(j, i, getARGBNum(255,red,green,blue));
                
                }
                
                
                if ( ( !bool_eq_height ) && j < maxheight.getWidth() && i >= minheight.getHeight() ){
                    
                    rgb1 = maxheight.getRGB(j, i);
        
                    red = (int)(getRedNum(rgb1) * (1- f_porcentaje));
                    green = (int)(getGreenNum(rgb1) * (1- f_porcentaje));
                    blue = (int)(getBlueNum(rgb1) * (1- f_porcentaje));
                    
                    imagen_creada.setRGB(j, i, getARGBNum(255,red,green,blue));
                    
                }
                
                if ( ( !bool_eq_width ) && j >= minwidth.getWidth() && i < maxwidth.getHeight() ){
                
                    rgb1 = maxwidth.getRGB(j, i);
                    
                     red = (int)(getRedNum(rgb1) * (1- f_porcentaje));
                    green = (int)(getGreenNum(rgb1) * (1- f_porcentaje));
                    blue = (int)(getBlueNum(rgb1) * (1- f_porcentaje));
                    
                    imagen_creada.setRGB(j, i, getARGBNum(255,red,green,blue));
                    
                    
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
            
                red = getRedNum(imagen.getRGB(j,i)) +  ( 2 * constante );
                green = getGreenNum(imagen.getRGB(j, i)) + constante;
                blue = getBlueNum(imagen.getRGB(j, i));
                
                nueva_imagen.setRGB(j, i, getARGBNum(255,red,green,blue));
                
            
            }
         }
          
        return nueva_imagen;
    }

    public BufferedImage filtro_alto_contraste(BufferedImage imagen){
        
        int height = imagen.getHeight();
        int width = imagen.getWidth();
        
        int promedio;
        
        int LIMITE = 7750000;
        
        int pixel_negro = getARGBNum(255,0,0,0);
        int pixel_blanco = getARGBNum(255,255,255,255);
        int pixel;
        
        BufferedImage nueva_imagen = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        
        int red, green, blue;
        
         for(int j = 0; j < imagen.getWidth() ; j++){
            for(int i = 0; i < imagen.getHeight(); i++){
            
                red = getRedNum(imagen.getRGB(j,i));
                green = getGreenNum(imagen.getRGB(j, i));
                blue = getBlueNum(imagen.getRGB(j, i));
                
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
             
              new_array = getCompMatrix(i,j,tam_matrix,tam_matrix,imagen);
              rgb_arrays = getRGBMatrixs(new_array);
               
              red = getMatrixMax(rgb_arrays[0]);
              green = getMatrixMax(rgb_arrays[1]);
              blue = getMatrixMax(rgb_arrays[2]);       
               
              nueva_imagen.setRGB(j, i, getARGBNum(255,red,green,blue));
                
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
             
              new_array = getCompMatrix(i,j,tam_matrix,tam_matrix,imagen);
              rgb_arrays = getRGBMatrixs(new_array);
               
              red = getMatrixMin(rgb_arrays[0]);
              green = getMatrixMin(rgb_arrays[1]);
              blue = getMatrixMin(rgb_arrays[2]);       
               
              nueva_imagen.setRGB(j, i, getARGBNum(255,red,green,blue));
                
            }
        } 
           
        return nueva_imagen;
        
        
        
        
        
        
    }
    
    // Sigue regresando -1
    public int getAlphaNum(int argb){

         return (argb & 0xFF000000) >> 24;
    }

    public int getRedNum(int argb){

        return (argb & 0x00FF0000) >> 16;
    }

    public int getGreenNum(int argb){

        return (argb & 0x0000FF00) >> 8;
    }

    public int getBlueNum(int argb){

        return (argb & 0x000000FF);
    }

    public int getARGBNum(int alpha, int red, int green, int blue){

        int alpha_comp = alpha > 255 ? 255 : alpha;
        int red_comp = red > 255 ? 255 : red ;
        int green_comp = green > 255 ? 255 : green;
        int blue_comp = blue > 255 ? 255 : blue;

        alpha_comp = alpha_comp < 0 ? 0 : alpha_comp << 24;
        red_comp = red_comp < 0 ? 0 : red_comp << 16;
        green_comp = green_comp < 0 ? 0 : green_comp << 8;
        blue_comp = blue_comp < 0 ? 0 : blue_comp;

        return alpha_comp | red_comp | green_comp | blue_comp;

    }

    public int getMax(int red,int green, int blue){

        int max;

        max = red > green ? red : green;
        max = max > blue ? max : blue;

        return max;

    }

    public int getMin(int red,int green, int blue){

        int min;

        min = red < green ? red : green;
        min = min < blue ? min : blue;

        return min;

    }

    public int getAverage(int[][] matrix){
        
        int average = 0;
        
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                average += matrix[i][j];
            }
        }
        
        return (int) ( average / (matrix.length * matrix[0].length ));
        
    }
    
    public int getMatrixMax(int [][] matrix){
        
        int width = matrix[0].length;
        int height = matrix.length;
    
        int maximo = Integer.MIN_VALUE;
    
        for(int i = 0; i < height; i++ ){
            for(int j = 0; j < width; j++){
   
                maximo = maximo < matrix[i][j] ? matrix[i][j] : maximo; 
                             
            }
        }
        
        return maximo;
        
    }
    
    public int getMatrixMin(int [][] matrix){
        
        int width = matrix[0].length;
        int height = matrix.length;
    
        int minimo = Integer.MAX_VALUE;
    
        for(int i = 0; i < height; i++ ){
            for(int j = 0; j < width; j++){
   
                minimo = minimo > matrix[i][j] ? matrix[i][j] : minimo; 
                             
            }
        }
        
        return minimo;
        
    }
    
    //Revisar (Matriz hexadecimales)
    public int[][][] getRGBMatrixs(int[][] img_matrix){
        
        int[][] red_matrix = new int [img_matrix.length][img_matrix[0].length];
        int[][] green_matrix = new int [img_matrix.length][img_matrix[0].length];;
        int[][] blue_matrix = new int [img_matrix.length][img_matrix[0].length];;
        
        int[][][] matrixes = new int[3][img_matrix.length][img_matrix[0].length]; 
        int pixel;
        
        
        for(int i = 0; i < img_matrix.length ; i++){
            for(int j = 0; j < img_matrix[0].length; j++){
                
                pixel = img_matrix[i][j];
                
                red_matrix[i][j] = getRedNum(pixel);
                green_matrix[i][j] = getGreenNum(pixel);
                blue_matrix[i][j] = getBlueNum(pixel);
                
            }
        }
        
        matrixes[0] = red_matrix;
        matrixes[1] = green_matrix;
        matrixes[2] = blue_matrix;
        
        return matrixes;
    } 
    
    // Tiene problemas con matrices de tamaño par que salen de los bordes
    public int[] getMatrix(int y, int x, int height_mat, int width_mat, BufferedImage imagen){

        int height_img = imagen.getHeight();
        int width_img =  imagen.getWidth();

        int x_init = x - (width_mat / 2);
        int y_init = y - (height_mat / 2);

        int exc_x = x_init < 0 ? -(x_init) : 0;
        int exc_y = y_init < 0 ? -(y_init) : 0;

        x_init += exc_x;
        y_init += exc_y;

        exc_x += x + (width_mat / 2) >= width_img ?
                            (x + (width_mat / 2)) - (width_img-1) : 0;
        exc_y += y + (height_mat / 2) >= height_img ?
                            (y + (height_mat / 2)) - (height_img-1) : 0;

        int tam = 0;
        int array_posc = 0;
        int[] pixel_values = new int[(width_mat - exc_x)*(height_mat - exc_y)];

        for(int i = y_init ; i < y_init + ( height_mat - exc_y ) ; i++){

            for(int j = x_init ; j < x_init + ( width_mat - exc_x ); j++){

                   pixel_values[array_posc] = imagen.getRGB(j,i);
                   array_posc++;

            }
        }

        return pixel_values;
    }

    // Tiene problemas con matrices de tamaño par que salen de los bordes
    public void setMatrix(int y, int x, int height_mat, int width_mat,int argb ,BufferedImage imagen ){

        int height_img = imagen.getHeight();
        int width_img =  imagen.getWidth();

        int x_init = x - (width_mat / 2);
        int y_init = y - (height_mat / 2);

        int exc_x = x_init < 0 ? -(x_init) : 0;
        int exc_y = y_init < 0 ? -(y_init) : 0;

        x_init += exc_x;
        y_init += exc_y;

        exc_x += x + (width_mat / 2) >= width_img ?
                            (x + (width_mat / 2)) - (width_img-1) : 0;
        exc_y += y + (height_mat / 2) >= height_img ?
                            (y + (height_mat / 2)) - (height_img-1) : 0;

        for(int i = y_init ; i < y_init + ( height_mat - exc_y ) ; i++){

            for(int j = x_init ; j < x_init + ( width_mat - exc_x ); j++){

                   imagen.setRGB(j, i, argb);

            }
        }

    }
  
    public int[][] getCompMatrix(int y, int x, int heigth_mat, int width_mat, BufferedImage imagen ){
    
        int heigth_img = imagen.getHeight();
        int width_img =  imagen.getWidth();
        
        int x_init = x - (width_mat / 2);
        int y_init = y - (heigth_mat / 2);
                
        int[][] matrix = new int[heigth_mat][width_mat];
        
        int val_x = 0;
        int val_y = 0;
        
        for(int i = 0; i < heigth_mat; i++){
            for(int j = 0; j < width_mat; j++ ){
                            
                if(x_init + j > 0 && x_init + j < width_img){
                    val_x = x_init + j;
                }else{
                    val_x = (x_init + j) < 0 ? width_img + (x_init + j) : (x_init + j) % width_img; 
                
                    // En caso de que width_img + (x_init + i) sea menor que 0
                    if(val_x < 0){
                        val_x = -(val_x) % width_img;
                    }          
                }
                
                if(y_init + i > 0 && y_init + i < heigth_img){
                    val_y = y_init + i;
                }else{
                    val_y = (y_init +i) < 0 ? heigth_img + (y_init + i) : (y_init + i) % heigth_img;    
                
                    // En caso de que heigth_img + (y_init + j) sea menor que 0 
                    if(val_y < 0){
                        val_y = -(val_y) % heigth_img;
                    }
                    
                }
                
                matrix[i][j] =  imagen.getRGB(val_x,val_y);
                
            }
            
        }
        
        return matrix;
        
    }
 
    public int[][] getEdgeCompMatrix(int y, int x, int heigth_mat, int width_mat, BufferedImage imagen){
    
        int heigth_img = imagen.getHeight();
        int width_img =  imagen.getWidth();
                
        int[][] matrix = new int[heigth_mat][width_mat];
        
        int val_x = 0;
        int val_y = 0;
                
        // j corresponde con x
        // i corresponde con y
        
        for(int i = 0; i < heigth_mat; i++){
            for(int j = 0; j < width_mat; j++ ){
                
                if(x + j > 0 && x + j < width_img){
                    val_x = x + j;
                }else{
                    val_x = (x +j) < 0 ? width_img + (x + j) : (x + j) % width_img;    
                
                    // En caso de que heigth_img + (y_init + j) sea menor que 0 
                    if(val_x < 0){
                        val_x = -(val_x) % width_img;
                    }             
                }
                     
                
                if(y + i > 0 && y + i < heigth_img){
                    val_y = y + i;
                }else{
                    val_y = (y + i) < 0 ? heigth_img + (y + i) : (y + i) % heigth_img; 
                
                    // En caso de que width_img + (x + i) sea menor que 0
                    if(val_y < 0){
                        val_y = -(val_y) % heigth_img;
                    }          
                }
                
                matrix[i][j] =  imagen.getRGB(val_x,val_y);
              
            }
        }   
    
        return matrix;
        
    }
    
    public int convolucion(int[][] pixel, double[][] values, int factor){
       
        int width;
        int heigth;
        
        int resultado = 0;
        
        if((pixel.length != values.length) || (pixel[0].length != values[0].length)  ){
            // Tirar Excepcion
        }
        
       heigth = pixel.length; 
       width = pixel[0].length;
        
        
       for(int i = 0; i < heigth; i++){
           for( int j = 0; j < width; j++){
               
               resultado += pixel[i][j] * values[i][j];
               
           }
       }
        
       return resultado/factor; 
       
    }
    
    public BufferedImage pegar_imagenes(BufferedImage top_left,BufferedImage top_rigth, BufferedImage bottom_left,
                              BufferedImage bottom_rigth){
        
        BufferedImage imagen_creada = new BufferedImage(top_left.getWidth() + top_rigth.getWidth(),
                                               top_left.getHeight() + bottom_left.getHeight(),BufferedImage.TYPE_INT_RGB);
        
        
        BufferedImage[] imagenes = {top_left,top_rigth,bottom_left,bottom_rigth};
        BufferedImage imagen;
        
        int[][] posc = {{0,0},{top_left.getWidth(),0 },{0,top_left.getHeight()},
                        {top_left.getWidth(),top_left.getHeight()}};
      
        System.out.println(Arrays.toString(posc[1]));
        System.out.println(Arrays.toString(posc[2]));
        System.out.println(Arrays.toString(posc[3]));
        
        int posc_x_ini;
        int posc_y_ini;
        
        System.out.println("width  " + imagen_creada.getWidth() );
        System.out.println("heigth  " + imagen_creada.getHeight() );
        
        for(int num_img = 0 ; num_img < 4; num_img++ ){
            
            posc_x_ini = posc[num_img][0];
            posc_y_ini = posc[num_img][1];
            imagen = imagenes[num_img]; 
            
            System.out.println("--------------------------------------" + num_img);
            
            for(int i = 0 ; i < imagen.getHeight(); i++){
                for(int j = 0; j < imagen.getWidth(); j++){
                    
                    try{
                    imagen_creada.setRGB(posc_x_ini + j, posc_y_ini + i, imagen.getRGB(j, i));
                    }catch(ArrayIndexOutOfBoundsException e){
                        System.out.println((posc_x_ini + j ) + "   " + (posc_y_ini + i ));
                    }
                        
                }
                
                posc_x_ini = posc[num_img][0];
            }
            
        }
        
        return imagen_creada;
        
    }
    
}