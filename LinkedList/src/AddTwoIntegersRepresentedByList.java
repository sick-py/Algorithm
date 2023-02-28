import Base.ListNode;

public class AddTwoIntegersRepresentedByList {
    /**We are given the head pointers of two linked lists, each linked list represents an integer number (each node is a digit). Add them and return the resulting linked list (i.e., sum).
     *
     * For a better understanding of the problem, let’s consider an example. Suppose we want to add the integers 9901 and 237, to get the sum 10138. The three linked lists represent the two integers and their sum as follows:
     *
     * The integers are stored inverted in the linked lists to make the addition easier. Now, the most significant digit of the number is the last element of the linked list.
     *
     * For addition, we start from the heads of the two linked lists.
     * At each iteration, we add the current digits of the two lists and insert a new node with the resulting digit at the tail of the resulting linked list.
     * We also need to maintain carry at each step.
     * We continue this for all digits in both the linked lists.
     * If one of the linked lists ends sooner, we continue with the other linked list.
     * Once both of the linked lists are exhausted, and no carry is left to be added, the algorithm is terminated.
     * Now, let’s walk through the solution step by step using this animation:
     *
     * */
    ListNode addIntegers(ListNode int1, ListNode int2) {
        ListNode res = null, last = null;
        int carry = 0;

        while (int1 != null || int2 != null || carry > 0) {
            int first = (int1 == null ? 0 : int1.val);

            int second = (int2 == null ? 0 : int2.val);

            int sum = first + second + carry;
            ListNode pNew = new ListNode(sum % 10);
            carry = sum / 10;

            if (res == null) {
                res = pNew;
            } else {
                last.next = pNew;
            }

            last = pNew;

            if (int1 != null) {
                int1 = int1.next;
            }

            if (int2 != null) {
                int2 = int2.next;
            }
        }

        return res;
    }
}
