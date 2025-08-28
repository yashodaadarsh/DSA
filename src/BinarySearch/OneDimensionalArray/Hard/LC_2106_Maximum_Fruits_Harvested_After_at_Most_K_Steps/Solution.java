package BinarySearch.OneDimensionalArray.Hard.LC_2106_Maximum_Fruits_Harvested_After_at_Most_K_Steps;

class Solution {
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int n = fruits.length;
        int[] position = new int[n];
        int[] prefix = new int[n];
        for( int i = 0 ; i < n ; i++ ){
            position[i] = fruits[i][0];
            prefix[i] = fruits[i][1] + ( i > 0 ? prefix[i-1] : 0 );
        }
        int maxFruits = 0;
        for( int d = 0 ; d <= k/2 ; d++ ){
            int remain = k - 2*d;

            // case 1 :- left -> right 
            int i = startPos - d;
            int j = startPos + remain;
            int left = lowerBound( position , i );
            int right = upperBound( position , j ) - 1;
            int result = 0;
            if( left <= right ){
                result = prefix[right] - ( left > 0 ? prefix[left-1] : 0 );
                maxFruits = Math.max( maxFruits , result );
            }

            // case 2 :- right -> left 
            i = startPos + d ;
            j = startPos - remain;
            left = lowerBound( position , j );
            right = upperBound( position , i ) - 1;
            if( left <= right ){
                result = prefix[right] - ( left > 0 ? prefix[left-1] : 0 );
                maxFruits = Math.max( maxFruits , result );
            }
        }
        return maxFruits;
    }
    private int lowerBound( int[] arr , int tar ){
        int st = 0;
        int end = arr.length - 1;
        while( st <= end ){
            int mid = ( end - st )/2 + st;
            if( arr[mid] >= tar ){
                end = mid-1;
            }
            else
                st = mid+1;
        }
        return st;
    }
    private int upperBound( int[] arr , int tar ){
        int st = 0 ; 
        int end = arr.length - 1;
        while( st <= end ){
            int mid = ( end - st )/2 + st;
            if( arr[mid] <= tar ){
                st = mid + 1;
            }
            else 
                end = mid - 1;
        }
        return st;
    }
}