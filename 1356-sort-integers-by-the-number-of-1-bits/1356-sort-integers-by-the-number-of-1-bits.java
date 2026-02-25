class Solution {
    public int[] sortByBits(int[] arr) {
        int n = arr.length;
        Integer[] nums = new Integer[n];
        for( int i = 0; i < n; i++ ){
            nums[i] = arr[i];
        }
        Arrays.sort( nums,(a,b)->{
            int a_bits = Integer.bitCount(a);
            int b_bits = Integer.bitCount(b);

            if (a_bits == b_bits) {
                return a - b;      
            }
            return a_bits - b_bits;
        });
        return Arrays.stream(nums).mapToInt(Integer::intValue).toArray();
    }
}