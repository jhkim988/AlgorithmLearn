import java.io.*;
import java.util.*;

public class BOJ14940 {
    private static int[] rowDi = {-1, 0, 1, 0}, colDi = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nRow = Integer.parseInt(st.nextToken());
        int nCol = Integer.parseInt(st.nextToken());
        int[][] board = new int[nRow][nCol];
        
        int target = -1;
        for (int i = 0; i < nRow; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < nCol; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 2) target = i*nCol+j;
            }
        }

        int[][] answer = new int[nRow][nCol];
        for (int i = 0; i < nRow; i++) {
            Arrays.fill(answer[i], -1);
        }
        Queue<Integer> que = new ArrayDeque<>();
        que.add(target);
        answer[target/nCol][target%nCol] = 0;
        while (!que.isEmpty()) {
            int crnt = que.poll();
            for (int k = 0; k < 4; k++) {
                int adjRow = crnt/nCol + rowDi[k];
                int adjCol = crnt%nCol + colDi[k];
                if (adjRow < 0 || adjRow >= nRow || adjCol < 0 || adjCol >= nCol) continue;
                if (answer[adjRow][adjCol] != -1 || board[adjRow][adjCol] == 0) continue;
                que.add(adjRow*nCol + adjCol);
                answer[adjRow][adjCol] = answer[crnt/nCol][crnt%nCol] + 1;
            }
        }

        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                if (board[i][j] == 0) bw.write("0");
                else bw.write(Integer.toString(answer[i][j]));
                bw.write(' ');
            }
            bw.newLine();
        }
        bw.flush();
    }
}