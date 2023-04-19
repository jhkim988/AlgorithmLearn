import java.io.*;
import java.util.*;

public class BOJ14496 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<Queue<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayDeque<>());
        }
        while (m--> 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        int[] dist = new int[n+1];
        Arrays.fill(dist, -1);
        Queue<Integer> que = new ArrayDeque<>();
        dist[a] = 0;
        que.add(a);

        while (!que.isEmpty()) {
            int crnt = que.poll();
            for (int adj : graph.get(crnt)) {
                if (dist[adj] != -1) continue;
                dist[adj] = dist[crnt] + 1;
                que.add(adj);
                if (adj == b) {
                    bw.write(Integer.toString(dist[b]));
                    bw.flush();
                    return;
                }
            }
        }
        bw.write(Integer.toString(dist[b]));
        bw.flush();
    }
}