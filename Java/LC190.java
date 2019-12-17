package lc190;

/**
 *
 * @author Jeffrey
 */

public class LC190 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Test variables
        int n = 43261596;
        
        LC190 LC190 = new LC190();
        System.out.println(LC190.reverse(n));
        System.out.println(LC190.reverseAlt(n)); 
    }

    // You need to treat n as an unsigned value
    public int reverse(int n) {
        
        return Integer.reverse(n);
    }
    
    // You need to treat n as an unsigned value
    public int reverseAlt(int n) {
        
        int answer = 0;
        
        // For 32 bit integers
        for(int i = 0; i < 32; i++) {
            
            // Shift answer over 1 to create space
            answer = answer << 1;
            
            // ((n >> i) & 1) takes the bit at position i
            answer = answer | ((n >> i) & 1);
        }
        return answer;
    }
}