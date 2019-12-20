package lc338;

import java.util.Arrays;

/**
 *
 * @author Jeffrey
 */

public class LC338 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Test variables
        int nums = 2;
        
        LC338 LC338 = new LC338();
        System.out.println(Arrays.toString(LC338.count(nums))); 
        System.out.println(Arrays.toString(LC338.countAlt(nums))); 
    }

    public int[] count(int num) {
        
        int[ ] arr = new int[num + 1];
        
        for(int i = 0; i < arr.length; i++) {
            arr[i] = Integer.bitCount(i);
        }
        return arr;
    }
    
    public int[] countAlt(int num) {
        
        int[ ] arr = new int[num + 1];
        arr[0] = 0;
        
        for(int i = 1; i < arr.length; i++) {
            arr[i] = arr[i & i - 1] + 1;
        }
        return arr;
    }
}