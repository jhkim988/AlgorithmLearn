import java.io.*;
import java.util.*;

public class BOJ17071 {
    private final static int RANGE = 500_001;
    private final static int INF = 1_000_001;
    private static int[] move = {-1, 1, 0};
    private static class Pair {
        int level, value;
        Pair (int level, int value) {
            this.level = level;
            this.value = value;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        int[] evenSubin = new int[RANGE];
        int[] oddSubin = new int[RANGE];
        bfs(evenSubin, oddSubin, n);
        bw.write(Integer.toString(moveSister(evenSubin, oddSubin, k)));
        bw.flush();
    }
    private static void bfs(int[] evenSubin, int[] oddSubin, int start) {
        Arrays.fill(evenSubin, INF);
        Arrays.fill(oddSubin, INF);
        Queue<Pair> que = new LinkedList<>();
        que.add(new Pair(0, start));
        evenSubin[start] = 0;
        while (!que.isEmpty()) {
            Pair crnt = que.poll();
            move[2] = crnt.value;
            for (int k = 0; k < 3; k++) {
                int next = crnt.value + move[k];
                if (next < 0 || next >= RANGE) continue;
                int nextLevel = crnt.level + 1;
                if ((nextLevel & 1) == 0) {
                    if (evenSubin[next] != INF) continue;
                    evenSubin[next] = nextLevel;
                } else {
                    if (oddSubin[next] != INF) continue;
                    oddSubin[next] = nextLevel;
                }
                que.add(new Pair(nextLevel, next));
            }
        }
    }
    private static int moveSister(int[] evenSubin, int[] oddSubin, int k) {
        int step = 0;
        int val = 0;
        while (k+val < RANGE) {
            if (evenSubin[k+val] <= step && (step - evenSubin[k+val]) % 2 == 0 || oddSubin[k+val] <= step && (step - oddSubin[k+val]) % 2 == 0) return step;
            step++;
            val += step;
        }
        return -1;
    }
}