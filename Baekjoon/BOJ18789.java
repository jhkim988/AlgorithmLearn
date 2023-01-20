import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class BOJ18789 {
    private static ThreadLocalRandom rand = ThreadLocalRandom.current();
    private static int nRow = 8, nCol = 14;
    private static int[] rowDi = {-1, -1, -1, 0, 0, 1, 1, 1}, colDi = {-1, 0, 1, -1, 1, -1, 0, 1};
    public static void main(String[] args) {
        // int[][] board = convertBoard("2938813957836093829765164257640120532164713456193247891312086741901390802372350862094731582986546566748541923183");
        int[][] board = randInit();
        board = simulatedAnnealing(board);
        System.out.println(score(board));
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                System.out.print(board[i][j]);
            }
            // System.out.println();
        }
    }

    private static int[][] simulatedAnnealing(int[][] board) {
        double t = 1, lim = 0.0001, d = 0.9995, k = 10;
        int max = score(board);
        int[][] ret = new int[nRow][nCol];
        for (int i = 0; i < nRow; i++) {
            System.arraycopy(board[i], 0, ret[i], 0, nCol);
        }
        int val = 0;
        while (t > lim) {
            int e1 = score(board);

            boolean flag = rand.nextDouble() < 0.5 ? true : false;
            int randRow = rand.nextInt(0, nRow);
            int randCol = rand.nextInt(0, nCol);
            int prevVal = board[randRow][randCol];
            if (flag) {
                board[randRow][randCol] = rand.nextInt(0, 10);
            }

            int r1 = rand.nextInt(0, nRow);
            int r2 = rand.nextInt(0, nRow);
            int[] tmp = board[r1];
            board[r1] = board[r2];
            board[r2] = tmp;

            int e2 = score(board);
            if (max < e2) {
                for (int j = 0; j < nRow; j++) {
                    System.arraycopy(board[j], 0, ret[j], 0, nCol);
                }
                max = e2;
            }

            double p = Math.exp((e2-e1)/(k*t*e1));
            if (p < rand.nextDouble()) {
                tmp = board[r1];
                board[r1] = board[r2];
                board[r2] = tmp;
                board[randRow][randCol] = prevVal;
                val = Integer.max(val, e1);
            } else {
                val = Integer.max(val, e2);
            }
            t *= d;
        }
        return ret;
    }

    private static int score(int[][] board) {
        int score = 1;
        nextScore: while (true) {
            for (int i = 0; i < nRow; i++) {
                for (int j = 0; j < nCol; j++) {
                    if (board[i][j] == score%10 && recur(i, j, score/10, board)) {
                        score++;
                        continue nextScore;
                    }
                }
            }
            return score-1;
        }
    }

    private static boolean recur(int row, int col, int score, int[][] board) {
        boolean flag = false;
        for (int k = 0; k < 8; k++) {
            int adjRow = row + rowDi[k];
            int adjCol = col + colDi[k];
            if (adjRow < 0 || adjRow >= nRow || adjCol < 0 || adjCol >= nCol) continue;
            if (score % 10 != board[adjRow][adjCol]) continue;
            if (score / 10 == 0) return true;
            flag = flag || recur(adjRow, adjCol, score / 10, board);
        }
        return flag;
    }

    private static int[][] randInit() {
        int[][] board = new int[nRow][nCol];
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                board[i][j] = rand.nextInt(0, 10);
            }
        }
        return board;
    }

    private static int[][] convertBoard(String str) {
        int[][] board = new int[nRow][nCol];
        char[] charArr = str.toCharArray();
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                board[i][j] = charArr[i*nCol+j] - '0';
            }
        }
        return board;
    }

    private static int[] permutation() {
        int[] ret = new int[10];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = i;
        }
        for (int i = ret.length-1; i >= 0; i--) {
            swap(ret, i, rand.nextInt(0, i+1));
        }
        return ret;
    }
    private static void swap(int[] arr, int idx1, int idx2) {
        int tmp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tmp;
    }
}
