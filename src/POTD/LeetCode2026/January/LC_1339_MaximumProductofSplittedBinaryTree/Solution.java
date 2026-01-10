package POTD.LeetCode2026.January.LC_1339_MaximumProductofSplittedBinaryTree;

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
    private int MOD = (int)1e9 + 7;
    public int maxProduct(TreeNode root) {
        int totalSum = findSumOfTree( root );
        //System.out.println( totalSum );
        return maxProdSum( root , totalSum , Integer.MIN_VALUE );
    }
    private int maxProdSum( TreeNode root , int totalSum , int max ){
        if( root == null ) return 0;
        int subTreeSum = root.val;
        subTreeSum += maxProdSum( root.left , totalSum , max );
        subTreeSum += maxProdSum( root.right , totalSum , max );
        int sum = (int)( 1l * (totalSum - subTreeSum) * subTreeSum )%MOD;
        return Math.max( max , sum );
    }
    private int findSumOfTree( TreeNode root ){
        if( root == null ) return 0;
        int sum = root.val;
        sum += findSumOfTree( root.left );
        sum += findSumOfTree( root.right );
        return sum;
    }
}