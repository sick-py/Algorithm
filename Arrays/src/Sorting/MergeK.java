package Sorting;

import java.util.PriorityQueue;

public class MergeK {
      public class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

    public ListNode mergeKLists(ListNode[] lists) {
          if (lists.length == 0) {
              return null;
          }
        //minHeap
          PriorityQueue<ListNode> heap = new PriorityQueue<>(lists.length, (a, b)->(a.val - b.val));
          ListNode dummy = new ListNode(-1), cur = dummy;

          for (ListNode n : lists) {
              if (n != null) {
                  heap.add(n);
              }
          }

          while (!heap.isEmpty()) {
              ListNode node = heap.poll();
              cur.next = node;
              if (node.next != null) {
                  heap.add(node.next);
              }
              cur = cur.next;
          }
          cur.next = null;
          return dummy.next;
    }

}
