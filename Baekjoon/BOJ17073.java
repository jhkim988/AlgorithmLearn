import java.io.*;
import java.util.*;

public class BOJ17073 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        List<Queue<Integer>> tree = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayDeque<>());
        }
        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        int root = 1;
        Queue<Integer> q = new ArrayDeque<>();
        q.add(root);
        boolean[] visit = new boolean[n+1];
        visit[root] = true;
        int nLeaf = 0;
        while (!q.isEmpty()) {
            int crnt = q.poll();
            int cnt = 0;
            for (int next : tree.get(crnt)) {
                if (visit[next]) continue;
                cnt++;
                q.add(next);
                visit[next] = true;
            }
            if (cnt == 0) nLeaf++;
        }
        bw.write(Double.toString(((double) w)/nLeaf));
        bw.flush();
    }
}
