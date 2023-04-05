import java.io.*;
import java.util.*;

public class BOJ16920 {
    private static class Pair {
        int row, col;
        long dist;
        Pair(int row, int col) {
            this(row, col, 0);
        }

        Pair(int row, int col, long dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        int[] S = new int[P + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= P; i++) {
            S[i] = Integer.parseInt(st.nextToken());
        }
        int numEmpty = 0;
        char[][] board = new char[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (board[i][j] == '.') numEmpty++;
            }
        }
        int[] rowDi = { -1, 0, 1, 0 };
        int[] colDi = { 0, 1, 0, -1 };
        int[] ans = new int[P + 1];

        List<Queue<Pair>> qList = new ArrayList<>();
        for (int i = 0; i <= P; i++) {
            qList.add(new ArrayDeque<>());
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if ('1' <= board[i][j] && board[i][j] <= '9') {
                    qList.get(board[i][j] - '0').add(new Pair(i, j));
                }
            }
        }

        int turn = 1;
        int prev = 0;
        while (numEmpty > 0 && prev != numEmpty) {
            prev = numEmpty;
            for (int i = 1; i <= P; i++) {
                if (S[i] == 0) continue;
                Queue<Pair> q = qList.get(i);
                while (!q.isEmpty()) {
                    Pair p = q.peek();
                    if (p.dist >= turn*S[i]) break;
                    q.poll();
                    for (int j = 0; j < 4; j++) {
                        int nextRow = p.row + rowDi[j];
                        int nextCol = p.col + colDi[j];
                        if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) continue;
                        if (board[nextRow][nextCol] != '.') continue;
                        board[nextRow][nextCol] = (char) ('0' + i);
                        numEmpty--;
                        q.add(new Pair(nextRow, nextCol, p.dist + 1));
                    }
                }
            }
            turn++;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if ('0' <= board[i][j] && board[i][j] <= '9') {
                    ans[board[i][j] - '0']++;
                }
            }
        }

        for (int i = 1; i <= P; i++) {
            bw.write(Integer.toString(ans[i]));
            bw.write(' ');
        }
        bw.flush();
    }
}
