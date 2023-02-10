import Base.LinkedListNode;

public class ReverseEvenNodes {
    /**
     * Given a singly linked list, reverse the nodes at even positions and return the linked list.
     * We won’t copy the data of nodes or re-allocate memory for nodes, as that is inefficient.
     *
     * We create two lists of nodes at even and odd positions.
     * While extracting even nodes, we push them to the head of a new list because we need them in reverse order while merging.
     * Now that the two lists are in the correct order, it’s just a matter of merging their nodes alternately.
     * */
    public LinkedListNode merge(LinkedListNode list1, LinkedListNode list2) {
        if (list1 == null) {
            return list2;
        }

        if (list2 == null) {
            return list1;
        }

        LinkedListNode head = list1;

        while (list1.next != null && list2.next != null) {
            LinkedListNode temp = list2;
            list2 = list2.next;

            temp.next = list1.next;
            list1.next = temp;
            list1 = temp.next;
        }

        if (list1.next == null) {
            list1.next = list2;
        }

        return head;
    }

    public LinkedListNode reverse(LinkedListNode head) {
        if (head == null) {
            return null;
        }

        LinkedListNode curr = head, listEven = new LinkedListNode(-1);
        while (curr != null && curr.next != null) {
            LinkedListNode even = curr.next;
            curr.next = even.next;

            even.next = listEven;
            listEven = even;
            curr = curr.next;
        }
        return merge(head, listEven);
    }
}
