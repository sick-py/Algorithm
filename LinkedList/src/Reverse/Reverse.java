package Reverse;

import Base.ListNode;

public class Reverse {
    /**
     * the iterative solution starts with two node pointers:
     *
     * prev, cur, temp
     *
     * draw the pic
     * */

    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode prevP = null, curP = head, nextP;
        while (curP != null) {
            nextP = curP.next;
            curP.next = prevP;
            prevP = curP;
            curP = nextP;
        }
        return prevP;
    }

    //revers [a, b), pay atttion to the interval
    public ListNode reverse(ListNode a, ListNode b) {
        ListNode prev = b, cur = a, next = null;
        while (cur != b) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    /**
     * Now we iteratively realize the function of reversing part of the linked list, and then write the reverseKGroupfunction :
     * */

    ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        ListNode a = head, b = head;
        for (int i = 0; i < k; i++) {
            //less than k, no need to reverse
            if (b == null) return head;
            b = b.next;
        }
        //reverse the front k
        ListNode newHead = reverse(a, b);
        //link the reversed one
        a.next = reverseKGroup(b, k);
        return newHead;
    }

    /**
     * recursive
     * Here’s how recursive reversal works:
     * still draw the pic
     * If we have a reversed linked list of all the nodes to the left of the current node, and we know the last node of the reversed linked list, then inserting the current node as the second to last node creates the new reversed linked list.
     * Then return the head of the new linked list.
     * The trick here is that we don’t explicitly need to track the last node.
     * The next pointer in the current node is already pointing to the last node in the partially reversed linked list.
     * */

    public static ListNode reverseRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode reversedList = reverseRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return reversedList;
    }
}
