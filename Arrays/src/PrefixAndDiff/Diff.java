package PrefixAndDiff;

public class Diff {
    /**
     *For example, I input an array for you nums, and then ask to add 1 to all intervals, nums[2..6]then nums[3..9]subtract 3 to nums[0..4]all add 2 to all, and then give...
     *
     * Here we need the technique of differential array, similar to the prefixarray , we first numsconstruct a diffdifferential array for the array, diff[i] is the difference between  nums[i] and nums[i-1]
     * Constructing the difference array diffin this way can quickly increase or decrease the interval . If you want to add 3 to all nums[i..j]the , you only need to let ,diff[i] += 3 and then let :diff[j+1] -= 3
     * The principle is very simple. diffRecalling numsthe process of inverting the array, it diff[i] += 3means adding 3 to nums[i..]all the elements, and diff[j+1] -= 3then nums[j+1..]means subtracting 3 from all the elements. In combination, is it just nums[i..j]adding 3 to all the elements
     * */
    private int[] diff;

    public Diff(int[] nums) {
        diff = new int[nums.length];
        diff[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            diff[i] = nums[i] = nums[i - 1];
        }
    }

    public void increment(int i, int j, int val) {
        diff[i] += val;
        if (j + 1 < diff.length) {
            diff[j + 1] -= val;
        }
    }

    public int[] res() {
        int[] res = new int[diff.length];
        res[0] = diff[0];
        for (int i = 1; i < diff.length; i++) {
            res[i] = res[i - 1] + diff[i];
        }
        return res;
    }

    /**
     * Of course, the actual algorithm problem may require us to associate and abstract the topic, and it will not be so straightforward to let you see that the differential array technique is used.
     *
     * you need to define the meaning of the array first
     *
     * Here is a look at the 1109th question of Lituo " Flight Booking Statistics":
     * You are given an nums where all elements are 0. Enter another one for you bookings, which contains several triples (i, j, k). The meaning of each triple is to require you to add  all elements in the closed interval [i-1,j-1] of the k. Please return the last nums array?
     * */
    int[] flightBooking(int[][] bookings, int n) {
        int[] nums = new int[n];
        Diff df = new Diff(nums);

        for (int[] booking : bookings) {
            //because this starts from 1
            int i = booking[0] - 1;
            int j = booking[1] - 1;
            int val = booking[2];
            df.increment(i, j, val);
        }

        return res();
    }

    /**
     * You are a bus driver, the maximum passenger capacity of the bus is capacity, and there are several stations along the way, and you are given a passenger itinerary int[][] trips, in which trips[i] = [num, start, end]represents num passenger who wants to get start and get off at station , please calculate whether you can transport all passengers at one time (cannot exceed the maximum passenger capacity).
     * */
    boolean carMax(int[][] trips, int capacity) {
        //at most 1001 bus stations
        int[] nums = new int[1001];
        Diff df = new Diff(nums);

        for (int[] trip : trips) {
            int val = trip[0];
            //trip[1] get on the bus
            int i = trip[1];
            //trip[2] get off, so the passenger is on the bus during [trip[1], trip[2] - 1]
            int j = trip[2] - 1;
            df.increment(i, j, val);
        }

        int[] res = res();
        for (int i = 0; i < res().length; i++) {
            if (capacity < res[i]) {
                return false;
            }
        }
        return true;
    }
}
