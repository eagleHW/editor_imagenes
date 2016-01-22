/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import java.util.Arrays;

/**
 *
 * @author rae
 */
public class Matriz {
    
    int[][] matriz;
    
    public Matriz(int[][] matriz){
        
        this.matriz = matriz;
        
    }
    
    public Matriz  trasponer(){
   
    int[][] new_matriz = new int[matriz.length][matriz[0].length]; 
        
    for(int i = 0; i < matriz.length; i++){
        for(int j = 0; j < matriz[0].length; j++){
            
            new_matriz[i][j] = matriz[j][i];
        }
    }
   
    return new Matriz(new_matriz);
    
    }
    
    public Matriz multiplicar(Matriz matriz){
        
        if( this.matriz[0].length != matriz.num_rows() ){
             // Levantar excepcion
        }
        
        int[][] new_matriz = new int[this.matriz.length][matriz.num_colums()]; 
        
        int entry;
        
        for(int i = 0; i < this.matriz.length; i++){

            for( int j = 0; j < matriz.num_rows(); j++){

                entry = 0;
                
                for(int n = 0; n < this.matriz.length ; n++){
                
                    entry += this.matriz[i][n] * matriz.get_entry(n, j);
                    
                }
                
               new_matriz[i][j] = entry; 
                
            }
            
        }
        
        return new Matriz(new_matriz);
    }
    
    public int sumar_entradas(){
        
        int sumar_entradas = 0;
        
        for(int i = 0; i < matriz.length; i++){
            for(int j = 0; j < matriz[0].length ; j++){
                
                sumar_entradas += matriz[i][j];
                
            }
        }
           
        return sumar_entradas;
    }
    
     public int num_rows(){
         
         return matriz.length;
         
     }
    
     public int num_colums(){
         
         return matriz[0].length;
     }
     
     public int get_entry(int i, int j){
         
         return matriz[i][j];
         
     }
     
     public void print(){
         
         for(int i = 0; i < matriz.length; i++){
     
             System.out.println(Arrays.toString(matriz[i]));
         }
         
      }
     
     
}
