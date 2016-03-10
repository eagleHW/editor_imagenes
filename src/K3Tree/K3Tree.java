package K3Tree;

import K3Tree.ComparatorZ;
import K3Tree.ComparatorY;
import K3Tree.ComparatorX;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 *
 * @author rae
 */

/* Para referencias https://en.wikipedia.org/wiki/K-d_tree */

public class K3Tree {
    
    private Collection<K3TreeNode> conjunto_puntos;
    private K3TreeNode[] array_puntos = new K3TreeNode[1];
    private LinkedList<K3TreeNode> puntos_x; // Puntos ordenados por x 
    private LinkedList<K3TreeNode> puntos_y; // Puntos ordenados por y
    private LinkedList<K3TreeNode> puntos_z; // Puntos ordenados por z
    public K3TreeNode root;
    
    int nodos_visitados = 0;
    
    public K3Tree(Collection<K3TreeNode> conjunto_puntos){
        
        this.conjunto_puntos = conjunto_puntos;
        array_puntos = conjunto_puntos.toArray(array_puntos);
        // Ordenar puntos por x 
        Arrays.sort(array_puntos,new ComparatorX());
        this.puntos_x = array_to_list(array_puntos);
        //System.out.println(Arrays.toString(array_puntos));
        // Ordenar puntos por y
        Arrays.sort(array_puntos,new ComparatorY());
        this.puntos_y = array_to_list(array_puntos);
        //System.out.println(Arrays.toString(array_puntos));
        // Ordenar puntos por z
        Arrays.sort(array_puntos,new ComparatorZ());
        this.puntos_z = array_to_list(array_puntos);
        //System.out.println(Arrays.toString(array_puntos));
        // Mandar a llamar a createk3Tree con profundidad 0 
        // Asignar el resultado de K3TreeNode a root
        this.root = create_K3Tree(puntos_x,puntos_y,puntos_z,0);
    }
    
    private K3TreeNode create_K3Tree(LinkedList<K3TreeNode> puntos_x,LinkedList<K3TreeNode> puntos_y,
                                                               LinkedList<K3TreeNode> puntos_z, int profundidad){

       
       K3TreeNode nodo;
       LinkedList<K3TreeNode>[] x;
       LinkedList<K3TreeNode>[] y;
       LinkedList<K3TreeNode>[] z;
              
       //System.out.println("Profundidad : " + profundidad );
       
       switch(profundidad % 3){
              
           // X
           case 0 :
           
               if(puntos_x.size() == 0){
                   //System.out.println("X : null\n");
                   return null;
               }else{
                   
                   nodo = find_media(puntos_x,profundidad);
                   //System.out.println("/-----------------------");
                   //System.out.println("X : " + nodo);
                   
                   puntos_x.remove(nodo);
                   puntos_y.remove(nodo);
                   puntos_z.remove(nodo);

                   x = split_list_media(puntos_x, nodo.getX(), profundidad); 
                   y = split_list_media(puntos_y,nodo.getX(),profundidad);
                   z = split_list_media(puntos_z,nodo.getX(),profundidad);
                   
                   
                   //System.out.println(Arrays.toString(x));
                   //System.out.println(Arrays.toString(y));
                   //System.out.println(Arrays.toString(z));
                   //System.out.println("/-----------------------");
                   
                   
                   nodo.setLeft(create_K3Tree(x[0],y[0],z[0], profundidad + 1));
                   nodo.setRight(create_K3Tree(x[1],y[1],z[1], profundidad + 1));
                   
                   
               }
               
               break;
           // Y
           case 1 :
               
               if(puntos_y.size() == 0){
                   ////System.out.println("Y : null\n");
                   return null;
               }else{
              
                   nodo = find_media(puntos_y,profundidad);
                   //System.out.println("/-----------------------");
                   //System.out.println("Y : " + nodo);
                   
                   puntos_x.remove(nodo);
                   puntos_y.remove(nodo);
                   puntos_z.remove(nodo);
      
                   x = split_list_media(puntos_x, nodo.getY(), profundidad); 
                   y = split_list_media(puntos_y,nodo.getY(),profundidad);
                   z = split_list_media(puntos_z,nodo.getY(),profundidad);

                   
                   //System.out.println(Arrays.toString(x));
                   //System.out.println(Arrays.toString(y));
                   //System.out.println(Arrays.toString(z));
                   //System.out.println("/-----------------------");
                   
                   
                   nodo.setLeft(create_K3Tree(x[0],y[0],z[0], profundidad + 1));
                   nodo.setRight(create_K3Tree(x[1],y[1],z[1], profundidad + 1));
                                      
               }
               
               break;
           // Z
           case 2 :
         
               if(puntos_z.size() == 0){
                   //System.out.println("Z : null\n");
                   return null;
               }else{
                   
                   
                   nodo  = find_media(puntos_z,profundidad);   
                   //System.out.println("/-----------------------");
                   //System.out.println("Z : " + nodo);
                   
                    puntos_x.remove(nodo);
                    puntos_y.remove(nodo);
                    puntos_z.remove(nodo);
                   
                    x = split_list_media(puntos_x, nodo.getZ(), profundidad); 
                    y = split_list_media(puntos_y,nodo.getZ(),profundidad);
                    z = split_list_media(puntos_z,nodo.getZ(),profundidad);

                    
                    //System.out.println(Arrays.toString(x));
                    //System.out.println(Arrays.toString(y));
                    //System.out.println(Arrays.toString(z)); 
                    //System.out.println("/-----------------------");
                    
                    
                    nodo.setLeft(create_K3Tree(x[0],y[0],z[0], profundidad + 1));
                    nodo.setRight(create_K3Tree(x[1],y[1],z[1], profundidad + 1));
                    
                    
               }
              
               break;  
           default :
               nodo = null;
               //System.out.println("Aqui sucedio un error");
       }
       
       //System.out.println("Fin profundidad : " + profundidad);
       
       return nodo;
   }
    
