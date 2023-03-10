import Base.ListNode;

public class SwapNthNodeWithHead {
    /**
     * Given the head of a singly linked list and n, swap the head with the nth node. Return the head of the new linked list.
     *
     * draw pic
     * */

    public ListNode swapN(ListNode head, int n) {
        if (head == null || n == 1) {
            return head;
        }

        ListNode prev = null, cur = head, temp;

        while (n > 0 && cur != null) {
            prev = cur;
            cur = cur.next;
        }

        if (cur == null) {
            return head;
        }

        prev.next = head;
        temp = head.next;
        head.next = cur.next;
        cur.next = temp;

        return cur;
    }
}
