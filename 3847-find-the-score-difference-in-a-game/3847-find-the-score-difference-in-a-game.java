class Solution {
    public int scoreDifference(int[] nums) {
        int n = nums.length;
        int[] pla = new int[2];
        int cur = 0;
        int p1 = 0 , p2 = 0;
        for( int i = 0; i < n; i++ ){
            if( nums[i]%2 == 1 || (i+1)%6 == 0 ){
                cur =  cur^1 ;
                if( nums[i]%2 == 1 && (i+1)%6 == 0 ) cur = cur ^ 1;
            }
            // System.out.println( cur + "  " + i );
            pla[cur] += nums[i];
        }

        return pla[0]-pla[1];
    }
}