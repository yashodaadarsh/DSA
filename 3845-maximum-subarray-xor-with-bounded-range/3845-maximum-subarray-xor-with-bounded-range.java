class Solution {
    public int maxXor(int[] nums, int k) {
        int n = nums.length;
        int[] prefixXor = new int[n];
        prefixXor[0] = nums[0];

        for( int i = 1; i < n; i++ ){
            prefixXor[i] = prefixXor[i-1] ^ nums[i];
        }

        Deque<Integer> minD = new ArrayDeque<>();
        Deque<Integer> maxD = new ArrayDeque<>();
        Trie trie = new Trie();
        trie.insert(0);

        int l = 0;
        int ans = Integer.MIN_VALUE;

        for( int r = 0; r < n; r++ ){

            while( !maxD.isEmpty() && nums[ maxD.peekLast() ] <= nums[r] ){
                maxD.pollLast();
            }
            maxD.offerLast(r);

            while( !minD.isEmpty() && nums[ minD.peekLast() ] >= nums[r] ){
                minD.pollLast();
            }
            minD.offerLast(r);

            while( nums[ maxD.peekFirst() ] - nums[ minD.peekFirst() ] > k ){

                if( maxD.peekFirst() == l ) maxD.pollFirst();
                if( minD.peekFirst() == l ) minD.pollFirst();

                if( l == 0 )
                    trie.remove( 0 );
                else
                    trie.remove( prefixXor[l-1] );
                l++;

            }

            int best = trie.search( prefixXor[r] );
            ans = Math.max( best,ans );
            trie.insert(prefixXor[r]);

        }

        return ans;

    }
}

class Trie{
    class TreeNode{
        int val; 
        int count;
        TreeNode left; // represents 0
        TreeNode right;// represents 1
    }

    TreeNode root;

    public Trie(){
        root = new TreeNode();
    }

    void insert( int num ){
        TreeNode crawl = root;
        for( int i = 31; i >= 0; i-- ){
            int bit = ( num >> i ) & 1;
            if( bit == 0 ){
                if( crawl.left == null ){
                    crawl.left = new TreeNode();
                }
                crawl = crawl.left;
                crawl.count++;
            }
            else{
                if( crawl.right == null ){
                    crawl.right = new TreeNode();
                }
                crawl = crawl.right;
                crawl.count++;
            }
        }
        crawl.val = num;
    }

    void remove( int num ){
        TreeNode crawl = root;
        for( int i = 31; i >= 0; i-- ){
            int bit = ( num >> i ) & 1;
            if( bit == 0 ){
                crawl = crawl.left;
                crawl.count--;
            }
            else{
                crawl = crawl.right;
                crawl.count--;
            }
        }
    }

    int search( int num ){
        TreeNode crawl = root;
        for( int i = 31; i >= 0; i-- ){
            int bit = ( num >> i ) & 1;
            if( bit == 0 ){
                if( crawl.right != null && crawl.right.count > 0 ){
                    crawl = crawl.right;
                }
                else{
                    crawl = crawl.left;
                }
            }
            else{
                if( crawl.left != null && crawl.left.count > 0 ){
                    crawl = crawl.left;
                }
                else{
                    crawl = crawl.right;
                }
            }
        }
        return num ^ crawl.val;
    }
}

