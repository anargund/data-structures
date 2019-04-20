package com.linkedlist;

/**
 * 708. Insert into a Cyclic Sorted List
 * https://leetcode.com/problems/insert-into-a-cyclic-sorted-list/
 */
public class InsertIntoCyclicSortedList {

    public static void main(String[] args) {

    }

    public Node insert(Node head, int insertVal) {
        //if head is null insert current node and point to self
        if(head == null ) {
            head = new Node(insertVal, null);
            head.next = head;
            //if there is only one node, insert next to head
        } else if(head.next == head) {
            head.next = new Node(insertVal, head);
        }
        else {
            Node trav = head;
            //keep traversing until you loop complete list
            while(trav.next != head) {
                //if value is increasing, check if new insertVal is within that range
                if(trav.val < trav.next.val && trav.val <= insertVal && trav.next.val >= insertVal) {
                    trav.next = new Node(insertVal, trav.next);
                    break;
                    //if value is decreasing, then we are at the changeover node
                    //then, you can insert new insertVal if, insertVal is greater than current node
                    //or if insertVal is lesser than value of next node.
                } else if(trav.val > trav.next.val && (trav.val <= insertVal || trav.next.val >= insertVal)) {
                    trav.next = new Node(insertVal, trav.next);
                    break;
                } else {
                    trav = trav.next;
                }
            }
            //if you are done traversing and have not found right posistion
            //then value you are trying to insert belongs next to your trav
            if(trav.next == head) {
                trav.next = new Node(insertVal, trav.next);
            }
        }
        return head;
    }

    class Node {
        public int val;
        public Node next;

        public Node() {}

        public Node(int _val,Node _next) {
            val = _val;
            next = _next;
        }
    }
}
