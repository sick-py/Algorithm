import java.util.Arrays;
import java.util.Comparator;

public class VideoRestore {
    /** Leetcode 1024
     * The topic is not difficult to understand. Given a target interval and several small intervals, how to piece together the target interval by cutting and combining the small intervals? How many small areas are needed at least?
     *
     * As I said many times before, interval problems must be sorted according to the starting point or end point of the interval .
     *
     * Because it is easier to find the connection between adjacent intervals after sorting, if it is a problem of finding the maximum value, you can use the greedy algorithm to solve it.
     *
     * As for how to sort, this will vary from question to question. My idea for this question is to sort in ascending order of the starting point first, and sort in descending order of the end point if the starting point is the same.
     *
     * . To use several short videos to create a complete video [0, T], at least one short video must have a starting point of 0.
     * This is easy to understand. If there is no short video that starts from 0, then the interval [0, T]must not be made up.
     *
     * 2. If there are several short videos with the same starting point, the longest (largest end point) video should be selected.
     * This one is a greedy strategy, because the title asks us to calculate the minimum number of short videos needed. If the starting point is the same, the longer the better, don’t waste it, and edit it if there are too many.
     *
     * Based on the above two characteristics, they will be sorted clipsin ascending order of the starting point, and sorted in descending order of the end point if the starting point is the same. The final interval order is like this:
     *
     * the same with jumping game
     * In this way, we can be sure that if the starting point of clips[0]yes is 0, then clips[0]this video will definitely be selected.
     * When we are clips[0]sure will be selected, we can select the next video that will be selected:We will compare all intervals clips[0][1]whose , and according to the greedy strategy, the interval with the largest end point among them is the second video that will be selected .
     *
     * Then the third video can be greedily selected through the second video interval, and so on, until the interval is covered [0, T], or -1 is returned if it cannot be covered.
     * To realize the above ideas, we need to use two variables curEndand nextEnd.
     * */
    int video(int[][] clips, int T) {
        if (T == 0) return 0;
        Arrays.sort(clips, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return b[1] - a[1];
                }
                return a[0] - b[0];
            }
        });
        int res = 0;

        int curEnd = 0, nextEnd = 0;
        int i = 0, n = clips.length;
        while (i < n && clips[i][0] <= curEnd) {
            //choose the second video in the res interval
            while (i < n && clips[i][0] <= curEnd) {
                nextEnd = Math.max(nextEnd, clips[i][1]);
                i++;
            }
            //find the next video
            res++;
            curEnd = nextEnd;
            if (curEnd >= T) {
                return res;
            }
        }
        return -1;
    }

}
