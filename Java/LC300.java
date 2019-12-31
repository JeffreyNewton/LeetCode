package lc300;

import java.util.Arrays;

/**
 *
 * @author Jeffrey
 */

public class LC300 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Test variables
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        
        LC300 LC300 = new LC300();
        System.out.println(LC300.lengthOfLIS(nums));
        System.out.println(LC300.lengthOfLISDPTD(nums));
        System.out.println(LC300.lengthOfLISDPBU(nums));
        System.out.println(LC300.lengthOfLISDPWithBST(nums));
    }

    public int lengthOfLIS(int[] nums) {
        
        return lengthOfLISHelper(0, Integer.MIN_VALUE, nums);
    }
    
    public int lengthOfLISHelper(int curr, int prev, int[] nums) {
        
        if(curr == nums.length) return 0;
        
        int add = 0;
        int notAdd = 0;
        
        if(nums[curr] > prev) {
            add = 1 + lengthOfLISHelper(curr + 1, nums[curr], nums);
        }
        notAdd = lengthOfLISHelper(curr + 1, prev, nums);
        return Math.max(add, notAdd);
    }
    
    public int lengthOfLISDPTD(int[] nums) {
        
        int[][] memo = new int[nums.length + 1][nums.length];
        
        for(int[] i : memo) {
            Arrays.fill(i, -1);
        }        
        return lengthOfLISDPTDHelper(0, -1, nums, memo);
    }
    
    public int lengthOfLISDPTDHelper(int curr, int prev, int[] nums, 
            int[][] memo) {
        
        if(curr == nums.length) return 0;
        if(memo[prev + 1][curr] >= 0) return memo[prev + 1][curr];
        
        int add = 0;
        int notAdd = 0;
        
        if(prev < 0 || nums[curr] > nums[prev]) {
            add = 1 + lengthOfLISDPTDHelper(curr + 1, curr, nums, memo);
        }
        notAdd = lengthOfLISDPTDHelper(curr + 1, prev, nums, memo);
        memo[prev + 1][curr] = Math.max(add, notAdd);
        return memo[prev + 1][curr];
    }
    
    public int lengthOfLISDPBU(int[] nums) {
        
        if(nums.length == 0) return 0;
        
        int[] maxLIS = new int[nums.length];
        maxLIS[0] = 1;
        
        int maxAns = 1;
        int maxVal = 0;
        
        for(int i = 1; i < maxLIS.length; i++) {
            maxVal = 0;
            for(int j = 0; j < i; j++) {
                if(nums[i] > nums[j]) {
                    maxVal = Math.max(maxVal, maxLIS[j]);
                }
            }
            maxLIS[i] = maxVal + 1;
            maxAns = Math.max(maxAns, maxLIS[i]);
        }
        return maxAns;
    }
    
    public int lengthOfLISDPWithBST(int[] nums) {
        
        int[] maxLIS = new int[nums.length];
        int size = 0;
        
        for(int num : nums) {
            int i = Arrays.binarySearch(maxLIS, 0, size, num);
            
            if(i < 0) {
                i = -(i + 1);
            }
            maxLIS[i] = num;
            
            if(i == size) {
                size++;
            }
        }
        return size;
    }
}