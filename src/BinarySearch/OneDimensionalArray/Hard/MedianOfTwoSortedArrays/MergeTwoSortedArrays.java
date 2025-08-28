package BinarySearch.OneDimensionalArray.Hard.MedianOfTwoSortedArrays;

class MergeTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        // merged array
        int[] merged = new int[n + m];
        int i = 0, j = 0, k = 0;

        // merge the two arrays
        while (i < n && j < m) {
            if (nums1[i] < nums2[j]) {
                merged[k++] = nums1[i++];
            } else {
                merged[k++] = nums2[j++];
            }
        }

        // add remaining elements of nums1
        while (i < n) {
            merged[k++] = nums1[i++];
        }

        // add remaining elements of nums2
        while (j < m) {
            merged[k++] = nums2[j++];
        }

        // find the median
        int total = n + m;
        if (total % 2 == 1) {
            return merged[total / 2]; // odd case
        } else {
            return (merged[total / 2] + merged[(total / 2) - 1]) / 2.0; // even case
        }
    }


    // Binary Search
    private int n, m;

    public double findMedianSortedArraysBinarySearch(int[] nums1, int[] nums2) {
        n = nums1.length;
        m = nums2.length;
        if (n == 0)
            return findMedian(nums2, m);
        if (m == 0)
            return findMedian(nums1, n);
        return solve(nums1, n, nums2, m);

    }

    private double solve(int[] nums1, int n, int[] nums2, int m) {
        if (n > m) {
            return solve(nums2, m, nums1, n);
        }
        int low = 0, high = n;
        int left = (n + m + 1) / 2;
        while (low <= high) {
            int mid1 = (high - low) / 2 + low; // for nums1
            int mid2 = left - mid1; // for nums2
            int l1 = Integer.MIN_VALUE;
            int l2 = Integer.MIN_VALUE;
            int r1 = Integer.MAX_VALUE;
            int r2 = Integer.MAX_VALUE;
            if (mid1 - 1 >= 0)
                l1 = nums1[mid1 - 1];
            if (mid2 - 1 >= 0)
                l2 = nums2[mid2 - 1];
            if (mid1 < n)
                r1 = nums1[mid1];
            if (mid2 < m)
                r2 = nums2[mid2];
            if (l1 <= r2 && l2 <= r1) {
                int N = n + m;
                if (N % 2 == 1f)
                    return Math.max(l1, l2);
                return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
            }
            if (l1 > r2)
                high = mid1 - 1;
            if (l2 > r1)
                low = mid1 + 1;
        }
        return 0;
    }

    private double findMedian(int[] arr, int n) {
        if ((n & 1) == 1) {
            return arr[n / 2];
        }
        return (arr[n / 2] + arr[n / 2 - 1]) / 2.0;
    }

}
