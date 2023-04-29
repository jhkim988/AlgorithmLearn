import java.io.*;
import java.util.*;

public class BOJ19940 {
    private static final int[] dt = {60, 10, -10, 1, -1};
    private static class State {
        int time;
        int[] click;
        State(int time, int[] click) {
            this.time = time;
            this.click = click;
        }
    }
    private static final int[][] dp = new int[61][5];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        init();
        int nTest = Integer.parseInt(br.readLine());
        while (nTest-- > 0) {
            int x = Integer.parseInt(br.readLine());
            int[] answer = solution(x);
            for (int i = 0; i < 5; ++i) {
                bw.write(Integer.toString(answer[i]));
                bw.write(' ');
            }
            bw.newLine();
        }
        bw.flush();
    }

    private static int[] solution(int x) {
        int[] answer = new int[5];
        System.arraycopy(dp[x%60], 0, answer, 0, 5);
        answer[0] += x/60;
        return answer;
    }

    private static void init() {
        boolean[] visit = new boolean[61];
        visit[0] = true;
        Queue<State> que = new ArrayDeque<>();
        que.offer(new State(0, new int[5]));
        while (!que.isEmpty()) {
            State crnt = que.poll();
            for (int i = dt.length-1; i >= 0; i--) {
                int next = crnt.time + dt[i];
                if (next < 0 || next > 60 || visit[next]) continue;
                int[] nextClick = Arrays.copyOf(crnt.click, 5);
                nextClick[i]++;
                dp[next] = nextClick;
                que.offer(new State(next, nextClick));
                visit[next] = true;
            }
        }
    }
}
