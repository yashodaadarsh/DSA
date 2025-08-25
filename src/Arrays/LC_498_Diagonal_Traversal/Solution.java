package Arrays.LC_498_Diagonal_Traversal;

import java.util.*;

class Solution {

    // Using Lists
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] res = generate(m, n, mat);
        return res;
    }

    private int[] generate(int m, int n, int[][] mat) {
        int[] res = new int[m * n];
        List<List<Integer>> resList = new ArrayList<>();
        //System.out.println("Entered");
        for (int i = 0; i < m; i++) {
            List<Integer> list = new ArrayList<>();
            for (int k = 0; k < n; k++) {
                int r = i - k;
                int c = k;
                if (r >= 0 && r < m && c >= 0 && c < n) {
                    list.add(mat[r][c]);
                }
            }
            resList.add(list);
        }
        for (int i = 1; i < n; i++) {
            List<Integer> list = new ArrayList<>();
            for (int k = 0; k < m; k++) {
                int r = m - 1 - k;
                int c = i + k;
                if (r >= 0 && r < m && c >= 0 && c < n) {
                    list.add(mat[r][c]);
                }
            }
            resList.add(list);
        }
        int k = 0;
        for( int i = 0 ; i < resList.size() ; i++ ){
            List<Integer> list = resList.get(i);
            if( (i&1) == 1 ){
                Collections.reverse( list );
            }
            for( int num : list ){
                res[k++] = num;
            }
        }
        return res;
    }

    //Using Maps
    public int[] findDiagonalOrder2(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        Map<Integer, List<Integer>> mp = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        // fill the map using [i+j]
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mp.computeIfAbsent(i + j, k -> new ArrayList<>()).add(mat[i][j]);
            }
        }
        //[i+j] for upward arrows
        //[i-j] for downward arrows

        boolean flip = true;
        for (int key = 0; key <= m + n - 2; key++) {
            List<Integer> diagonal = mp.get(key);

            if (flip) {
                Collections.reverse(diagonal); // reverse like in C++
            }

            result.addAll(diagonal);
            flip = !flip;
        }

        // convert List<Integer> to int[]
        return result.stream().mapToInt(Integer::intValue).toArray();
    }



}
