package lc1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jeffrey
 */

public class LC1 {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Test variables
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        
        LC1 LC1 = new LC1();
        System.out.println(Arrays.toString(LC1.twoSum(nums, target)));        
        System.out.println(Arrays.toString(LC1.twoSumHash1(nums, target)));
        System.out.println(Arrays.toString(LC1.twoSumHash2(nums, target)));
    }
    
    public int[] twoSum(int[] nums, int target) {
        
        // Initialize the indicies array to contain [0 0]
        int[] indicies = new int[2];
        indicies[0] = 0;
        indicies[1] = 0;
        
        for(int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                if(nums[i] + nums[j] == target) {
                    indicies[0] = i;
                    indicies[1] = j;
                    return indicies;
                }
            }
        }
        return indicies;
    }
    
    public int[] twoSumHash1(int[] nums, int target) {
        
        // Initialize the indicies array to contain [0 0]
        int[] indicies = new int[2];
        indicies[0] = 0;
        indicies[1] = 0;
        
        Map <Integer, Integer> map = new HashMap<>();
        int complement;
        
        for(int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        
        for(int i = 0; i < nums.length; i++) {
            complement = target - nums[i];
            if(map.containsKey(complement) && map.get(complement) != i) {
                indicies[0] = i;
                indicies[1] = map.get(complement);
                return indicies;
            }
        }
        return indicies;
    }
    
    public int[] twoSumHash2(int[] nums, int target) {
        
        // Initialize the indicies array to contain [0 0]
        int[] indicies = new int[2];
        indicies[0] = 0;
        indicies[1] = 0;
        
        Map <Integer, Integer> map = new HashMap<>();
        int complement;
        
        for(int i = 0; i < nums.length; i++) {
            complement = target - nums[i];
            if(map.containsKey(complement)) {
                indicies[0] = map.get(complement);
                indicies[1] = i;
                return indicies;
            }
            map.put(nums[i], i);
        }
        return indicies;
    }
}