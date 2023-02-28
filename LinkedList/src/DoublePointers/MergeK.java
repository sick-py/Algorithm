package DoublePointers;

import Base.ListNode;

import java.util.PriorityQueue;

public class MergeK {
    /**
     * kThe logic of merging ordered linked lists is similar to merging two ordered linked lists. The difficulty lies in how to quickly get the kth smallest node among nodes and connect it to the result linked list
     *
     * all linked list nodes will be added and popped up , so the overall time complexity of the algorithm is O(N * logK)
     * */

    ListNode merge(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }

        ListNode dummy = new ListNode(-1), curr = dummy;
        //minHeap
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, (a, b)->(a.val - b.val));

        for (ListNode node : lists) {
            if (node != null) {
                pq.add(node);
            }
        }

        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            curr.next = node;
            curr = curr.next;
            if (node.next != null) {
                pq.add(node.next);
            }
        }
        return dummy.next;
    }
}
