import java.util.ArrayList;

public class MergeIntervals {
    /**
     * We’re given an array of interval pairs as input where each interval has a start and end timestamp. The input array is sorted by starting timestamps. Merge the overlapping intervals and return a new output array.
     *
     * This problem can be solved in a simple linear scan algorithm. We know that the input is sorted by starting timestamps. Here is the approach we are following:
     *
     * Using the given list of input intervals, we keep merged intervals in the output list.
     * For each interval in the input list:
     * If the input interval is overlapping with the last interval in the output list, then we merge these two intervals and update the last interval of the output list with the merged interval.
     * Otherwise, we add an input interval to the output list.
     * */

    class Interval {
        public int first;
        public int second;

        public Interval(int x, int y) {
            first = x;
            second = y;
        }
    }

    ArrayList<Interval> merge(ArrayList<Interval> v) {
        if (v == null || v.size() == 0) {
            return null;
        }

        ArrayList<Interval> res = new ArrayList<>();

        res.add(new Interval(v.get(0).first, v.get(0).second));
        for (int i = 1; i < v.size(); i++) {
            Interval recent = res.get(res.size() - 1);
            if (recent.second >= v.get(i).first) {
                recent.second = Math.max(recent.second, v.get(i).second);
            } else {
                res.add(new Interval(v.get(i).first, v.get(i).second));
            }
        }
        return res;
    }
}
