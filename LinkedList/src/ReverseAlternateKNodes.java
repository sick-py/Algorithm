import Base.ListNode;

public class ReverseAlternateKNodes {
    /**
     * Given a singly linked list and an integer k, write a function to reverse every k element.
     *
     * Writing code for this is a bit tricky because it involves keeping track of a few pointers. Logically, we break down the list to sub-lists each of size k. We’ll use the below pointers:
     *
     * Reversed: Points to the head of the output list.
     * Current head: Head of the sub-list of size k currently being worked upon.
     * Current tail: Tail of the sub-list of size k currently being worked upon.
     * Previous tail: Tail of the already processed linked list (where sublists of size k are reversed).
     *
     * We’ll work upon one sub0-list of size k at a time. Once that sub-list is reversed, we have its head and tail in the current head and current tail respectively.
     *
     * If it is the first sub-list of size k, then its head (the current head) is the head of the output linked list(successfully reversed), which means we point reversed to the current head of the first sub-list.
     * If it is the second or higher sub-list, we connect the tail of the previous sub-list to the head of the current sub-list. In other words, we update the next pointer of the tail of the previous sub-list with the head pointer of the current sub-list to join the two sub-lists.
     * */
    ListNode reverse(ListNode head, int k) {
        if (k <= 1 || head == null) {
            return head;
        }

        ListNode reversed = null; //the head of the output list
        ListNode prevTail = null; //the tail of the already processed Linked List

        //loop for reverse
        while (head != null && k > 0) {
            ListNode currentHead = null, currentTail = head;
            int n = k; //local variable to run for each sub-list

            while (head != null && n > 0) {
                ListNode temp = head.next;
                head.next = currentHead;
                currentHead = head;
                head = temp;
                n--;
            }

            if (reversed == null) {
                reversed = currentHead;
            }

            if (prevTail != null) {
                prevTail.next = currentHead;
            }
            prevTail = currentTail;
        }
        return reversed;
    }
}
