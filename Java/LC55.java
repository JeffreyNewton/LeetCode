package lc55;

/**
 *
 * @author Jeffrey
 */

enum Index { GOOD, BAD, UNKNOWN }

public class LC55 {

    Index[] memo;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Test variables
        int[] nums = new int[]{2, 3, 1, 1, 4};
        
        LC55 LC55 = new LC55();
        System.out.println(LC55.canJump(nums)); 
        System.out.println(LC55.canJumpDPTD(nums)); 
        System.out.println(LC55.canJumpDPBU(nums)); 
        System.out.println(LC55.canJumpGreedy(nums));
    }

    public boolean canJump(int[] nums) {
            
        return canJumpFromHere(0, nums);
    }
    
    public boolean canJumpFromHere(int position, int[] nums) {
        
        // If we can reach the final index
        if(position == nums.length - 1) return true;

        // Careful not to go too far
        int furthestJump = Math.min(position + nums[position], nums.length - 1);
        
        // {2, 3, 1, 1, 4}
        //  ^
        //  |
        //  We can move 1 to 2 positions at position 0
        
        for(int i = position + 1; i < furthestJump + 1; i++) {
            
            // Can we reach the final index
            if(canJumpFromHere(i, nums)) return true;
        }       
        return false;
    }
    
    public boolean canJumpDPTD(int[] nums) {
        
        // Fill the enumeration with UNKNOWN values
        memo = new Index[nums.length];
        for(int i = 0; i < memo.length; i++) {
            memo[i] = Index.UNKNOWN;
        }
        
        // Except the final index
        memo[memo.length - 1] = Index.GOOD;
               
        return canJumpFromHereDPTD(0, nums);
    }
    
    public boolean canJumpFromHereDPTD(int position, int[] nums) {
        
        // If we can or cannot reach the final index
        if(memo[position] != Index.UNKNOWN)
            return memo[position] == Index.GOOD;

        // Careful not to go too far
        int furthestJump = Math.min(position + nums[position], nums.length - 1);
        
        // {2, 3, 1, 1, 4}
        //  ^
        //  |
        //  We can move 1 to 2 positions at position 0
        
        for(int i = position + 1; i < furthestJump + 1; i++) {
            
            // Can we reach the final index
            if(canJumpFromHereDPTD(i, nums)) {
                
                // Store the GOOD and BAD indexes
                memo[position] = Index.GOOD;
                return true;
            }
        }
        memo[position] = Index.BAD;
        return false;
    }
    
    public boolean canJumpDPBU(int[] nums) {
        
        // Fill the enumeration with UNKNOWN values
        memo = new Index[nums.length];
        for(int i = 0; i < memo.length; i++) {
            memo[i] = Index.UNKNOWN;
        }
        
        // Except the final index
        memo[memo.length - 1] = Index.GOOD;
               
        for(int i = nums.length - 2; i >= 0; i--) {
            int furthestJump = Math.min(i + nums[i], nums.length - 1);
            for(int j = i + 1; j < furthestJump + 1; j++) {
                if(memo[j] == Index.GOOD) {
                    memo[i] = Index.GOOD;
                    break;
                }
            }
        }
        return memo[0] == Index.GOOD;
    }
    
    public boolean canJumpGreedy(int[] nums) {
        
        int prevGoodIndex = nums.length - 1;
        
        for(int i = nums.length - 1; i >= 0; i--) {
            if(i + nums[i] >= prevGoodIndex) {
                prevGoodIndex = i;
            }
        }
        return prevGoodIndex == 0;
    }
}