package FastSlowPointers;

import java.util.HashSet;

public class RemoveDuplicate {
    class  LinkedListNode {
        int data;
        LinkedListNode next;

        public LinkedListNode(int a) {
            data = a;
            next = null;
        }
    }
    /**
     * Since the array is already sorted, duplicate elements must be connected together, and it is not difficult to find them. But if you delete a duplicate element immediately in place every time you find it, since deleting elements in the array involves data movement, the entire time complexity will be reached O(N^2).
     * To solve this problem efficiently, you need to use the fast and slow pointer technique:
     * We let the slow pointer slow go at the back, and the fast pointer fast go ahead to explore the way, and assign a value to slow
     * In this way, it is guaranteed that there nums[0..slow]are no repeated elements. When the fastpointer traverses the entire numsarray , nums[0..slow]it is the result of deduplication of the entire array.
     *
     * */
    int remove(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int slow = 0, fast = 0;

        while (fast < nums.length) {
            if (nums[fast] != nums[slow]) {
                slow++; //the first is equal
                nums[slow] = nums[fast];
            }
            fast++;
        }

        return slow + 1;
    }

    /**
     * Let's expand it briefly, and look at question 83 of Lituo " Remove duplicate elements in sorted linked list", if you are given an ordered singly linked list, how to remove the duplicates?
     *
     * In fact, it is exactly the same as array deduplication. The only difference is that the array assignment operation is turned into an operation pointer. You can compare it with the previous code:
     * */
    LinkedListNode remove(LinkedListNode head) {
        if (head == null) {
            return null;
        }

        LinkedListNode slow = head, fast = head;
        while (fast != null) {
            if (fast.data != slow.data) {
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }
        slow.next = null;
        return head;
    }

    /**
     * In addition to allowing you to deduplicate in an ordered array/linked list, the title may also allow you to "delete in place" some elements in the array .
     *
     * For example, in the 27th question of Lituo, " remove element", look at the topic:
     *
     * Note that there is a detailed difference between this and the solution of deduplication of ordered arrays. Here, we first nums[slow]assign to and then give slow++it, so as to ensure nums[0..slow-1]that does not contain valelements whose value is , and the length of the final result array is slow.
     * */

    int removeVal(int[] nums, int val) {
        if (nums.length == 0) {
            return 0;
        }

        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }

    /**
     * Move zeros to the end
     * */

    void removeZero(int[] nums) {
        if (nums.length == 0) {
            return;
        }

        /**
         * or int slow = removeVal(nums, 0);
         * */
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }

        for (;slow < nums.length; slow++) {
            nums[slow] = 0;
        }
    }

    class review {
        //if it's 2 duplicate, we use a count to recorde the times
        public int removeDuplicates(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            int slow = 0, fast = 0;
            int count = 0;
            while (fast < nums.length) {
                if (nums[fast] != nums[slow]) {
                    slow++;
                    nums[slow] = nums[fast];
                } else if (slow < fast && count < 2) {
                    //if the duplicate is not more than 2
                    slow++;
                    nums[slow] = nums[fast];
                }
                fast++;
                count++;
                if (fast < nums.length && nums[fast] != nums[fast - 1]) {
                    count = 0;
                }
            }
            return slow + 1;
        }
    }

    class review2 {
        public int removeDuplicates(int[] nums) {
            int slow = 0, fast = 0;
            while (fast < nums.length) {
                if (nums[slow] != nums[fast]) {
                    nums[++slow] = nums[fast];
                }
                fast++;
            }
            return slow + 1;
        }

        public boolean containsDuplicate(int[] nums) {
            HashSet<Integer> set = new HashSet<>();
            for (int i : nums) {
                if (set.contains(i)) {
                    return true;
                }
                set.add(i);
            }
            return false;
        }

        public int singleNumber(int[] nums) {
            //1 ^ 0 = 1
            //1 ^ 1 = 0
            int n = nums.length;
            int res = 0;
            for (int i = 0; i < n; i++) {
                res = res ^ nums[i];
            }
            return res;
        }

        public void moveZeroes(int[] nums) {
            int slow = 0, fast = 0;
            while (fast < nums.length) {
                if (nums[fast] != 0) {
                    nums[slow++] = nums[fast];
                }
                fast++;
            }
            while (slow < nums.length) {
                nums[slow++] = 0;
            }
        }
    }

}
