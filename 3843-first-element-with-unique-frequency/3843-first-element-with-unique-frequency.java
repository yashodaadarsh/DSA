class Solution {
    public int firstUniqueFreq(int[] nums) {
        HashMap<Integer,Integer> f = new HashMap<>();
        HashMap<Integer,Integer> fc = new HashMap<>();
        for( int num : nums ){
            f.put( num, f.getOrDefault(num,0) + 1 );
        }
        for( int fre : f.values() ){
            fc.put( fre,fc.getOrDefault(fre,0)+1 );
        }
        for( int num: nums ){
            int fre = f.get(num);
            if( fc.get(fre) == 1 ) return num;
        }
        return -1;
    }
}