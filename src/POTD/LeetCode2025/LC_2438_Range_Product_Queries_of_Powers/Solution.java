package POTD.LeetCode2025.LC_2438_Range_Product_Queries_of_Powers;

import java.util.ArrayList;
import java.util.List;

class Solution {
    private final int MOD = 1000000000 + 7;

    public int[] productQueries(int n, int[][] queries) {
        int ql = queries.length;
        int[] res = new int[ql];
        List<Integer> powers = new ArrayList<>();

        // Step 1: Build powers array
        for (int i = 0; i < 31; i++) {
            if (((1 << i) & n) != 0) {
                powers.add(1 << i);
            }
        }

        // Step 2: Answer queries
        int k = 0;
        for (int[] query : queries) {
            int st = query[0];
            int end = query[1];
            long prod = 1;

            for (int i = st; i <= end; i++) {
                prod = (prod * powers.get(i)) % MOD;
            }
            res[k++] = (int) prod;
        }
        return res;
    }
}
