import Base.ListNode;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public ListNode middleNode(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}