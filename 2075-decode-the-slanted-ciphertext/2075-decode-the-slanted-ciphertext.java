class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        int n = encodedText.length();
        int col = (n)/rows;
        char[][] mat = new char[rows][col];
        for( int i = 0; i < n; i++ ){
            char ch = encodedText.charAt(i);
            int r = i/col;
            int c = i%col;
            mat[r][c] = ch;
        }

        // System.out.println( Arrays.deepToString(mat) );

        StringBuilder sb = new StringBuilder();
        for( int j = 0; j < col; j++ ){
            for( int i = 0; i < rows; i++ ){
                int r = i;
                int c = j+i;
                if( r < rows && c < col )
                    sb.append( mat[r][c] );
            }
        }
        int idx = sb.length()-1;
        System.out.println( idx + "" + sb + "!" );
        for( int i = sb.length() - 1; i >= 0; i-- ){
            idx = i;
            if( sb.charAt(i) != ' ' ){
                break;
            }
        }
        return sb.substring(0,idx+1);
    }
}