package lc153;

/**
 *
 * @author Jeffrey
 */

public class LC153 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Test variables
        int[] nums = new int[]{3, 4, 5, 1, 2};
        
        LC153 LC153 = new LC153();
        System.out.println(LC153.findMin(nums));
    }

    public int findMin(int[] nums) {
        
        // Corner case
        if(nums.length == 1)  return nums[0];
        
        // Initial values
        int L = 0;
        int R = nums.length - 1;
        
        while(L < R) {
            int M = (L + R) / 2;
            
            if(nums[M] > nums[R])
                L = M + 1;
            else
                R = M;
        }
        return nums[R];
    }
}