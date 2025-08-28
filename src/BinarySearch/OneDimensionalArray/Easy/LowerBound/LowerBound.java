package BinarySearch.OneDimensionalArray.Easy.LowerBound;

public class LowerBound {
    public int findLowerBound( int[] arr , int len , int tar ){
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
