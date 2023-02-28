package SlidingWindows;

public class SubArrayAverageBiggerThanK {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int res = 0;
        int left = 0, right = 0;
        int sumK = 0;
        int numK = 0;
        int n = arr.length;
        while (right < n) {
            sumK += arr[right];
            right++;
            numK++;
            while (numK >= k) {
                if (numK == k) {
                    if (sumK >= threshold * k) {
                        res++;
                    }
                    sumK -= arr[left];
                    left++;
                    numK--;
                }
            }
        }
        return res;
    }
}
