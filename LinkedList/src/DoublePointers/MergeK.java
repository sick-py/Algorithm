package DoublePointers;

import Base.LinkedListNode;

import java.util.PriorityQueue;

public class MergeK {
    /**
     * kThe logic of merging ordered linked lists is similar to merging two ordered linked lists. The difficulty lies in how to quickly get the kth smallest node among nodes and connect it to the result linked list
     *
     * all linked list nodes will be added and popped up , so the overall time complexity of the algorithm is O(N * logK)
     * */

    LinkedListNode merge(LinkedListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }

        LinkedListNode dummy = new LinkedListNode(-1), curr = dummy;
        //minHeap
        PriorityQueue<LinkedListNode> pq = new PriorityQueue<>(lists.length, (a, b)->(a.data - b.data));

        for (LinkedListNode node : lists) {
            if (node != null) {
                pq.add(node);
            }
        }

        while (!pq.isEmpty()) {
            LinkedListNode node = pq.poll();
            curr.next = node;
            curr = curr.next;
            if (node.next != null) {
                pq.add(node.next);
            }
        }
        return dummy.next;
    }
}
