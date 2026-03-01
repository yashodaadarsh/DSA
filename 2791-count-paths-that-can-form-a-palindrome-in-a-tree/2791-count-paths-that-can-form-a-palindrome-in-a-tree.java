class Solution {

    class Pair{
        int node;
        char ch;
        Pair( int node, char ch ){
            this.node = node;
            this.ch = ch;
        }
        public String toString(){
            return "{ " + node + " , " + ch + " }"; 
        }
    }

    private int[] hash;

    public long countPalindromePaths(List<Integer> parent, String s) {
        int n = parent.size();
        List<List<Pair>> adj = new ArrayList<>();
        for( int i = 0; i < n; i++ ){
            adj.add( new ArrayList<>() );
        }
        for( int i = 0; i < n; i++ ){
            int u = parent.get(i);
            char ch = s.charAt(i);
            if( u != -1 )
                adj.get(u).add(new Pair(i,ch));
            adj.get(i).add(new Pair(u,ch));    
        }

        hash = new int[26];
        for( int i = 0; i < 26; i++ ){
            hash[i] = (1<<i);
        }

        int[] pathXor = new int[n];
        boolean[] vis = new boolean[n];
        computeRootToNode(0,adj,pathXor,0,vis);

        long count = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        
        for( int node = 0; node < n; node++ ){

            int mask = pathXor[node];

            count += freq.getOrDefault(mask,0);

            for( int i = 0; i < 26; i++ ){
                int newMask = mask ^ ( 1<<i );
                count += freq.getOrDefault( newMask,0 );
            }

            freq.put( mask, freq.getOrDefault(mask,0) + 1 );

        }

        return count;

    }

    private void computeRootToNode( int node, List<List<Pair>> adj, int[] pathXor, int xor, boolean[] vis ){

        vis[node] = true;
        pathXor[node] = xor;

        for( Pair neigh : adj.get(node) ){
            int next = neigh.node;
            if( next == -1 || vis[next] ) continue;
            char ch = neigh.ch;
            computeRootToNode( next,adj,pathXor,xor ^ hash[ch-'a'],vis );
        }
    }
}