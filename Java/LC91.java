package lc91;

/**
 *
 * @author Jeffrey
 */

public class LC91 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Test variables
        String s = "12";
                
        LC91 LC91 = new LC91();
        System.out.println(LC91.decodings(s));
        System.out.println(LC91.decodingsDPTD(s));
        System.out.println(LC91.decodingsDPBU(s));
        System.out.println(LC91.decodingsDPBUConstantSpace(s));
    }

    public int decodings(String s) {
        
        // Corner cases
        if(s == null) return 0;
        if(s.length() == 0) return 0;
        
        return decodingsHelper(0, s);
    }
    
    public int decodingsHelper(int p, String s) {

        if(p == s.length()) return 1;
        if(s.charAt(p) == '0') return 0;
        
        int num = decodingsHelper(p + 1, s);
        
        if((p < s.length() - 1) && ((s.charAt(p) == '1') || 
                ((s.charAt(p) == '2') && s.charAt(p + 1) < '7'))) {
            
            num = num + decodingsHelper(p + 2, s);
        }
        return num;
    }
    
    public int decodingsDPTD(String s) {
        
        // Corner cases
        if(s == null) return 0;
        if(s.length() == 0) return 0;
        
        int[] memo = new int[s.length() + 1];
        
        // Initialize array
        for(int i = 0; i < memo.length; i++) memo[i] = -1;
        
        return decodingsDPTDHelper(0, s, memo);
    }
    
    public int decodingsDPTDHelper(int p, String s, int[] memo) {

        if(p == s.length()) return 1;
        if(s.charAt(p) == '0') return memo[p] = 0;
        if(memo[p] > -1) return memo[p];
        
        memo[p] = decodingsDPTDHelper(p + 1, s, memo);
        
        if((p < s.length() - 1) && ((s.charAt(p) == '1') || 
                ((s.charAt(p) == '2') && s.charAt(p + 1) < '7'))) {
            
            memo[p] = memo[p] + decodingsDPTDHelper(p + 2, s, memo);
        }        
        return memo[p];
    }
    
    public int decodingsDPBU(String s) {
        
        // Corner cases
        if(s == null) return 0;
        if(s.length() == 0) return 0;
        
        int n = s.length();
        int[] memo = new int[n + 1]; memo[n] = 1;
        
        for(int i = n - 1; i >= 0; i--) {
            
            if(s.charAt(i) == '0') 
                memo[i] = 0;
            
            else {
                memo[i] = memo[i] + memo[i + 1];
                
                if((i + 1 < n) && (s.charAt(i) == '1' || (s.charAt(i) == '2' && 
                        s.charAt(i + 1) < '7'))) {
                    
                    memo[i] = memo[i] + memo[i + 2];
                }
            }
        }
        return memo[0];
    }
    
    public int decodingsDPBUConstantSpace(String s) {
        
        // Corner cases
        if(s == null) return 0;
        if(s.length() == 0) return 0;
        
        int n = s.length();
        int prev2 = 0;
        int prev1 = 1;
        int curr1 = 0;
        
        for(int i = n - 1; i >= 0; i--) {
            
            curr1 = s.charAt(i) == '0' ? 0 : prev1;
            
            if((i + 1 < n) && (s.charAt(i) == '1' || (s.charAt(i) == '2' && 
                    s.charAt(i + 1) < '7'))) {
                
                curr1 = curr1 + prev2;
            }
            prev2 = prev1;
            prev1 = curr1;
        }
        return prev1;
    }
}