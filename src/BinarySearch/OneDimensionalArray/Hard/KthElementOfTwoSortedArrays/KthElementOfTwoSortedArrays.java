package BinarySearch.OneDimensionalArray.Hard.KthElementOfTwoSortedArrays;

class KthElementOfTwoSortedArrays {
    private int k;
    public int kthElement(int a[], int b[], int k) {
        this.k = k;
        // code here
        return solve( a , a.length , b , b.length );
        
    }
    private int solve(int[] nums1, int n, int[] nums2, int m) {
        if (n > m) {
            return solve(nums2, m, nums1, n);
        }
        int low = Math.max(0,k-m), high = Math.min(n,k);
        int left = k;
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
                return Math.max(l1, l2);
            }
            if (l1 > r2)
                high = mid1 - 1;
            if (l2 > r1)
                low = mid1 + 1;
        }
        return 0;
    }
}