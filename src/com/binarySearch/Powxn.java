package com.binarySearch;

/**
 * 50. Pow(x, n)
 * https://leetcode.com/problems/powx-n/
 */
public class Powxn {
    public static void main(String[] args) {
        Powxn powxn = new Powxn();
        System.out.println(powxn.myPow(2,6));
    }

    public double myPow(double x, int n) {
        long N = n;
        if(N < 0) {
            N = -N;
            x = 1/x;
        }

        double ans = 1;
        double current_prod = x;
        for(long i = N ; i > 0 ; i = i / 2) {
            if(i % 2 == 1) {
                ans = ans * current_prod;
            }
            current_prod = current_prod * current_prod;
        }
        return ans;
    }
}
