import Base.ListNode;
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
    public static ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        //find the mid
        ListNode slow = head, fast = head, preSlow = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            preSlow = slow;
            slow = slow.next;
        }

        preSlow.next = null; // split the two half

        return MergeSortedList.merge(mergeSort(head), mergeSort(slow));
    }

    class review {
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            ListNode dummy = new ListNode(-1), cur = dummy;
            while (list1 != null && list2 != null) {
                if (list1.val < list2.val) {
                    cur.next = list1;
                    list1 = list1.next;
                } else {
                    cur.next = list2;
                    list2 = list2.next;
                }
                cur = cur.next;
            }

            if (list1 != null) {
                cur.next = list1;
            }
            if (list2 != null) {
                cur.next = list2;
            }
            return dummy.next;
        }


    }
}
