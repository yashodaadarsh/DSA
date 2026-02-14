class Solution {
    public int prefixConnected(String[] words, int k) {
        Map<String,Integer> map = new HashMap<>();
        int n = words.length;
        Set<Integer> grp = new HashSet<>();
        for( int i = 0 ;i < n ; i++ ){
            if( grp.contains(i) ) continue;
            String word  = words[i];
            if( word.length() < k ) continue;
            String pre1 = word.substring(0,k);
            for( int j = i+1 ; j < n ; j++ ){
                String word2 = words[j];
                if( word2.length() < k ) continue;
                String pre2 = word2.substring(0,k);
                if( pre1.equals(pre2) ){
                    map.put( pre1,1 );
                    grp.add(j);
                    grp.add(i);
                }
            }
        }
        return map.size();
    }
}