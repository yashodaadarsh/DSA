package POTD.LeetCode2026.January.LC_865_SmallestSubtreewithalltheDeepestNodes;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

 class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
 }

class Solution {

    private TreeNode subTree = null;
    private int maxDepth = 0;

    private int findDepth( TreeNode root , int depth ){

        if( root == null ) return depth;

        int left = findDepth( root.left , depth + 1 );
        int right = findDepth( root.right , depth + 1 );
        
        return Math.max( left , right );

    }

    private int findSubTree( TreeNode root , int depth ){
        
        if( root == null ) return depth;

        int left = findSubTree( root.left , depth + 1 );
        int right = findSubTree( root.right , depth + 1 );

        if( left == maxDepth && left == right ){
            subTree = root;
        } 
        
        return Math.max( left , right );  

    }

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        this.maxDepth = findDepth(root,0);
        this.subTree = root;
        findSubTree( root , 0 );
        return subTree;
    }
}