package com.binarySearch;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * #683 : K Empty Slots
 * https://leetcode.com/problems/k-empty-slots/
 */
public class KEmptySlots {

    public static void main(String[] args) {
        KEmptySlots emptySlots = new KEmptySlots();
        int[] input1 = {6,5,8,9,7,1,10,2,3,4};
        System.out.println(emptySlots.kEmptySlots(input1, 2));
        System.out.println(emptySlots.kEmptySlotsArray(input1, 2));
        int[] input2 = {1,2,3};
        System.out.println(emptySlots.kEmptySlots(input2, 1));
        System.out.println(emptySlots.kEmptySlotsArray(input2, 1));
        int[] input3 = {1,3,2};
        System.out.println(emptySlots.kEmptySlots(input3, 1));
        System.out.println(emptySlots.kEmptySlotsArray(input3, 1));
        int[] input4 = {3,9,2,8,1,6,10,5,4,7};
        System.out.println(emptySlots.kEmptySlots(input4, 1));
        System.out.println(emptySlots.kEmptySlotsArray(input4, 1));
    }

    /**
     * Time O(nlogn) : TreeSet add/gher is logn and is executed N times
     * Space O(n) size of TreeSet
     */
    public int kEmptySlots(int[] flowers, int k) {
        TreeSet<Integer> set = new TreeSet<>();
        for(int i = 0 ; i < flowers.length; i++) {
            set.add(flowers[i]);
            Integer lower = set.lower(flowers[i]);
            Integer higher = set.higher(flowers[i]);
            if(lower != null && flowers[i] - lower - 1 == k) return i + 1;
            if(higher != null && higher - flowers[i] - 1 == k) return i + 1;
        }
        return -1;
    }

    /**
     * Time O(n) : Iterate over array once
     * Space O(n) : Allocation of position array
     */
    public int kEmptySlotsArray(int[] flowers, int k) {
        //convert to visual representation
        int[] position = new int[flowers.length];
        for(int i = 0 ; i < flowers.length ; i++) {
            position[flowers[i] - 1] = i + 1;
        }
        System.out.println(Arrays.toString(position));

        //loop through
        int left = 0, right = k+1, result = Integer.MAX_VALUE;
        for(int i = 0 ; right < position.length ; i++) {
            if(position[i] > position[left] && position[i] > position[right])
                continue;
            //found a possible solution
            if(i == right)
                //check if current solution is better
                result = Math.min(result, Math.max(position[left], position[right]));
            left = i;
            right = left + k + 1;
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}
