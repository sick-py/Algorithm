package Base;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode prev;
    public ListNode arbitraryPointer;

    public ListNode(int val) {
        this.val = val;
        this.next = null;
        this.prev = null;
        this.arbitraryPointer = null;
    }
}
