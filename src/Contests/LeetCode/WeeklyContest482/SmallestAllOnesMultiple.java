package Contests.LeetCode.WeeklyContest482;

// Link :- https://leetcode.com/problems/smallest-all-ones-multiple/

class SmallestAllOnesMultiple {
    public int minAllOneMultiple(int k) {
        if( k %2 == 0 || k%5 == 0 ) return -1;
        int rem = 0 , i = 1;
        while( true ){
            rem = ( rem*10 + 1 )%k;
            if( rem%k == 0 ) return i;
            i++;
        }
    }
}