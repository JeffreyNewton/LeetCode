package lc268;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Jeffrey
 */

public class LC268 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Test variables
        int[] nums = new int[]{3, 0, 1};
        
        LC268 LC268 = new LC268();
        System.out.println(LC268.missingNumber(nums)); 
        System.out.println(LC268.missingNumberHashSet(nums));
        System.out.println(LC268.missingNumberAlt(nums)); 
        System.out.println(LC268.missingNumberGaussFormula(nums)); 
    }

    public int missingNumber(int[] nums) {

        Arrays.sort(nums);
        
        // Corner cases
        if(nums.length == 0) return 0;
        if(nums[0] != 0) return 0;
        if(nums[nums.length - 1] == nums.length - 1) return nums.length;
        
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] + 1 != nums[i + 1]) {
                return nums[i] + 1;
            }
        }
        return -1;
    }
    
    public int missingNumberHashSet(int[] nums) {

        Set<Integer> numSet = new HashSet<>();
        
        // Corner cases
        if(nums.length == 0) return 0;

        for(int num : nums) {
            numSet.add(num);
        }
        
        for(int i = 0; i < nums.length; i++) {
            if(!numSet.contains(i)) return i;
        }
        return nums.length;
    }
    
    public int missingNumberAlt(int[] nums) {

        int missingNum = nums.length;
        
        for(int i = 0; i < nums.length; i++) {
            
            // XOR is its own inverse
            //
            // {0, 1, 3}
            // nums.length = 3 ^ (0 ^ 0) ^ (1 ^ 1) ^ (2 ^ 3)
            // (3 ^ 3) ^ (0 ^ 0) ^ (1 ^ 1) ^ 2
            missingNum = missingNum ^ (i ^ nums[i]);
        }
        return missingNum;
    }
    
    public int missingNumberGaussFormula(int[] nums) {
        
        // Gauss Formula
        // i = n * (n + 1) / 2
        int expected = nums.length * (nums.length + 1) / 2;
        int sum = 0;
        
        for(int num : nums) sum = sum + num;
        return expected - sum;
    }
}