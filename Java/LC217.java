package lc217;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Jeffrey
 */

public class LC217 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Test variables
        int[] nums = new int[]{1, 2, 3, 1};
        
        LC217 LC217 = new LC217();
        System.out.println(LC217.containsMatch(nums));
        System.out.println(LC217.containsMatchSort(nums));
        System.out.println(LC217.containsMatchHash(nums));
    }

    public boolean containsMatch(int[] nums) {
        
        for(int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                if(nums[i] == nums[j]) return true;
            }
        }
        return false;
    }
    
    public boolean containsMatchSort(int[] nums) {
        
        Arrays.sort(nums);
        
        for(int i = 0; i < nums.length - 1; i++) {
            if(nums[i] == nums [i + 1]) return true;
        }
        return false;
    }
    
    public boolean containsMatchHash(int[] nums) {
        
        Set<Integer> values = new HashSet<>(nums.length);
        
        for(int x : nums) {
            if(values.contains(x)) return true;
            values.add(x);
        }
        return false;
    }
}