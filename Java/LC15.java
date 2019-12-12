package lc15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Jeffrey
 */

public class LC15 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Test variables
        int[] nums = new int[]{-1, 0, 1, 2, -1, 4};
        
        LC15 LC15 = new LC15();
        System.out.println(LC15.threeSum(nums));
    }
    
    public List<List<Integer>> threeSum(int[] nums) {
        
        List<List<Integer>> sumZero = new ArrayList<>();
        Arrays.sort(nums);
        
        for(int i = 0; i < nums.length - 2; i++) {
           if(i > 0 && (nums[i] == nums[i - 1])) continue;
           
           for(int j = i + 1, k = nums.length - 1; j < k;) {
               if(nums[i] + nums[j] + nums[k] == 0) {
                   sumZero.add(Arrays.asList(nums[i], nums[j], nums[k]));
                   j++;
                   k--;
                   while((j < k) && (nums[j] == nums[j - 1])) j++;
                   while((j < k) && (nums[k] == nums[k + 1])) k--;
               }
               else if((nums[i] + nums[j] + nums[k]) > 0) k--;
               else j++;
           }
        }
        return sumZero;
    }
}