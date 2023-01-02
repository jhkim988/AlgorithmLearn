import java.io.*;
import java.util.*;

public class BOJ12761 {
    @FunctionalInterface
    private static interface Lambda {
        int operation(int x);
    }

    private static List<Lambda> operations;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        operations = new ArrayList<>();
        operations.add(x -> x+1);
        operations.add(x -> x-1);
        operations.add(x -> x + a);
        operations.add(x -> x + b);
        operations.add(x -> x - a);
        operations.add(x -> x - b);
        operations.add(x -> x * a);
        operations.add(x -> x * b);

        bw.write(Integer.toString(bfs(start, target)));
        bw.flush();
    }
    private static int bfs(int start, int target) {
        int[] dist = new int[100_001];
        Arrays.fill(dist, -1);
        Queue<Integer> que = new ArrayDeque<>();
        dist[start] = 0;
        que.add(start);

        while (!que.isEmpty()) {
            int crnt = que.poll();
            for (Lambda lambda : operations) {
                int next = lambda.operation(crnt);
                if (next < 0 || next >= dist.length) continue;
                if (dist[next] != -1) continue;
                que.add(next);
                dist[next] = dist[crnt] + 1;
                if (next == target) return dist[next];
            }
        }
        return -1;
    }
}
