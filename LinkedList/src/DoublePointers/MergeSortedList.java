package DoublePointers;

import Base.LinkedListNode;

public class MergeSortedList {
    /**
     * We maintain a dummy and a curr pointer on the merged linked list.
     * We choose the head of the merged linked list by comparing the first node of both linked lists.
     * For all subsequent nodes in both lists, we choose the smaller current node and link it to the tail of the merged list, and move the current pointer of that list one step forward.
     * We keep doing this while there are some remaining elements in both lists. If there are still some elements in only one of the lists, we link this remaining list to the tail of the merged list.
     *
     * We can also use this to separate linkedList, just divide the original linked list into two small linked lists, and merge them
     * */

    public static LinkedListNode merge(LinkedListNode head1, LinkedListNode head2) {
        if (head1 == null) {
            return head2;
        } else if (head2 == null) {
            return head1;
        }

        LinkedListNode dummy = new LinkedListNode(-1), cur = dummy;
        while (head1 != null && head2 != null) {
            if (head1.data <= head2.data) {
                cur.next = head1;
                head1 = head1.next;
            } else {
                cur.next = head2;
                head2 = head2.next;
            }
            cur = cur.next;
        }

        if (head1 != null) {
            cur.next = head1;
        }

        if (head2 != null) {
            cur.next = head2;
        }

        return dummy.next;
    }

}
