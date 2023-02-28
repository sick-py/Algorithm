public class Main {
    /**
     * Basic bitwise operation
     * */
    void example() {
        //AND
        //Bitwise AND (&): The bitwise AND operator compares each bit of two integer operands and sets the corresponding bit in the result to 1 if and only if both bits are 1.
        int a = 6; // 0110 in binary
        int b = 3; // 0011 in binary
        int resultAND = a & b; // result is 0010 in binary

        //OR
        //The bitwise OR operator compares each bit of two integer operands and sets the corresponding bit in the result to 1 if either or both bits are 1.
        int c = 6; // 0110 in binary
        int d = 3; // 0011 in binary
        int resultOR = c | d; // result is 0111 in binary


        //XOR
        //he bitwise XOR operator compares each bit of two integer operands and sets the corresponding bit in the result to 1 if and only if exactly one of the bits is 1.
        int e = 6; // 0110 in binary
        int f = 3; // 0011 in binary
        int resultXOR = a ^ b; // result is 0101 in binary

        //complement ~
        //The bitwise complement operator inverts all bits of a single integer operand.
        int g = 6; // 0110 in binary
        int resultComplement = ~g; // result is 1001 in binary (assuming 32-bit integer)

        //Left shift << like * 2
        // The left shift operator shifts the bits of an integer operand to the left by a specified number of positions. The bits that are shifted out of the left side are discarded, and the bits that are shifted in on the right side are set to 0.
        int h = 6; // 0110 in binary
        int resultLeft = h << 2; // result is 11000 in binary
        System.out.println(resultLeft);

        //right shift >> like / 2
        //The right shift operator shifts the bits of an integer operand to the right by a specified number of positions. The bits that are shifted out of the right side are discarded, and the bits that are shifted in on the left side depend on the sign of the original value.
        int i = 6; // 0110 in binary (assuming 32-bit integer)
        int result = i >> 2; // result is 0001 in binary

    }

    public static void main(String[] args) {
        int h = 6;
        int resultLeft = h >> 2;
        System.out.println(resultLeft);
        System.out.println("Hello world!");
    }

    /**Several interesting bit operations
     * Convert English characters to lowercase using OR |and spaces
     * ('a' | ' ') = 'a'
     * ('A' | ' ') = 'a'
     * Use AND &and underscore to convert English characters to uppercase
     * ('b' & '_') = 'B'
     * ('B' & '_') = 'B'
     * Use XOR operation ^and space to exchange upper and lower case of English characters
     * ('d' ^ ' ') = 'D'
     * ('D' ^ ' ') = 'd'
     * The reason the above works so well is because of the ASCII encoding.
     *
     * Determine if two numbers have different signs
     * int x = -1, y = 2;
     * boolean f = ((x ^ y) < 0); // true
     * int x = 3, y = 2;
     * boolean f = ((x ^ y) < 0); // false
     * If the first 6 techniques are not very useful, the seventh technique is quite practical, using the sign bit of the complement code . The highest bit of an integer code is the sign bit, the sign bit of a negative number is 1, and the sign bit of a non-negative number is 0, and with the help of the XOR feature, it can be judged whether two numbers have different signs.
     *
     * */

    /** 191 number of bit 1
     * It is to let you nreturn how many 1s there are in the binary representation. Because n & (n - 1)can eliminate the last 1, so you can use a loop to eliminate 1 and count at the same time until nbecomes 0.
     * */
    int numberOfBit(int n) {
        int res = 0;
        while (n != 0) {
            n = n & (n - 1);
            res++;
        }
        return res;
    }

    /** 231
     * Determine whether a number is an exponent of 2
     * If a number is an exponent of 2, its binary representation must contain only one 1:
     * 2^0 = 1 = 0b0001
     * 2^1 = 2 = 0b0010
     * 2^2 = 4 = 0b0100
     * */
    boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        return (n & (n - 1)) == 0;
    }

    /** the use of a ^ a = 0, a ^ 0 = a
     * 136 number that occurs only once
     *
     * For this question, we only need to XOR all the numbers, and the paired numbers will become 0, and the XOR of the single number and 0 is still itself, so the final XOR result is an element that only appears once
     * */
    int single(int[] nums) {
        int res = 0;
        for (int n : nums) {
            res ^= n;
        }
        return res;
    }

    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        res[0] = 0;
        for (int i = 1; i <= n; i++) {
            res[i] = numberOfBit(i);
        }
        return res;
    }

    /** Reverse bits
     *
     * */
    public int reverseBits(int n) {
        int res = 0; //0000 0000 0000 ...
        for (int i = 0; i < 32; i++) {
            //least significant bit
            int lsb = n & 1;
            //next loop we always get the lsb
            n = n >> 1;

            //put the lsb to the 31 spot of res
           res |= lsb << (31 - i);
            //use | because res is all 0 at the begining

        }

        return res;
    }

}