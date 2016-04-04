package ManipulacionImagenes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import K3Tree.K3Tree;
import K3Tree.K3TreeNode;
import Vista.Interfaz;
import java.util.HashMap;
import javax.swing.UIManager;

/**
 *
 * @author rae
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        /*
        String path = "";
        try { 
            
            path = new File("").getCanonicalPath();
            System.out.println(path);
            System.out.println(path.lastIndexOf("home"));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
       /////new LectorArchivoFotomosaico("fotograma/");
       UIManager.put("OptionPane.yesButtonText", "Si"); 
       new Interfaz(); 
  
        HashMap<String,K3TreeNode> hash = null;
       
       K3Tree tree;
       K3TreeNode[] array = new K3TreeNode[1];
       
       /*
       
       try{
           
           FileInputStream fileIn = new FileInputStream("hashtable.ser");
           ObjectInputStream in = new ObjectInputStream(fileIn);
           hash = (HashMap) in.readObject();
           in.close();
           fileIn.close();
           
       }catch(IOException e){
           System.out.println("Error al leer el archivo");
           
       } catch (ClassNotFoundException ex) {
            System.out.println("Clase no encontrada");
       }
       
       tree = new K3Tree(hash.values());
       
       System.out.println("Total de valores : " + hash.values().size());
       
       //tree.print_dfs(tree.root);
       
       int x = 255;
       int y = 255;
       int z = 255;
       
       System.out.println("K3Tree : ");
       System.out.println(tree.search_nearest_neighbour(x, y, z).toString());
       System.out.println("Nodos visitados : " + tree.nodos_visitados());
       
       System.out.println("-------------------------------------------");
       
       System.out.println("Busqueda lineal : ");
       System.out.println(menor_distancia(hash.values().toArray(array),x,y,z).toString());
       
       */
       
       // Recuperar el archivo .ser
       // Pasarlo al constructor de K3Tree
       
       /* 
       Set<K3TreeNode> set = new HashSet();
       K3TreeNode[] array = new K3TreeNode[1];
       
       
       K3TreeNode n1 = new K3TreeNode(25,12,110,null);
       K3TreeNode n2 = new K3TreeNode(50,12,3,null);
       K3TreeNode n3 = new K3TreeNode(75,26,9,null);
       K3TreeNode n4 = new K3TreeNode(100,75,2,null);
       K3TreeNode n5 = new K3TreeNode(125,42,5,null);
       K3TreeNode n6 = new K3TreeNode(150,90,139,null);
       K3TreeNode n7 = new K3TreeNode(200,4,254,null);
       K3TreeNode n8 = new K3TreeNode(250,83,7,null);
       K3TreeNode n9 = new K3TreeNode(275,1,82,null);
       K3TreeNode n10 = new K3TreeNode(5,52,100,null);
       K3TreeNode n11 = new K3TreeNode(10,90,24,null);
       K3TreeNode n12 = new K3TreeNode(15,37,45,null);
       
       
       K3TreeNode nodo = new K3TreeNode(5,5,5,null);
       
       set.add(n1);
       set.add(n2);
       set.add(n3);
       set.add(n4);
       set.add(n5);
       set.add(n6);
       set.add(n7);
       set.add(n8);
       set.add(n9);
       set.add(n10);
       set.add(n11);
       set.add(n12);
       
       tree = new K3Tree(set);
        
       
       int x = 42;
       int y = 43;
       int z = 6;
               
       System.out.println("K3Tree : ");
       System.out.println(tree.search_nearest_neighbour(x, y, z).toString());
       
       System.out.println("Busqueda lineal : ");
       System.out.println(menor_distancia(set.toArray(array),x,y,z).toString());
       
       System.out.println("---------------------");
       
       tree.print_dfs(tree.root);
       */
       /*
       BibliotecaGrafica bg = new BibliotecaGrafica();
       
       
       int[][] matrizA = {{1,  3, 3,4,  5,24},
                          {1,  3, 8,9, 10,24},
                          {1,12,13,14,3,24},
                          {1,17,18,3,20,24},
                          {1,3,23,24,25,24}};
       
       int[][] matrizB = {{1,  0, 0, 0, 0},
                          {0,  1, 0, 0, 0},
                          { 0, 0, 1, 0, 0},
                          { 0, 0, 0, 1, 0},
                          { 0, 0, 0, 0, 1}};
       
   
       System.out.println(bg.getMaxFrecuence(matrizA));
       
       int[][] res = bg.getEdgeCompMatrix(0, 0, 5, 6, matrizA);
        
       int[][] matrizC = res;
        
       for(int i = 0; i < matrizC.length; i++){
           
            System.out.println(Arrays.toString(matrizC[i]));
           
       }
       */
    }    
    
    public static K3TreeNode menor_distancia(K3TreeNode[] nodos, int x, int y, int z){
        
        K3TreeNode minimo = null;
        
        for (K3TreeNode nodo : nodos) {
            
            if(minimo == null){
                minimo = nodo;
            }else{
                
               minimo =  medir_distancia(nodo,x,y,z) < medir_distancia(minimo,x,y,z) ? nodo : minimo;
               
            }
                        
        }
      
        return minimo;
        
    }
    
    private static double medir_distancia(K3TreeNode nodo, int x,int y,int z){
        
        return Math.pow((nodo.getX() - x),2) + Math.pow((nodo.getY() - y),2) + Math.pow((nodo.getZ() - z),2);
    
    }      
    
    
}
