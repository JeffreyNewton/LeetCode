package lc417;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Jeffrey
 */

public class LC417 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    }

    int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public List<List<Integer>> pacificAtlanticBFS(int[][] matrix) {

        if(matrix == null || matrix.length == 0 || matrix[0] == null 
                || matrix[0].length == 0) {
            return new ArrayList<>();
        }
        
        List<List<Integer>> res = new ArrayList<>();
        int row = matrix.length;
        int col = matrix[0].length;
        
        boolean[][] pOcean = new boolean[row][col];
        boolean[][] aOcean = new boolean[row][col];
        Queue<int[]> queue = new LinkedList<>();

        for(int i = 0; i < col; i++) {
            queue.offer(new int[]{0, i});
            pOcean[0][i] = true;
        }
        for(int j = 1; j < row; j++) {
            queue.offer(new int[]{j, 0});
            pOcean[j][0] = true;
        }
        bfs(res, queue, pOcean, aOcean, matrix);

        for(int i = 0; i < col; i++) {
            queue.offer(new int[]{row - 1, i});
            aOcean[row - 1][i] = true;
        }
        for(int j = 0; j < row - 1; j++) {
            queue.offer(new int[]{j, col - 1});
            aOcean[j][col - 1] = true;
        }
        bfs(res, queue, aOcean, pOcean, matrix);
        return res;
    }

    public void bfs(List<List<Integer>> res, Queue<int[]> queue, 
            boolean[][] self, boolean[][] other, int[][] matrix) {
        
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while(!queue.isEmpty()) {
            int[] curr = new int[2];
            curr = queue.poll();
            
            if(other[curr[0]][curr[1]]) {
                List<Integer> tmp = new ArrayList<>();
                tmp.add(curr[0]);
                tmp.add(curr[1]);
                res.add(tmp);
            }
            for(int[] dire : directions) {
                int i = curr[0] + dire[0];
                int j = curr[1] + dire[1];
                if (i >= 0 && i < row && j >= 0 && j < col && !self[i][j] && 
                        matrix[i][j] >= matrix[curr[0]][curr[1]]) {
                    queue.offer(new int[]{i, j});
                    self[i][j] = true;
                }
            }
        }
    }
    
    public List<List<Integer>> pacificAtlanticDFS(int[][] matrix) {
        
        if(matrix == null || matrix.length == 0 || matrix[0] == null
                || matrix[0].length == 0) {
            return new ArrayList<>();
        }
        
        List<List<Integer>> res = new ArrayList<>();
        int[][] visited = new int[matrix.length][matrix[0].length];
        
        for(int i = 0; i < matrix.length; i++) {
            dfs(matrix, visited, i, 0, -1);
            dfs(matrix, visited, i, matrix[0].length - 1, -2);
        }
        
        for(int i = 0; i < matrix[0].length; i++) {
            dfs(matrix, visited, 0, i, -1);
            dfs(matrix, visited, matrix.length - 1, i, -2);
        }
        
        for(int x = 0; x < matrix.length; x++) {
            for(int y = 0; y < matrix[0].length; y++) {
                if(visited[x][y] == -3) {
                    res.add(Arrays.asList(x,y));
                }
            }
        }
        return res;
    }
    
    public void dfs(int[][] origMatrix, int[][] visited, int x, int y, int code) {
        
        int val = origMatrix[x][y];
        int vis = visited[x][y];
        
        if (vis == code || vis == -3) return;
        visited[x][y] = vis < 0 ? -3 :  code;
        
        boolean L = y > 0 && origMatrix[x][y - 1] >= val;
        boolean R = y < origMatrix[0].length - 1 && origMatrix[x][y + 1] >= val;
        boolean U = x > 0 && origMatrix[x - 1][y] >= val;
        boolean D = x < origMatrix.length - 1 && origMatrix[x + 1][y] >= val;
        
        if(L) dfs(origMatrix, visited, x, y - 1, code);
        if(R) dfs(origMatrix, visited, x, y + 1, code);
        if(U) dfs(origMatrix, visited, x - 1, y, code);
        if(D) dfs(origMatrix, visited, x + 1, y, code);
    }
}