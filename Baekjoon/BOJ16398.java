import java.io.*;
import java.util.*;

public class BOJ16398 {
    private static class UnionFind {
        int[] id, sz;
        UnionFind(int n) {
            id = new int[n];
            sz = new int[n];
            for (int i = 0; i < n; i++) {
                id[i] = i;
                sz[i] = 1;
            }
        }
        int root(int x) {
            while (id[x] != x) {
                x = id[x] = id[id[x]];
            }
            return x;
        }
        void union(int p, int q) {
            int prt = root(p);
            int qrt = root(q);
            if (prt == qrt) return;
            if (sz[prt] < sz[qrt]) {
                sz[qrt] += sz[prt];
                id[prt] = qrt;
            } else {
                sz[prt] += sz[qrt];
                id[qrt] = prt;
            }
        }
        boolean isConnected(int p, int q) {
            return root(p) == root(q);
        }
    }
    private static class Edge {
        int start, end;
        long cost;
        Edge(int start, int end, long cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Edge> allEdge = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                allEdge.add(new Edge(i, j, Long.parseLong(st.nextToken())));
            }
        }
        Collections.sort(allEdge, (e1, e2) -> Long.compare(e1.cost, e2.cost));
        UnionFind uf = new UnionFind(n);
        long sum = 0;
        for (Edge e : allEdge) {
            if (uf.isConnected(e.start, e.end)) continue;
            sum += e.cost;
            uf.union(e.start, e.end);
        }

        bw.write(Long.toString(sum));
        bw.flush();
    }
}