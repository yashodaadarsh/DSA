class Solution {

    int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

    public int minimumEffortPath(int[][] heights) {

        int n = heights.length;
        int m = heights[0].length;

        int[][] dist = new int[n][m];
        for(int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq =
            new PriorityQueue<>((a,b)->a[2]-b[2]);

        pq.offer(new int[]{0,0,0});
        dist[0][0] = 0;

        while(!pq.isEmpty()){

            int[] cur = pq.poll();
            int r = cur[0];
            int c = cur[1];
            int effort = cur[2];

            if(r == n-1 && c == m-1) return effort;

            for(int[] d : dirs){

                int nr = r + d[0];
                int nc = c + d[1];

                if(nr>=0 && nr<n && nc>=0 && nc<m){

                    int newEffort =
                        Math.max(effort,
                        Math.abs(heights[r][c]-heights[nr][nc]));

                    if(newEffort < dist[nr][nc]){

                        dist[nr][nc] = newEffort;
                        pq.offer(new int[]{nr,nc,newEffort});
                    }
                }
            }
        }

        return 0;
    }
}