package com.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * #17. Letter Combinations of a Phone Number
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 */
public class LetterCombinationOfPhoneNumber {
    public List<String> letterCombinations(String digits) {
        List<String> combination = new ArrayList<>();
        //build digit to letter map
        String[] dToL = {"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        if(digits.length() == 0) return combination;
        backtrack(combination, new StringBuffer(), digits, 0, dToL);
        return combination;
    }

    private void backtrack(List<String> ans, StringBuffer temp, String digits, int index, String[] dToL) {
        //if length of letters is same as digit, we found one of the answer.
        if(temp.length() == digits.length()) {
            ans.add(temp.toString());
        } else {
            int digit = Integer.parseInt(""+digits.charAt(index));
            //check if valid digits
            if(digit > 1 && digit <= 9) {
                //get all candidate letters
                String candidates = dToL[digit-2];
                for(int i = 0 ; i < candidates.length(); i++) {
                    //backtrack
                    temp.append(candidates.charAt(i));
                    backtrack(ans, temp, digits, index + 1, dToL);
                    temp.deleteCharAt(temp.length() - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        LetterCombinationOfPhoneNumber combination = new LetterCombinationOfPhoneNumber();
        System.out.println(combination.letterCombinations("23"));
        System.out.println(combination.letterCombinations("789"));
    }
}
