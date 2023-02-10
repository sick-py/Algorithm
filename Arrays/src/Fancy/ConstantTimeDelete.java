package Fancy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class ConstantTimeDelete {
    /**
     * The difficulty of this question lies in two points:
     * 1. The time complexity of the three operations of inserting, deleting, and obtaining random elements must be O(1) .
     *
     * 2. getRandomThe elements returned by the method must return random elements with equal probability , that is, if there nare , the probability of each element being returned must be 1/n.
     *
     * Let's analyze it first: For the operations of insertion, deletion, and search, which data structure has a time complexity of O(1)?
     *
     * HashSetIt must be one, right? The underlying principle of the hash set is a large array. We map the elements to an index through a hash function; if the zipper method is used to resolve hash conflicts, then this index may be connected to a linked list or a red-black tree.
     *
     * So for such a standard HashSet, can you implement the getRandomfunction ?
     *
     * In fact, it is impossible, because according to the underlying implementation just mentioned, the elements are "scattered" into the entire array by the hash function, not to mention there are zipper methods and other mechanisms to resolve hash conflicts, so it cannot be done. O (1) Time "equal probability" to randomly obtain elements.
     *
     * In addition HashSet, there are some similar data structures, such as hash linked lists LinkedHashSet, which we will see later Implement the LRU algorithm by handand Realize the LFU algorithm by handI have talked about the realization principle of this type of data structure, which is essentially a hash table combined with a double-linked list, and the elements are stored in the double-linked list.
     *
     * However, LinkedHashSetit only HashSetadds order to , but still cannot implement our getRandomfunction as required, because if the bottom layer uses a linked list structure to store elements, it is impossible to access a certain element in O(1) time.
     *
     * According to the above analysis, for the getRandommethod , if you want to extract elements with "equal probability" and "in O(1) time", you must satisfy: the bottom layer is implemented with an array, and the array must be compact .
     *
     * In this way, we can directly generate a random number as an index, and take out the element corresponding to the random index from the array as a random element.
     *
     * But if you use an array to store elements, how can the time complexity of insertion and deletion be O(1) ?
     *
     * can do! Insertion and deletion operations at the end of the array do not involve data movement, and the time complexity is O(1).
     *
     * Therefore, if we want to delete an element in the array in O(1) time val, we can first exchange this element to the end of the array, and popthen delete it .
     * Exchanging two elements must be exchanged by index, right? Then we need a hash table valToIndexto record the index corresponding to each element value.
     * */
    List<Integer> nums;
    HashMap<Integer, Integer> valToIndex;
    Random random;

    public ConstantTimeDelete() {
        nums = new ArrayList<>();
        valToIndex = new HashMap<>();
        random = new Random();
    }

    boolean insert(int val) {
        if (valToIndex.containsKey(val)) {
            return false;
        }

        valToIndex.put(val, nums.size());
        nums.add(val);
        return true;
    }

    boolean remove(int val) {
        if (!valToIndex.containsKey(val)) {
            return false;
        }
        //exchange the removed with the last
        int index = valToIndex.get(val);
        valToIndex.put(nums.get(nums.size() - 1), index);
        int temp = nums.get(index);
        nums.set(index, nums.get(nums.size() - 1));
        nums.set(nums.size() - 1, temp);

        nums.remove(nums.size() - 1);
        valToIndex.remove(val);
        return true;
    }

    int getRandom() {
        return nums.get(random.nextInt(nums.size()));
    }







    /**
     * LC710
     * Enter a positive integer for you N, representing the left-closed right-open interval [0,N), and then enter an array for you blacklist, which contains some "blacklist numbers". then pick one random number which is not in blacklist
     *
     * This should not be difficult to understand. For example, if you input N = 5, blacklist = [1,3]the pickfunction , it will randomly return a number among 0, 2, and 4 with equal probability.
     *
     * And the title requires that picktherand() random number generation function should be called as little as possible in the function .
     *
     * What does this sentence mean? For example, we may come up with the following solution:
     *
     * int pick() {
     *     int res = rand() % N;
     *     while (res exists in blacklist) {
     *         // redo
     *         res = rand() % N;
     *     }
     *     return res;
     * }
     * This function will call the rand()function , and the execution efficiency is actually related to the random number, which is not a beautiful solution.
     *
     * The clever solution is similar to the previous question. We can regard the interval [0,N)as an array, then move blacklist the elements in to the end of the array, and use a hash table for mapping :
     */

    class Solution701{
        int sz;
        HashMap<Integer, Integer> valToIndex;

        public Solution701(int n, int[] blackList) {
            sz = n - blackList.length;
            for (int item : blackList) {
                valToIndex.put(item, -1);
            }
            int last = n - 1;
            for (int item : blackList) {
                //if b is already in [sz, N), just ignore
                if (item >= sz) {
                    continue;
                }
                while (valToIndex.containsKey(last)) {
                    last--;
                }
                valToIndex.put(item, last);
                last--;
            }
        }

        public int pick() {
            int index = (int)(Math.random() * sz);
            //if it's in blacklist, it needs to map to other place
            if (valToIndex.containsKey(index)) {
                return valToIndex.get(index);
            }
            return index;
        }
    }

    /**
     * 1. If you want to efficiently and randomly obtain elements with equal probability, you must use an array as the underlying container.
     *
     * 2. If you want to keep the compactness of the array elements, you can change the elements to be deleted to the end, and then popdelete the elements at the end, so the time complexity is O(1). Of course, we need an additional hash table to record the value-to-index mapping.
     *
     * 3. For the second question, the array contains "holes" (blacklist numbers), and the hash table can also be used to handle the mapping relationship cleverly, so that the array is logically compact and it is convenient to randomly select elements.
     * */
}
