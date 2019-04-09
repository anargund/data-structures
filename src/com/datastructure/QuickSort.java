package com.datastructure;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] temp = {45,12,85,32,89,39,69,44,42,1,6,8};
        quickSort.quickSort(temp);
        System.out.println(Arrays.toString(temp));
    }

    public void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivot = left + (right - left) / 2;
        int index = partition(arr, left, right, pivot);
        quickSort(arr, left, index - 1);
        quickSort(arr, index, right);
    }

    private int partition(int[] arr, int left, int right, int pivot) {
        while (left <= right) {
        while (arr[left] < arr[pivot]) left++;
            while (arr[right] > arr[pivot]) right--;

            if (left <= right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }
        return left;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
