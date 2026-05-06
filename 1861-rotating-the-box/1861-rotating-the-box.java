class Solution {
    public char[][] rotateTheBox(char[][] box) {
        int r = box.length;
        int c = box[0].length;
        for( int i = 0 ; i < r ; i++ ){
            int empty = c-1;
            for( int j = c-1 ; j >= 0 ; j-- ){
                if( box[i][j] == '*' ){
                    empty = j - 1;
                }
                if( box[i][j] == '#' ){
                    box[i][j] = '.';
                    box[i][empty] = '#';
                    empty--;
                }
            }
        }
        char[][] ans = new char[c][r];
        for( int i = 0 ; i < r ; i++ ){
            for( int j = 0 ; j < c ; j++ ){
                ans[j][r-1-i] = box[i][j];
            }
        }
        return ans;
    }
}