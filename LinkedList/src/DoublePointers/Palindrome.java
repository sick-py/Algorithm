package DoublePointers;

import Base.ListNode;

public class Palindrome {
    /**
     * judgment of palindrome single linked list:
     * The key to this question is that the single-linked list cannot be traversed backwards, and the double-pointer technique cannot be used.
     *
     * Then the easiest way is to reverse the original linked list and store it in a new linked list, and then compare whether the two linked lists are the same.
     *
     * In fact, with the help of the idea of ​​post-order traversal of the binary tree, the linked list can be traversed in reverse order without explicitly reversing the original linked list .
     *
     *  the linked list has a recursive structure, and the tree structure is just a derivative of the linked list. Then, the linked list can actually have pre-order traversal and post-order traversal :
     * */
    void postOrder(ListNode head) {
        if (head == null) return;
        postOrder(head.next);
        //post order code
        System.out.println(head.val);
    }

    ListNode left;
    boolean isPalindrome1(ListNode head) {
        left = head;
        return traverse(head);
    }

    private boolean traverse(ListNode right) {
        if (right == null) return true;
        boolean res = traverse(right.next);
        //post order
        res = res && (right.val == left.val);
        left = left.next;
        return res;
    }
    /**
     *
     * What is the core logic behind this? In fact, it is to put the linked list nodes into a stack and then take them out. At this time, the order of elements is reversed ,
     * Of course, the time and space complexity of the algorithm is O(N) no matter whether you create a reverse linked list or use post-order traversal. Now let's think about it, can we solve this problem without using extra space?
     *
     * Optimizing space complexity:
     * 1 Pass first two-pointer techniqueFast and slow pointers in to find the midpoint of the linked list, If the fast pointer does not point null, it means that the length of the linked list is an odd number,slow  need to go one step further.
     * Reverse the linked list from the beginning, and now you can start comparing palindrome strings :
     *
     * I know that some readers will definitely ask: Although this solution is efficient, it destroys the original structure of the input linked list. Can this defect be avoided?
     *
     * In fact, this problem is easy to solve, the key is to get p, qthese two pointer positions:
     * In this way, just add a piece of code before the function return to restore the original linked list order:
     * */

    boolean isPalindrome0(ListNode head) {
        ListNode slow = head, fast = head, p = null;
        while (fast != null && fast.next != null) {
            p = slow;
            fast = fast.next.next;
            slow = slow.next;
        }

        if (fast != null) {
            p = slow;
            slow = slow.next;
        }


        ListNode left = head, q = slow, right = reverse(slow);
        while (right != null) {
            if (left.val != right.val) {
                //restore the linked-list
                p.next = reverse(q);
                return false;
            }


            left = left.next;
            right = right.next;
        }
        //restore the linked-list

        p.next = reverse(q);
        return true;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null, cur = head, next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    public boolean isPalindrome(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode right = reverse(slow), left = head;
        while (left != null && right != null) {
            if (left.val != right.val) {
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;
    }



}
