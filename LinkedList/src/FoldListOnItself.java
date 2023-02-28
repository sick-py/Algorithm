import Base.ListNode;

public class FoldListOnItself {
    /**
     * The solution to this problem can be divided into the following three parts:
     *
     * Find the middle node. If there are two middle nodes then choose the second node.
     *  We use two pointers to find the middle node, slow and fast.
     * Reverse the second half of the linked list.
     *
     * Merge both halves of the linked lists alternatively.
     * */
    void reorder(ListNode head) {
        if (head == null) return;

        //find the mid
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        //reverse
        ListNode prev = null, curr = slow, tmp = null;
        while (curr != null) {
            tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }

        //merge
        ListNode first = head, second = prev;
        while (second.next != null) {
            //draw the pic
            tmp = first.next;
            first.next = second;
            first = tmp;

            tmp = second.next;
            second.next = first;
            second = tmp;
        }
    }
}
