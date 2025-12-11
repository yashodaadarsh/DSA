package TwoPointerAndSlidingWindow.LC_1493_Longest_Subarray_of_1s_After_Deleting_One_Element;

class Solution {

    // ------------------------------
    // 1. Brute Force Approach O(N^2)
    // ------------------------------
    public int longestSubarrayBruteForce(int[] nums) {
        int n = nums.length;
        int countZero = 0;
        for (int num : nums) {
            if (num == 0) countZero++;
        }

        // All 1s → must delete one
        if (countZero == 0) return n - 1;

        int result = 0;
        for (int i = 0; i < n; i++) {
            result = Math.max(result, findMaxLenAfterSkipping(nums, i));
        }
        return result;
    }

    private int findMaxLenAfterSkipping(int[] nums, int skipIndex) {
        int maxLen = 0, curLen = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == skipIndex) continue;
            if (nums[i] == 1) {
                curLen++;
                maxLen = Math.max(maxLen, curLen);
            } else {
                curLen = 0;
            }
        }
        return maxLen;
    }

    // --------------------------------
    // 2. Standard Sliding Window O(N)
    // --------------------------------
    public int longestSubarraySlidingWindow(int[] nums) {
        int n = nums.length;
        int i = 0, zeroCount = 0, result = 0;

        for (int j = 0; j < n; j++) {
            if (nums[j] == 0) zeroCount++;

            while (zeroCount > 1) {
                if (nums[i] == 0) zeroCount--;
                i++;
            }
            result = Math.max(result, j - i);
        }
        return result;
    }

    // --------------------------------
    // 3. Cleaner Sliding Window O(N)
    // --------------------------------
    public int longestSubarrayCleanerSliding(int[] nums) {
        int n = nums.length;
        int i = 0, lastZeroIndex = -1, result = 0;

        for (int j = 0; j < n; j++) {
            if (nums[j] == 0) {
                i = lastZeroIndex + 1;
                lastZeroIndex = j;
            }
            result = Math.max(result, j - i);
        }
        return result;
    }

    // --------------------------------
    // 4. Two-Segment Tracking O(N)
    // --------------------------------
    public int longestSubarrayTwoSegments(int[] nums) {
        int n = nums.length;
        int l = 0, r = 0;
        int size = 0, prev = 0;

        for (r = 0; r < n; r++) {
            if (nums[r] == 0) {
                size = Math.max(size, prev + r - l);
                prev = r - l;
                l = r + 1;
            }
        }

        if (r - l == n) return n - 1; // all 1s case
        return Math.max(size, prev + r - l);
    }

}
