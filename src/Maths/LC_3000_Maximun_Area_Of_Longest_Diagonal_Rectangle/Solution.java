package Maths.LC_3000_Maximun_Area_Of_Longest_Diagonal_Rectangle;

class Solution {
    public int areaOfMaxDiagonal(int[][] dimensions) {
        int n = dimensions.length;
        int area = 0;
        double maxD = Integer.MIN_VALUE;
        for( int i = 0 ; i < n ; i++ ){
            double diagonal = findDiagonal( dimensions[i][0] , dimensions[i][1]);
            if( diagonal > maxD ){
                area = dimensions[i][0]*dimensions[i][1];
                maxD = diagonal;
            }
            else if( diagonal== maxD){
                area = Math.max(area,dimensions[i][0]*dimensions[i][1]);
            }
        }
        return area;
    }
    private double findDiagonal( int l , int w ){
        return Math.sqrt(l*l+w*w);
    }
}