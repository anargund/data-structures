package com.string;

import java.util.HashSet;
import java.util.Set;

/**
 * 681. Next Closest Time
 * https://leetcode.com/problems/next-closest-time/
 */
public class NextClosestTime {

    public String nextClosestTime(String time) {
        int cur = 60 * Integer.parseInt(time.substring(0, 2)); //convert hrs into mins
        cur += Integer.parseInt(time.substring(3)); //add mins

        Set<Integer> allowedSet = new HashSet<>();
        for (char c : time.toCharArray()) {
            if (c == ':') continue;
            allowedSet.add(c - '0');
        }

        while (true) {
            //increment time by 1 min and mod it by total
            //number of minutes in 24 hrs
            cur = (cur + 1) % (24 * 60);
            //hours = divide time by 60 mins i.e. cur/60
            //mins = mod time by 60 mins i.e. cur%60
            //1st digit of hours is hour/10 and mins is mins/10
            //2nd digit of hour is hour%10 and mins is mins%10
            int[] digits = {cur / 60 / 10, cur / 60 % 10, cur % 60 / 10, cur % 60 % 10};
            boolean ansFound = true;
            for (int digit : digits) {
                if (!allowedSet.contains(digit)) {
                    ansFound = false;
                    break;
                }
            }
            if (ansFound) {
                return String.format("%02d:%02d", cur / 60, cur % 60);
            }
        }
    }

    public static void main(String[] args) {
        NextClosestTime time = new NextClosestTime();
        System.out.println(time.nextClosestTime("19:34"));
        System.out.println(time.nextClosestTime("23:59"));
        System.out.println(time.nextClosestTime("00:01"));
    }
}
