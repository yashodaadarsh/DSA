class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int m = g.length, n = s.length;
        int i = 0 , j = 0 ; 
        int count = 0;
        while( i < m && j < n ){
            if( s[j] >= g[i] ){
                count++;
                i++; j++;
            }
            else{
                j++;
            }
        }
        return count;
    }
}