    private LinkedList<K3TreeNode> array_to_list(K3TreeNode[] puntos){
       
       LinkedList<K3TreeNode> lista = new LinkedList();
       
       for (K3TreeNode punto : puntos) {
           lista.addLast(punto);
       }
       
       return lista;
   }
  
    private K3TreeNode find_media( LinkedList<K3TreeNode> lista, int profundidad){
       
       if(lista.size() == 0 ){
           return null;
       }
       
       int posicion_media = lista.size() / 2;
       ListIterator<K3TreeNode> iterador = lista.listIterator(posicion_media); 
       int valor_entrada = iterador.next().getX();
       
       int movimiento_adelante = iterador.hasNext() ? 1 : 0;

       switch(profundidad % 3){
           
           case 0:
               
               while(iterador.hasNext() && (iterador.next().getX() == valor_entrada) ){
                   movimiento_adelante = iterador.hasNext() ? 1 : 0;      
               }
               
               break;
               
           case 1:
               
               while(iterador.hasNext() && (iterador.next().getY() == valor_entrada) ){
                  movimiento_adelante = iterador.hasNext() ? 1 : 0;      
               }
               
               break;
               
           case 2:
               
               while(iterador.hasNext() && (iterador.next().getZ() == valor_entrada) ){
                  movimiento_adelante = iterador.hasNext() ? 1 : 0;               
               }
               
               break;
               
           default :
               
               //System.out.println("Error plano inexistente");
               return null;
                  
       }
       
       if(movimiento_adelante == 1){
           iterador.previous();
       }
       
       return iterador.previous();
       
   } 

    // WARNING : La lista pasada como parametro sera modificada
    // Los elementos que tengan un valor igual a la media estaran en la lista izquierda
    private LinkedList<K3TreeNode>[] split_list_media(LinkedList<K3TreeNode> lista, int media, int profundidad ){
       
       if(lista.size() == 0){
           LinkedList[] vacio = {new LinkedList(), new LinkedList()};
           return vacio;
           
       }
     
       LinkedList before_media_lista = new LinkedList<K3TreeNode>(); // Elementos con valor igual a la media incluidos
       ListIterator<K3TreeNode> iterador = lista.listIterator();
       K3TreeNode elemento;  
       LinkedList[] splited_list = {before_media_lista,lista};
       
       
       switch(profundidad % 3){
           
           case 0:
               
               while(iterador.hasNext()){  
                   
                   elemento = iterador.next();
                   
                   if(elemento.getX() <= media){
                       iterador.remove();
                       before_media_lista.add(elemento);
                   }
                        
               }
                       
               break;
           case 1:
               
                while(iterador.hasNext()){  
                   
                   elemento = iterador.next();
                   
                   if(elemento.getY() <= media){
                       iterador.remove();
                       before_media_lista.add(elemento);
                   }
                        
               }
               
               break;
           case 2:
               
                while(iterador.hasNext()){  
                   
                   elemento = iterador.next();
                   
                   if(elemento.getZ() <= media){
                       iterador.remove();
                       before_media_lista.add(elemento);
                   }
                        
               }
               
               break;
           
       }
       
       
       return splited_list;
       
   }
   
  
    public K3TreeNode search_nearest_neighbour(int x, int y, int z){
        
        ////System.out.println(root.toString());
        return search_nearest_neighbour(x,y,z,root,0);
        
    }
    
