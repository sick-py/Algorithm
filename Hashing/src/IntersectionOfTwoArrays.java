import java.util.*;
import java.util.LinkedList;

public class IntersectionOfTwoArrays {
    public int[] intersect(int[] nums1, int[] nums2) {
        //find the duplicate
        HashMap<Integer, Integer> valToCount = new HashMap<>();
        //nums1
        for (int i : nums1) {
            valToCount.put(i, valToCount.getOrDefault(i, 0) + 1);
        }
        List<Integer> elements = new LinkedList<>();

        //nums2
        HashMap<Integer, Integer> valToCount2 = new HashMap<>();
        for (int i : nums2) {
            valToCount2.put(i, valToCount2.getOrDefault(i, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : valToCount.entrySet()) {
            if (valToCount2.containsKey(entry.getKey())) {
                int count = Math.min(valToCount2.get(entry.getKey()), entry.getValue());
                for (int i = 0; i < count; i++) {
                    elements.add(entry.getKey());
                }
            }
        }

        int[] res = new int[elements.size()];
        for (int i = 0; i < elements.size(); i++) {
            res[i] = elements.get(i);
        }
        return res;
    }
}
