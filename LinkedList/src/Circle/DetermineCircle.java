package Circle;

import Base.ListNode;

public class DetermineCircle {
    /**
     *Judging whether the linked list contains a circle is a classic problem, and the solution is to use the fast and slow pointer:
     * Every time the slow slowhand advances one step, the fast hand fast advances two steps.If fast finally encounters a null pointer, it means that there is no ring in the linked list; if fast finally slowmeets with , it must be fastmore than a slowcircle , indicating that there is a circle in the linked list.
     * */
    boolean circle(ListNode root) {
        if (root == null) {
            return false;
        }

        ListNode slow = root, fast = root;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     *  if there is a ring in the linked list, how to calculate the starting point of this ring:
     *
     *  It can be seen that when the fast and slow pointers meet, let either pointer point to the head node, and then let them both advance at the same speed, and the node position when they meet again is the position where the ring starts.
     *  We assume that when the fast and slow pointers meet, the slow pointer slow moves k steps, then the fast pointer fast must move 2k steps:
     *  (slow) k + m = 2k(fast), so k = m, m is the distance between the meet point and the start circle point
     *
     * */
    ListNode detectCycle(ListNode head) {
        ListNode fast, slow;
        fast = slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }
        // has circle
        if (fast == null || fast.next == null) {
            // fast no circle
            return null;
        }

        slow = head;
        // when they interact there is the point
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    class review {
        public boolean hasCycle(ListNode head) {
            ListNode fast = head, slow = head;
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
                if (slow == fast) {
                    return true;
                }
            }
            return false;
        }

        public ListNode detectCycle(ListNode head) {
            if (!hasCycle(head)) {
                return null;
            }
            ListNode slow = head, fast = head;
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
                if (slow == fast) {
                    break;
                }
            }
            fast = head;
            while (fast != slow) {
                fast = fast.next;
                slow = slow.next;
            }
            return slow;
        }


    }

}
