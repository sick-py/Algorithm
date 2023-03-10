import Base.ListNode;

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

    public ListNode sortedInsert(ListNode head, ListNode node) {
        if (node == null) {
            return head;
        }

        if (head == null || node.val <= head.val) {
            node.next = head;
            return node;
        }

        ListNode cur = head;

        while (cur.next != null && cur.next.val < node.val) {
            cur = cur.next;
        }
        node.next = cur.next;
        cur.next = node;

        return head;
    }

    public ListNode insertionSort(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode sorted = null, cur = head;

        while (cur != null) {
            ListNode temp = cur.next;
            sorted = sortedInsert(sorted, cur);
            cur = temp;
        }

        return sorted;
    }

    class review {
        ListNode removeNthNode(ListNode head, int n) { //n = 1, the first node
            ListNode dummy = new ListNode(-1), cur = dummy;
            dummy.next = head;
            while (--n != 0) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
            return dummy.next;
        }
        public ListNode removeNthFromEnd(ListNode head, int n) {
            int len = 0;
            ListNode cur = head;
            while (cur != null) {
                cur = cur.next;
                len++;
            }
            n = len - n + 1;
            System.out.println(n);
            return removeNthNode(head, n);
        }
    }
}
