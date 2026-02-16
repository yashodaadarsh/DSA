class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        Trie trie = new Trie();
        for(int num : arr1){
            trie.insert( String.valueOf(num) );
        }
        int maxL = 0;
        for( int num : arr2 ){
            maxL = Math.max( maxL , trie.getCommonPrefix( String.valueOf(num) ) );
        }
        return maxL;
    }
}

class Trie{
    static class TreeNode{
        TreeNode[] child;
        TreeNode(){
            child = new TreeNode[26];
        }
    }
    TreeNode root = new TreeNode();

    void insert( String word ){
        int n = word.length();
        TreeNode crawl = root;
        for( int i = 0 ; i < n ; i++ ){
            char ch = word.charAt(i);
            if( crawl.child[ch-'0'] == null ){
                crawl.child[ch-'0'] = new TreeNode();
            }
            crawl = crawl.child[ch-'0'];
        }

    }

    int getCommonPrefix( String word ){
        int count = 0;
        int n = word.length();
        TreeNode crawl = root;
        for( int i = 0 ; i < n ; i++ ){
            char ch = word.charAt(i);
            if( crawl.child[ch-'0'] == null ){
                return count;
            }
            count++;
            crawl = crawl.child[ch-'0'];
        }
        return count;
    }
}