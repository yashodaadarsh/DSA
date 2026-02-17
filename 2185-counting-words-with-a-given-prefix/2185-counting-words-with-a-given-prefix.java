class Solution {
    public int prefixCount(String[] words, String pref) {
        Trie trie = new Trie();
        for( String word : words ){
            trie.insert( word );
        }

        return trie.getCount( pref );
    }
}

class Trie{
    static class TreeNode{
        int count;
        TreeNode[] child;
        TreeNode(){
            count = 0;
            child = new TreeNode[26];
        }
    }
    TreeNode root;
    Trie(){
        root = new TreeNode();
    }

    void insert( String word ){

        int n = word.length();

        // inserting in the trie
        TreeNode crawl = root;
        for( int i = 0 ; i < n ; i++ ){
            char ch = word.charAt(i);
            int idx = ch-'a';
            if( crawl.child[idx] == null ){
                crawl.child[idx] = new TreeNode();
            }
            crawl.child[idx].count++;
            crawl = crawl.child[idx];
        }

    }

    int getCount( String pref ){

        int count = 0;
        TreeNode crawl = root;
        int n = pref.length();
        for( int i = 0 ; i < n ; i++ ){
            char ch = pref.charAt(i);
            int idx = ch - 'a';
            if( crawl.child[idx] == null ){
                return 0;
            }
            crawl = crawl.child[idx];
        }

        count = crawl.count;

        return count;
    }
}