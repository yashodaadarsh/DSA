package POTD.LeetCode.LC_342_Power_Of_Four;

class Solution {

    // Approach 1: Iterative Division
    public boolean isPowerOfFour_Iterative(int n) {
        if (n <= 0) return false;
        while (n % 4 == 0) {
            n /= 4;
        }
        return n == 1;
    }

    // Approach 2: Mathematical (Logarithm)
    public boolean isPowerOfFour_Logarithm(int n) {
        if (n <= 0) return false;
        double x = Math.log(n) / Math.log(4);
        return x == (int) x; // check if x is an integer
    }

    // Approach 3: Bit Manipulation + Modulo Arithmetic
    public boolean isPowerOfFour_BitManipulation(int n) {
        if (n <= 0) return false;
        if ((n & (n - 1)) != 0) return false; // not a power of two
        return (n - 1) % 3 == 0;
    }

    // Approach 4: Bit Manipulation + bitCount Parity Check
    public boolean isPowerOfFour_BitCount(int n) {
        if (n <= 0) return false;
        return (n & (n - 1)) == 0 && Integer.bitCount(n - 1) % 2 == 0;
    }
}