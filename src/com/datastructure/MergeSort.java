package com.datastructure;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        MergeSort sorting = new MergeSort();
        int[] temp = {45,12,85,32,89,39,69,44,42,1,6,8};
        sorting.mergeSort(temp);
        System.out.println(Arrays.toString(temp));
    }

    public void mergeSort(int[] arr) {
        mergeSort(arr, new int[arr.length], 0 , arr.length - 1);
    }

    private void mergeSort(int[] arr, int[] temp, int leftStart, int rightEnd) {
        if(leftStart >= rightEnd) {
            return;
        }

        int middle = leftStart + (rightEnd - leftStart)/2;

        mergeSort(arr, temp, leftStart, middle);
        mergeSort(arr, temp, middle + 1, rightEnd);
        merge(arr, temp, leftStart, rightEnd);
    }

    private void merge(int[] arr, int[] temp, int leftStart, int rightEnd) {
        int middle = leftStart + (rightEnd - leftStart)/2;
        //pointer for left side of the array
        int left = leftStart;
        //pointer where left part ends
        int leftEnd = middle;
        //pointer where right part starts
        int rightStart = middle + 1;
        //pointer for right side of the array
        int right = rightStart;
        //pointer where result should be copied in temp array
        int index = left;

        while(left <= leftEnd && right <= rightEnd) {
            if(arr[left] <= arr[right]) {
                temp[index] = arr[left];
                left++;
            } else {
                temp[index] = arr[right];
                right++;
            }
            index++;
        }

        //only one of the below two lines will result into actual copy
        System.arraycopy(arr, left, temp, index, leftEnd - left + 1);
        System.arraycopy(arr, right, temp, index, rightEnd - right + 1);
        //copy sorted array back to arr from temp
        System.arraycopy(temp, leftStart, arr, leftStart, rightEnd - leftStart + 1);
    }
}
