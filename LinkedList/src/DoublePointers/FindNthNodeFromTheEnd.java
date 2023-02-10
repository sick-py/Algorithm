package DoublePointers;

import Base.LinkedListNode;

public class FindNthNodeFromTheEnd {
    /**
     * We’re given a singly linked list. Return the Nth node from the last node. Return null if n is larger than the size of the list.
     *
     * We’ll use two pointers to find the Nth from the last node:
     *
     * The idea is to have two pointers n nodes apart: one pointing to the head, and the other pointing to the Nth node.
     * Then, we move both pointers forward until the second pointer reaches the tail.
     * Now, the first pointer points to the Nth node from last. If we reach the tail and both pointers are no longer n nodes apart, that means n is larger than the size of the list, and we may return null.
     * */

    public LinkedListNode nth(LinkedListNode head, int n) {
        if (head == null) {
            return null;
        }

        LinkedListNode fast = head;
        while (fast != null && n > 0) {
            fast = fast.next;
            n--;
        }

        if (n != 0) {
            return null;
        }

        while (fast != null) {
            fast = fast.next;
            head = head.next;
        }
        return head;
    }
}
