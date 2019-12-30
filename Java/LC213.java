package lc213;

/**
 *
 * @author Jeffrey
 */

public class LC213 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Test variables
        int[] nums = {2, 3, 2};
        
        LC213 LC213 = new LC213();
        System.out.println(LC213.rob(nums));
    }

    public int rob(int[] nums) {
        
        // Corner cases
        if(nums == null) return 0;
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        
        return Math.max(robHelper(nums, 0, nums.length - 2), 
                robHelper(nums, 1, nums.length - 1));
    }
    
    public int robHelper(int[] nums, int L, int H) {
        
        int include = 0;
        int exclude = 0;
        int inc = 0;
        int exc = 0;
        
        for(int i = L; i < H + 1; i++) {
            inc = include;
            exc = exclude;
            include = exc + nums[i];
            exclude = Math.max(exc, inc);
        }
        return Math.max(include, exclude);
    }
}