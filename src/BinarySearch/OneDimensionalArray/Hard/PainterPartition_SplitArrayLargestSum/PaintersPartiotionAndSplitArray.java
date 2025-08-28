package BinarySearch.OneDimensionalArray.Hard.PainterPartition_SplitArrayLargestSum;

import java.util.Arrays;

class PaintersPartiotionAndSplitArray {

    // Painter's Partition Problem
    public int paintersPartition(int[] arr, int painters) {
        int n = arr.length;
        if (painters > n) return -1; // not enough sections

        int low = Arrays.stream(arr).max().getAsInt(); // largest single wall section
        int high = Arrays.stream(arr).sum();           // all painted by one painter

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int requiredPainters = countPainters(arr, mid);

            if (requiredPainters <= painters) {
                high = mid - 1; // try smaller maximum time
            } else {
                low = mid + 1;  // need more painters → increase max time
            }
        }
        return low;
    }

    // Split Array Largest Sum
    public int splitArray(int[] nums, int k) {
        int n = nums.length;
        if (k > n) return -1; // cannot split into more parts than elements

        int low = Arrays.stream(nums).max().getAsInt(); // largest element
        int high = Arrays.stream(nums).sum();           // total sum

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int parts = countPainters(nums, mid); // same helper logic

            if (parts <= k) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    //  Helper function used by both
    private int countPainters(int[] arr, int maxAllowed) {
        int painters = 1;
        long currSum = 0;

        for (int val : arr) {
            if (currSum + val <= maxAllowed) {
                currSum += val;
            } else {
                painters++;
                currSum = val;
            }
        }
        return painters;
    }

    // ✅ Testing both
    public static void main(String[] args) {
        PaintersPartiotionAndSplitArray sol = new PaintersPartiotionAndSplitArray();

        int[] wall = {10, 20, 30, 40};
        int painters = 2;
        System.out.println("Painter's Partition: " + sol.paintersPartition(wall, painters));
        // Expected: 60 (Painter1: 10+20+30=60, Painter2: 40)

        int[] nums = {7, 2, 5, 10, 8};
        int k = 2;
        System.out.println("Split Array Largest Sum: " + sol.splitArray(nums, k));
        // Expected: 18 (Split into [7,2,5] and [10,8])
    }
}
