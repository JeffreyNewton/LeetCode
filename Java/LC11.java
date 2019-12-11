package lc11;

/**
 *
 * @author Jeffrey
 */

public class LC11 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Test variables
        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        
        LC11 LC11 = new LC11();
        System.out.println((LC11.maxArea(height)));  
        System.out.println((LC11.maxAreaAlt(height))); 
    }
    
    public int maxArea(int[] height) {
        
        int maxArea = 0;
        
        for(int i = 0; i < height.length; i++) {
            for(int j = i + 1; j < height.length; j++) {
                maxArea = Math.max(maxArea, Math.min(height[i], height[j]) * (j - i));
            }
        }
        return maxArea;
    }
    
    public int maxAreaAlt(int[] height) {
        
        int L = 0;
        int R = height.length - 1;
        
        int maxArea = 0;
        
        while(L < R) {
            maxArea = Math.max(maxArea, Math.min(height[L], height[R]) * (R - L));
            
            if(height[L] < height[R]) L++;
            else R--;
        }
        return maxArea;
    }
}