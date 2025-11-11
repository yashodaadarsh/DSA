package Contests.LeetCode.BiweeklyContest169;

class CountSubarraysWithMajorityElementII {

    int[] bit;
    int size;

    void update( int idx ){
        while( idx < size ){
            bit[idx]++;
            idx += idx & (-idx);
        }
    }

    int query( int idx ){
        int sum = 0;
        while( idx > 0 ){
            sum += bit[idx];
            idx -= idx & (-idx);
        }
        return sum;
    }
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int[] arr = nums;

        this.size = 2*n + 2;
        this.bit = new int[size];

        int offset = n+1;

        long ans = 0;
        int curBal = 0;

        update( curBal + offset );

        for( int j = 0; j < n ;j++ ){
            if( arr[j] == target ) curBal++;
            else curBal--;

            int nQI = ( curBal - 1 ) + offset;
            ans += query( nQI );

            int nUI = curBal + offset;
            update(nUI);
        }

        return ans;
    }
}