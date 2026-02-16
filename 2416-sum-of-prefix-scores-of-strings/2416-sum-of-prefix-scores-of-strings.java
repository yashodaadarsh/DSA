class Solution {
    public int[] sumPrefixScores(String[] words) {
        Trie trie = new Trie();
        for( String word : words ){
            trie.insert( word );
        }
        int n = words.length;
        int[] res = new int[n];
        for( int i = 0 ; i < n ; i++ ){
            res[i] = trie.getScore( words[i] );
        }
        return res;
    }
}
class Trie{
    static class TreeNode{
        TreeNode[] child;
        int count;
        TreeNode(){
            count = 0;
            child = new TreeNode[26];
        }
    }
    TreeNode root = new TreeNode();

    void insert( String word ){
        int n = word.length();
        TreeNode crawl = root;
        for( int i = 0 ; i < n ; i++ ){
            char ch = word.charAt(i);
            if( crawl.child[ch-'a'] == null ){
                crawl.child[ch-'a'] = new TreeNode();
            }
            crawl.child[ch-'a'].count++;
            crawl = crawl.child[ch-'a'];
        }

    }

    int getScore( String word ){
        int count = 0;
        int n = word.length();
        TreeNode crawl = root;
        for( int i = 0; i < n ; i++ ){
            char ch = word.charAt(i);
            if( crawl.child[ch-'a'] == null ){
                return count;
            }
            count += crawl.child[ch-'a'].count;
            crawl = crawl.child[ch-'a'];
        }
        return count;
    }
}