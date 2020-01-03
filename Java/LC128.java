package lc128;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Jeffrey
 */

public class LC128 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Test variables
        int[] nums = {100, 4, 200, 1, 3, 2};
        LC128 LC128 = new LC128();
        System.out.println(LC128.longestConsecutive(nums));
        System.out.println(LC128.longestConsecutiveSort(nums));
        System.out.println(LC128.longestConsecutiveHashSet(nums));
    }

    public int longestConsecutive(int[] nums) {
        
        int max = 0;
        for(int num : nums) {
            
            int currentNum = num;
            int tmp = 1;
            
            while(longestConsecutiveHelper(currentNum + 1, nums)) {
                currentNum++;
                tmp++;
            }
            max = Math.max(max, tmp);
        }
        return max;
    }
    
    public boolean longestConsecutiveHelper(int nextNum, int[] nums) {
        
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == nextNum) {
                return true;
            }
        }
        return false;
    }
    
    public int longestConsecutiveSort(int[] nums) {
        
        if(nums.length == 0) return 0;
        
        Arrays.sort(nums);
        // {1, 2, 3, 4, 100, 200}
        //  0  1  2  3   4    5
        
        int max = 1;
        int tmp = 1;
        
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] != nums[i - 1]) {
                if(nums[i] == nums[i - 1] + 1) {
                    tmp++;
                }
                else {
                    max = Math.max(max, tmp);
                    tmp = 1;
                }
            }
        }
        return Math.max(max, tmp);
    }
    
    public int longestConsecutiveHashSet(int[] nums) {
        
        Set<Integer> setNums = new HashSet<>();
        for(int num : nums) {
            setNums.add(num);
        }
        
        int max = 0;
        for(int num : nums) {
            
            if(!setNums.contains(num - 1)) {
                int currentNum = num;
                int tmp = 1;
            
                while(setNums.contains(currentNum + 1)) {
                    currentNum++;
                    tmp++;
                }
                max = Math.max(max, tmp);
            }
        }
        return max;
    }
}
