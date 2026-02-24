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
class Solution {
    public int sumRootToLeaf(TreeNode root) {
        return solve( root,0 );
    }
    private int solve( TreeNode root, int cur ){
        if( root == null ) return 0;
        cur = cur*2+root.val;
        if( root.left == null && root.right == null ) return cur;
        return solve( root.left,cur ) + solve( root.right,cur );
    }

    // private int solve( TreeNode root, StringBuilder sb ){
    //     if( root == null ) return 0;
    //     if( root.left == null && root.right == null ){
    //         sb.append( root.val );
    //         int pathSum = Integer.parseInt(sb.toString(),2);
    //         sb.deleteCharAt( sb.length() - 1 );
    //         return pathSum;
    //     }
    //     int sum = 0;
    //     sb.append(root.val);
    //     sum += solve(root.left,sb);
    //     sum += solve(root.right,sb);
    //     sb.deleteCharAt( sb.length() - 1 );
    //     return sum;
    // }
}