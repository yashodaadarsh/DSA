package POTD.LeetCode2026.January.LC_1161_MaximumLevelSumofaBinaryTree;

import java.util.*;

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
    public int maxLevelSum(TreeNode root) {
        return BFS(root);
    }
    private int BFS( TreeNode root ){
        Queue<TreeNode> q = new LinkedList<>();
        int index = -1 , level = 1;
        long max = Long.MIN_VALUE;
        q.offer(root);
        while( !q.isEmpty() ){
            long sum = 0;
            int size = q.size();
            for( int i = 0 ; i < size ; i++ ){
                TreeNode node = q.poll();
                sum += node.val;
                if( node.left != null ) q.offer( node.left );
                if( node.right !=  null ) q.offer( node.right );
            }
            if( sum > max ){
                max = sum;
                index = level;
            }
            level++;
        }
        return index;
    }
}

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