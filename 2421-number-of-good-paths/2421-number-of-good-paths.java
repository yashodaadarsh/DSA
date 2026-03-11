class Solution {
    public int numberOfGoodPaths(int[] vals, int[][] edges) {
        int n = vals.length;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        TreeMap<Integer, List<Integer>> nodes = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            nodes.putIfAbsent(vals[i], new ArrayList<>());
            nodes.get(vals[i]).add(i);
        }

        boolean[] active = new boolean[n];
        DSU dsu = new DSU(n);

        int ans = n;

        for (int key : nodes.keySet()) {
            for (int node : nodes.get(key)) {
                active[node] = true;
                for (int next : adj.get(node)) {
                    if (active[next]) {
                        dsu.union(next, node);
                    }
                }
            }
            ans += dsu.goodPaths(nodes.get(key));
        }

        return ans;
    }
}

class DSU {

    int[] parent;
    int[] size;

    public DSU(int n) {
        parent = new int[n];
        size = new int[n];

        for (int i = 0; i < n; i++) {
            size[i] = 1;
            parent[i] = i;
        }
    }

    public int findParent(int num) {
        if (parent[num] == num) {
            return num;
        }
        return parent[num] = findParent(parent[num]);
    }

    public boolean union(int a, int b) {
        int p1 = findParent(a);
        int p2 = findParent(b);
        if (p1 == p2)
            return false;

        if (size[p1] < size[p2]) {
            size[p2] += size[p1];
            parent[p1] = p2;
        } else {
            size[p1] += size[p2];
            parent[p2] = p1;
        }

        return true;
    }

    public int goodPaths(List<Integer> nodes) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int node : nodes) {
            int parent = findParent(node);
            map.put(parent, map.getOrDefault(parent, 0) + 1);
        }

        int good = 0;

        for (int count : map.values()) {
            good += (count * (count - 1)) / 2;
        }

        return good;
    }

}