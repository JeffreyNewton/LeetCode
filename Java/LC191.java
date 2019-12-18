package lc191;

/**
 *
 * @author Jeffrey
 */

public class LC191 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Test variables
        int n = 11;
        
        LC191 LC191 = new LC191();
        System.out.println(LC191.hammingWeight(n)); 
        System.out.println(LC191.hammingWeightAlt1(n));
        System.out.println(LC191.hammingWeightAlt2(n));
    }
    
    // You need to treat n as an unsigned value
    public int hammingWeight(int n) {
        
        return Integer.bitCount(n);
    }
    
    // You need to treat n as an unsigned value
    public int hammingWeightAlt1(int n) {
        
        // Initial variables
        int bits = 0;
        int mask = 1;
        
        for(int i = 0; i < 32; i++) {
            if((mask & n) != 0) {
                bits++;
            }
            mask = mask << 1;
        }
        return bits;
    }
    
    // You need to treat n as an unsigned value
    public int hammingWeightAlt2(int n) {
        
        // Initial variables
        int bits = 0;
        
        while(n != 0) {
            bits++;
            n = n & (n - 1);
        }
        return bits;
    }
}