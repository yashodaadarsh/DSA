package Strings.LC_869_Reordered_Power_of_2;

import java.util.*;

class Solution {

    // Approach 1: Sort and Compare
    public String getSort(int n) {
        String str = String.valueOf(n);
        char[] arr = str.toCharArray();
        Arrays.sort(arr); // sorts in ascending order
        return new String(arr); // return sorted digits as a string
    }

    public boolean reorderedPowerOf2_Approach1(int n) {
        String s = getSort(n);
        for (int p = 0; p <= 29; p++) {
            if (s.equals(getSort(1 << p))) return true;
        }
        return false;
    }

    // Approach 2: Precompute and Store Sorted Powers of Two
    public static String getSortStatic(int n) {
        String str = String.valueOf(n);
        char[] arr = str.toCharArray();
        Arrays.sort(arr); // sorts in ascending order
        return new String(arr); // return sorted digits as a string
    }

    static Set<String> st = new HashSet<>();
    static {
        for (int p = 0; p <= 29; p++) {
            st.add(getSortStatic(1 << p));
        }
    }

    public boolean reorderedPowerOf2_Approach2(int n) {
        String s = Solution.getSortStatic(n);
        return Solution.st.contains(s);
    }

    // Approach 3: Digit Frequency Counting (Vector)
    private int[] getDigitCountVector(int n) {
        int[] count = new int[10];
        while (n > 0) {
            count[n % 10]++;
            n /= 10;
        }
        return count;
    }

    public boolean reorderedPowerOf2_Approach3(int n) {
        int[] nCount = getDigitCountVector(n);
        for (int p = 0; p <= 29; p++) {
            if (Arrays.equals(nCount, getDigitCountVector(1 << p))) {
                return true;
            }
        }
        return false;
    }

    // Approach 4: Digit Frequency Counting (Integer Encoding)
    private int pow(int a, int b) {
        if (b == 0) return 1;
        int half = pow(a, b / 2);
        int result = half * half;
        if ((b & 1) == 1) result *= a;
        return result;
    }

    private int getNumberFormat(int n) {
        int num = 0;
        while (n != 0) {
            num += pow(10, n % 10);
            n = n / 10;
        }
        return num;
    }

    public boolean reorderedPowerOf2_Approach4(int n) {
        int input = getNumberFormat(n);
        for (int p = 0; p <= 29; p++) {
            if (input == getNumberFormat(1 << p)) return true;
        }
        return false;
    }

}
