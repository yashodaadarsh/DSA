class Solution {
    public int countPrefixSuffixPairs(String[] words) {
        int count = 0;
        int n = words.length;

        for( int j = 0 ; j < n ; j++ ){
            Trie trie = new Trie();
            trie.insert( words[j] );
            for( int i = j-1 ; i >= 0 ; i-- ){
                if( words[i].length() > words[j].length() ) continue;
                if( trie.isPrefixAndSuffix(words[i]) ) count++;
            }
        }
        return count;
    }
}

class Trie{
    static class TreeNode{
        TreeNode[] child;
        TreeNode(){
            child = new TreeNode[26];
        }
    }
    // prefix trie root
    TreeNode pRoot;
    // suffix trie root
    TreeNode sRoot;
    Trie(){
        pRoot = new TreeNode();
        sRoot = new TreeNode();
    }

    void insert( String word ){

        int n = word.length();

        // inserting in the prefix trie
        TreeNode crawl = pRoot;
        for( int i = 0 ; i < n ; i++ ){
            char ch = word.charAt(i);
            if( crawl.child[ch-'a'] == null ){
                crawl.child[ch-'a'] = new TreeNode();
            }
            crawl = crawl.child[ch-'a'];
        }

        // inserting in suffix trie
        crawl = sRoot;
        for( int i = n-1 ; i >= 0 ; i-- ){
            char ch = word.charAt(i);
            if( crawl.child[ch-'a'] == null ){
                crawl.child[ch-'a'] = new TreeNode();
            }
            crawl = crawl.child[ch-'a'];
        }
    }

    boolean isPrefixAndSuffix( String word ){

        TreeNode crawl = pRoot;
        int n = word.length();
        for( int i = 0 ; i < n ; i++ ){
            char ch = word.charAt(i);
            if( crawl.child[ch-'a'] == null ){
                return false;
            }
            crawl = crawl.child[ch-'a'];
        }

        crawl = sRoot;
        for( int i = n-1 ; i >= 0 ; i-- ){
            char ch = word.charAt(i);
            if( crawl.child[ch-'a'] == null ){
                return false;
            }
            crawl = crawl.child[ch-'a'];
        }

        return true;
    }
}