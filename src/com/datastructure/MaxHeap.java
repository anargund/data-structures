package com.datastructure;

public class MaxHeap {
    private int[] heap;
    private int size;

    public MaxHeap(int maxSize) {
        heap = new int[maxSize];
    }

    public void push(int i) throws IllegalArgumentException{
        if(size == heap.length) throw new IllegalArgumentException();

        // Put the value in the next available space in the queue
        int pos = size;
        heap[pos] = i;

        // While val is bigger than its parent, swap it with its parent
        while(pos > 0) {
            int parent = (pos + 1) / 2 - 1;
            if(heap[pos] < heap[parent]) break;
            swap(pos, parent);
            pos = parent;
        }

        size++;
    }

    public int pop() {
        if(size == 0) throw  new IllegalStateException();

        // The top of the heap is the first item in the array, so save it off
        // to the side so we don't lose it
        int retVal = heap[0];

        // Move the bottom item in the heap to the first position. We don't need
        // to remove it from the array because we have the size variable
        heap[0] = heap[size - 1];

        // Bubble down the top element to the right spot
        int pos = 0;
        size--;

        // We're going to be swapping with the children and any pos >= size / 2
        // doesn't have any children
        while(pos < size/2) {
            int leftChild = pos*2 + 1;
            int rightChild = leftChild + 1;

            // If the right child exists and is greater than the left child,
            // compare it to the current position
            if(rightChild < size && heap[rightChild] > heap[leftChild]) {
                // Only swap if the value is less than the child
                if(heap[rightChild] < heap[pos]) break;
                swap(rightChild, pos);
                pos = rightChild;
            } else {
                // Do the same comparison with the left child
                if(heap[leftChild] < heap[pos]) break;
                swap(leftChild, pos);
                pos = leftChild;
            }
        }

        return retVal;
    }
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}
