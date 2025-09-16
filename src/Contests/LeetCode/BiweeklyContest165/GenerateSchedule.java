package Contests.LeetCode.BiweeklyContest165;
import java.util.*;

class GenerateSchedule {
    public int[][] generateSchedule(int n) {
        if( n < 5 ) return new int[][]{};
        ArrayList<int[]> matches = new ArrayList<>();
        for( int i = 0 ; i < n ; i++ ){
            for( int j = 0 ; j < n ; j++ ){
                if( i == j ) continue;
                matches.add( new int[]{i,j} );
            }
        }
        
        ArrayList<int[]> ans = new ArrayList<>();

        while( !matches.isEmpty() ){

            ArrayList<int[]> temp = new ArrayList<>();

            for( int i = 0 ; i < matches.size() ; i++ ){
                if( ans.size() == 0 ){
                    ans.add(matches.get(i));
                    continue;
                }

                int[] lastMatch = ans.get( ans.size() - 1 );
                int lastHomeTeam = lastMatch[0] , lastAwayTeam = lastMatch[1];
                int[] currMatch = matches.get(i);
                int currHomeTeam = currMatch[0] , currAwayTeam = currMatch[1];

                if( lastHomeTeam != currHomeTeam && lastHomeTeam != currAwayTeam && 
                    lastAwayTeam != currHomeTeam && lastAwayTeam != currAwayTeam ){
                        ans.add(currMatch);
                        continue;
                }

                int matchDay = 0;
                for( ; matchDay < ans.size() ; matchDay++ ){
                    if( matchDay == 0 ){
                        int[] nextMatch = ans.get(matchDay);
                        int nextHomeTeam = nextMatch[0] , nextAwayTeam = nextMatch[1];
                        if( currHomeTeam != nextHomeTeam && currHomeTeam != nextAwayTeam &&
                         currAwayTeam != nextHomeTeam && currAwayTeam != nextAwayTeam ){
                            ans.add( matchDay , currMatch );
                            break;
                        }
                    }
                    else{
                        int[] prevMatch = ans.get( matchDay - 1 );
                        int[] nextMatch = ans.get( matchDay );
                        

                        int prevHomeTeam = prevMatch[0] , prevAwayTeam = prevMatch[1];
                        int nextHomeTeam = nextMatch[0] , nextAwayTeam = nextMatch[1];

                        if( currHomeTeam != prevHomeTeam && currHomeTeam != prevAwayTeam && 
                            currAwayTeam != prevHomeTeam && currAwayTeam != prevAwayTeam &&
                            currHomeTeam != nextHomeTeam && currHomeTeam != nextAwayTeam && 
                            currAwayTeam != nextHomeTeam && currAwayTeam != nextAwayTeam ){
                                ans.add(matchDay,currMatch);
                                break;
                        }
                    }
                }

                if( matchDay == ans.size() ){
                    temp.add( currMatch );
                }

            }

            matches = temp;

        }
        int[][] schedule = new int[n*(n-1)][2];
        int k = 0;
        for( int[] match : ans ){
            schedule[k++] = match;
        }
        return schedule;

    }
}