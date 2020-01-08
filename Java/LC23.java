package lc23;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 *
 * @author Jeffrey
 */

public class LC23 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Test variables
        int k = 3;
        ListNode arr[] = new ListNode[k];  
    
        arr[0] = new ListNode(1);
        arr[0].next = new ListNode(4);
        arr[0].next.next = new ListNode(5);
        
        arr[1] = new ListNode(1);
        arr[1].next = new ListNode(3);
        arr[1].next.next = new ListNode(4);
        
        arr[2] = new ListNode(2);
        arr[2].next = new ListNode(6);

        LC23 LC23 = new LC23();
        //printList(LC23.mergeKLists(arr));
        //printList(LC23.mergeKListsCompare1(arr));
        //printList(LC23.mergeKListsCompare2(arr));
        //printList(LC23.mergeKListsMergeLists(arr));
        //printList(LC23.mergeKListsDNC(arr));
    }
    
    public static void printList(ListNode node) {
        
        while(node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println("");
    }

    public ListNode mergeKLists(ListNode[] lists) {
        
        // Corner cases
        if(lists == null) return null;
        if(lists.length == 0) return null;
        if(lists.length == 1) return lists[0];
        
        // Store the int values in an ArrayList
        ArrayList<Integer> values = new ArrayList<>();
        for(ListNode curr : lists) {
            while(curr != null) {
                values.add(curr.val);
                curr = curr.next;
            }
        }
        // Sort the ArrayList
        Collections.sort(values);
        
        // Construct a new LinkedList from the sorted ArrayList
        ListNode node = new ListNode(0);
        ListNode currNode = node;
        node.next = currNode;
        
        if(!values.isEmpty()) {
            for(Integer x : values) {
                currNode.next = new ListNode(x);
                currNode = currNode.next;
            }
        }
        else return null;
        return node.next;
    }
    
    public ListNode mergeKListsCompare1(ListNode[] lists) {
        
        // Corner cases
        if(lists == null) return null;
        if(lists.length == 0) return null;
        
        ListNode node = new ListNode(-1);
        ListNode prev = node;
        
        while(true) {
            ListNode miniNode = null;
            int minIndex = -1;
            
            // Iterate
            for(int i = 0; i < lists.length; i++) {
                ListNode currList = lists[i];
                
                if(currList == null) continue;
                if(miniNode == null || currList.val < miniNode.val) {
                    miniNode = currList;
                    minIndex = i;
                }
            }
            
            if(miniNode == null) break;
            prev.next = miniNode;
            prev = prev.next;
            lists[minIndex] = miniNode.next;
        }
        return node.next;
    }
    
    public ListNode mergeKListsCompare2(ListNode[] lists) {
        
        if(lists == null) return null;
        if(lists.length == 0) return null;
        
        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.length, (a, b) -> a.val - b.val);

        ListNode node = new ListNode(0);
        ListNode currNode = node;

        for(ListNode curr : lists) {
            if(curr != null) {
                queue.add(curr);
            }
        }
        
        while(!queue.isEmpty()) {
            currNode.next = queue.poll();
            currNode = currNode.next;
            
            if(currNode.next != null) {
                queue.add(currNode.next);
            }
        }
        return node.next;
    }
    
    public ListNode mergeKListsMergeLists(ListNode[] lists) {
        
        // Corner cases
        if(lists == null) return null;
        if(lists.length == 0) return null;
        
        for(int i = 1; i < lists.length; ++i) {
            lists[0] = mergeList(lists[0], lists[i]);
        }
        return lists[0];
    }

    public ListNode mergeList(ListNode n1, ListNode n2) {
        
        ListNode node = new ListNode(-1);
        ListNode prev = node;
        
        while(n1 != null && n2 != null) {
            if(n1.val < n2.val) {
                prev.next = n1;
                n1 = n1.next;
            }
            else {
                prev.next = n2;
                n2 = n2.next;
            }
            prev = prev.next;
        }
        prev.next = (n1 != null) ? n1 : n2;
        return node.next;
    }
    
    public ListNode mergeKListsDNC(ListNode[] lists) {
        
        // Corner cases
        if(lists == null) return null;
        if(lists.length == 0) return null;
        
        return divideAndConquer(lists, 0, lists.length - 1);
    }

    public ListNode divideAndConquer(ListNode[] lists, int L, int H) {
        
        if(L >  H) return null;
        if(L == H) return lists[L];
        
        int M = L + (H - L) / 2;
        ListNode node1 = divideAndConquer(lists, L, M);
        ListNode node2 = divideAndConquer(lists, M + 1, H);
        
        return mergeList(node1, node2);
    }
}