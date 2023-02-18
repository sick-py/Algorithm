package LeftRightPointers;

public class BinarySearchApplication {
    /**
     *What problems can use the binary search algorithm trick?
     *
     * First, you need to abstract an independent variable x, f(x), and a target value from the title .
     *
     * At the same time, x, f(x), target must meet the following conditions:
     *
     * 1.f(x)must be a monotone function x on (both monotonically increasing and monotonically decreasing) .
     *
     * 2. The question is to let you calculate the value of f(x) == target when the constraints are meet x .
     *
     * The above rules sound a bit abstract, let's give a concrete example:
     *
     * Given an ordered array in ascending order numsand a target element target, please calculate the index position targetin the array, if there are multiple target elements, return the smallest index.
     *
     * This is the basic question type of "searching the left boundary". The solution code has been written before, but what x, f(x), targetare the differences in it?
     *
     * We can regard the index of the element in the array as an independent variable x, and the functional relationship f(x)can be set as follows:
     *
     * // f(x), nums won't change, so ignore it
     * int f(int x, int[] nums) {
     *     return nums[x];
     * }
     *
     * draw the picture: important
     * If you encounter an algorithm problem and can abstract it into this picture, you can use the binary search algorithm on it
     *
     * Conclusion:
     * 1. Determine what x, f(x), targeteach is, and write fthe code for the function .
     *
     * 2. Find xthe value range of as the search interval of the binary search, and initialize the leftand rightvariables .
     *
     * 3. According to the requirements of the topic, determine whether to use the binary search algorithm on the left side or the right side of the search, and write the solution code .
     * */

    /** LeetCode 875
     * Keke can only eat a bunch of bananas at most every hour, and if she can’t finish it, she will eat it in the next hour; if she still has an appetite after eating this bunch, she will only eat another bunch in the next hour.
     *
     * He wants to eat all the bananas before the guards come back, let's determine the minimum speedK to eat bananas . The function signature is as follows:
     *
     * 1. Determine what x, f(x), targeteach is, and write the code for the function .
     *
     * x What is the independent variable ? Recalling the previous function image, the essence of binary search is to search for independent variables.
     *
     * Therefore, whatever the question asks for is set as an independent variable, and the speed at which Keke eats bananas is the independent variable x.
     *
     * So, what xis the monotonic functional relationship f(x)on ?
     *
     * Obviously, the faster you eat bananas, the less time it takes to eat all the banana piles, and the relationship between speed and time is a monotonic function.
     *
     * So, the f(x)function can be defined like this:
     *
     * If the rate of eating bananas is x roots /hour, it takes f(x)hours to eat all the bananas.
     * */
    static int f(int[] piles, int x) {
        int hours = 0;
        for (int i = 0; i < piles.length; i++) {
            hours += piles[i] / x;
            if (piles[i] % x > 0) {
                hours++;
            }
        }
        return hours;
    }
    /**targetIt is obvious that the time limit for eating bananas is Hnaturally the largest constraint targeton the f(x)return value.
     * 2. Find x the value range of as the search interval of the binary search, and initialize the left and right variables .
     *
     * What is the minimum speed for Keke to eat bananas? How big is it?
     *
     * Obviously, the minimum speed should be 1, and the maximum speed should be the maximum value of the elements in the piles array , because eating at most one bunch of bananas per hour is useless no matter how big your appetite is.
     *
     * There are two choices here, either you use a for loop to traverse the piles array and calculate the maximum value, or you look at the constraints given by the title, piles what is the value range of the elements in, and then right initialize a value outside the value range.
     *
     * I choose the second one, as the title says 1 <= piles[i] <= 10^9, then I can determine the interval boundary of the binary search:
     * */

    /**
     * According to the requirements of the topic, determine whether to use the binary search algorithm on the left side or the right side of the search, and write the solution code .
     *
     * Now we have determined that the independent variable xis the speed of eating bananas, which f(x)is a monotonically decreasing function, targe twhich is the time limit for eating bananas . The question requires us to calculate the minimum speed, that is, it x should be as small as possible:
     * This is the binary search for searching the left boundary
     * */
    public int minSpeed(int[] piles, int H) {
        int left = 0, right = 1000000000 + 1;

        while (left <= right) { //left = right + 1;
            int mid = left + (right - left) / 2;
            if (f(piles, mid) == H) {
                right = mid - 1;
            } else if (f(piles, mid) < H) {
                right = mid - 1; // pay attention, the less time the quick, so slow down
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /**LeetCode 1011
     * To transport all the goods in order Dwithin days , the goods are indivisible, how to determine the minimum load for transportation?
     * */
    int f2(int[] weights, int weight) {
        int days = 0;
        for (int i = 0; i < weights.length; i++) {
            int w = weight;
            while (i < weights.length) {
                if (w < weights[i]) break;
                else w -= weights[i];
                i++;
            }
            days++;
        }
        return days;
    }

    int ship(int[] weights, int days) {
        int left = 0, right = 1;
        for (int w : weights) {
            left = Math.max(left, w);
            right += w;
        }

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (f2(weights, mid) <= days) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length, col = matrix[0].length;
        int left = 0, right = row * col - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (matrix[mid / col][mid % col] == target) {
                return true;
            } else if (matrix[mid / col][mid % col] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

    class review {
        //875
        public int minEatingSpeed(int[] piles, int h) {
            int max = -1;
            for (int i : piles) {
                max = Math.max(i, max);
            }

            int left = 1, right = max;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (f(piles, mid) == h) {
                    right = mid - 1;
                } else if (f(piles, mid) < h) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return left;

        }

        int f(int[] piles, int k) {
            int h = 0;
            for (int i = 0; i < piles.length; i++) {
                h += piles[i] / k;
                if (piles[i] % k > 0) {
                    h++;
                }
            }
            return h;
        }
    }


    public static void main(String[] args) {

        for (int i = 0; i < 12; i++) {
            int[] piles = {3,6,7,11};
            int[] piles2 = {3,6,7,11};
            System.out.printf("for f3 : %d\n", f(piles, i));
            System.out.printf("for f : %d\n", f(piles2, i));
            System.out.printf("**********");
        }


    }


}
