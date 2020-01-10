package lc295;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Jeffrey
 */

public class LC295 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    List<Integer> store = new ArrayList<>();
    
    public MedianFinder() { }
    
    public void addNum(int num) {
        store.add(num);
    }
    
    public double findMedian() {
        
        Collections.sort(store);
        
        int n = store.size();
        return ((n & 1) == 1 ? store.get(n / 2) : (store.get(n / 2 - 1) + store.get(n / 2)) * 0.5);
    }
    
    ArrayList<Integer> store = new ArrayList<>();
    
    public MedianFinder() { }
    
    public void addNum(int num) {
        
        store.add(num);
        insertionSort(store);
    }
    
    public double findMedian() {

        int n = store.size();
        return ((n & 1) == 1 ? store.get(n / 2) : (store.get(n / 2 - 1) + store.get(n / 2)) * 0.5);
    }
    
    public void insertionSort(ArrayList<Integer> store) {
        
        for(int i = 1; i < store.size(); i++) {
            int key = store.get(i);
            
            for(int j = i - 1; j >= 0; j--) {
                if(key < store.get(j)) {
                    store.set(j + 1, store.get(j));
                    
                    if(j == 0) {
                        store.set(0, key);
                    }
                }
                else {
                    store.set(j + 1, key);
                    break;
                }
            }
        }
    }
    
    Queue<Long> small = new PriorityQueue(),
                large = new PriorityQueue();
    
    public MedianFinder() { }
    
    public void addNum(int num) {
        
        large.add((long) num);
        small.add(-large.poll());
        if(large.size() < small.size()) {
            large.add(-small.poll());
        }
    }
    
    public double findMedian() {

        return large.size() > small.size () ? large.peek() : (large.peek() - 
                small.peek()) / 2.0;
    }
}