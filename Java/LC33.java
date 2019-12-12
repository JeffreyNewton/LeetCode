package lc33;

/**
 *
 * @author Jeffrey
 */

public class LC33 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Test variables
        int[] nums = new int[]{4, 5, 6, 7, 0, 1 , 2};
        int target = 0;
        
        LC33 LC33 = new LC33();
        System.out.println(LC33.search(nums, target));
    }

    public int search(int[] nums, int target) {
        
        // Corner cases
        if(nums == null) return -1;
        if(nums.length == 0) return -1;
        
        int L = 0;
        int R = nums.length - 1;
            
        while(L < R) {
            int midIndex = (L + R) / 2;
            if(nums[midIndex] == target) return midIndex;
            
            // Array values are ascending on the L side
            if(nums[L] <= nums[midIndex]) {
                
                // If the target falls between the L and midIndex...
                if(target >= nums[L] && target < nums[midIndex]) 
                    R = midIndex - 1;
                else 
                    L = midIndex + 1;
            }
            
            // Array values are ascending on the R side
            else {
                
                // If the target falls between the midIndex and R...
                if(target > nums[midIndex] && target <= nums[R])
                    L = midIndex + 1;
                else
                    R = midIndex - 1;
            }
        }
        return nums[L] == target ? L : -1;
    }
}