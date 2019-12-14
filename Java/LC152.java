package lc152;

/**
 *
 * @author Jeffrey
 */

public class LC152 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Test variables
        int[] nums = new int[]{2, 3, -2, 4};
        
        LC152 LC152 = new LC152();
        System.out.println(LC152.max(nums));
    }

    public int max(int[] nums) {
        
        // Corner cases
        if(nums == null) return 0;
        if(nums.length == 0) return 0;
        
        int max = nums[0];
        int min = nums[0];
        int totalMax = nums[0];
        
        int tempMax;
        int tempMin;
        
        // (-1) * (-1) = (1)
        for (int i = 1; i < nums.length; i++) {
            tempMax = nums[i] * max;
            tempMin = nums[i] * min;
            max = Math.max(Math.max(tempMax,tempMin), nums[i]);
            min = Math.min(Math.min(tempMax,tempMin), nums[i]);
            totalMax = Math.max(totalMax, max);
        }
        return totalMax;
    }
}