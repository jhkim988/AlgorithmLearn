import java.io.*;
import java.util.*;

public class BOJ1245 {
    private static int[] dx = {1, 1, 0, -1, -1, -1, 0, 1};
    private static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    private static class Pair {
        int row, col;
        Pair(int row, int col) {
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
        int[][] height = new int[nRow][nCol];
        for (int i = 0; i < nRow; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < nCol; j++) {
                height[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][] visit = new boolean[nRow][nCol];
        Queue<Pair> que = new ArrayDeque<>();
        int ans = 0;
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                if (height[i][j] == 0 || visit[i][j]) continue;
                visit[i][j] = true;
                que.add(new Pair(i, j));
                ans += numPeek(visit, que, height);
            }
        }

        bw.write(Integer.toString(ans));
        bw.flush();
    }
    private static int numPeek(boolean[][] visit, Queue<Pair> que, int[][] height) {
        int h = height[que.peek().row][que.peek().col];
        boolean isPeak = true;
        while (!que.isEmpty()) {
            Pair crnt = que.poll();
            for (int i = 0; i < 8; i++) {
                int adjRow = crnt.row + dx[i];
                int adjCol = crnt.col + dy[i];
                if (adjRow < 0 || adjRow >= height.length || adjCol < 0 || adjCol >= height[0].length) continue;
                if (height[adjRow][adjCol] > h) {
                    isPeak = false;
                }
                if (visit[adjRow][adjCol]) continue;
                if (height[adjRow][adjCol] != h) continue;
                visit[adjRow][adjCol] = true;
                que.add(new Pair(adjRow, adjCol));
            }
        }
        return isPeak ? 1 : 0;
    }
}