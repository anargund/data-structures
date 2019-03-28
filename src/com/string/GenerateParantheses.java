package com.string;

import java.util.ArrayList;
import java.util.List;

class GenerateParantheses {

    public static void main(String[] args) {
        GenerateParantheses generateParantheses = new GenerateParantheses();
        List<String> ans = null;
        int n = 7;
        long startTime = System.currentTimeMillis();
        ans = generateParantheses.generateParenthesis(n);
        long endTime = System.currentTimeMillis();
        System.out.println("Finished generateParenthesis(" + n + ") in " + (endTime - startTime) + "milliseconds");
        startTime = System.currentTimeMillis();
        ans = generateParantheses.generateParenthesisPermute(n);
        endTime = System.currentTimeMillis();
        System.out.println("Finished generateParenthesisPermute(" + n + ") in " + (endTime - startTime) + "milliseconds");
    }

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        //If String is used instead of SB, Leetcode is saying
        //memory usage is lower, but removing of a char is more obvious
        //with SB.
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    private void backtrack(List<String> ans, StringBuilder sb, int open, int close, int n) {
        if (sb.length() == n * 2) {
            ans.add(sb.toString());
            return;
        }

        //add only if it is valid to add (
        if (open < n) {
            backtrack(ans, sb.append("("), open + 1, close, n);
            //backtrack : remove last appended brace.
            sb.setLength(sb.length() - 1);
        }
        //add only if it is valid to add )
        if (close < open) {
            backtrack(ans, sb.append(")"), open, close + 1, n);
            //backtrack : remove last appended brace.
            sb.setLength(sb.length() - 1);
        }
    }

    //Implemented using permutation logic.
    //Less efficient, was not accepted in Leetcode
    public List<String> generateParenthesisPermute(int n) {
        List<String> ans = new ArrayList<>();
        backtrackPermute(ans, new StringBuilder(), generatePara(n), 0, new boolean[n * 2]);
        return ans;
    }

    private void backtrackPermute(List<String> ans, StringBuilder temp, String original, int sum, boolean[] seen) {
        if (temp.length() == original.length()) {
            ans.add(temp.toString());
        } else {
            for (int i = 0; i < original.length(); i++) {
                if (seen[i] || (i > 0 && original.charAt(i) == original.charAt(i - 1) && seen[i - 1])) continue;
                if (original.charAt(i) == '(') {
                    sum++;
                } else if (sum > 0) {
                    sum--;
                } else
                    continue;
                temp.append(original.charAt(i));
                seen[i] = true;
                backtrackPermute(ans, temp, original, sum, seen);
                temp.setLength(temp.length() - 1);
                seen[i] = false;
                if (original.charAt(i) == '(') sum--;
                else sum++;
            }
        }
    }

    private String generatePara(int n) {
        StringBuilder builder1 = new StringBuilder();
        StringBuilder builder2 = new StringBuilder();
        while (n > 0) {
            builder1.append("(");
            builder2.append(")");
            n--;
        }
        builder1.append(builder2.toString());
        return builder1.toString();
    }
}
