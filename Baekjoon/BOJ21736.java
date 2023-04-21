import java.io.*;
import java.util.*;

public class BOJ21736 {
    private static int[] rowDi= { -1, 0, 1, 0}, colDi = {0, -1, 0, 1};
    private static class Position {
        int row, col;
        Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nRow = Integer.parseInt(st.nextToken());
        int nCol = Integer.parseInt(st.nextToken());
        char[][] board = new char[nRow][nCol];

        Position start = null;
        for (int i = 0; i < nRow; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < nCol; j++) {
                if (board[i][j] == 'I') {
                    start = new Position(i, j);
                }
            }
        }

        Queue<Position> que = new ArrayDeque<>();
        que.add(start);
        boolean[][] visit = new boolean[nRow][nCol];
        visit[start.row][start.col] = true;

        int num = 0;

        while (!que.isEmpty()) {
            Position crnt = que.poll();
            for (int k = 0; k < 4; k++) {
                int adjRow = crnt.row + rowDi[k];
                int adjCol = crnt.col + colDi[k];
                if (adjRow < 0 || adjRow >= nRow || adjCol < 0 || adjCol >= nCol) continue;
                if (visit[adjRow][adjCol]) continue;
                if (board[adjRow][adjCol] == 'X') continue;
                if (board[adjRow][adjCol] == 'P') num++;
                visit[adjRow][adjCol] = true;
                que.add(new Position(adjRow, adjCol));
            }
        }

        bw.write(num == 0 ? "TT" : Integer.toString(num));
        bw.flush();
    }
}