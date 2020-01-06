package lc207;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author Jeffrey
 */

public class LC207 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    }
    
    public boolean canFinishBFS(int numCourses, int[][] prerequisites) {
        
        if(prerequisites == null) throw new IllegalArgumentException();
        if(numCourses == 0) return true;
        if(prerequisites.length == 0) return true;

        int[] count = new int[numCourses];
        
        for(int i = 0; i < prerequisites.length; i++) {
            count[prerequisites[i][0]]++;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        
        for(int i = 0; i < numCourses; i++) {
            if(count[i] == 0) {
                queue.add(i);
            }
        }
        int numNoPR = queue.size();

        while(!queue.isEmpty()) {
            int top = queue.remove();
            
            for(int i = 0; i < prerequisites.length; i++) {
                if(prerequisites[i][1] == top) {
                    
                    count[prerequisites[i][0]]--;
                    if(count[prerequisites[i][0]] == 0) {
                        
                        numNoPR++;
                        queue.add(prerequisites[i][0]);
                    }
                }
            }
        }
        return numNoPR == numCourses;
    }

    public boolean canFinishDFS(int numCourses, int[][] prerequisites) {
        
        if(prerequisites == null) throw new IllegalArgumentException();
        if(numCourses == 0) return true;
        if(prerequisites.length == 0) return true;

        int[] visit = new int[numCourses];

        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        
        for(int[] a : prerequisites) {
            if(map.containsKey(a[1])) {
                map.get(a[1]).add(a[0]);
            }
            else {
                ArrayList<Integer> l = new ArrayList<>();
                l.add(a[0]);
                map.put(a[1], l);
            }
        }

        for(int i = 0; i < numCourses; i++) {
            if(!canFinishDFS(map, visit, i)) {
                return false;
            }
        }
        return true;
    }

    public boolean canFinishDFS(HashMap<Integer, ArrayList<Integer>> map,
            int[] visit, int i) {
        
        if(visit[i] == -1) return false;
        if(visit[i] ==  1) return true;

        visit[i] = -1;
        
        if(map.containsKey(i)) {
            for(int j : map.get(i)) {
                if(!canFinishDFS(map, visit, j)) {
                    return false;
                }
            }
        }
        visit[i] = 1;
        return true;
    }
}