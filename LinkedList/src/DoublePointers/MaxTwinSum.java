package DoublePointers;

import Base.ListNode;

public class MaxTwinSum {
    /**
     * We know that the nodes of the first half are twins of nodes in the second half, so try dividing the linked list in half and reverse the second half.
     * */
    ListNode reverse(ListNode head) {
        ListNode prev = null, cur = head, next;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    public int pairSum(ListNode head) {
        ListNode slow = head, fast = head;
        if (fast != null && fast.next != null) {
            fast = fast.next.next;
        }
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode right = reverse(slow.next), left = head;
        int res = Integer.MIN_VALUE;
        while (right != null) {
            res = Math.max(res, right.val + left.val);
            right = right.next;
            left = left.next;
        }
        return res;
    }
}
