class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for( int i = 0; i < numCourses; i++ ){
            adj.add( new ArrayList<>() );
        }
        for( int[] edge : prerequisites ){
            adj.get( edge[1] ).add( edge[0] );
        }

        return khanAlgorithm( adj );
    }

    private int[] khanAlgorithm( List<List<Integer>> adjList ){

        int n = adjList.size();
        int[] toposort = new int[n];

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

        int i = 0;
        while( !q.isEmpty() ){
            int node = q.poll();
            toposort[i++] = node;
            for( int nei : adjList.get(node) ){
                indegree[nei]--;
                if( indegree[nei] == 0 ){
                    q.offer( nei );
                }
            }
        }

        return i == n ?  toposort : new int[]{};

    }
}