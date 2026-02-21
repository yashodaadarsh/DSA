class Solution {
    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        Trie trie = new Trie();
        for( List<String> path : paths ){
            trie.insert( path );
        }

        trie.propagate();
        trie.removeDuplicateFolder();
        return trie.getRemainingFolder();
    }
}

class Trie {

    static class TreeNode {
        String nodeName;
        String subFolderName = "";
        Map<String, TreeNode> children = new HashMap<>();
    }

    private TreeNode root = new TreeNode();
    private Map<String, Integer> subFolderFreq = new HashMap<>();

    void insert(List<String> folderPath) {
        TreeNode crawl = root;
        for (String folder : folderPath) {
            crawl.children.putIfAbsent(folder, new TreeNode());
            crawl.children.get(folder).nodeName = folder;
            crawl = crawl.children.get(folder);
        }
    }

    void propagate() {
        fillSubFolder(root);
    }

    private String fillSubFolder(TreeNode node) {
        if (node.children.isEmpty()) return "";

        List<String> parts = new ArrayList<>();

        for (TreeNode child : node.children.values()) {
            String sub = fillSubFolder(child);
            parts.add(child.nodeName + "(" + sub + ")");
        }

        Collections.sort(parts);

        StringBuilder sb = new StringBuilder();
        for (String p : parts) sb.append(p);

        String sig = sb.toString();
        node.subFolderName = sig;

        subFolderFreq.put(sig, subFolderFreq.getOrDefault(sig, 0) + 1);
        return sig;
    }

    void removeDuplicateFolder() {
        remove(root);
    }

    private void remove(TreeNode node) {
        List<String> toRemove = new ArrayList<>();

        for (TreeNode child : node.children.values()) {
            if (!child.subFolderName.isEmpty()
                    && subFolderFreq.get(child.subFolderName) > 1) {
                toRemove.add(child.nodeName);
            }
        }

        for (String name : toRemove) node.children.remove(name);

        for (TreeNode child : node.children.values()) {
            remove(child);
        }
    }

    List<List<String>> getRemainingFolder() {
        List<List<String>> ans = new ArrayList<>();
        dfs(root, new ArrayList<>(), ans);
        return ans;
    }

    private void dfs(TreeNode node, List<String> path, List<List<String>> ans) {
        for (TreeNode child : node.children.values()) {
            path.add(child.nodeName);
            ans.add(new ArrayList<>(path));
            dfs(child, path, ans);
            path.remove(path.size() - 1);
        }
    }
}