package lc198;

import java.util.Arrays;

/**
 *
 * @author Jeffrey
 */

public class LC198 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Test variables
        int[] nums = {1, 2, 3, 1};
        
        LC198 LC198 = new LC198();
        System.out.println(LC198.rob(nums));
        System.out.println(LC198.robDPTD(nums));
        System.out.println(LC198.robDPBU(nums));
        System.out.println(LC198.robDPBUConstantSpace(nums));
    }

    public int rob(int[] nums) {
        
        return robHelper(nums.length - 1, nums);
    }
    
    public int robHelper(int i, int[] nums) {
        
        if(i < 0) return 0;
        
        return Math.max(robHelper(i - 2, nums) + nums[i], 
                robHelper(i - 1, nums));
    }
    
    public int robDPTD(int[] nums) {
        
        int[] memo = new int[nums.length + 1];
        Arrays.fill(memo, -1);
        
        return robDPTDHelper(nums.length - 1, nums, memo);
    }
    
    public int robDPTDHelper(int i, int[] nums, int[] memo) {
        
        if(i < 0) return 0;
        if(memo[i] > -1) return memo[i];
        
        memo[i] =  Math.max(robDPTDHelper(i - 2, nums, memo) + nums[i], 
                robDPTDHelper(i - 1, nums, memo));
        
        return memo[i];
    }
    
    public int robDPBU(int[] nums) {
        
        if(nums.length == 0) return 0;
        
        int[] memo = new int[nums.length + 1];
       
        memo[0] = 0;
        memo[1] = nums[0];
        int val = 0;
        
        for(int i = 1; i < nums.length; i++) {
            val = nums[i];
            memo[i + 1] = Math.max(memo[i], memo[i - 1] + val);
        }
        return memo[nums.length];
    }
    
    public int robDPBUConstantSpace(int[] nums) {
        
        if (nums.length == 0) return 0;
        
        int prev1 = 0;
        int prev2 = 0;
        int temp1 = 0;
        
        for(int num : nums) {
            temp1 = prev1;
            prev1 = Math.max(prev2 + num, prev1);
            prev2 = temp1;
        }
        return prev1;
    }
}