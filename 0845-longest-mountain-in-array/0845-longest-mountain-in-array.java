class Solution {
    public int longestMountain(int[] arr) {
        int n = arr.length;
        if (n < 3) return 0;

        int[] up = new int[n];
        int[] down = new int[n];

        for (int i = 1; i < n; i++) {
            if (arr[i] > arr[i - 1]) {
                up[i] = up[i - 1] + 1;
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) {
                down[i] = down[i + 1] + 1;
            }
        }

        // System.out.println( Arrays.toString(up) );
        // System.out.println( Arrays.toString(down) );
        int max = 0;

        for (int i = 0; i < n; i++) {
            if (up[i] > 0 && down[i] > 0) {  
                max = Math.max(max, up[i] + down[i] + 1);
            }
        }

        return max;
    }
}