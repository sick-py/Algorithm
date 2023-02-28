package DoublePointers;

import Base.ListNode;

import java.util.HashSet;

public class RemoveDuplicate {
    /**
     * Given a linked list of integers, remove all the duplicate nodes from the linked list while retaining only the first occurrence of each duplicate.
     *
     * Here is the pseudo-code of the algorithm that we are going to use. We iterate through the entire linked list and compare each node data with the hashset. Duplicate nodes are deleted as we iterate through the list.
     *
     * We can take one of these two approaches, depending on the precise requirements:
     * If we are allowed to change the order of the list, we can sort the linked list in O(n \space logn)
     *  time. After sorting, all the duplicates would be adjacent to each other and can be removed in a linear scan.
     * For each node in the linked list, we can do another scan of the preceding nodes to see if it already exists or not. The time complexity of this algorithm is O(n^2)
     * O(n
     * 2
     *  )
     *  and does not require any extra space.
     * */
    public static ListNode removeDuplicate(ListNode head) {
        if (head == null) {
            return head;
        }

        HashSet<Integer> dupSet = new HashSet<>();
        dupSet.add(head.val);
        ListNode curr = head;

        while (curr.next != null) {
            if (dupSet.contains(curr.next.val)) {
                curr.next = curr.next.next;
            } else {
                dupSet.add(curr.next.val);
                curr = curr.next;
            }
        }
        return head;
    }
}
