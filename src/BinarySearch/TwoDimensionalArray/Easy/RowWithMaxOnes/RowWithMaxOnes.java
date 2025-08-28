package BinarySearch.TwoDimensionalArray.Easy.RowWithMaxOnes;// User function Template for Java

class RowWithMaxOnes {
    public int rowWithMax1s(int arr[][]) {
        // code here
        int n = arr.length;
        int m = arr[0].length;
        int max = 0;
        int index = -1;
        for( int i = 0 ; i < n ; i++ ){
            int count = m - findLowerBound( arr[i] , m , 1 );
            if( count > max ){
                max = count;
                index = i;
            }
        }
        return index;
    }
    private int findLowerBound( int[] arr , int len , int tar ){
        int low = 0 , high = len - 1;
        while( low <= high ){
            int mid = ( high - low ) / 2 + low ;
            if( arr[mid] >= tar ){
                high = mid - 1;
            }
            else{
                low = mid + 1;
            }
        }
        return low;
    }
}
