class Solution {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> res = new ArrayList<>();
        if( turnedOn > 9 ) return res;
        for( int i = 0 ; i < 12 ; i++ ){
            int hourBitCount = Integer.bitCount(i);
            for( int j = 0 ; j < 60 ; j++ ){
                int minuteBitCount = Integer.bitCount(j);
                if( hourBitCount+minuteBitCount == turnedOn ){
                    String hour = ""+i;
                    String minute = "";
                    if( j >= 0 && j < 10 ){
                        minute = ":0"+j;
                    }
                    else{
                        minute = ":"+j;
                    }
                    res.add( hour+minute );
                }
            }
        }
        return res;

    }
}