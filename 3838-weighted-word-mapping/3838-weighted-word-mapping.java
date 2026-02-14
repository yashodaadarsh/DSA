class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
        String res = "";
        for( String str : words ){
            int w = 0;
            for( char ch : str.toCharArray() ){
                w += weights[ch-'a'];
            }
            int ch = (w%26);
            res += (char)(25-(ch-'a'));
        }
        return res;
    }
}