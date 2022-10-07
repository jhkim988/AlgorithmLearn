import java.io.*;
import java.util.*;

public class BOJ15586 {
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
        int getNumberGroupMember(int x) {
            return sz[root(x)];
        }
    }

    private static class Edge {
        int start, end, weight;
        Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
        @Override
        public String toString() {
            return "start=" + start + ", end=" + end + ", weight=" + weight;
        }
    }
    private static class Query {
        int idx, limit, video;
        Query(int idx, int limit, int video) {
            this.idx = idx;
            this.limit = limit;
            this.video = video;
        }
        @Override
        public String toString() {
            return "idx=" + idx + ", limit=" + limit + ", video=" + video;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
    
        List<Edge> edges = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges.add(new Edge(a, b, w));
        }
        Collections.sort(edges, (a, b) -> b.weight-a.weight);

        List<Query> queries = new ArrayList<>();
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            queries.add(new Query(i, k, v));
        }
        Collections.sort(queries, (a, b) -> b.limit-a.limit);

        int[] answer = new int[q];

        int edgePtr = 0;
        UnionFind uf = new UnionFind(n+1);
        for (Query query : queries) {
            while (edgePtr < edges.size()) {
                Edge edge = edges.get(edgePtr);
                if (edge.weight >= query.limit) {
                    uf.union(edge.start, edge.end);
                    edgePtr++;
                } else {
                    break;
                }
            }
            answer[query.idx] = uf.getNumberGroupMember(query.video)-1;
        }

        for (int a : answer) {
            bw.write(Integer.toString(a));
            bw.newLine();
        }
        bw.flush();
    }
}
