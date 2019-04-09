package com.datastructure;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        int[] temp = {45,12,85,32,89,39,69,44,42,1,6,8};
        bubbleSort.bubbleSort(temp);
        System.out.println(Arrays.toString(temp));
    }

    public void bubbleSort(int[] arr) {
        boolean isSorted = false;
        int lastSortedIndex = arr.length - 1;

        while(!isSorted) {
            isSorted = true;
            for(int i = 0 ; i < lastSortedIndex; i++) {
                if(arr[i] >= arr[i+1]) {
                    swap(arr, i, i+1);
                    isSorted = false;
                }
            }
            lastSortedIndex--;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
