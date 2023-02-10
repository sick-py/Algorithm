import Base.LinkedListNode;

import java.util.List;

public class LinkedList {
    public LinkedListNode head;

    public LinkedList() {
        this.head = null;
    }

    public void insertAtHead(int data) {
        if (this.head == null) {
            this.head = new LinkedListNode(data);
        } else {
            LinkedListNode newNode = new LinkedListNode(data);
            newNode.next = head;
            head = newNode;
        }
    }

    public void insertAtTail(int data) {
        if (head == null) {
            head = new LinkedListNode(data);
        } else {
            LinkedListNode newNode = new LinkedListNode(data);
            LinkedListNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public void createLinkedList(List<Integer> lst) {
        for (int i = 0; i < lst.size(); i++) {
            insertAtTail(lst.get(i));
        }
    }

    public void display() {
        LinkedListNode temp = head;
        StringBuilder res = new StringBuilder("[");
        while (temp != null) {
            res.insert(res.length(), temp.data);
            temp = temp.next;
            if (temp != null) {
                res.insert(res.length(), ",");
            }
        }
        res.insert(res.length(),"]");
        System.out.println(res.toString());
    }
}
