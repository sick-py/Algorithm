package Base;

/**
 * pay attention to:
 * using dummy
 * the < or <= . the index of the node
 * those details are really important and time spending
 * */
class Node{
    int val;
    Node next;
    Node(int a) {
        val = a;
        next = null;
    }
}

public class MyLinkedList {

    Node dummy;
    int len;

    public MyLinkedList() {
        dummy = new Node(-1);
        len = 0;
    }

    public int get(int index) {
        if (index >= len) {
            return -1;
        }
        Node cur = dummy;
        for (int i = 0; i <= index; i++) { //i <= pat attetion
            cur = cur.next;
        }
        return cur.val;
    }

    public void addAtHead(int val) {
        Node head = new Node(val);
        head.next = dummy.next;
        dummy.next = head;
        len++;
    }

    public void addAtTail(int val) {
        Node node = new Node(val);
        Node cur = dummy;
        for (int i = 0; i < len; i++) {
            cur = cur.next;
        }
        cur.next = node;
        len++;
    }

    public void addAtIndex(int index, int val) {
        if (index > len) { /*here it's > not >=*/
            return;
        }

        Node node = new Node(val);
        Node cur = dummy;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        node.next = cur.next;
        cur.next = node;
        len++;
    }

    public void deleteAtIndex(int index) {
        if (index >= len) {
            return;
        }
        Node cur = dummy;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        len--;
    }

}
