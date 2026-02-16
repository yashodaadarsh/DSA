class Solution {
    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        Trie trie = new Trie( wordsContainer );
        for( int i = 0 ; i < wordsContainer.length ; i++ ){
            trie.insert(i);
        }
        int n = wordsQuery.length;
        int[] ans = new int[n];
        int i = 0;
        for( String word : wordsQuery ){
            ans[i++] = trie.search(word);
        }
        return ans;

    }
}

class Trie{

    private String[] wordList;
    static class TreeNode{
        int idx;
        TreeNode[] children;
        TreeNode(){
            this.idx = -1;
            children = new TreeNode[26];
        }
    }

    private TreeNode root;

    Trie( String[] wordList ){
        this.wordList = wordList;
        root = new TreeNode();
    }

    public void insert( int ind ){

        String str = wordList[ind];
        TreeNode crawl = root;
        crawl.idx = ( crawl.idx ) == -1 ? ind : ( wordList[crawl.idx].length() <= str.length() ) ? crawl.idx : ind ;
        int n = str.length();

        for( int i = n-1 ; i >= 0 ; i-- ){
            char ch = str.charAt(i);
            if( crawl.children[ ch-'a' ] == null ){
                crawl.children[ ch-'a' ] = new TreeNode();
            }
            crawl.children[ch-'a'].idx = ( crawl.children[ch-'a'].idx ) == -1 ? ind : ( wordList[crawl.children[ch-'a'].idx].length() <= str.length() ) ? crawl.children[ch-'a'].idx : ind ;
            crawl = crawl.children[ch-'a'];
        }
        // crawl.children[ch-'a'] = ind;

    }

    public int search( String word ){
        TreeNode crawl = root;
        int idx = crawl.idx;

        int n = word.length();

        for( int i = n-1 ; i >= 0 ; i-- ){
            char ch = word.charAt(i);
            if( crawl.children[ch-'a'] == null ) return idx;
            crawl = crawl.children[ch-'a'];
            idx = crawl.idx;
        }
        return idx;
    }
}