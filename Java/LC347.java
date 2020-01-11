package lc347;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author Jeffrey
 */

public class LC347 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        int k = 2;
        
        LC347 LC347 = new LC347();
        System.out.println(LC347.topKFrequent(nums, k));
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        
        // HashMap
        Map<Integer, Integer> map = new HashMap<>();
        for(int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        
        // Heap
        Queue<Integer> heap = new PriorityQueue<>((n1, n2) -> map.get(n2) - map.get(n1));
        for(int n : map.keySet()) {
            heap.add(n);
        }
        
        // Output
        List<Integer> topK = new ArrayList<>();
        for(int i = 0; i < k; i++) {
            topK.add(heap.poll());
        }
        return topK;
    }
}