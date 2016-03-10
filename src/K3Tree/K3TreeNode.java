/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package K3Tree;

import java.io.Serializable;

/**
 *
 * 
 * @author rae
 * @param <E>
 */
public class K3TreeNode<E extends Serializable> implements Serializable{
   
    
    private int x;
    private int y;
    private int z;
    private E atributo;
    private K3TreeNode<E> left;
    private K3TreeNode<E> right;
    
    public K3TreeNode(int x, int y ,int z, E atributo){
        
        this.x = x;
        this.y = y;
        this.z = z;
        this.atributo = atributo;
        this.left = null;
        this.right = null;
       
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public E getAtributo() {
        return atributo;
    }

    public K3TreeNode<E> getLeft() {
        return left;
    }

    public K3TreeNode<E> getRight() {
        return right;
    }

    public void setAtributo(E atributo) {
        this.atributo = atributo;
    }

    public void setLeft(K3TreeNode<E> left) {
        this.left = left;
    }

    public void setRight(K3TreeNode<E> right) {
        this.right = right;
    }
    
    @Override
    public boolean equals(Object obj){
        
        K3TreeNode elemento;
        
        if(!( obj instanceof K3TreeNode )){
            return false;
        }
        
        elemento = (K3TreeNode) obj;
        
        if(elemento.getX() == x && elemento.getY() == y && elemento.getZ() == z){
            return true;
        } 
       
        return false;
    }
    
    
    @Override
    public String toString(){
    
        return "( X: " + x + " , Y: " + y + " , Z: " + z + " )" + atributo.toString();
        
    }
    
}
