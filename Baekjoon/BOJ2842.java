import java.io.*;
import java.util.*;

public class BOJ2842 {
    private static int n;
    private static int count = 0;
    private static Pair start = null;
    private static Queue<Pair> que = new LinkedList<>();
    private static int[][] height;
    private static char[][] pos;
    private static int[] rowDi = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static int[] colDi = {-1, 0, 1, -1, 1, -1, 0, 1};

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
        n = Integer.parseInt(br.readLine());
        
        pos = new char[n][n];
        for (int i = 0; i < n; i++) {
            pos[i] = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                if (pos[i][j] == 'P') start = new Pair(i, j);
                else if (pos[i][j] == 'K') count++;
            }
        }

        List<Integer> arrList = new ArrayList<>();
        height = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                height[i][j] = Integer.parseInt(st.nextToken());
                arrList.add(height[i][j]);
            }
        }
        arrList = Arrays.asList(arrList.stream().sorted().distinct().toArray(Integer[]::new));
        int answer = Integer.MAX_VALUE;
        int lo = 0, hi = 0;
        while (lo < arrList.size() && hi < arrList.size()) {
            int loVal = arrList.get(lo);
            int hiVal = arrList.get(hi);
            if (possible(loVal, hiVal)) {
                answer = Integer.min(hiVal - loVal, answer);
                lo++;
            } else {
                hi++;
                if (lo > hi) hi++;
            }
        }

        bw.write(Integer.toString(answer));
        bw.flush();
    }

    private static boolean possible(int lo, int hi) {
        que.clear();
        boolean[][] visit = new boolean[n][n];
        if (outOfValue(start.row, start.col, lo, hi)) return false;
        int numK = 0;
        visit[start.row][start.col] = true;
        que.add(start);
        while (!que.isEmpty()) {
            Pair crnt = que.poll();
            for (int k = 0; k < 8; k++) {
                int adjRow = crnt.row + rowDi[k];
                int adjCol = crnt.col + colDi[k];
                if (outOfRange(adjRow, adjCol)) continue;
                if (visit[adjRow][adjCol]) continue;
                if (outOfValue(adjRow, adjCol, lo, hi)) continue;
                if (pos[adjRow][adjCol] == 'K') {
                    numK++;
                    if (numK == count) return true;
                }
                visit[adjRow][adjCol] = true;
                que.add(new Pair(adjRow, adjCol));
            }
        }
        return false;
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || row >= n || col < 0 || col >= n;
    }

    private static boolean outOfValue(int row, int col, int lo, int hi) {
        return height[row][col] < lo || hi < height[row][col];
    }
}
