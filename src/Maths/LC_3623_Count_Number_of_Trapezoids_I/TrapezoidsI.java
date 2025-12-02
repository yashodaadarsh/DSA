package Maths.LC_3623_Count_Number_of_Trapezoids_I;
import java.util.*;
class TrapezoidsI {
    public int countTrapezoids(int[][] points) {
        final int MOD = 1_000_000_007;

        // Count number of points for each unique y-coordinate
        Map<Integer, Integer> yCount = new HashMap<>();
        for (int[] point : points) {
            int y = point[1];
            yCount.put(y, yCount.getOrDefault(y, 0) + 1);
        }

        // Extract unique y-values
        int[] yVals = new int[yCount.size()];
        int idx = 0;
        for (int y : yCount.keySet()) {
            yVals[idx++] = y;
        }
        Arrays.sort( yVals );


        long total = 0;

        // Check each pair of y-values
        int prev = 0;
        for (int i = 0; i < yVals.length; i++) {
            int cnt1 = yCount.get(yVals[i]);

            long comb1 = nCr2(cnt1);  // Efficiently compute nC2

            long comb2 = prev;

            total = (total + (comb1 * comb2) % MOD) % MOD;
            prev += comb1;
        }

        return (int) total;
    }

    // Efficient nCr(n, 2) = n * (n - 1) / 2
    private long nCr2(int n) {
        if( n < 2 ) return 0;
        return (long) n * (n - 1) / 2;
    }
}
