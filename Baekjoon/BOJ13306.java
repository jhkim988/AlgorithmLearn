import java.io.*;
import java.util.*;

public class BOJ13306 {
    private static class UnionFind {
        private int[] id, sz;
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

        void union(int p, int q) {
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
    private static class Query {
        int type;

    }
    private static class RemoveEdgeQuery extends Query {
        int remove;
        RemoveEdgeQuery(int type, int remove) {
            this.type = type;
            this.remove = remove;
        }
    }
    private static class ConnectedQuery extends Query {
        int a, b;
        boolean answer;
        ConnectedQuery(int type, int a, int b) {
            this.type = type;
            this.a = a;
            this.b = b;
        }
        void setAnswer(boolean answer) {
            this.answer = answer;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int[] parent = new int[n+1];
        for (int i = 2; i <= n; i++) {
            parent[i] = Integer.parseInt(br.readLine());
        }

        int[] copy = new int[n+1];
        System.arraycopy(parent, 0, copy, 0, parent.length);

        ArrayList<Query> queries = new ArrayList<>(q);

        String input = br.readLine();
        while (input != null) {
            st = new StringTokenizer(input);
            int type = Integer.parseInt(st.nextToken());
            if (type == 0) {
                int remove = Integer.parseInt(st.nextToken());
                queries.add(new RemoveEdgeQuery(type, remove));
                copy[remove] = 0;
            } else {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                queries.add(new ConnectedQuery(type, a, b));
            }
            input = br.readLine();
        }

        UnionFind uf = new UnionFind(n+1);
        for (int i = 1; i < copy.length; i++) {
            if (copy[i] == 0) continue;
            uf.union(i, copy[i]);                        
        }

        for (int i = queries.size()-1; i >= 0; i--) {
            Query query = queries.get(i);
            if (query.type == 0) {
                RemoveEdgeQuery rq = (RemoveEdgeQuery) query;
                uf.union(rq.remove, parent[rq.remove]);
            } else {
                ConnectedQuery cq = (ConnectedQuery) query;
                cq.setAnswer(uf.isConnected(cq.a, cq.b));
            }
        }

        for (Query query : queries) {
            if (query.type == 0) continue;
            ConnectedQuery cq = (ConnectedQuery) query;
            bw.write(cq.answer ? "YES\n" : "NO\n");
        }
        bw.flush();
    }
}
