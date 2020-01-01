package lc377;

import java.util.Arrays;

/**
 *
 * @author Jeffrey
 */

public class LC377 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Test variables
        int[] nums = {1, 2, 3};
        int target = 4;
        
        LC377 LC377 = new LC377();
        System.out.println(LC377.combinationSum4(nums, target));
        System.out.println(LC377.combinationSum4DPTD(nums, target));
        System.out.println(LC377.combinationSum4DPBU(nums, target));
    }
    
    public int combinationSum4(int[] nums, int target) {
        
        return combinationSum4Helper(nums, target);
    }
    
    public int combinationSum4Helper(int[] nums, int target) {
        
        if(target <  0) return 0;
        if(target == 0) return 1;
        
        int count = 0;
            
        for(int i = 0; i < nums.length; i++) {
            count = count + combinationSum4Helper(nums, target - nums[i]);
        }
        return count;
    }
    
    public int combinationSum4DPTD(int[] nums, int target) {
        
        int[] memo = new int[target + 1];
        Arrays.fill(memo, -1);
        return combinationSum4DPTDHelper(nums, target, memo);
    }
    
    public int combinationSum4DPTDHelper(int[] nums, int target, int[] memo) {
        
        if(target <  0) return 0;
        if(target == 0) return 1;
        if(memo[target] != -1) return memo[target];
        
        int count = 0;
            
        for(int i = 0; i < nums.length; i++) {
            count = count + combinationSum4DPTDHelper(nums, target - nums[i], 
                    memo);
        }
        return memo[target] = count;
    }
    
    public int combinationSum4DPBU(int[] nums, int target) {
        
        int[] memo = new int[target + 1];
        Arrays.fill(memo, 0);
        memo[0] = 1;
        
        for(int i = 1; i < target + 1; i++) {
            int count = 0;
            
            for(int num : nums) {
                if(i - num >= 0) count = count + memo[i - num];
            }
            memo[i] = count;
        }
        return memo[target];
    }
}