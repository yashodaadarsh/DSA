class Solution {
    public int[] maximizeXor(int[] nums, int[][] queries) {

        int N = nums.length;
        int n = queries.length;

        Arrays.sort( nums );

        Integer[][] queriesModify = new Integer[n][3];
        
        for( int i = 0; i < n; i++ ){
            queriesModify[i][0] = queries[i][0];
            queriesModify[i][1] = queries[i][1];
            queriesModify[i][2] = i;
        }

        Arrays.sort( queriesModify,(a,b) -> {
            return Integer.compare(a[1],b[1]);
        });

        Trie trie = new Trie();

        int[] answer = new int[n];
        int i = 0;
        for( Integer[] query : queriesModify ){
            int num = query[0];
            int limit = query[1];
            int index = query[2];

            while( i < N && nums[i] <= limit ){
                trie.insert(nums[i]);
                i++;
            }
            
            if (i == 0) {
                answer[index] = -1;
            } else {
                answer[index] = trie.findXORMax(num);
            }

        }

        return answer;
    }

}

class Trie{

    class TreeNode{
        int val;
        TreeNode right;
        TreeNode left;
    }

    TreeNode root;

    public Trie(){
        root = new TreeNode();
    }

    public void insert( int num ){
        TreeNode crawl = root;
        for( int i = 31; i >= 0; i-- ){
            int ith_bit = ( num >> i ) & 1;
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
        for( int i = 31; i >= 0; i-- ){
            int ith_bit = ( num >> i ) & 1;
            if( ith_bit == 0 ){
                if( crawl.right == null ){
                    crawl = crawl.left;
                }
                else
                    crawl = crawl.right;
            }
            else{
                if( crawl.left == null ){
                    crawl = crawl.right;
                }
                else
                    crawl = crawl.left;
            }
        }
        return num ^ crawl.val;
    }

}