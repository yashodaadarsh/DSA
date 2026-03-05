class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {

        List<List<Integer>> revAdj = new ArrayList<>();
        int n = graph.length;

        for( int i = 0; i < n; i++ ){
            revAdj.add( new ArrayList<>() );
        }

        for( int i = 0; i < n; i++ ){
            for( int edge : graph[i] ){
                revAdj.get(edge).add( i );
            }
        }

        return khanAlgorithm( revAdj );
    }

    private List<Integer> khanAlgorithm( List<List<Integer>> adjList ){

        int n = adjList.size();
        List<Integer> toposort = new ArrayList<>();

        int[] indegree = new int[n];
        for( int i = 0; i < n; i++ ){
            for( int j : adjList.get(i) ){
                indegree[j]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for( int i = 0; i < n; i++ ){
            if( indegree[i] == 0 ){
                q.offer( i );
            }
        }

        while( !q.isEmpty() ){
            int node = q.poll();
            toposort.add( node );
            for( int nei : adjList.get(node) ){
                indegree[nei]--;
                if( indegree[nei] == 0 ){
                    q.offer( nei );
                }
            }
        }

        Collections.sort( toposort );
        return toposort;

    }
}