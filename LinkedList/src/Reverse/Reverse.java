package Reverse;

import Base.LinkedListNode;

public class Reverse {
    /**
     * the iterative solution starts with two node pointers:
     *
     * prev, cur, temp
     *
     * draw the pic
     * */

    public LinkedListNode reverse(LinkedListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        LinkedListNode prevP = null, curP = head, nextP;
        while (curP != null) {
            nextP = curP.next;
            curP.next = prevP;
            prevP = curP;
            curP = nextP;
        }
        return prevP;
    }

    //revers [a, b), pay atttion to the interval
    public LinkedListNode reverse(LinkedListNode a, LinkedListNode b) {
        LinkedListNode prev = b, cur = a, next = null;
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

    LinkedListNode reverseKGroup(LinkedListNode head, int k) {
        if (head == null) return null;
        LinkedListNode a = head, b = head;
        for (int i = 0; i < k; i++) {
            //less than k, no need to reverse
            if (b == null) return head;
            b = b.next;
        }
        //reverse the front k
        LinkedListNode newHead = reverse(a, b);
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

    public static LinkedListNode reverseRecursive(LinkedListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        LinkedListNode reversedList = reverseRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return reversedList;
    }
}
