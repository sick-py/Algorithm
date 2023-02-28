import Base.ListNode;

import java.util.List;

public class LinkedList {
    public ListNode head;

    public LinkedList() {
        this.head = null;
    }

    public void insertAtHead(int data) {
        if (this.head == null) {
            this.head = new ListNode(data);
        } else {
            ListNode newNode = new ListNode(data);
            newNode.next = head;
            head = newNode;
        }
    }

    public void insertAtTail(int data) {
        if (head == null) {
            head = new ListNode(data);
        } else {
            ListNode newNode = new ListNode(data);
            ListNode temp = head;
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
        ListNode temp = head;
        StringBuilder res = new StringBuilder("[");
        while (temp != null) {
            res.insert(res.length(), temp.val);
            temp = temp.next;
            if (temp != null) {
                res.insert(res.length(), ",");
            }
        }
        res.insert(res.length(),"]");
        System.out.println(res.toString());
    }
}
