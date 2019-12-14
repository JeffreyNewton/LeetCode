package lc121;

/**
 *
 * @author Jeffrey
 */

public class LC121 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Test variables
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        
        LC121 LC121 = new LC121();
        System.out.println(LC121.max(prices));
        System.out.println(LC121.maxAlt(prices)); 
    }
    
    public int max(int[] prices) {
        
        int max = 0;
        
        for(int i = 0; i < prices.length - 1; i++) {
            for(int j = i + 1; j < prices.length; j++) {
                max = Math.max(max, (prices[j] - prices[i]));
            }
        }        
        return max;
    }
    
    public int maxAlt(int[] prices) {
        
        // Initial variables
        int min = Integer.MAX_VALUE;
        int max = 0;
        
        for(int i = 0; i < prices.length; i++) {
            
            // {7, 1, 5, 3, 6, 4}
            //  
            //  *            
            //              *
            //        *
            //                 *
            //           *
            //
            //     *
            
            // Check for the relative valley in the graph
            min = Math.min(min, prices[i]);
            max = Math.max(max, prices[i] - min);
        }
        return max;
    }
}