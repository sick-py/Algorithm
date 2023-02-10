package SlidingWindows;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class RabinKarpStringMatching {
    /**
     *  if you input a positive integer in the form of a string, how to convert it into a digital form?
     *
     *  It can be seen that the core idea of ​​this algorithm is to continuously add numbers to the lowest digit (ones digit) , and at the same time shift the previous digits to the left by one digit (multiplied by 10).
     *
     * Why multiply by 10? Because we are talking about decimal numbers by default. This is the same as when we operate binary numbers. Shifting one bit to the left is to multiply the binary number by 2, and shifting one bit to the right is to divide by 2.
     *
     * he above scenario is to continuously add the lowest digit to the number, so if I want to delete the highest digit of the number, how should I do it ? For example, if I want to change 8264 into 264, how should I calculate it? In fact, it is also very simple. Subtract 8000 from 8264 to get 264.
     *
     * How did this 8000 come about? It is calculated by 8 x 10^3. 8 is the highest digit, 10 is because we are a decimal number here, and 3 is because 8264 has three digits left after removing the highest digit.
     *
     * The above content mainly discusses how to add a number to the lowest digit of a number and how to delete the highest digit of Ra number. Use to represent the base number of a number, and use to Lrepresent the number of digits of a number. The following formula can be summarized:
     * */
    void addLast() {
        int n = 8264;
        int R = 10; //decimal
        int appendVal = 3; // the number to add at the last position
        int res = n * R + appendVal;
    }

    void removeFirst() {
        int n = 8264;
        int R = 10;
        int removeVal = 8;
        int L = 4; //the position of the removed number
        int res = n - removeVal * R ^ (L - 1);
    }

    /**
     * If you can understand these two formulas, then there is no difficulty in the Rabin-Karp algorithm. The algorithm is like this. No matter how advanced the skills are, they are all built on the simplest and most basic principles. But before talking about the Rabin-Karp algorithm, let's look at a simple buckle problem.
     *
     * Take a look at question 187 of Likou " repetitive DNA sequence", I briefly describe the topic:
     *
     * The DNA sequence is A, G, C, T composed. and now you input a A, G, C, T string S containing only four characters to represent a DNA sequence, please find all repeated substrings of length 10 in S.
     *
     * You noticed that our matching process actually maintains L = 10a fixed-length window of length that slides from left to right. Can we learn from the previous article?Sliding window algorithm frameworkIn the method, only maintain the left, rightpointer to delineate the substring interval?
     * */

    /**
     * int L = 10;
     * HashSet<String> seen;
     *
     * // 滑动窗口代码框架
     * CharWindow window;
     * int left = 0, right = 0;
     * while (right < s.size()) {
     *     // 扩大窗口，移入字符
     *     window.addRight(s[right]);
     *     right++;
     *
     *     // 当子串的长度达到要求
     *     if (right - left == L) {
     *         // 把窗口中的字符变成字符串，还是需要 O(L) 的时间
     *         String windowStr = window.toString();
     *         if (seen.contains(windowStr)) {
     *             print("找到一个重复子串: ", windowStr);
     *         } else {
     *             seen.add(windowHash);
     *         }
     *
     *         // 缩小窗口，移出字符
     *         window.removeLeft();
     *         left++;
     *     }
     * }
     *
     * This algorithm is definitely no problem, but the time complexity is slightly higher. Assuming that the length of S is N, and the length of the target substring is L(this question L = 10), the for loop traverses the O(N)characters of and intercepts L a , so the time complexity of this algorithm is O(NL).
     *
     * Traversing the whole is sdefinitely inevitable. The question is, can we not call every time substringto intercept the substring ?
     * You noticed that our matching process actually maintains L = 10a fixed-length window of length that slides from left to right. Can we learn from the previous article?Sliding window algorithm frameworkIn the method, only maintain the left, rightpointer to delineate the substring interval?
     *
     * So the key to optimization is, can we not actually generate the substring, but use some other form of unique identifier to represent the substring in the sliding window, and can it be updated quickly during the window sliding ?
     *
     * If you equate AGCTfour characters to 0123four numbers, then a base sequence L = 10of can actually be equivalent to a ten-digit number, which can uniquely identify a substring. Moreover, the process of moving the window is actually the process of adding numbers to the lowest digit of the number and deleting the highest digit . Looking back at the previous explanation, the operations of adding and deleting numbers are two formulas, which can O(1)be completed in .
     *
     * Then, instead of directly storing substrings in the hash set, we store the ten digits corresponding to the substrings. Because a tens digit can uniquely identify a substring, it can also play a role in identifying repetitions.
     *
     * In this way, we avoid directly generating substrings and storing them in the collection, but generate a ten-digit number to represent the substring, and the time spent on generating this ten-digit number is O(1), thus reducing the time complexity of the matching algorithm.
     *
     * In fact, if you think about it, you converted a string object into a number, what is this? This is a hash algorithm you designed, and the generated number can be considered as the hash value of the string. Quickly calculate the hash value of the elements in the window in the sliding window, which is called the sliding hash technique .
     * */

    /**
     * Further, we use a 10-digit number to identify a base character sequence with a length of 10, which requires long type storage, and an int cannot store 10-digit numbers. But you notice that all the numbers in this 10-digit number will only be limited 0,1,2,3, isn't it a bit wasteful?
     *
     * In other words, what we need to store is actually only a tens digit in quaternary (contains 4^10 numbers in total), but we use decimal tens digits (which can contain 10^10 numbers) to save, obviously It is a bit wasteful.
     *
     * Because 4^10 = 1048576 < 10^8, as long as we operate under the operation rules of quaternary, the eight digits of decimal can be stored. In this case, the int type is enough, and the long type is not needed.
     *
     * Specifically, just change the base Rof :
     *
     * The time complexity of the sliding window algorithm itself is O(N), look at the time-consuming operation during the window sliding process, the process of resadding substrings uses the complexity required bysubstring the method , but in general the method will not be called many times, only extreme In some cases (for example, the strings are all the same characters), the method .O(L)substringsubstring
     *
     * So we can say that the average time complexity of this algorithm in general is , and the time complexity in O(N)extreme cases will degenerate into O(NL).
     * */

    List<String> find(String s) {
        //transfer the s to num
        int[] nums = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case 'A':
                    nums[i] = 0;
                    break;
                case 'B':
                    nums[i] = 1;
                    break;
                case 'C':
                    nums[i] = 2;
                    break;
                case 'T':
                    nums[i] = 3;
                    break;
            }
        }
        HashSet<Integer> seen = new HashSet<>();
        HashSet<String> res = new HashSet<>();

        int L = 10;
        int R = 4;
        int RL = (int) Math.pow(R, L - 1);
        int windowHash = 0;
        int left = 0, right = 0;
        while (right < s.length()) {
            windowHash = R * windowHash + nums[right];
            right++;

            if (right - left == L) {
                if (seen.contains(windowHash)) {
                    res.add(s.substring(left, right));
                } else {
                    seen.add(windowHash);
                }
                windowHash = windowHash - nums[left] * RL;
                left++;
            }
        }
        return new LinkedList<>(res);
    }

    /**
     * Then learn from the above ideas, instead of comparing substrings and pattern strings character by character every time, we maintain a sliding window, use the sliding hash algorithm to calculate the hash value of the string in the window while sliding, and take This hash value is compared with the hash value of the pattern string, so that the interception of substrings can be avoided, thereby reducing the matching algorithm to O(N), which is the core logic of the Rabin-Karp fingerprint string search algorithm .
     *
     * Then you may ask, the topic we dealt with just now only input AGCTfour , so it can be converted into numbers, but in the face of all kinds of strings, how to convert them into numbers to calculate the hash value? In fact, it is very simple. Characters are essentially codes, and codes are actually numbers.
     * For example, take the ASCII code as an example. The ASCII code is actually 256 numbers from 0 to 255, corresponding to all English characters and English symbols. Then Lan can be equivalently understood as a 256-digit number, which can uniquely identify the string and can also be used as a hash value.
     *
     * // 文本串
     * String txt;
     * // 模式串
     * String pat;
     *
     *
     * // 需要寻找的子串长度为模式串 pat 的长度
     * int L = pat.length();
     * // 仅处理 ASCII 码字符串，可以理解为 256 进制的数字
     * int R = 256;
     * // 存储 R^(L - 1) 的结果
     * int RL = (int) Math.pow(R, L - 1);
     * // 维护滑动窗口中字符串的哈希值
     * int windowHash = 0;
     * // 计算模式串的哈希值
     * long patHash = 0;
     * for (int i = 0; i < pat.length(); i++) {
     *     patHash = R * patHash + pat.charAt(i);
     * }
     *
     * // 滑动窗口代码框架
     * int left = 0, right = 0;
     * while (right < txt.length()) {
     *     // 扩大窗口，移入字符（在最低位添加数字）
     *     windowHash = R * windowHash + txt[right];
     *     right++;
     *
     *     // 当子串的长度达到要求
     *     if (right - left == L) {
     *         // 根据哈希值判断窗口中的子串是否匹配模式串 pat
     *         if (patHash == windowHash) {
     *             // 找到模式串
     *             print("找到模式串，起始索引为", left);
     *             return left;
     *         }
     *
     *         // 缩小窗口，移出字符（删除最高位数字）
     *         windowHash = windowHash - txt[left] * RL;
     *         left++;
     *     }
     * }
     * // 没有找到模式串
     * return -1;
     * */

    /**
     * However, when this code actually runs, there will be a serious problem, that is, integer overflow. But now the input is an ASCII code string, we have to abstract the string into 256 numbers, that is, in the algorithm R = 256. Under the same number of digits, the number of digits contained in the 256 base is obviously much larger than the number of digits contained in the decimal system. L = 10For example , 256^10 = 1.2 x 10^24 < 10 ^25, so you need a 25-digit decimal number to represent a 10-digit 256-digit number.
     *
     * So what's the solution? How to map a large number to a smaller range? The answer is modulo (remainder) .
     *
     * No matter how big a number is, if you divide it by Q, the remainder will definitely fall within [0, Q-1]the range of . So we can set one , and keep and between theQ way of modulus , which can effectively avoid integer overflow.windowHashpatHash[0, Q-1]
     * */

    /**
     * Well, the problem of integer overflow is solved, but a new problem arises: the hash value after the modulus cannot correspond to the original string one-to-one, and there may be a one-to-many situation, that is, a hash conflict .
     * Let's say 10 % 7 equals 3, and 17 % 7 equals 3, so if you get a remainder of 3, can you be sure that the original number must be 10? cannot.
     *
     * Similarly, if you find windowHash == patHashthat you are not completely sure that the string in the window must patmatch , it is possible that they do not match, but the hash value calculated by the modulus happens to be the same, which results in " hash collision".
     *
     * For the Rabin-Karp algorithm, windowHash == patHashwhen , use the brute force matching algorithm to check whether the string in the window is the patsame as to avoid hash conflicts. Because the probability of Greek conflicts is relatively small, using the brute force matching algorithm occasionally will not affect the overall time complexity.
     * */

    int rabinKarp(String txt, String pat) {
        int L = pat.length();
        int R = 256;
        long Q = 1658770037;//mode a prime number
        long RL = 1;
        for (int i = 1; i <= L; i++) {
            RL = (RL * R) % Q; //in case of overflow
        }

        long patHash = 0;
        for (int i = 0; i < pat.length(); i++) {
            patHash = (patHash * R + pat.charAt(i)) % Q;
        }

        long windowHash = 0;
        int left = 0, right = 0;

        while (right < txt.length()) {
            windowHash = (windowHash * R + txt.charAt(right)) % Q;
            right++;

            if (right - left == L) {
                if (windowHash == patHash) {
                    if (pat.equals(txt.substring(left, right))) {
                        //collision
                        return left;
                    }
                }
                windowHash = ((windowHash - (txt.charAt(left) * RL) % Q) + Q) % Q;
                // X % Q == (X + Q) % Q 是一个模运算法则
                // because windowHash - (txt[left] * RL) % Q may be negative
                // so add a Q
                left++;
            }
        }
        return -1;
    }

    /**
     * X % Q == (X + Q) % Q
     * (X + Y) % Q == (X % Q + Y % Q) % Q
     * If you think about it for a while, you can understand these two algorithms. Whenever multiplication and addition are involved in the code, large results may be generated, so you can use the above rules to modulo the results whenever you have a chance to avoid overflow. .
     *
     * Why should this Qbe as large as possible? Mainly to reduce the probability of hash collisions .
     * Because you use this Qas , the remainder (hash value) must fall [0, Q-1]between , so Qthe larger the , the larger the space for the hash value, the less likely hash conflicts will occur, and the efficiency of the entire algorithm will be higher Some.
     *
     * Why is this Qa prime number? It is still to reduce the probability of hash collision .
     *
     * To give an extreme example, if you make Q = 100, Xthen , X % Qthe result must be Xthe last two digits of . In other words, you didn’t use the Xprevious bits at all. I
     * */

}
