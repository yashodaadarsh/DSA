class Solution {
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        List<int[]> arr = new ArrayList<>();
        for( int i = 0 ; i < n ; i++ ){
            arr.add( new int[]{nums[i],i});
        }
        Collections.sort( arr,(a , b)-> a[0] - b[0]  );
        int i = 0 , j = n-1;
        int a = 0 , b =0;
        while( i < j ){
            int sum = arr.get(i)[0] + arr.get(j)[0];
            if( sum == target ){
                a = arr.get(i)[1];
                b = arr.get(j)[1];
                break;
            }
            if( sum < target ){
                i++;
            }
            else 
                j--;
        }
        return new int[]{ a , b };
    }
}