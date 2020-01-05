package lc133;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

/**
 *
 * @author Jeffrey
 */

public class LC133 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    }

    public Node cloneGraphBFS(Node node) {
        
        if(node == null) return null;
        
        Node newNode = new Node(node.val, new ArrayList<>());
        HashMap<Node, Node> map = new HashMap<>();
        map.put(node, newNode);
        
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(node);
        
        while(!queue.isEmpty()) {
            Node n = queue.poll();
            
            for(Node origNeighbor : n.neighbors) {
                
                if(!map.containsKey(origNeighbor)) {
                    Node newNeighborNode = new Node(origNeighbor.val, new ArrayList<>());
                    map.put(origNeighbor, newNeighborNode);
                    queue.add(origNeighbor);
                }
                map.get(n).neighbors.add(map.get(origNeighbor));
            }
        }
        return newNode;
    }
    
    public Node cloneGraphDFS(Node node) {

        HashMap<Node, Node> map = new HashMap<>();
        cloneGraphDFSHelper(map, node);
        return map.get(node);
    }
    
    public void cloneGraphDFSHelper(HashMap<Node, Node> map, Node node) {
        
        if(node == null) return;
        if(map.containsKey(node)) return;
        
        map.put(node, new Node(node.val, new ArrayList<>()));
        
        for(Node neighbor : node.neighbors) {
            cloneGraphDFSHelper(map, neighbor);
            map.get(node).neighbors.add(map.get(neighbor));
        }
    }
    
    public Node cloneGraphDFSIterative(Node node) {

        if(node == null) return null;
        
        Node root = new Node(node.val, new ArrayList<>());
        HashMap<Node, Node> map = new HashMap<>();
        map.put(node, root);
        
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        
        while(!stack.isEmpty()) {
            Node curr = stack.pop();
            
            for(Node neighbor : curr.neighbors) {
                
                if(!map.containsKey(neighbor)) {
                    Node temp = new Node(neighbor.val, new ArrayList<>());
                    map.put(neighbor, temp);
                    stack.push(neighbor);
                }
                map.get(curr).neighbors.add(map.get(neighbor));
            }
        }
        return root;
    }
}