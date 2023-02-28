import Base.ListNode;

public class RotateLinkedList {
    /**
     * Given the head of a singly linked list and an integer n, rotate the linked list by n and return the rotated linked list.
     *
     * Note: For positive integers, we’ll do a positive rotation and for negative integers, we’ll do a left rotation.
     *
     * Naive solution
     * To rotate by one node, we find the last node of the linked list and make it the head of the linked list. One way of solving our original problem is to rotate by one node the last node of a linked list,n times. We need a linear scan to get to the last node of a linked list, so this algorithm requires n scans of the linked list.
     *
     * Note: The time complexity of this algorithm is O(mn)
     *
     * There is an alternate and simpler algorithm that avoids multiple scans of the linked list. We know that performing n rotations (if n > 0) on the last node is equivalent to performing one rotation on the last n nodes of the linked list.
     *
     * The O(n) algorithm to find the list rotated by n nodes are given below:
     *
     * Find the length of the linked list.
     *
     * If n is negative or n is larger than the length of the linked list, adjust the number of rotations needed at the tail of the linked list. The adjusted number is always an integer N, where 0 <= N < n.
     *
     * If the adjusted number of rotations is 0, then just return the head pointer. This means that no rotations were needed.
     *
     * Find the nth node from the last node of the linked list. As we already have the length of the linked list, it is simpler, which is basically getting to the node x at length n - N. The next pointer of the node previous to this node, that is x, should be updated to point to NULL.
     *
     * Start from x and move to the last node of the linked list. Update the next pointer of the last node to point to the head node.
     *
     * Make x as the new head node. Now, x is the head of the rotated linked list after performing n rotations.
     * */
    int findLength(ListNode head) {
        int len = 0;

        while (head != null) {
            ++len;
            head = head.next;
        }
        return len;
    }

    int adjustN(int n, int len) {
        n = n % len;

        if (n < 0) {
            n = n + len;
        }

        return n;
    }

    ListNode rotate(ListNode head, int n) {
        if (head == null || n == 0) {
            return head;
        }

        int len = findLength(head);

        n = adjustN(n, len);
        if (n == 0) {
            return head;
        }

        // Find the start of rotated list.
        // If we have 1,2,3,4,5 where n = 2,
        // 4 is the start of rotated list.
        int rotationCount = len - n - 1;
        ListNode temp = head;

        // After this loop temp will be pointing
        // to one node prior to rotation point
        while (rotationCount > 0) {
            rotationCount--;
            temp = temp.next;
        }
        ListNode newHead = temp.next;
        temp.next = null;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = head;
        return newHead;
    }
}
