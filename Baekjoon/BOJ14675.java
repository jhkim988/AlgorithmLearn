import java.io.*;
import java.util.*;

public class BOJ14675 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        List<Queue<Integer>> tree = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayDeque<>());
        }
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree.get(a).add(b);
            tree.get(b).add(a);
        }
        Set<Integer> cut = new HashSet<>();
        if (tree.get(1).size() != 1) {
            cut.add(1);
        }
        boolean[] visit = new boolean[n+1];
        visit[1] = true;
        Queue<Integer> que = new ArrayDeque<>();
        que.add(1);
        while (!que.isEmpty()) {
            int crnt = que.poll();
            for (int next : tree.get(crnt)) {
                if (!visit[next]) {
                    visit[next] = true;
                    que.add(next);
                    if (tree.get(next).size() == 1) {
                        cut.add(next);
                    }
                }
            }
        }

        int q = Integer.parseInt(br.readLine());
        for (int i = 0; i < q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            if (t == 1) {
                bw.write(tree.get(k).size() != 1 ? "yes\n" : "no\n");
            } else {
                    bw.write("yes\n");
                }
            }
            bw.flush();
        }
}
