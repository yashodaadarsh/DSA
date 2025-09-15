package Contests.WeeklyContest466;

class MinimumOperationsToTransformString {
    public int minOperations(String s) {
        int maxSteps = 0;
        int n = s.length();
        for( int i = 0 ; i < n ; i++ ){
            char ch = s.charAt(i);
            if( ch == 'a' ) continue;
            int step = ('z' - ch );
            if( step >= maxSteps ){
                maxSteps = step + 1;
            }
        }
        return ( maxSteps  ) ;
    }
}