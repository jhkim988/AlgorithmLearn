import java.io.*;
import java.util.*;

public class BOJ14716 {
    private static class Pair {
        int row, col;
        Pair (int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        int[][] board = new int[row][col];
        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] rowDi = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colDi = {-1, 0, 1, -1, 1, -1, 0, 1};
        Queue<Pair> que = new LinkedList<>();
        boolean[][] visit = new boolean[row][col];
        int num = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 0 || visit[i][j]) continue;
                num++;
                visit[i][j] = true;
                que.add(new Pair(i, j));
                while (!que.isEmpty()) {
                    Pair crnt = que.poll();
                    for (int k = 0; k < 8; k++) {
                        int adjRow = crnt.row + rowDi[k];
                        int adjCol = crnt.col + colDi[k];
                        if (adjRow < 0 || adjRow >= row || adjCol < 0 || adjCol >= col) continue;
                        if (board[adjRow][adjCol] == 0 || visit[adjRow][adjCol]) continue;
                        visit[adjRow][adjCol] = true;
                        que.add(new Pair(adjRow, adjCol));
                    }
                }
            }
        }

        bw.write(Integer.toString(num));
        bw.flush();
    }
}