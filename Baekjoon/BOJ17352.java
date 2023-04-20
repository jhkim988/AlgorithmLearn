import java.io.*;
import java.util.*;

public class BOJ17352 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        List<Queue<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayDeque<>());
        }
        for (int i = 0; i < n - 2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        boolean[] visit = new boolean[n+1];
        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);
        visit[1] = true;
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int next : graph.get(cur)) {
                if (visit[next]) continue;
                visit[next] = true;
                q.add(next);
            }
        }
        int val = 1;
        for (int i = 1; i <= n; i++) {
            if (!visit[i]) {
                val = i;
                break;
            }
        }
        bw.write(Integer.toString(1));
        bw.write(' ');
        bw.write(Integer.toString(val));
        bw.flush();
    }
}
