package lc139;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Jeffrey
 */

public class LC139 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Test variables
        String s = "leetcode";
        List<String> dict = new ArrayList<>();
        dict.add("leet");
        dict.add("code");
        
        LC139 LC139 = new LC139();
        System.out.println(LC139.wordSegment(s, dict));
        System.out.println(LC139.wordSegmentDPTD(s, dict));
        System.out.println(LC139.wordSegmentDPBU(s, dict));
        System.out.println(LC139.wordSegmentDFSM(s, dict));
    }

    public boolean wordSegment(String s, List<String> dict) {
        
        Set<String> set = new HashSet<>(dict);
        return wordSegmentHelper(0, s, set);
    }
    
    public boolean wordSegmentHelper(int index, String s, Set<String> set) {
        
        if(s.length() == 0) return true;
        if(s.length() == index) return true;
        
        for(int i = index; i < s.length(); i++) {
            
            if(set.contains(s.substring(index, i + 1)) && 
                    wordSegmentHelper(i + 1, s, set)) {

                return true;
            }
        }
        return false;
    }
    
    public boolean wordSegmentDPTD(String s, List<String> dict) {
        
        int n = s.length();
        Boolean[] memo = new Boolean[n];
        Set<String> set = new HashSet<>(dict);
        return wordSegmentDPTDHelper(0, s, set, memo);
    }
    
    public boolean wordSegmentDPTDHelper(int index, String s, Set<String> set, 
            Boolean[] memo) {
        
        if(s.length() == 0) return true;
        if(s.length() == index) return true;
        if(memo[index] != null) return memo[index];
        
        for(int i = index; i < s.length(); i++) {
            
            if(set.contains(s.substring(index, i + 1)) && 
                    wordSegmentDPTDHelper(i + 1, s, set, memo)) {

                memo[index] = true;
                return true;
            }
        }
        memo[index] = false;
        return false;
    }
    
    public boolean wordSegmentDPBU(String s, List<String> dict) {
        
        int n = s.length();
        Set<String> set = new HashSet<>(dict);
        
        boolean[] memo = new boolean[n + 1];
        memo[0] = true;
        
        for(int i = 1; i < n + 1; i++) {
            for(int j = 0; j < i; j++) {
                
                if(memo[j] && set.contains(s.substring(j, i))) {
                    memo[i] = true;
                    break;
                }
            }
        }
        return memo[n];
    }
    
    public boolean wordSegmentDFSM(String s, List<String> dictList) {

        Set<String> dict = new HashSet<>(dictList);
        Set<Integer> set = new HashSet<>();
        
        return wordSegmentDFSMHelper(0, s, dict, set);
    }
    
    public boolean wordSegmentDFSMHelper(int index, String s, Set<String> dict, 
            Set<Integer> set) {
        
        if(index == s.length()) return true;
        if(set.contains(index)) return false;
        
        for(int i = index + 1; i < s.length() + 1; i++) {   
            String t = s.substring(index, i);
            
            if(dict.contains(t)) {
                if(wordSegmentDFSMHelper(i, s, dict, set))
                    return true;
                else
                    set.add(i);
            }
        }
        set.add(index);
        return false;
    }
}