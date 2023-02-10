import Base.LinkedListNode;

public class InsertionSort {
    /**
     * We’re given the head pointer of a linked list. Sort the linked list in ascending order using insertion sort. Return the new head pointer of the sorted linked list.
     *
     * It only takes a few steps to implement insertion sort. To do so, we’ll maintain two linked lists:
     *
     * Original list (given to us as input)
     * Sorted list (initially empty)
     *
     * time O(n^2), space O(1)
     *
     * */

    public LinkedListNode sortedInsert(LinkedListNode head, LinkedListNode node) {
        if (node == null) {
            return head;
        }

        if (head == null || node.data <= head.data) {
            node.next = head;
            return node;
        }

        LinkedListNode cur = head;

        while (cur.next != null && cur.next.data < node.data) {
            cur = cur.next;
        }
        node.next = cur.next;
        cur.next = node;

        return head;
    }

    public LinkedListNode insertionSort(LinkedListNode head) {
        if (head == null) {
            return null;
        }

        LinkedListNode sorted = null, cur = head;

        while (cur != null) {
            LinkedListNode temp = cur.next;
            sorted = sortedInsert(sorted, cur);
            cur = temp;
        }

        return sorted;
    }
}
