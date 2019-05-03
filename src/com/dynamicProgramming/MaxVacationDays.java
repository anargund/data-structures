package com.dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/**
 * Leetcode# 568 : Maximum Vacation Days
 * https://leetcode.com/problems/maximum-vacation-days/
 */
public class MaxVacationDays {

    class Memo {
        int week;
        int city;

        Memo(int week, int city) {
            this.week = week;
            this.city = city;
        }
        @Override
        public boolean equals(Object o) {
            Memo m = (Memo) o;
            return this.week == m.week && this.city == m.city;
        }
    }
    private int max = 0, cities = 0, weeks = 0;
    private int maxVacationDays(int[][] flights, int[][] days) {
        cities = flights.length;
        weeks = days[0].length;
        Map<Memo, Integer> map = new HashMap<>();
        backtrack(0, 0, 0, flights, days, map);
        return max;
    }

    private void backtrack(int week, int currentCity, int holidaySum, int[][] flights, int[][] days
                        , Map<Memo, Integer> map) {
        Memo m = new Memo(week, currentCity);
        if(map.containsKey(m)) {
            System.out.println("cache hit");
        } else {
            map.put(m, 0);
        }
        if(week == weeks) {
            max = Math.max(max, holidaySum);
            return;
        }
        for(int i = 0 ; i < cities ; i++) {
            if(i == currentCity || flights[currentCity][i] == 1) {
                backtrack(week + 1, i, holidaySum + days[i][week], flights, days, map);
            }
        }
    }

    public static void main(String[] args) {
        int[][] flights = {{0,1,1},{1,0,1},{1,1,0}};
        int[][] days = {{1,3,1},{6,0,3},{3,3,3}};
        MaxVacationDays vacationDays = new MaxVacationDays();
        int vacation = vacationDays.maxVacationDays(flights, days);
        System.out.println("max vacation days = " + vacation);
    }
}
