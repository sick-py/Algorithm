package DoublePointers;

import Base.ListNode;

import static java.lang.reflect.Array.getLength;

public class IntersectionPoint {
    /**
     * Given the head nodes of two linked lists, return the node at which the two lists intersect. We will return null if we do not find any intersection between the two linked lists.
     * Note: The input linked list will not have cycles in it.
     *
     * Firstly, we’ll find the lengths of both L1 and L2 linked lists.
     *
     * If both linked lists are of the same lengths, we can start from the heads of both lists and compare their addresses.
     * If the lengths of both linked lists are not the same, then we can traverse the longer linked list ∣L1−L2∣ steps forward and then compare the two lists.
     * While traversing both linked lists, if we find any node with the same addresses, it means it is an intersection point. Repeat the traversal until an intersection point is found, or both of the lists are exhausted.
     * */

    public static ListNode intersect(ListNode L1, ListNode L2) {
        ListNode l1 = null, l2 = null;
        int len1 = getLength(L1);
        int len2 = getLength(L2);
        int diff = 0;

        //l1 is always the longer one
        if (len1 >= len2) {
            diff = len1 - len2;
            l1 = L1;
            l2 = L2;
        } else {
            diff = len2 - len1;
            l1 = L2;
            l2 = L1;
        }

        // traverse the longer linked list ∣L1−L2∣ steps forward and then compare the two lists.
        while (diff > 0) {
            l1 = l1.next;
            diff--;
        }

        while (l1 != null && l2 != null) {
            if (l1 == l2) {
                return l1;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        return null;
    }
}
