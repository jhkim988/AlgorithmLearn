import java.util.*;

public class HLD {
    private static class SegTree {
        void update(int idx, int val) {

        }

        int get(int left, int right) {
            return 0;
        }
    }

    private static class Node {
        int vertex, weight;
    }

    int id = 0; // Euler tour id
    int[] dep, par, sz, in, out, top;
    // depth, parent, size of subtree
    // Euler Tour order(in, out), top node of same heavy chain
    ArrayList<ArrayList<Node>> tree;
    SegTree sg;

    HLD(int n, ArrayList<ArrayList<Node>> tree) {
        n++;
        this.tree = tree;
        dep = new int[n];
        par = new int[n];
        sz = new int[n];
        in = new int[n];
        out = new int[n];
        top = new int[n];
        dfs1(1);
        dfs2(1);
    }

    void dfs1(int v) {
        sz[v] = 1;
        ArrayList<Node> edge = tree.get(v);
        for (int i = 0; i < edge.size(); i++) {
            int adj = edge.get(i).vertex;
            dep[adj] = dep[v] + 1;
            par[adj] = v;
            dfs1(adj);
            sz[v] += sz[adj];
            // Set Heavy Edge as index 0:
            if (sz[adj] > sz[edge.get(0).vertex])
                swap(edge, 0, i);
        }
    }

    void dfs2(int v) {
        in[v] = ++id;
        ArrayList<Node> edge = tree.get(v);
        for (int i = 0; i < edge.size(); i++) {
            int adj = edge.get(i).vertex;
            top[adj] = i == 0 ? top[v] : adj;
            dfs2(adj);
        }
        out[v] = id;
    }

    void update(int idx, int v) {
        sg.update(in[idx], v);
    }

    int query(int a, int b) {
        int ret = 0;
        while (top[a] != top[b]) {
            if (dep[top[a]] < dep[top[b]])
                b = swap(a, a = b);
            // choose update method: max, sum, ...
            ret = Integer.max(ret, sg.get(in[top[a]], in[a]));
            a = par[top[a]];
        }
        if (dep[a] > dep[b])
            b = swap(a, a = b);
        return Integer.max(ret, sg.get(in[a], in[b]));
    }

    <T> T swap(T a, T b) {
        return a;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    void swap(ArrayList arrList, int idx1, int idx2) {
        arrList.set(idx1, arrList.set(idx2, arrList.get(idx1)));
    }
}
