import java.util.HashMap;
import java.util.Stack;

public class NextElementGreaterThanSubset {
    /**
     * Summary:
     *
     * how to use stack
     *
     * Given two integer arrays, nums1 and nums2, where nums1 is a subset of nums2, find the next greater element of some element x in nums2 that is to the right of x in the same array. If there is no next greater element, then the answer is -1
     *
     * We’ll solve this problem using a stack and a hashmap (map). The hashmap is used to store the result for every number in nums2. Let’s walk through the solution step-by-step:
     *
     * First, we iterate over the nums2 array from the left.
     * If we find an element in nums2 greater than the top of the stack, we keep popping the element from the stack until we find an element on the top of the stack greater than the nums2 element.
     * * put the element ot the stack.
     * We put the popped element into the hashmap with its next greater number.
     * When we find an element in nums2 greater than all stack elements, we put this element as the next greater element for each stack element(s) in the map.
     * Eventually, when there is no next greater element, we reach the end of the nums2 array. We pop the remaining elements from the stack and put their entries in a hashmap with a -1.
     * In the end, we iterate over the nums1 array to find the corresponding results from the hashmap.
     * */

    int[] nextGreater(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums2.length; i++) {
            //// if the stack is not empty and the new element is greater than stack's top element, then add this to hashMap
            while (!stack.isEmpty() && nums2[i] > stack.peek()) {
                map.put(stack.pop(), nums2[i]);
            }
            stack.push(nums2[i]);
        }

        while (!stack.isEmpty()) {
            map.put(stack.pop(), -1);
        }

        int[] res = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }
        return res;

    }
}
