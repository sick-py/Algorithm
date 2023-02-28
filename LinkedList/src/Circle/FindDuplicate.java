package Circle;

public class FindDuplicate {
    /** The problem can be treated as a linked list cycle detection problem.
     Since there is only one duplicate number, the array can be treated as a linked list,
     where each number points to the index indicated by the value.
     * For example, if nums[0] = 2, it means that there is a pointer from index 0 to index 2.
     * We can use Floyd's Tortoise and Hare algorithm to detect the cycle in the linked list,
     * which is guaranteed to exist since there is at least one duplicate number in the list.
     * */
    public int findDuplicate(int[] nums) {
        int slow = nums[0], fast = nums[0];

        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) {
                break;
            }
        }

        fast = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
