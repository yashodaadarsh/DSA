package POTD.LeetCode2025.LC_326_Power_Of_Three;

class Solution {

    // Approach 1: Iterative Division
    public boolean isPowerOfThreeIterative(int n) {
        if (n <= 0) return false;
        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }

    // Approach 2: Recursive Division
    public boolean isPowerOfThreeRecursive(int n) {
        if (n <= 0) return false;
        if (n == 1) return true; // 3^0 = 1
        if (n % 3 != 0) return false;
        return isPowerOfThreeRecursive(n / 3);
    }

    // Approach 3: Logarithm Method
    public boolean isPowerOfThreeLogarithm(int n) {
        if (n <= 0) return false;
        double x = Math.log10(n) / Math.log10(3);
        return x == (int) x ; // check if x is nearly integer
    }

    // Approach 4: Precomputed Maximum Power of Three
    public boolean isPowerOfThreeMaxConst(int n) {
        int MAX_POWER_OF_THREE = 1162261467; // 3^19
        return n > 0 && MAX_POWER_OF_THREE % n == 0;
    }
}
