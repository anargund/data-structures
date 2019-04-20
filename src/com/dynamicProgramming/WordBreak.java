package com.dynamicProgramming;


import java.util.*;

/**
 * #139. Word Break
 * https://leetcode.com/problems/word-break/
 */
public class WordBreak {

    public static void main(String[] args) {
        WordBreak wb = new WordBreak();
        String[] words1 = {"leet", "code"};
        System.out.println(wb.wordBreak("leetcode", Arrays.asList(words1)));
        String[] words2 = {"apple", "pen"};
        System.out.println(wb.wordBreak("applepenapple", Arrays.asList(words2)));
        String[] words3 = {"cats", "dog", "sand", "and", "cat"};
        System.out.println(wb.wordBreak("catsandog", Arrays.asList(words3)));
    }
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        int[] memo = new int[s.length()];
        for(int i = 0 ; i < memo.length ; i++) {
            memo[i] = -1;
        }
        return backtrack(0, s, wordSet, memo);
    }

    private boolean backtrack(int start, String s, Set<String> wordDict, int[] memo) {
        //if you have reached length, return true
        if(start == s.length()) return true;
        //check if you have already done calculation for current start
        if(memo[start] != -1) return memo[start] == 1;
        else {
            String sub = "";
            for(int i = start ; i < s.length(); i++) {
                //add one char at a time
                sub += s.charAt(i);
                //if current word is in dict, check what happens to rest
                if(wordDict.contains(sub) && backtrack(i+1, s, wordDict, memo)) {
                    memo[i] = 1;
                    return true;
                }
            }
            memo[start] = 0;
            return false;
        }
    }
}
