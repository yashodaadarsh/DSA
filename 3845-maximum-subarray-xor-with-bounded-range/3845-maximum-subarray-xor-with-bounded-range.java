class Solution {
    public int maxXor(int[] nums, int k) {

        int n = nums.length;

        // Proper prefix array
        int[] pref = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pref[i + 1] = pref[i] ^ nums[i];
        }

        Deque<Integer> minD = new ArrayDeque<>();
        Deque<Integer> maxD = new ArrayDeque<>();

        Trie trie = new Trie();
        trie.insert(0);  // very important

        int l = 0;
        int ans = 0;

        for (int r = 0; r < n; r++) {

            while (!maxD.isEmpty() && nums[maxD.peekLast()] <= nums[r]) {
                maxD.pollLast();
            }
            maxD.offerLast(r);

            while (!minD.isEmpty() && nums[minD.peekLast()] >= nums[r]) {
                minD.pollLast();
            }
            minD.offerLast(r);

            while (nums[maxD.peekFirst()] - nums[minD.peekFirst()] > k) {

                if (maxD.peekFirst() == l) maxD.pollFirst();
                if (minD.peekFirst() == l) minD.pollFirst();

                trie.remove(pref[l]);
                l++;
            }

            ans = Math.max(ans, trie.search(pref[r + 1]));

            trie.insert(pref[r + 1]);
        }

        return ans;
    }
}

class Trie{

    class TreeNode{
        int count;
        int val;
        TreeNode left;
        TreeNode right;
    }

    TreeNode root;

    Trie(){
        root = new TreeNode();
    }


    void insert( int num ){
        TreeNode crawl = root;
        for( int i = 31; i >= 0; i-- ){
            int bit = ( (num>>i) & 1 );
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
            int bit = ( (num>>i) & 1 );
            if( bit == 0 ){
                if( crawl.left == null ){
                    crawl.left = new TreeNode();
                }
                crawl = crawl.left;
                crawl.count--;
            }
            else{
                if( crawl.right == null ){
                    crawl.right = new TreeNode();
                }
                crawl = crawl.right;
                crawl.count--;
            }
        }
    }

    int search( int num ){
        TreeNode crawl = root;
        for( int i = 31; i >= 0; i-- ){
            int bit = ( (num>>i) & 1 );
            if( bit == 0 ){
                if( crawl.right != null && crawl.right.count > 0 ){
                    crawl = crawl.right;
                }
                else
                    crawl = crawl.left;
            }
            else{
                if( crawl.left != null && crawl.left.count > 0 ){
                    crawl = crawl.left;
                }
                else
                    crawl = crawl.right;
            }
        }

        return num ^ crawl.val;
    }
}