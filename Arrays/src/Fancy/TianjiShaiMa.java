package Fancy;

import java.util.Arrays;
import java.util.PriorityQueue;

public class TianjiShaiMa {
    /**
     * At that time, I didn't come up with any good ideas. I just felt that the core issue here was to make myself as good as possible and let the other party suffer. In summary, if you can beat it, you can fight it, if you can't beat it, you can exchange your own garbage with the opponent's elite .
     *
     * If Tian Ji's No. 1 player can't compare with Qi Wang's No. 1 player , then the other horses must be given for nothing. Obviously, in this case, Tian Ji's bottom horse should be used to give away the head, reduce the loss of one's own side, preserve strength, and increase the odds of the next game. winning percentage.
     *
     * But if Tian Ji's No. 1 player can compare to Qi Wang's No. 1 player , then he will be just as strong as Qi Wang. Anyway, Tian Ji can win this match.
      You may say, in this case, maybe Tian Ji's No. 2 player can also beat King Qi's No. 1 player? If it is possible, wouldn't it be more economical for the number two player to face Qi Wang's number one player?
     * For example, if you can pass the test with 60 points, why bother with 90 points? Every extra point in the test will result in a loss of one point, and it is the most cost-effective to be stuck at 60 points.
     * This frugal strategy is fine, but unnecessary. This is also the interesting part of this question, which requires a sharp turn around the brain :
     *
     * Let's call Tian Ji's No. 1 player T1, No. 2 player T2, and Qi Wang's No. 1 player for the time being Q1
     * If you T2 can win Q1, you are trying to preserve your own strength, let T2 fight Q1, and who are you T1 keeping against?
     * Obviously, you are worried that King Qi still T2 has , so you can let T1 deal with them.
     * But if you think about it carefully, you can defeat Q1 with  T2, but Q1 is King Qi's fastest, then T2 must also be stronger than Q2 and the horses left by King Qi, how could there be horses T2 stronger you?
     * Therefore, there is no need to save, and the final strategy we came up with is:
     *
     * Sort the horses of King Qi and Tian Ji according to their combat effectiveness, and then compare them one by one according to their rankings. If Tian Ji's horse can win, then the race, if not, then change to the bottom horse to give away the head and preserve strength .
     *
     * According to this idea, we need to sort the two arrays, but the order nums2of the elements in cannot be changed, because the order of the calculation results depends on nums2the order of , so we cannot nums2sort , but use other data structures to assist.
     * */
    int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        //nums2 decrease order
        PriorityQueue<int[]> maxpq = new PriorityQueue<>((int[] pair1, int[] pair2) -> {
            return pair2[1] - pair1[1];
        });
        for (int i = 0; i < n; i++) {
            maxpq.offer(new int[] {i, nums2[i]});
        }

        //nums1 increase order
        Arrays.sort(nums1);

        int left = 0, right = n - 1;
        int[] res = new int[n];

        while (!maxpq.isEmpty()) {
            int[] pair = maxpq.poll();
            int i = pair[0], maxVal = pair[1];
            if (maxVal < nums1[right]) {
                res[i] = nums1[right];
                right--;
            }else {
                res[i] = nums1[left];
                left++;
            }
        }
        return res;
    }
}
