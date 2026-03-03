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
    class Node{
        int col;
        int row;
        int val;
        Node( int row,int col,int val ){
            this.col = col;
            this.row = row;
            this.val = val;
        }
    }
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<Node> nodes = new ArrayList<>();

        solve( root,nodes,0,0 );

        Collections.sort( nodes,(a,b) -> {
            if( a.col != b.col ) return a.col - b.col;
            if( a.row != b.row ) return a.row - b.row;
            return a.val - b.val;
        });

        List<List<Integer>> res = new ArrayList<>();
        int prevCol = Integer.MIN_VALUE;
        for( Node node : nodes ){
            if( node.col != prevCol ){
                prevCol = node.col;
                res.add( new ArrayList<>() );
            }

            res.get( res.size() - 1 ).add( node.val );
        }

        return res;
    }
    private void solve( TreeNode root, List<Node> nodes, int row, int col ){

        if( root == null ) return;

        nodes.add( new Node( row,col,root.val ) );
        solve( root.left,nodes,row+1,col-1 );
        solve( root.right,nodes,row+1,col+1 );

    }
}