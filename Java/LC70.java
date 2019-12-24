package lc70;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jeffrey
 */

public class LC70 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Test variables
        int n = 2;
                
        LC70 LC70 = new LC70();
        System.out.println(LC70.climbStairs(n));
        System.out.println(LC70.climbStairsDPTD(n));
        System.out.println(LC70.climbStairsDPBU(n));
        System.out.println(LC70.climbStairsFibonacci(n));
    }

    public int climbStairs(int n) {
        
        return climbStairsFrom(0, n);
    }
    
    public int climbStairsFrom(int i, int n) {
        
        if(i >  n) return 0;
        if(i == n) return 1;
        
        return climbStairsFrom(i + 1, n) + climbStairsFrom(i + 2, n);
    }
    
    public int climbStairsDPTD(int n) {
        
        int[] memo = new int[n + 1];
        
        return climbStairsFromDPTD(0, n, memo);
    }
    
    public int climbStairsFromDPTD(int i, int n, int[] memo) {
        
        if(i >  n) return 0;
        if(i == n) return 1;
        
        if(memo[i] > 0) return memo[i];
        
        memo[i] = climbStairsFromDPTD(i + 1, n, memo) + climbStairsFromDPTD(i + 2, n, memo);
        return memo[i];
    }
    
    public int climbStairsDPBU(int n) {
        
        Map<Integer, Integer> map = new HashMap<>();
        
        // Corner cases
        if(n == 0) return 0;
        if(n == 1) return 1;
        if(n == 2) return 2;
        
        map.put(1, 1);
        map.put(2, 2);
        
        int val;
        
        for(int i = 3; i < n + 1; i++) {
            val = map.get(i - 1) + map.get(i - 2);
            map.put(i, val);
        }
        return map.get(n);
    }
    
    public int climbStairsFibonacci(int n) {
        
        // Corner cases
        if(n == 0) return 0;
        if(n == 1) return 1;
        if(n == 2) return 2;
        
        int n1 = 1;
        int n2 = 2;
        int n3 = 3;
        
        for(int i = 3; i < n + 1; i++) {
            n3 = n1 + n2;
            n1 = n2;
            n2 = n3;
        }
        return n2;
    }
}