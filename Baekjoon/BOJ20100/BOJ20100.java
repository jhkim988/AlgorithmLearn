package BOJ20100;

import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class BOJ20100 {
    private static int n, p;
    private static List<Map<Integer, Integer>> init;
    private static ThreadLocalRandom rand = ThreadLocalRandom.current();
    private static int[][] board;
    private static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("./BOJ20100/07.in"));
        n = Integer.parseInt(br.readLine());
        init = new ArrayList<>();
        for (int i = 0; i < n*n; i++) {
            init.add(new HashMap<>());
        }

        board = new int[n * n][n * n];
        check = new boolean[n * n + 1];

        p = 0;
        for (int i = 0; i < n * n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n * n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] != 0) {
                    init.get(i).put(j, board[i][j]);
                } else {
                    p++;
                }
            }
        }

        for (int cellRow = 0; cellRow < n*n; cellRow += n) {
            for (int cellCol = 0; cellCol < n*n; cellCol += n) {
                Arrays.fill(check, false);
                for (int i = cellRow; i < cellRow + n; i++) {
                    for (int j = cellCol; j < cellCol + n; j++) {
                        check[board[i][j]] = true;
                    }
                }
                int id = 1;
                for (int i = cellRow; i < cellRow + n; i++) {
                    for (int j = cellCol; j < cellCol + n; j++) {
                        if (board[i][j] == 0) {
                            while (check[id]) id++;
                            board[i][j] = id;
                            check[id] = true;
                        }
                    }
                }
            }
        }

        System.out.println("score: " + score());
        simulatedAnnealingNumAbsent(n*n*n*n, 0.0001, 0.9999, 10);
        System.out.println("numAbsent: " + numAbsent());
        System.out.println("score: " + score());
        print();
    }

    private static void simulatedAnnealingNumAbsent(double t, double lim, double d, double k) throws IOException{
        double e1 = Double.POSITIVE_INFINITY;
        while (t > lim) {
            e1 = numAbsent();
            int cellId = rand.nextInt(0, n*n);
            int rowRand1 = (cellId/n)*n + rand.nextInt(0, n);
            int colRand1 = (cellId%n)*n + rand.nextInt(0, n);
            while (init.get(rowRand1).containsKey(colRand1)) {
                rowRand1 = (cellId/n)*n + rand.nextInt(0, n);
                colRand1 = (cellId%n)*n + rand.nextInt(0, n);
            }

            int rowRand2 = (cellId/n)*n + rand.nextInt(0, n);
            int colRand2 = (cellId%n)*n + rand.nextInt(0, n);
            while (init.get(rowRand2).containsKey(colRand2)) {
                rowRand2 = (cellId/n)*n + rand.nextInt(0, n);
                colRand2 = (cellId%n)*n + rand.nextInt(0, n);
            }
            swap(board, rowRand1, colRand1, rowRand2, colRand2);
            double e2 = numAbsent();
            double prob = Math.exp((e1 - e2) / (k * t));
            if (prob < rand.nextDouble()) {
                swap(board, rowRand1, colRand1, rowRand2, colRand2);

            }
            t *= d;
        }
    }

    private static double numAbsent() {
        int ret = 0;
        for (int row = 0; row < n*n; row++) {
            Arrays.fill(check, false);
            for (int col = 0; col < n*n; col++) {
                check[board[row][col]] = true;
            }
            for (int x = 1; x <= n*n; x++) {
                if (!check[x]) ret++;
            }
        }
        for (int col = 0; col < n*n; col++) {
            Arrays.fill(check, false);
            for (int row = 0; row < n*n; row++) {
                check[board[row][col]] = true;
            }
            for (int x = 1; x <= n*n; x++) {
                if (!check[x]) ret++;
            }
        }
        return ret;
    }

    private static void swap(int[][] arr, int row1, int col1, int row2, int col2) {
        int tmp = arr[row1][col1];
        arr[row1][col1] = arr[row2][col2];
        arr[row2][col2] = tmp;
    }

    private static double score() {
        for (int i = 0; i < n * n; i++) {
            if (!rowCheck(i) || !colCheck(i) || !cellCheck(i / n, i % n))
                return 0;
        }
        int q = 0;
        for (int i = 0; i < n * n; i++) {
            for (int j = 0; j < n * n; j++) {
                if (board[i][j] == 0)
                    q++;
            }
        }

        return 10 * ((double) p - (double) q) / ((double) p);
    }

    private static boolean rowCheck(int row) {
        Arrays.fill(check, false);
        for (int i = 0; i < n * n; i++) {
            if (board[row][i] == 0)
                continue;
            if (check[board[row][i]])
                return false;
            check[board[row][i]] = true;
        }
        return true;
    }

    private static boolean colCheck(int col) {
        Arrays.fill(check, false);
        for (int i = 0; i < n * n; i++) {
            if (board[i][col] == 0)
                continue;
            if (check[board[i][col]])
                return false;
            check[board[i][col]] = true;
        }
        return true;
    }

    private static boolean cellCheck(int cellRow, int cellCol) {
        Arrays.fill(check, false);
        for (int i = cellRow * n; i < n + cellRow * n; i++) {
            for (int j = cellCol * n; j < n + cellCol * n; j++) {
                if (board[i][j] == 0)
                    continue;
                if (check[board[i][j]])
                    return false;
                check[board[i][j]] = true;
            }
        }
        return true;
    }

    private static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("./BOJ20100/07.out", false));
        for (int i = 0; i < n * n; i++) {
            for (int j = 0; j < n * n; j++) {
                bw.write(Integer.toString(board[i][j]));
                bw.write(' ');
            }
            bw.newLine();
        }
        bw.flush();
    }
}
