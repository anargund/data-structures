package com.linkedlist;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

/**
 * #148. Sort List
 * https://leetcode.com/problems/sort-list/
 * time complexity O(nlogn)
 * Space complexity O(logn)
 */
public class SortLinkedList {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        //divide list into 2 halfs using fast and slow pointer
        ListNode prev = null, mid = head, fast = head;
        while (fast != null && fast.next != null) {
            prev = mid;
            mid = mid.next;
            fast = fast.next.next;
        }

        //break list just before slow pointer
        prev.next = null;

        //sort first half and second half
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(mid);

        //merge
        return merge(l1, l2);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0), l = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                l.next = l1;
                l1 = l1.next;
            } else {
                l.next = l2;
                l2 = l2.next;
            }
            l = l.next;
        }

        if (l1 != null) {
            l.next = l1;
        }

        if (l2 != null) {
            l.next = l2;
        }

        return dummy.next;
    }

    public ListNode buildLinkedList(int[] input) {
        ListNode dummy = new ListNode(0), temp = dummy;
        for(int i : input) {
            temp.next = new ListNode(i);
            temp = temp.next;
        }
        return dummy.next;
    }

    public void printLinkedList(ListNode head) {
        ListNode dummy = head;
        StringBuilder builder = new StringBuilder();
        while(dummy != null) {
            builder.append(dummy.val).append("->");
            dummy = dummy.next;
        }
        builder.replace(builder.length()-2, builder.length(), "");
        System.out.println(builder.toString());
    }

    public static void main(String[] args) {
        SortLinkedList sortLinkedList = new SortLinkedList();
        int[] arr = {4,2,1,3};
        ListNode head = sortLinkedList.buildLinkedList(arr);
        sortLinkedList.printLinkedList(head);
        head = sortLinkedList.sortList(head);
        sortLinkedList.printLinkedList(head);
    }
}
