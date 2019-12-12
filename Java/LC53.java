package lc53;

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
}