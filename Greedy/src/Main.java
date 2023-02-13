import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    /**
     * Greedy algorithm can be considered as a special case of dynamic programming algorithm. Compared with dynamic programming, using greedy algorithm needs to meet more conditions (greedy selection property), but the efficiency is higher than dynamic programming.
     *
     * Greedy algorithm can be considered as a special case of dynamic programming algorithm. Compared with dynamic programming, using greedy algorithm needs to meet more conditions (greedy selection property), but the efficiency is higher than dynamic programming.
     *
     *  Main ideas: each step makes a local optimal choice, and the final result is the global optimal. Note that this is a special property. In fact, only some problems have this property.
     *
     *  For example, there are 100 RMB in front of you, and you can only take ten. How can you get the most denominations? Obviously, every time you choose the one with the largest face value among the remaining banknotes, your final choice must be the best.
     * */

    /** Closer to home, this article solves a very classic greedy algorithm problem Interval Scheduling (interval scheduling problem), which is the 435th question " No overlapping interval":

     Given a lot of closed intervals [start, end]like , please design an algorithm to calculate how many disjoint intervals there are at most among these intervals .
     * For example, intvs = [[1,3], [2,4], [3,6]], these intervals have at most 2 intervals that are mutually disjoint, ie [[1,3], [3,6]], your algorithm should return 2. Note that boundaries that are identical do not count as intersections.
     *
     * This question is widely used in daily life. For example, you have several activities today, and each activity can use intervals [start, end]to indicate start and end time. How many activities can you participate in at most today ? Obviously you cannot participate in two activities at the same time, so the problem is to find the largest disjoint subset of these time intervals.
     *
     * There are many greedy ideas that look good on this question, but none of them can get the correct answer. For example:
     *
     * Maybe we can choose the one with the earliest start in the optional range every time? But there may be some intervals that start very early, but are very long, making us mistakenly miss some short intervals. Or do we choose the shortest one in the optional range each time? Or choose the interval with the fewest conflicts? These solutions can easily give counterexamples, which are not correct solutions.
     *
     * The correct idea is actually very simple, which can be divided into the following three steps:
     *
     * 1. Select an interval from the interval intvs set, which x is the earliest ( ends mallest) end among all current intervals.
     *
     * 2. Delete all the intervals that intersect with the interval from the interval set intvs.
     *
     * 3. Repeat steps 1 and 2 until intvsis empty. Those previously selected xare the largest disjoint subsets.
     *
     * If this idea is implemented into an algorithm, it can be sorted in ascending order according to the endvalue , because it is much more convenient to implement step 1 and step 2 after this processing,
     *
     * Now to implement the algorithm, for step 1, since we have endsorted , it xis easy to choose . The key is, how to remove the xintersecting interval and select the next cycle x?
     *
     * Since we sorted in advance , it is not difficult to find that all intervals that xintersect must intersectx with ; if an interval does not want to intersect with , its must be greater than (or equal to ) :endxendstartxend
     * */
    int interval(int[][] intvs) {
        if (intvs.length == 0) return 0;
        Arrays.sort(intvs, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });
        //at least one interval is not in intersection
        int count = 1;

        int xEnd = intvs[0][1];
        for (int[] interval : intvs) {
            int start = interval[0];
            if (start >= xEnd) {
                //find the next interval
                count++;
                xEnd = interval[1];
            }
        }
        return count;
    }

    /** The first is the 435th question " No overlapping interval"question:

     Input a set of intervals, please calculate, how many intervals need to be removed in order to make the intervals in it non-overlapping? The function signature is as follows:
     For example, if the input is intvs = [[1,2],[2,3],[3,4],[1,3]], the algorithm returns 1, because as long as [1,3]is , the remaining intervals will not overlap.

     Let's say the input is [[10,16],[2,8],[1,6],[7,12]], the algorithm should return 2, because we can shoot an arrow where xis 6 and burst both[2,8] balloons and then shoot an arrow where is 10, 11 or 12 and both balloons.[1,6]x[10,16][7,12]

     In fact, if you think about it a little bit, this problem is exactly the same as the interval scheduling algorithm! If nthere non-overlapping intervals, then at least narrows need to penetrate all intervals:

     The only difference is that in the intervalSchedulealgorithm , if the boundaries of two intervals touch, they are not considered overlapping; according to the description of this question, if the arrow touches the boundary of the balloon, the balloon will explode, so it is equivalent to the boundary of the interval Touching also counts as overlap:



     int findMinArrowShots(int[][] intvs) {
     // ...

     for (int[] interval : intvs) {
     int start = interval[0];
     // 把 >= 改成 > 就行了
     if (start > x_end) {
     count++;
     x_end = interval[1];
     }
     }
     return count;
     }

     *
     * */



}