class Trie {

    static class TreeNode{
        boolean endOfWord;
        TreeNode[] children;
        TreeNode(){
            children = new TreeNode[26];
        }
    }
    TreeNode root;
    public Trie() {
        root = new TreeNode();
    }
    
    public void insert(String word) {
        TreeNode crawl = root;
        for( char ch : word.toCharArray() ){
            if( crawl.children[ch-'a'] == null ){
                crawl.children[ch-'a'] = new TreeNode();
            }
            crawl = crawl.children[ch-'a'];
        }
        crawl.endOfWord = true;
    }
    
    public boolean search(String word) {
        TreeNode crawl = root;
        for( char ch : word.toCharArray() ){
            if( crawl.children[ch-'a'] == null ){
                return false;
            }
            crawl = crawl.children[ch-'a'];
        }
        return crawl.endOfWord == true;
    }
    
    public boolean startsWith(String prefix) {
        TreeNode crawl = root;
        for( char ch : prefix.toCharArray() ){
            if( crawl.children[ch-'a'] == null ){
                return false;
            }
            crawl = crawl.children[ch-'a'];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */