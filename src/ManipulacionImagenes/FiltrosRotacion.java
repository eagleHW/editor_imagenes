/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManipulacionImagenes;

import java.awt.image.BufferedImage;

/**
 *
 * @author rae
 */
public class FiltrosRotacion {
    
    private BibliotecaGrafica bg = new BibliotecaGrafica();
    
    public FiltrosRotacion(){}
    
    public BufferedImage filtro_rotacion90(BufferedImage imagen_original){
        
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
    
    public BufferedImage filtro_rotacion180(BufferedImage imagen_original){

       
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

    public BufferedImage filtro_rotacion270(BufferedImage imagen_original){
           
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

    public BufferedImage filtro_rotacion_matriz(BufferedImage imagen, int grados){
        
        // Rotaciones sin perdida de información
        switch(grados){    
            case 0 : case 360 :
                return imagen;
            case 90:
                return filtro_rotacion90(imagen);
            case 180:
                return filtro_rotacion180(imagen);
            case 270:
                return filtro_rotacion270(imagen);
        }
        
        BufferedImage nueva_imagen;
        int[][] referencia;
        
        double[][] matriz_rotacion = new double[2][2];  
        double radianes = Math.toRadians(-grados);
        
        double[] vector = new double[2];
        double[] vector_resultado;
        
        // Orillas de la imagen
        double[][] edges = {{0,0} , {0,imagen.getHeight()}, {imagen.getWidth(),0}, {imagen.getWidth(), imagen.getHeight()}};
        // Posiciones de las orillas rotadas de la imagen
        double[][] rotated_edges = new double[4][2];
        double[] rotated_edge;
        int[] minium_edges;
        int[] maxium_edges;
        
        matriz_rotacion[0][0] = Math.cos(radianes);
        matriz_rotacion[1][0] = Math.sin(radianes);
        matriz_rotacion[0][1] = -Math.sin(radianes);
        matriz_rotacion[1][1] = Math.cos(radianes);
       
        for (int i = 0; i < edges.length; i++) {
            
            rotated_edge = multiplicacion_matriz(matriz_rotacion, edges[i]);
            rotated_edges[i][0] =  rotated_edge[0];
            rotated_edges[i][1] = rotated_edge[1];
        }
             
        minium_edges = find_minium_edges(rotated_edges); 
        maxium_edges = find_maxium_edges(rotated_edges);
                
        nueva_imagen = new BufferedImage( Math.abs(minium_edges[0]) + Math.abs(maxium_edges[0]),
                                             Math.abs(minium_edges[1]) + Math.abs(maxium_edges[1]),BufferedImage.TYPE_INT_RGB);
        referencia = new int[ Math.abs(minium_edges[1]) + Math.abs(maxium_edges[1])][ Math.abs(minium_edges[0]) + Math.abs(maxium_edges[0])]; 
        
        for(int i = 0 ; i < imagen.getHeight(); i++){
            for(int j = 0; j < imagen.getWidth(); j++){
            
                vector[0] = j;
                vector[1] = i;
                vector_resultado = multiplicacion_matriz(matriz_rotacion, vector);
                   
                if((int)(vector_resultado[0]) + Math.abs(minium_edges[0]) < nueva_imagen.getWidth()
                        && (int)(vector_resultado[1]) + Math.abs(minium_edges[1]) < nueva_imagen.getHeight()){
                
                     nueva_imagen.setRGB((int)(vector_resultado[0]) + Math.abs(minium_edges[0]),
                                         (int)(vector_resultado[1]) + Math.abs(minium_edges[1]) ,
                                         imagen.getRGB(j,i));
                    referencia[(int)(vector_resultado[1]) + Math.abs(minium_edges[1]) ]
                                            [(int)(vector_resultado[0]) + Math.abs(minium_edges[0])] = 1;
                }
            }
        }
        
        interpolacion_bilinear(nueva_imagen,referencia);
        return nueva_imagen;
    }
    
    private double[] multiplicacion_matriz(double[][] matriz, double[] vector){
   
        double[] resultado = new double[vector.length]; 
        double suma_fila = 0;
        
        
        for(int fila = 0; fila < matriz.length ; fila++){
            for(int columna = 0 ; columna < vector.length ; columna++){
                
                suma_fila += matriz[fila][columna] * vector[columna];
            
            }

            resultado[fila] = suma_fila;
            suma_fila = 0;
        }
        
        return resultado;
        
    }
   
    private int[] find_minium_edges(double[][] rotated_edges){
        
        int[] minimos = {Integer.MAX_VALUE,Integer.MAX_VALUE};
        
        for(double[] edge : rotated_edges){
           
            minimos[0] = Integer.min(minimos[0], (int) edge[0]);
            minimos[1] = Integer.min(minimos[1], (int) edge[1]);
            
        }
        
        return minimos;
        
    }
    
    private int[] find_maxium_edges(double[][] rotated_edges){
        
        int[] maximos = {Integer.MIN_VALUE,Integer.MIN_VALUE};
        
        for(double[] edge : rotated_edges){
           
            maximos[0] = Integer.max(maximos[0], (int) edge[0]);
            maximos[1] = Integer.max(maximos[1], (int) edge[1]);
            
        }
        
        return maximos;
        
    }
    
    // Referencia sirve para aplicar la interpolacion solo a los puntos que no fueron afectados 
    // durante la transformacion ( los puntos que quedan en negro en la imagen )
    private void interpolacion_bilinear(BufferedImage imagen, int[][] referencia){
        
        int height = imagen.getHeight();
        int width = imagen.getWidth();
        
        BufferedImage copia = bg.copiar_imagen(imagen);
        
        int arriba, abajo, izquierda, derecha;
        int red, green, blue;
        
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                
                if(referencia[i][j] != 1){
                
                arriba = i-1 > 0 ? copia.getRGB(j, i-1) : bg.getARGBNum(255, 255, 255, 255);
                abajo = i+1 < height ? copia.getRGB(j, i+1) : bg.getARGBNum(255, 255, 255, 255);
                izquierda = j-1 > 0 ? copia.getRGB(j-1, i) : bg.getARGBNum(255, 255, 255, 255);
                derecha = j+1 < width ? copia.getRGB(j+1, i) : bg.getARGBNum(255, 255, 255, 255);
                
                red = (bg.getRedNum(arriba) + bg.getRedNum(abajo) + bg.getRedNum(izquierda) + bg.getRedNum(derecha))/4; 
                green = (bg.getGreenNum(arriba) + bg.getGreenNum(abajo) + bg.getGreenNum(izquierda) + bg.getGreenNum(derecha))/4;
                blue = (bg.getBlueNum(arriba) + bg.getBlueNum(abajo) + bg.getBlueNum(izquierda) + bg.getBlueNum(derecha))/4;
                        
                imagen.setRGB(j, i, bg.getARGBNum(255, red, green, blue));
                
                }
            }
        }
    }
    
}