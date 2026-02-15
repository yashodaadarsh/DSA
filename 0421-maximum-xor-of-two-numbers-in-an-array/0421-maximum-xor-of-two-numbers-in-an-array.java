class Solution {
    public int findMaximumXOR(int[] nums) {
        Trie trie = new Trie();
        int max = 0;
        for( int num : nums ){
            trie.insert(num);
        }
        for( int num : nums ){
            max = Math.max( max, trie.findXORMax(num) );
        }
        return max;
    }
}

class Trie{
    static class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
    }
    private TreeNode root;
    public Trie(){
        root = new TreeNode();
    }

    public void insert( int num ){
        TreeNode crawl = root;
        for( int i = 31 ; i >= 0 ; i-- ){
            int ith_bit = (num>>i) & 1;
            if( ith_bit == 0 ){
                if( crawl.left == null ){
                    crawl.left = new TreeNode();
                }
                crawl = crawl.left;
            }
            else{
                if( crawl.right == null ){
                    crawl.right = new TreeNode();
                }
                crawl = crawl.right;
            }
        }
        crawl.val = num;
    }

    public int findXORMax( int num ){
        TreeNode crawl = root;
        for( int i = 31 ; i >= 0 ; i-- ){
            int ith_bit = (num>>i) & 1;
            int flip = 1 - ith_bit;
            if( flip == 0  ){
                if( crawl.left != null ) crawl = crawl.left;
                else crawl = crawl.right;
            }
            else{
                if( crawl.right != null ) crawl = crawl.right;
                else crawl = crawl.left;
            }
        }
        return num ^ crawl.val;
    }
}