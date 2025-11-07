package POTD.LeetCode.LC_2528_Maximize_the_Minimum_Powered_City;

import java.util.Arrays;

class Solution {
    public long maxPower(int[] stations, int r, int k) {
        int n = stations.length;
        long ans = 0;
        long low = Arrays.stream( stations ).min().getAsInt();
        long high = Arrays.stream( stations ).asLongStream().sum() + k ;

        while( low <= high ){
            long mid = ( high - low )/2 + low;
            if( check( mid , stations , r , k ) ){
                ans = mid;
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }

        return ans;

    }
    private boolean check( long tar , int[] stations , int r , long k ){
        int n = stations.length;
        long[] diff = new long[n];
        for( int i = 0 ; i < n ; i++ ){
            diff[ Math.max(0,i-r) ] += stations[i];
            if( i+r+1 < n )
                diff[ i+r+1 ] -= stations[i];
        }

        long sum = 0 ;
        for( int i = 0 ; i < n ; i++ ){
            sum += diff[i];
            if( sum < tar ){
                long need = tar - sum;
                if( need > 1l*k ){
                    return false;
                }
                
                    sum += need;
                    k = k-need;
                    if ( i + 2 * r + 1 < n )
                        diff[ i + 2 * r + 1 ] -= need;

                }
        
        }
        return true;
    }
}


