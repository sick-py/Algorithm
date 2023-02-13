import java.util.Arrays;

public class SchedulingMeetingRoom {
    /**
     * You can input several intervals in the form [begin, end]of , which represent the start time and end time of several meetings. Please calculate at least how many meeting rooms you need to apply for.
     * For example, given your input meetings = [[0,30],[5,10],[15,20]], the algorithm should return 2, because the time of the last two meetings and the first meeting are in conflict, and at least two meeting rooms can be applied to allow all meetings to proceed smoothly.
     *
     * For this kind of time arrangement problem, it is essentially an interval scheduling problem. In all likelihood, it must be sorted, and then find the law to solve it.
     *
     * We have written many articles related to interval scheduling before, so here is a way to help you sort out the ideas of this kind of problem:
     *
     * In the first scenario , assuming there is only one meeting room and several meetings, how do you arrange as many meetings as possible in this meeting room?
     *
     * This problem needs to sort these meetings (intervals) according to the end time (right endpoint), and then process them, see the previous article for details Greedy algorithm for time management。
     *
     * In the second scene , you are given several shorter video clips and one longer video clip. Please pick out as few clips as possible from the shorter clips and stitch together a longer clip.
     *
     * This problem needs to sort these video clips (intervals) according to the start time (left endpoint) and then process them. See below for details Cut the video and cut out a greedy algorithm。
     *
     * In the third scenario , you are given several intervals, some of which may be relatively short and completely covered by other intervals, please delete these covered intervals.
     *
     * This problem requires sorting these intervals according to the left endpoint, and then you can find and delete those intervals that are completely covered. See below for details delete coverage interval。
     *
     * In the fourth scenario , you are given several intervals, please merge all overlapping intervals.
     *
     * This problem needs to sort these intervals according to the left endpoint, so as to find out the overlapping intervals, see below for details Merge Overlapping Intervals。
     *
     * In the fifth scenario , two departments reserve several time slots of the same conference room at the same time, please calculate the conflict time slots of the conference room.
     *
     * This problem is to give you a list of two sets of intervals. Please find the intersection of these two sets of intervals. This requires you to sort these intervals by their left endpoints. See below for details. interval intersection problem。
     *
     * In the sixth scenario , assuming that there is only one meeting room and several meetings, how to arrange the meeting to minimize the idle time of this meeting room?
     *
     * This problem needs to use your brain. To put it bluntly, this is a deformation of the 0-1 knapsack problem:
     *
     * The conference room can be regarded as a backpack, and each meeting can be regarded as an item. The value of the item is the duration of the meeting. How do you choose the item (meeting) to maximize the value in the backpack (the usage time of the conference room)?
     *
     * Of course, the constraint of the backpack here is not a maximum weight, but that each item (session) cannot conflict with each other. Sort the meetings according to the end time, and then refer to the previous article 0-1 Detailed explanation of knapsack problemThe idea can be solved, and I can write about this problem when I have a chance in the future.
     *
     * The seventh scene is the scene I want to talk about in this article. I will give you some meetings and let you apply for a meeting room reasonably.
     *
     * Well, with so many examples, let's see how to solve this problem today.
     * */

    /**
     * Enter a number of time intervals for you, and let you calculate how many intervals overlap "at most" at the same time .
     *
     * The key point of the question is, given you any time, can you tell how many meetings there are at this time?
     *
     * If it can be done, then I will go through all the moments and find the maximum value, which is the number of meeting rooms that need to be applied for.
     *
     * Is there a data structure or algorithm, if I input several intervals, I can know how many intervals overlap each position?
     *
     * Old readers can definitely think of an algorithmic trick mentioned before: Differential array trick。
     *
     * Think of the timeline as an array with an initial value of 0. Each time interval [i, j]is equivalent to a sub-array. If there is a conference in this time interval, I will add one to the elements in the sub-array.
     *
     * Finally, don't I know how many meetings there are at each moment? I traverse the entire array, don't I know at least how many meeting rooms are needed?
     *
     * For example, if input meetings = [[0,30],[5,10],[15,20]], then we add one to each of [0,30],[5,10],[15,20]these index intervals in the array, and finally traverse the array to find the maximum value.
     *
     * Remember, the difference array technique can add and subtract elements in the entire interval in O(1) time, so it can be used to solve this problem.
     * */

    /** Based on the idea of ​​differential arrays, we can derive a more efficient and elegant solution .
     * The red dots represent the start time of each meeting, and the green dots represent the end time of each meeting.
     *
     * Now imagine a line with a counter, scanning from left to right on the timeline, every time a red point is encountered, the counter countis incremented by , and every time a green point is encountered, the counter countis decremented by one:
     *
     * In this way, how many meetings are going on at the same time at each moment is countthe , and countthe maximum value is the number of meeting rooms that need to be applied for .
     * */
    int meetingArrange(int[][] meetings) {
        int n = meetings.length;
        int[] begin = new int[n];
        int[] end = new int[n];
        for (int i = 0; i < n; i++) {
            begin[i] = meetings[i][0];
            end[i] = meetings[i][1];
        }
        Arrays.sort(begin);
        Arrays.sort(end);

        int count = 0;
        int res = 0, i = 0, j = 0;
        while (i < n && j < n) {
            if (begin[i] < end[i]){
                count++;
                i++;
            } else {
                count--;
                j++;
            }
            res = Math.max(res, count);
        }
        return res;
    }
}
