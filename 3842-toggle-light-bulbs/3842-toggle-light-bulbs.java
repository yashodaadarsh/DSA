class Solution {
    public List<Integer> toggleLightBulbs(List<Integer> bulbs) {
        int[] arr = new int[101];
        for( int b : bulbs ){
            arr[b] ^= 1;
        }
        List<Integer> list = new ArrayList<>();
        for( int i = 0 ; i < 101 ; i++ ){
            if( arr[i] == 1 )list.add(i);
        }
        return list;
    }
}