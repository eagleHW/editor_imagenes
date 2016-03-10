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
public class ComparatorY implements Comparator<K3TreeNode>{

    public ComparatorY(){}
    
    @Override
    public int compare(K3TreeNode o1, K3TreeNode o2) {
          return o1.getY() - o2.getY();
    }
    
    
}