    private K3TreeNode search_nearest_neighbour(int x, int y, int z, K3TreeNode nodo, int profundidad){
        
        nodos_visitados++;
        
        // dm = distancia minima
        K3TreeNode candidato_dm;
        K3TreeNode rama_candidato_dm;
        boolean nodo_derecho_revisado = false;
        
        int valor_eje_punto = 0;
        int valor_eje_nodo = 0;

        
        switch(profundidad % 3){
        
        case 0:

            valor_eje_punto = x;
            valor_eje_nodo = nodo.getX();
            
            break;
        case 1:

            valor_eje_punto = y;
            valor_eje_nodo = nodo.getY();
            
            break;
        case 2:
            
            valor_eje_punto = z;
            valor_eje_nodo = nodo.getZ();
            
            break;
        default:
            //  Esto matematicamente no puede pasar
        }
        
        if(valor_eje_punto <= valor_eje_nodo){
                
                if(nodo.getLeft() != null){
                    candidato_dm = search_nearest_neighbour(x,y,z,nodo.getLeft(), profundidad + 1);   
                }else{
                    //System.out.println(nodo.toString() + "HOJA");
                    return nodo;
                }
                
            }else{
                
                if(nodo.getRight() != null){
                    candidato_dm = search_nearest_neighbour(x,y,z,nodo.getRight(), profundidad + 1);
                    nodo_derecho_revisado = true;
                }else{
                    //System.out.println(nodo.toString() + "HOJA");
                    return nodo;
                }
                
            }
        
        
        candidato_dm = medir_distancia(nodo,x,y,z) <  medir_distancia(candidato_dm,x,y,z) ? nodo : candidato_dm;
          
        if ( Math.pow(valor_eje_punto-valor_eje_nodo,2) < medir_distancia(candidato_dm,x,y,z)){
        
           if(nodo_derecho_revisado){
            
               if(nodo.getLeft() != null ){
                   
                   rama_candidato_dm = search_nearest_neighbour(x,y,z, nodo.getLeft(), profundidad + 1);
                   candidato_dm = medir_distancia(rama_candidato_dm,x,y,z) < medir_distancia(candidato_dm,x,y,z) ?                   
                           rama_candidato_dm : candidato_dm; 
               
               }         
               
           }else{
            
               if(nodo.getRight() != null){
               
                   rama_candidato_dm = search_nearest_neighbour(x,y,z, nodo.getRight(), profundidad + 1);
                   candidato_dm = medir_distancia(rama_candidato_dm,x,y,z) < medir_distancia(candidato_dm,x,y,z) ? 
                           rama_candidato_dm : candidato_dm; 
                   
               }      
           }   
        }
        
        ////System.out.println(candidato_dm.toString());
        return candidato_dm;
 
    }
    
    private double medir_distancia(K3TreeNode nodo, int x,int y,int z){
        
        return Math.pow((nodo.getX() - x),2) + Math.pow((nodo.getY() - y),2) + Math.pow((nodo.getZ() - z),2);
    
  
    }

    public void print_dfs(K3TreeNode nodo){

        System.out.println(nodo.toString());
        
        if(nodo.getLeft() != null){
            print_dfs(nodo.getLeft());
        }else{
            System.out.println("NULL");
        }
        
        if(nodo.getRight() != null){
            print_dfs(nodo.getRight());
        }else{
            System.out.println("NULL");
        }


    }
    
    public int nodos_visitados(){

        return nodos_visitados;

    }
    
               
}
    




 /*
   public static void main(String[] args){
       
       
       K3TreeNode[] array_ktreenode = new K3TreeNode[7];
       LinkedList[] array;
       LinkedList<K3TreeNode> lista;
       K3TreeNode media;
       
       
       array_ktreenode[0] = new K3TreeNode(1,2,1,null);
       array_ktreenode[1] = new K3TreeNode(1,3,22,null);
       array_ktreenode[2] = new K3TreeNode(7,3,3,null);
       array_ktreenode[3] = new K3TreeNode(2,4,8,null);
       array_ktreenode[4] = new K3TreeNode(5,4,5,null);
       array_ktreenode[5] = new K3TreeNode(0,34,6,null);
       array_ktreenode[6] = new K3TreeNode(0,3,1,null);
       
       K3TreeNode nodo = new K3TreeNode(5,5,5,null);
   
       Arrays.sort(array_ktreenode, new ComparatorX());
       lista = array_to_list(array_ktreenode);
       media = find_media(lista, 0);
   
       Arrays.sort(array_ktreenode, new ComparatorY());
       lista = array_to_list(array_ktreenode);
       
       array = split_list_media(lista,7,0); 
       
       //System.out.println(array[0]);
       //System.out.println(array[1]);
       
   }
   */