class Solution {
    public int minimumDifference(int[] nums) {

        List<List<Integer>> left = new ArrayList<>();
        List<List<Integer>> right = new ArrayList<>();
        int N = nums.length;
        int n = N/2;

        int total = Arrays.stream( nums ).sum();
        int target = total;

        for( int i = 0; i <= n; i++ ){
            left.add( new ArrayList<>() );
            right.add( new ArrayList<>() );
        }

        for( int i = 0 ; i < (1<<n) ; i++ ){
            int sz = 0; int leftSum = 0, rightSum = 0;
            for( int j = 0 ; j < n ; j++ ){
                int mask = (1<<(j));
                if( ( i&mask ) != 0 ){
                    sz++;
                    leftSum += nums[j];
                    rightSum += nums[n+j];
                } 
            }
            left.get(sz).add( leftSum );
            right.get(sz).add(rightSum);
        }


        for( int i = 0 ; i <= n ; i++ ){
            Collections.sort( right.get(i) );
        }
        // System.out.println( left );
        // System.out.println( right );

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i <= n; i++) {
            for (int lt : left.get(i)) {
                List<Integer> rList = right.get(n-i);
                int need = target/2 - lt;
                int idx = lowerBound( rList, need );
                if( idx == -1 ) continue;
                ans = Math.min( ans, Math.abs( target-2*(lt+rList.get(idx))));
                if( idx-1 > 0 ){
                    ans = Math.min( ans, Math.abs( target-2*(lt+rList.get(idx-1))));
                }
                if( idx+1 < rList.size() ){
                    ans = Math.min( ans, Math.abs( target-2*(lt+rList.get(idx+1))));
                }
            }
        }

        return ans;

    }
    private int lowerBound( List<Integer> list, int target ){
        int low = 0 , high = list.size() -1 ;
        int ans = -1;
        while( low <= high ){
            int mid = ( high - low )/2 + low;
            if( list.get(mid) <= target ){
                ans = mid;
                low = mid+1;
            }
            else{
                high = mid-1;
            }
        }
        return ans;
    }
}