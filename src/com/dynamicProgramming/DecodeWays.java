package com.dynamicProgramming;

import java.util.HashMap;

/**
 * #91 Decode Ways
 * https://leetcode.com/problems/decode-ways/
 */
public class DecodeWays {
    public static void main(String[] args) {
        DecodeWays d = new DecodeWays();
        System.out.println(d.numDecodings("123"));
    }

    public int numDecodings(String s) {
        //map to avoid repeated calculations
        HashMap<String, Integer> dp = new HashMap<>();
        return noDecHelper(s, dp);
    }

    //Total number of ways to decode a String s is:
    //noOfWays(s) = numOfWays(s-1) + numOfWays(s-2)
    //i.e. noOfWays excluding last character + noOfWays excluding last 2 character
    private int noDecHelper(String s, HashMap<String, Integer> dp) {
        if (s.length() == 0) {
            return 1;
        }
        //check we have already done calculations of current string
        Integer n = dp.get(s);
        if (n != null) {
            //if yes, return from map
            return n;
        }
        int noOfWays = 0;
        //check if last character is in valid range of 1-9
        int no1 = Integer.parseInt(s.substring(s.length() - 1));
        if (no1 >= 1 && no1 <= 9) {
            //if yes, find number of ways to decode excluding last character
            String sub = s.substring(0, s.length() - 1);
            int num = noDecHelper(sub, dp);
            dp.put(sub, num);
            noOfWays += num;
        }
        //if lenght is more than 2
        if (s.length() > 1) {
            //check if last 2 characters are of valid ranger of 10-26
            int no2 = Integer.parseInt(s.substring(s.length() - 2));
            if (no2 >= 10 && no2 <= 26) {
                //if yes, find number of ways to decode excluding last 2 characters
                String sub = s.substring(0, s.length() - 2);
                int num = noDecHelper(sub, dp);
                dp.put(sub, num);
                noOfWays += num;
            }
        }
        return noOfWays;
    }
}
