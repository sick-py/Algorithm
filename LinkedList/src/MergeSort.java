import Base.LinkedListNode;
import DoublePointers.MergeSortedList;

public class MergeSort {
    /**
     * The concept of merge sort is very straightforward:
     *
     * In the dividing step, we split our input linked list into two halves and keep doing so until there is a linked list of size 0 or 1. Linked lists of sizes 0 or 1 are always sorted.
     *
     * In the combining step, we merge sorted lists and keep doing so until we have a completely sorted list.
     * At each step, we divide our problem into two sub-problems. The size of each sub-problem is n/2
     *  and the total cost of combining steps (merging sorted lists) is n
     * */
    public static LinkedListNode mergeSort(LinkedListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        //find the mid
        LinkedListNode slow = head, fast = head, preSlow = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            preSlow = slow;
            slow = slow.next;
        }

        preSlow.next = null; // split the two half

        return MergeSortedList.merge(mergeSort(head), mergeSort(slow));
    }
}
