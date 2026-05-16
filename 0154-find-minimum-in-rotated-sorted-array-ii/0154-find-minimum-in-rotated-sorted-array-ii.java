
class Solution {
    public int findMin(int[] nums) {
        int n = nums.length;
        int st = 0, end = n-1;
        int ans = 6000;
        while( st <= end ){
            while( st+1 < n && nums[st] == nums[st+1] ) st++;
            while( end-1 >= 0 && nums[end] == nums[end-1] ) end--;
            int mid = ( end-st )/2 + st;
            if( nums[st] <= nums[mid] ){
                ans = Math.min( nums[st], ans );
                st = mid+1;
            }
            else{
                end = mid;
            }
        }
        return ans;
    }
}