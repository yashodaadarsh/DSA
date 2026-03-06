class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Set<String> set = new HashSet<>();
        for( String word : wordList ){
            set.add( word );
        }

        if( !set.contains(endWord) ) return 0;

        Queue<String> q = new LinkedList<>();
        int level = 0;
        q.offer( beginWord );

        while( !q.isEmpty() ){
            int size = q.size();
            level++;

            for( int i = 0; i < size; i++ ){
                String node = q.poll();
                for( int j = 0; j < node.length(); j++ ){
                    for( char ch = 'a' ; ch <= 'z' ; ch++ ){
                        String subString = node.substring(0,j) + ch + node.substring(j+1,node.length());
                        if( subString.equals(node) ) continue;
                        if( endWord.equals( subString ) ) return level+1;
                        if( set.contains(subString) ){
                            set.remove( subString );
                            q.offer( subString );
                        }
                    }
                }
            }

        }

        return 0;

    }
}