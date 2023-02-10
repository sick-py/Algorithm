import Base.LinkedListNode;

public class DeleteAllOccurrencesOfKey {
    /**
     * We’re given the head of a linked list and a key. Delete all the nodes that contain the given key.
     * */

    public LinkedListNode deleteKey(LinkedListNode head, int key) {
        LinkedListNode prev = null, cur = head, temp;

        while (cur != null) {
            if (cur.data == key) {
                if (cur == head) {
                    head = head.next;
                    cur = head;
                } else {
                    prev.next = cur.next;
                    cur = cur.next;
                }
            } else {
                prev = cur;
                cur = cur.next;
            }
        }
        return head;
    }
}
