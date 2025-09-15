package Contests.LeetCode.BiweeklyContest165;

class SmallestAbsentPositiveGreaterThanAverage {
    public int smallestAbsent(int[] nums) {
        double avg = 0.0;
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        for( int num : nums ){
            avg += num;
            max = Math.max(max , num);
        }
        avg /= n;
        System.out.println( avg );
        if( max < 0 ){
            return 1;
        }
        boolean[] ab = new boolean[max+1];
        for( int num : nums ){
            if( num < 0 ) continue;
            ab[num] = true;
        }
        for( int i = 1 ; i <= max ; i++ ){
            if( i > avg && !ab[i]) return i;
        }
        return max+1;
        
    }
}