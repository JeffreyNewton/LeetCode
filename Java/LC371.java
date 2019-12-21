package lc371;

/**
 *
 * @author Jeffrey
 */

public class LC371 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Test variables
        int a = 1;
        int b = 2;
        
        LC371 LC371 = new LC371();
        System.out.println(LC371.getSum(a, b));
    }

    public int getSum(int a, int b) {
        
        if(a == 0) return b;
        if(b == 0) return a;
        
        return getSum((a ^ b), ((a & b) << 1));
    }
}