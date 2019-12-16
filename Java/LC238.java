package lc238;

import java.util.Arrays;

/**
 *
 * @author Jeffrey
 */

public class LC238 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Test variables
        int[] nums = new int[]{1, 2, 3, 4};
        
        LC238 LC238 = new LC238();
        System.out.println(Arrays.toString(LC238.productExceptSelf(nums)));
    }
    
    public int[] productExceptSelf(int[] nums) {
        
        // Initial variables
        int size = nums.length;
        
        int[] L = new int[size];
        int[] R = new int[size];
        
        int[] answer = new int[size];
        
        // {1, 2, 3, 4}
        //  x  -------
        //  -  x  ----
        //  ----  x  -
        //  -------  x
        
        L[0] = 1;
        
        for(int i = 1; i < size; i++) {
            L[i] = nums[i - 1] * L[i - 1];
        }
               
        R[size - 1] = 1;
        
        for(int i = size - 2; i >= 0; i--) {
            R[i] = nums[i + 1] * R[i + 1];
        }
        
        for(int i = 0; i < size; i++) {
            answer[i] = L[i] * R[i];
        }
        return answer;
    }
}