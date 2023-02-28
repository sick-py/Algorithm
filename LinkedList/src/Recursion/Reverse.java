package Recursion;

import Base.ListNode;

public class Reverse {
    /**
     * Reverse the entire liked list
     *
     * For recursive algorithms, the most important thing is to clearly define the recursive function . Specifically, our reverse function definition looks like this:
     * Input a node head, reverse the linked list, and return the reversed head node .
     * draw the pictures https://labuladong.github.io/algo/di-yi-zhan-da78c/shou-ba-sh-8f30d/di-gui-mo--1eaae/
     * */

    /**
     * recursive
     * Here’s how recursive reversal works:
     *
     * If we have a reversed linked list of all the nodes to the left of the current node, and we know the last node of the reversed linked list, then inserting the current node as the second to last node creates the new reversed linked list.
     * Then return the head of the new linked list.
     * The trick here is that we don’t explicitly need to track the last node.
     * The next pointer in the current node is already pointing to the last node in the partially reversed linked list.
     * */

    ListNode reverseAll(ListNode head) {
        //The recursive function must have a base case, that is, this sentence:
        if (head == null || head.next == null) {
            return head;
        }

        //After the linked list is reversed recursively, the new head node is last, and the previous head becomes the last node. Don’t forget that the end of the linked list must point to null:
        ListNode last = reverseAll(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    /**
     * Reverse the first N nodes
     * 1. The base case becomes n == 1, reverse an element, which is itself, and record the back-drive node at the same time .
     *
     * 2. Just now we directly head.nextset to null, because the original headbecomes the last node of the entire linked list after the entire linked list is reversed. But now the headnode is not necessarily the last node after the recursive reversal, so it is necessary to record the back drive successor( n + 1the node), and head connect to after the reversal.
     * */
    ListNode successor = null;

    ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            //record the n + 1 node
            successor = head.next;
            return head;
        }

        ListNode last = reverseN(head.next, n - 1);
        head.next.next = head;
        head.next = successor;
        return last;
    }

    /**
     *Now to solve our original problem, given an index range [m, n](index starts from 1), just reverse the linked list elements in the range.
     * First of all, if m == 1, it is equivalent to reversing the elements at the beginning nof , which is the function we just realized:
     * What if m != 1? If we regard head the index of as 1, then we want m to reverse from the element, right; what if we regard head.next the index of as 1? Then, relative to head.next, the interval of inversion should start from m - 1 the element; then for head.next.next......
     *
     * Different from iterative thinking, this is recursive thinking, so we can complete the code:
     * */
    ListNode reverseBetween(ListNode head, int m, int n) {
        //base case
        if (m == 1) {
            return reverseN(head, n);
        }

        //go forward to invoke the base case
        head.next = reverseBetween(head, m - 1, n - 1);
        return head;
    }

    /**
     * The trick to deal with it is: don't jump into recursion, but use clear definitions to implement algorithmic logic.
     *
     * To deal with seemingly difficult problems, you can try to break them into parts and modify some simple solutions to solve difficult problems.
     *
     * It is worth mentioning that recursively operating linked lists is not efficient. Compared with the iterative solution, although the time complexity is O(N), the space complexity of the iterative solution is O(1), while the recursive solution requires a stack, and the space complexity is O(N).
     * */
}
