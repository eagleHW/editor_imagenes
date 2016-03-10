
package K3Tree;

import java.util.Comparator;

/**
 *
 * @author rae
 */
public class ComparatorZ implements Comparator<K3TreeNode>{

    public ComparatorZ(){}
    
    @Override
    public int compare(K3TreeNode o1, K3TreeNode o2) {
        return o1.getZ() - o2.getZ();
    }

    
    
}
