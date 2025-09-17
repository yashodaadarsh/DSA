package POTD.LeetCode.LC_2197_ReplaceNonCoprimeNumbersInArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class ReplaceNonCoprimeNumbersInArray {


    private int findGCD(int a, int b) {
        if (a % b == 0)
            return b;
        return findGCD(b, a % b);
    }

    public List<Integer> replaceNonCoprimes(int[] nums) {
        Stack<Integer> st = new Stack<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int curr = nums[i];
            while (!st.isEmpty()) {
                int last = st.peek();
                int gcd = findGCD(last, curr);
                if (gcd == 1)
                    break;
                st.pop();
                curr = (int) ((1l * last * curr) / gcd);
            }
            st.push(curr);
        }
        return new ArrayList<>(st);
    }

    //Failed - Because we are just checking the immediate last element to be co-prime or not . But we should also check with the previous elements

    /* EX :- TC : [287,41,49,287,899,23,23,20677,5,825] 
            Output : [2009,899,20677,825]
          Expected : [2009,20677,825]
    
          mistake :- gcd(20677,5) = 1. So we added it directly to answer. But 20677 is not checked with 899 . 20677,899 are not co-primes.
    */

    class Solution {
        private long findGCDII(long a, long b) {
            if (a % b == 0)
                return b;
            return findGCDII(b, a % b);
        }

        public List<Integer> replaceNonCoprimesII(int[] nums) {
            List<Integer> ans = new ArrayList<>();
            int n = nums.length;
            long last = nums[0];
            for (int i = 1; i < n; i++) {
                int curr = nums[i];
                long a = Math.max(last, curr);
                long b = Math.min(last, curr);
                long gcd = findGCDII(a, b);
                if (gcd == 1) {
                    ans.add((int) last);
                    last = curr;
                } else {
                    long lcm = (1l * curr * last) / gcd;
                    last = lcm;
                }
            }
            ans.add((int) last);
            return ans;
        }
    }

}