import java.io.*;
import java.util.*;

public class BOJ1189 {
    private static char[][] board;
    private static int nRow, nCol, k;
    private static int[] rowDi = {-1, 0, 1, 0}, colDi = {0, -1, 0, 1};
    private static boolean[][] visit;
    private static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        nRow = Integer.parseInt(st.nextToken());
        nCol = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        visit = new boolean[nRow][nCol];
        board = new char[nRow][nCol];
        for (int i = 0; i < nRow; i++) {
            board[i] = br.readLine().toCharArray();
        }
        
        recur(0, nRow-1, 0);
        bw.write(Integer.toString(answer));
        bw.flush();
    }
    private static void recur(int depth, int row, int col) {
        if (depth >= k) return;
        if (depth == k-1 && row == 0 && col == nCol-1) {
            answer++;
            return;
        }
        visit[row][col] = true;
        for (int k = 0; k < 4; k++) {
            int adjRow = row + rowDi[k];
            int adjCol = col + colDi[k];
            if (adjRow < 0 || adjRow >= nRow || adjCol < 0 || adjCol >= nCol) continue;
            if (visit[adjRow][adjCol] || board[adjRow][adjCol] != '.') continue;
            recur(depth+1, adjRow, adjCol);
        }
        visit[row][col] = false;
    }
}
