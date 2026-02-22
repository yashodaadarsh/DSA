/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {

    private Map<TreeNode,TreeNode[]> up = new HashMap<>();
    private Map<TreeNode,Integer> depth = new HashMap<>();
    private static int LOG = 20;

    private void binaryLift( TreeNode node, TreeNode parent ){

        if( node == null ) return ;

        up.putIfAbsent( node, new TreeNode[LOG] );
        TreeNode[] ancestor = up.get( node );
        depth.put( node, (parent == null) ? 0 : depth.get(parent)+1 );
        ancestor[0] = parent;

        for( int i = 1; i < LOG; i++ ){
            TreeNode i_thParent = up.get(node)[i-1];
            ancestor[i] = ( i_thParent == null ) ? null : up.get( i_thParent )[i-1];
        }

        binaryLift( node.left, node );
        binaryLift( node.right, node );
        
    }

    private TreeNode lift( TreeNode node , int depth ){
        if( node == null || depth == 0 ) return node;
        for( int i = 19 ; i >= 0 ; i-- ){
            if( depth >= (1<<i) ){
                return lift( up.get(node)[i], depth - (1<<i) );
            }
        }
        return null;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        binaryLift( root, null );

        int u = depth.get(p);
        int v = depth.get(q);

        if( u < v ){
            TreeNode temp = p;
            p = q;
            q = temp;
            u = depth.get(p);
            v = depth.get(q);
        }

        p = lift( p, u-v );
        if( p == q ) return p;

        for (int i = LOG - 1; i >= 0; i--) {

            TreeNode pu = up.get(p)[i];
            TreeNode qu = up.get(q)[i];

            if (pu != null && qu != null && pu != qu) {
                p = pu;
                q = qu;
            }
        }

        return up.get(p)[0];

    }
}