package lc62;

/**
 *
 * @author Jeffrey
 */

public class LC62 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Test variables
        int m = 3;
        int n = 2;
                
        LC62 LC62 = new LC62();
        System.out.println(LC62.unique(m, n)); 
        System.out.println(LC62.uniqueDPTD(m, n));
        System.out.println(LC62.uniqueDPBU(m, n));
        System.out.println(LC62.uniqueDPBULinear(m, n));
    }

    public int unique(int m, int n) {
        
        // Corner cases
        if(m == 0) return 0;
        if(n == 0) return 0;
        
        // Starting at the very end
        return uniqueHelper(m - 1, n - 1);
    }
    
    public int uniqueHelper(int m, int n) {
        
        // Which ways can we move?
        // (0, 0) = 1
        // (0, n) = 1
        // (m, 0) = 1
        if(m == 0) return 1;
        if(n == 0) return 1;
        
        return uniqueHelper(m - 1, n) + uniqueHelper(m, n - 1);
    }
    
    public int uniqueDPTD(int m, int n) {
        
        // Corner cases
        if(m == 0) return 0;
        if(n == 0) return 0;
        
        int[][] memo = new int[m][n];
        
        // Initialize array
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                memo[i][j] = -1;
            }
        }
        return uniqueDPTDHelper(m - 1, n - 1, memo);
    }
    
    public int uniqueDPTDHelper(int m, int n, int[][] memo) {
        
        // Which ways can we move?
        // (0, 0) = 1
        // (0, n) = 1
        // (m, 0) = 1
        if(m == 0) return 1;
        if(n == 0) return 1;
        
        if(memo[m - 1][n] == -1) memo[m - 1][n] = uniqueDPTDHelper(m - 1, n, memo);
        if(memo[m][n - 1] == -1) memo[m][n - 1] = uniqueDPTDHelper(m, n - 1, memo);
        
        return memo[m - 1][n] + memo[m][n - 1];
    }
    
    public int uniqueDPBU(int m, int n) {
        
        // Corner cases
        if(m == 0) return 0;
        if(n == 0) return 0;
        
        int[][] grid = new int[m][n];
        
        // Initialize array
        for(int i = 0; i < m; i++) grid[i][0] = 1;
        for(int j = 0; j < n; j++) grid[0][j] = 1;
        
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                grid[i][j] = grid[i - 1][j] + grid[i][j - 1];
            }
        }
        return grid[m - 1][n - 1];
    }
    
    public int uniqueDPBULinear(int m, int n) {
        
        // Corner cases
        if(m == 0) return 0;
        if(n == 0) return 0;
        
        // Linear space
        int[] row = new int[n];
        
        // Initialize array
        for(int i = 0; i < n; ++i) row[i] = 1;
        
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                row[j] = row[j] + row[j - 1];
            }
        }
        return row[n - 1];
    }
}