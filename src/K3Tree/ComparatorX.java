/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package K3Tree;

import java.util.Comparator;

/**
 *
 * @author rae
 */
public class ComparatorX implements Comparator<K3TreeNode>{

    public ComparatorX(){}
    
    @Override
    public int compare(K3TreeNode o1, K3TreeNode o2) {
        return o1.getX() - o2.getX();
    }
    
    
}
