import java.util.HashMap;
import java.util.Stack;

public class MonotonicStack {
    /** https://appktavsiei5995.pc.xiaoe-tech.com/p/t_pc/course_pc_detail/image_text/i_627cd21ce4b01a4851fe126d
     *
     * The monotone stack template provided in this article is nextGreaterElementa function , which can calculate the next larger element of each element,
     *
     *  A monotonic stack is actually a stack, but some clever logic is used to keep the elements in the stack in order (monotonically increasing or monotonically decreasing) every time a new element is pushed onto the stack.
     *
     *  Sounds a bit like a heap? No, monotonic stacks are not widely used, and only deal with a typical class of problems, such as "next larger element", "previous smaller element", etc.
     *
     *  Now here is a question for you: input an array nums, please return a result array of equal length, the corresponding index in the result array stores the next larger element, if there is no larger element, store -1. The function signature is as follows:
     *  For example, input an array nums = [2,1,2,4,3], you return the array [4,2,4,-1,-1]. Because the number greater than 2 after the first 2 is 4; the number greater than 1 after 1 is 2; the number greater than 2 after the second 2 is 4; if there is no number greater than 4 after 4, fill in -1; If there is no number greater than 3 after 3, fill in -1.
     *
     *  The violent solution to this question is easy to think of, that is, to scan the back of each element and find the first larger element. But the time complexity of the brute force solution is O(n^2).
     *
     * This problem can be thought of abstractly in this way: imagine the elements of the array as people standing side by side, and the size of the elements as the height of an adult. These people stand in a row facing you, how to find the next larger element of element "2"? It's very simple, if you can see the element "2", then the first person visible behind him is the next larger element of "2", because the elements smaller than "2" are not tall enough, and they are all blocked by "2" , the first one to reveal is the answer.
     * */

    int[] nextGreaterElement(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Stack<Integer> s = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            //judge height
            while (!s.isEmpty() && s.peek() <= nums[i]) {
                //ignore the short one
                s.pop();
            }
            //the bigger element behind nums[i]
            res[i] = s.isEmpty() ? -1 : s.peek();
            s.push(nums[i]);
        }
        return res;
    }

    /** 496
     * The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.
     * You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
     * For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element of nums2[j] in nums2. If there is no next greater element, then the answer for this query is -1.
     * Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.
     *
     * */
    int[] next(int[] nums1, int[] nums2) {
        int[] greater = nextGreaterElement(nums2);

        HashMap<Integer, Integer> greatMap = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            greatMap.put(nums2[i], greater[i]);
        }

        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = greatMap.get(nums1[i]);
        }
        return res;
    }

    /**
     * Daily temperature 739
     * Give you an array temperatures, this array stores the weather and temperature in recent days, you return an array of equal length, calculate: For each day, how many days do you have to wait at least to get a warmer temperature; if you can’t wait On that day, fill in 0. The function signature is as follows:
     * For example, if you input temperatures = [73,74,75,71,69,76], you return [1,1,3,2,1,0]
     * This question is essentially to find the next larger element, but now instead of asking you what the value of the next larger element is, it is asking you the index distance from the current element to the next larger element.
     * */

    int[] dailyTemperature(int[] temperature) {
        int[] res = new int[temperature.length];
        //we put the index in the stack
        Stack<Integer> stack = new Stack<>();
        //monotonicStack template
        for (int i = temperature.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && temperature[stack.peek()] <= temperature[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }
        return res;
    }

    /** 503 next larger element II
     * how to handle circular arrays
     * input a "circular array", please calculate the next larger element of each element in it.
     * For example, input [2,1,2,4,3], you should return [4,2,4,-1,4], because it has the circular property, the last element 3 goes around and finds an element 4 that is bigger than itself .
     *
     * We generally use the % operator to find the modulus (remainder) to simulate the ring effect:
     * int[] arr = {1,2,3,4,5};
     * int n = arr.length, index = 0;
     * while (true) {
     *     // 在环形数组中转圈
     *     print(arr[index % n]);
     *     index++;
     * }
     *
     * This problem must still use the monotone stack problem-solving template, but the difficulty lies in, for example, the input is [2,1,2,4,3], for the last element 3, how to find element 4 as the next larger element.
     *
     * For this requirement, a common routine is to double the length of the array :
     * In this way, element 3 can find element 4 as the next greater element, and the other elements can be calculated correctly.
     *
     * With the idea in mind, the simplest way to implement it is of course to construct this double-length array, and then apply the algorithm template. However, instead of constructing a new array, we can use the technique of looping the array to simulate the effect of doubling the length of the array . Just look at the code:
     * */
    int[] nextCircle(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 2 * n - 1; i >= 0; i--) { //compute the res two times, the first time is the normal one, the second time will update the res
            while (!stack.isEmpty() && stack.peek() <= nums[i % n]) {
                stack.pop();
            }
            res[i % n] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i % n]);
            for (int j = 0; j < n; j++) {
                System.out.printf(" %d ", res[j]);
            }
            System.out.println(" ");
        }
        return res;
    }

    public static void main(String[] args) {
        int[] test = new int[] {
                2, 1, 2, 4, 3
        };

        MonotonicStack t = new MonotonicStack();
        int[] te = t.nextCircle(test);

        //System.out.println(res);
    }


}
