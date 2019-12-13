package lc53;

import java.util.Arrays;

/**
 *
 * @author Jeffrey
 */

public class LC53 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Test variables
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1 ,-5, 4};
        
        LC53 LC53 = new LC53();
        System.out.println(LC53.maxSubArray(nums));
        System.out.println(LC53.maxSubArrayAlt(nums));
    }
    
    public int maxSubArray(int[] nums) {
        
        // Corner cases
        if(nums == null) throw new IllegalArgumentException();
        if(nums.length == 0) throw new IllegalArgumentException();
        
        int maxEndingHere = nums[0];
        int maxSum = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            maxEndingHere = Math.max(maxEndingHere + nums[i], nums[i]);
            maxSum = Math.max(maxSum, maxEndingHere);
        }
        return maxSum;
    }
    
    public int maxSubArrayAlt(int[] nums) {
        
        // Corner cases
        if(nums == null) throw new IllegalArgumentException();
        if(nums.length == 0) throw new IllegalArgumentException();
        
        return subArray(nums, 0, nums.length - 1);
    }
    
    public int subArray(int[] nums, int L, int R) {
        
        if(L == R) return nums[L];
        
        int M = L + (R - L) / 2;
        
        // {-2, 1, -3, 4, -1, 2, 1, -5, 4}
        // |---------------|
        //       LSum      M  |----------|
        //                        RSum
        int LSum = subArray(nums, L, M);
        int RSum = subArray(nums, M + 1, R);
        int crossSum = crossSubArray(nums, L, R);
        
        // L is Max
        if(LSum >= RSum && LSum >= crossSum) return LSum;
        
        // R is Max
        if(RSum >= LSum && RSum >= crossSum) return RSum;
        
        // Cross is Max
        return crossSum;
    }
    
    public int crossSubArray(int[] nums, int L, int R) {
        
        int M = L + (R - L) / 2;
        
        int LSum = Integer.MIN_VALUE;
        int RSum = Integer.MIN_VALUE;
        int temp = 0;
        
        for(int i = M; i >= L; i--) {
            temp = temp + nums[i];
            
            if(LSum < temp) LSum = temp;
        }
        temp = 0;
        
        for(int i = M + 1; i <= R; i++) {
            temp = temp + nums[i];
            
            if(RSum < temp) RSum = temp;
        }
        return LSum + RSum;
    }
}
