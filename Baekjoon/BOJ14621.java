import java.io.*;
import java.util.*;

public class BOJ14621 {
    private static class Pair {
        int start, end, weight;
        Pair(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
    private static class UnionFind {
        private int numGroup;
        private int[] id, sz;
        UnionFind(int n) {
            numGroup = n;
            id = new int[n];
            sz = new int[n];
            for (int i = 0; i < n; i++) {
                id[i] = i;
                sz[i] = 1;
            }
        }
        int root(int x) {
            while (x != id[x]) {
                x = id[x] = id[id[x]];
            }
            return x;
        }

        void union(int p, int q) {
            int prt = root(p);
            int qrt = root(q);
            if (prt == qrt) return;
            numGroup--;
            if (sz[prt] < sz[qrt]) {
                id[prt] = qrt;
                sz[qrt] += sz[prt];
            } else {
                id[qrt] = prt;
                sz[prt] += sz[qrt];
            }
        }

        boolean isConnected(int p, int q) {
            return root(p) == root(q);
        }
        int getNumGroup() {
            return numGroup;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        char[] info = new char[n+1];
        for (int i = 1; i <= n; i++) {
            info[i] = st.nextToken().charAt(0);
        }

        ArrayList<Pair> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            if (!isDifferent(info, a, b)) continue;
            edges.add(new Pair(a, b, w));
        }

        Collections.sort(edges, (a, b) -> a.weight-b.weight);

        // MST
        long sum = 0;
        UnionFind uf = new UnionFind(n+1);
        for (Pair edge : edges) {
            if (uf.isConnected(edge.start, edge.end)) continue;
            uf.union(edge.start, edge.end);
            sum += edge.weight;
        }

        bw.write(Long.toString(uf.getNumGroup() == 2 ? sum : -1));
        bw.flush();
    }
    private static boolean isDifferent(char[] info, int idx1, int idx2) {
        return info[idx1] != info[idx2];
    }
}
