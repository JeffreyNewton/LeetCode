package lc200;

import java.util.LinkedList;

/**
 *
 * @author Jeffrey
 */

public class LC200 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    }
    
    public int numIslandsBFS(char[][] grid) {
        
        int count = 0;
        
        for(int i = 0 ; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                
                if(grid[i][j] == '1') {
                    bfsFill(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }
    
    private void bfsFill(char[][] grid, int x, int y){
        
        grid[x][y] = '0';
        
        int n = grid.length;
        int m = grid[0].length;
        
        LinkedList<Integer> queue = new LinkedList<>();
        int code = x * m + y;
        queue.offer(code);
        
        while(!queue.isEmpty()) {
            
            code = queue.poll();
            
            int i = code / m;
            int j = code % m;

            if(i > 0 && grid[i - 1][j] == '1') {
                queue.offer((i - 1) * m + j);  
                grid[i - 1][j] = '0';  
            }
            if(i < n - 1 && grid[i + 1][j] == '1') {
                queue.offer((i + 1) * m + j);  
                grid[i + 1][j] = '0';  
            }
            if(j > 0 && grid[i][j - 1] == '1') {
                queue.offer(i * m + (j - 1));  
                grid[i][j - 1] = '0';  
            }
            if(j < m - 1 && grid[i][j + 1] == '1') {
                queue.offer(i * m + (j + 1));  
                grid[i][j + 1] = '0';  
            }
        } 
    }
    
    public int numIslandsDFS(char[][] grid) {
        
        int count = 0;
        
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                
                if(grid[i][j] == '1') {
                    dfsFill(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfsFill(char[][] grid, int i, int j) {
        
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length
                || grid[i][j] != '1') {
            return;
        }

        grid[i][j] = '0';

        dfsFill(grid, i + 1, j);
        dfsFill(grid, i - 1, j);
        dfsFill(grid, i, j + 1);
        dfsFill(grid, i, j - 1);
    }
}