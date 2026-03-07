// TC( E*root(E) )
class Solution {
    public int minTrioDegree(int n, int[][] edges) {
        
        int[] degree = new int[n+1];

        for( int[] edge : edges ){
            int u = edge[0];
            int v = edge[1];
            degree[u]++;
            degree[v]++;
        }

        List<Set<Integer>> adj = new ArrayList<>();

        for( int i = 0; i <= n; i++ ){
            adj.add( new HashSet<>() );
        }


        // Creating Orientation graph such that no duplicate trio's occur
        for( int[] edge : edges ){
            int u = edge[0];
            int v = edge[1];

            if( degree[u] < degree[v] || ( degree[u] == degree[v] && u < v ) ){
                adj.get(u).add(v);
            }
            else{
                adj.get(v).add(u);
            }

        }

        boolean[] mark = new boolean[n+1];

        int min = Integer.MAX_VALUE;
        // From Graph Theory :- maximum nodes connected to node <= root(E)
        for( int u = 1; u <= n; u++ ){

            // marking all the connected nodes to u
            for( int v : adj.get(u) ){
                mark[v] = true;
            }

            for( int v : adj.get(u) ){
                for( int w : adj.get(v) ){
                    // u -> v && v -> w && u -> w : checking in u adjList
                    if( mark[w] ){
                        int deg = degree[u] + degree[v] + degree[w] - 6;
                        min = Math.min( deg,min );
                    }
                }
            }
            
            // unmarking all the connected nodes to u
            for( int v : adj.get(u) ){
                mark[v] = false;
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;

    }
}

// TC( E*root(E) )* hash look up
class Approach3 {
    public int minTrioDegree(int n, int[][] edges) {
        
        int[] degree = new int[n+1];

        for( int[] edge : edges ){
            int u = edge[0];
            int v = edge[1];
            degree[u]++;
            degree[v]++;
        }

        List<Set<Integer>> adj = new ArrayList<>();

        for( int i = 0; i <= n; i++ ){
            adj.add( new HashSet<>() );
        }

        // Creating Orientation graph such that no duplicate trio's occur
        for( int[] edge : edges ){
            int u = edge[0];
            int v = edge[1];

            if( degree[u] < degree[v] || ( degree[u] == degree[v] && u < v ) ){
                adj.get(u).add(v);
            }
            else{
                adj.get(v).add(u);
            }

        }

        int min = Integer.MAX_VALUE;
        // From Graph Theory :- maximum nodes connected to node <= root(E)
        for( int u = 1; u <= n; u++ ){
            for( int v : adj.get(u) ){
                for( int w : adj.get(v) ){
                    // u -> v && v -> w && u -> w : checking in u adjList
                    if( adj.get(u).contains(w) ){
                        int deg = degree[u] + degree[v] + degree[w] - 6;
                        min = Math.min( deg,min );
                    }
                }
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;

    }
}

// TC :- O(E*n)
class Approach2 {
    public int minTrioDegree(int n, int[][] edges) {

        int[] degree = new int[n+1];
        int[][] matrix = new int[n+1][n+1];

        for( int[] edge : edges ){
            int u = edge[0];
            int v = edge[1];
            degree[u]++;
            degree[v]++;
            matrix[u][v] = 1;
            matrix[v][u] = 1;
        }

        int min = Integer.MAX_VALUE;
        
        for( int[] edge : edges ){
            int u = edge[0];
            int v = edge[1];
            for( int k = 1; k <= n; k++ ){
                if( matrix[u][k] == 1 && matrix[k][v] == 1 ){
                    int deg = degree[u] + degree[v] + degree[k] - 6;
                    min = Math.min( deg,min );
                }
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;

    }
}

// TC :- O( n^3 )
class Approach1 {
    public int minTrioDegree(int n, int[][] edges) {

        int[] degree = new int[n+1];
        int[][] matrix = new int[n+1][n+1];

        for( int[] edge : edges ){
            int u = edge[0];
            int v = edge[1];
            degree[u]++;
            degree[v]++;
            matrix[u][v] = 1;
            matrix[v][u] = 1;
        }

        int min = Integer.MAX_VALUE;

        for( int i = 1; i <= n; i++ ){
            for( int j = i+1; j <= n; j++ ){
                if( matrix[i][j] == 1 ){
                    for( int k = j+1; k <= n; k++ ){
                        if( matrix[i][k] == 1 && matrix[k][j] == 1 ){
                            min = Math.min( min, degree[i] + degree[j] + degree[k] - 6 );
                        }
                    }
                }
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;

    }
}