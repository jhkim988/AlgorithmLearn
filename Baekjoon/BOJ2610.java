import java.io.*;
import java.util.*;

public class BOJ2610 {
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
            while (x != id[x]) {
                x = id[x] = id[id[x]];
            }
            return x;
        }
        void union(int p,int q) {
            int prt = root(p);
            int qrt = root(q);
            if (prt == qrt) return;
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
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        // init:
        UnionFind uf = new UnionFind(n+1);
        ArrayList<Queue<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new LinkedList<>());
        }
        while (m-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph.get(start).add(end);
            graph.get(end).add(start);
            uf.union(start, end);
        } 
        
        // Floyd-Warshall:
        final int INF = Integer.MAX_VALUE/2;
        int[][] dist = new int[n+1][n+1];
        for (int[] memArr : dist) {
            Arrays.fill(memArr, INF);
        }
        for (int i = 1; i <= n; i++) {
            for (int adj : graph.get(i)) {
                dist[i][adj] = 1;
            }
            dist[i][i] = 0;
        }
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    dist[i][j] = Integer.min(dist[i][j], dist[i][k]+dist[k][j]);
                }
            }
        }

        int[] max = new int[n+1];
        for (int r = 1; r <= n; r++) {
            for (int i = 1; i <= n; i++) {
                if (!uf.isConnected(r, i)) continue;
                max[r] = Integer.max(max[r], dist[r][i]);
            }
        }
        HashMap<Integer, Integer> hm = new HashMap<>();
        ArrayList<Integer> representations = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            int min = i;            
            for (int j = 1; j <= n; j++) {
                if (!uf.isConnected(i, j)) continue;
                if (max[j] < max[min]) min = j;
            }
            if (!hm.containsKey(uf.root(i))) {
                hm.put(uf.root(i), min);
            } else {
                int x = hm.get(uf.root(i));
                if (max[min] < max[x]) {
                    hm.put(uf.root(i), min);
                }
            }
        }
        for (int val : hm.values()) {
            representations.add(val);
        }
        Collections.sort(representations);
        bw.write(Integer.toString(representations.size()));
        for (int r : representations) {
            bw.write('\n');
            bw.write(Integer.toString(r));
        }
        bw.flush();
    }
}
