import java.io.*;
import java.util.*;

public class BOJ15558 {
    private static int n, k;
    private static int[] di = {1, -1};
    private static class Pair {
        int pos, line, time;
        Pair(int pos, int line, int time) {
            this.pos = pos;
            this.line = line;
            this.time = time;
        }
    }
    private static char[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new char[2][n];
        for (int i = 0; i < 2; i++) {
            board[i] = br.readLine().toCharArray();
        }

        bw.write(isPossible() ? "1\n" : "0\n");
        bw.flush();
    }
    private static boolean isPossible() {
        Pair start = new Pair(0, 0, 0);
        Queue<Pair> que = new ArrayDeque<>();
        que.add(start);
        boolean[][] visit = new boolean[2][n];
        while (!que.isEmpty()) {
            Pair crnt = que.poll();
            for (int i = 0; i < 2; i++) {
                int nextPos = crnt.pos + di[i];
                if (nextPos < 0) continue;
                if (nextPos >= n) return true;
                if (visit[crnt.line][nextPos]) continue;
                if (crnt.time + 1 > nextPos) continue;
                if (board[crnt.line][nextPos] == '0') continue;
                que.add(new Pair(nextPos, crnt.line, crnt.time + 1));
                visit[crnt.line][nextPos] = true;
            }

            int nextPos = crnt.pos + k;
            if (nextPos >= n) return true;
            if (!visit[1-crnt.line][nextPos] && board[1 - crnt.line][nextPos] == '1' && crnt.time + 1 <= nextPos) {
                que.add(new Pair(nextPos, 1 - crnt.line, crnt.time + 1));
                visit[1 - crnt.line][nextPos] = true;
            }
        }
        return false;
    }
}
