package Contests.LeetCode.WeeklyContest467;

class EarliestTimeToFinishOneTask {
    public int earliestTime(int[][] tasks) {
        int min = 10001 ;
        for( int[] task : tasks ){
            int time = task[0] + task[1];
            min = Math.min( time , min );
        }
        return min;
    }
}