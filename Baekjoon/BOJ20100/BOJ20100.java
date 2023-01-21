package BOJ20100;
import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BOJ20100 {
    private static int n, p;
    private static ThreadLocalRandom rand = ThreadLocalRandom.current();
    private static Set<Pair> set;
    private static int[][] board;
    private static boolean[] check;

    @FunctionalInterface
    private static interface Lambda {
        double score();
    }

    private static class Pair {
        int row, col;
        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + row;
            result = prime * result + col;
            return result;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Pair other = (Pair) obj;
            if (row != other.row)
                return false;
            if (col != other.col)
                return false;
            return true;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("./BOJ20100/07.in"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("./BOJ20100/07.out"));
        
        n = Integer.parseInt(br.readLine());
        set = new HashSet<>();
        board = new int[n*n][n*n];
        check = new boolean[n*n+1];

        p = 0;
        for (int i = 0; i < n*n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n*n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] != 0) set.add(new Pair(i, j));
                else p++;
            }
        }

        for (int i = 0; i < 100_000; i++) {
            simulatedAnnealing(1, 0.0001, 0.9999, 10, () -> score());
            System.out.println("score: " + score());
            simulatedAnnealing(1, 0.01, 0.99, 10, () -> numCandidate());
            System.out.println("numCandidate: " + numCandidate());
            print(bw);
        }
    }

    private static void print(BufferedWriter bw) throws IOException {
        for (int i = 0; i < n*n; i++) {
            for (int j = 0; j < n*n; j++) {
                bw.write(Integer.toString(board[i][j]));
                bw.write(' ');
            }
            bw.newLine();
        }
        bw.flush();
    }
    
    private static void simulatedAnnealing(double t, double lim, double d, double k, Lambda lambda) {
        while (t > lim) {
            double e1 = lambda.score();
            int count = 0;
            int rowRand = rand.nextInt(0, n*n);
            int colRand = rand.nextInt(0, n*n);
            while (set.contains(new Pair(rowRand, colRand)) && count < 100_000) {
                rowRand = rand.nextInt(0, n*n);
                colRand = rand.nextInt(0, n*n);
                count++;
            }
            count = 0;
            List<Integer> candidate = candidate(rowRand, colRand);
            int prev = board[rowRand][colRand];
            if (candidate.size() > 0) {
                board[rowRand][colRand] = candidate.get(rand.nextInt(0, candidate.size()));
            }
            double e2 = lambda.score();
            double prob = Math.exp((e2-e1)/(k*t));
            if (prob < rand.nextDouble()) {
                board[rowRand][colRand] = prev;
            }
            t *= d;
        }
    }

    private static List<Integer> candidate(int row, int col) {
        Arrays.fill(check, false);
        for (int i = 0; i < n*n; i++) {
            check[board[row][i]] = check[board[i][col]] = true;
        }
        for (int i = (row/n)*n; i < (row/n)*n + n; i++) {
            for (int j = (col/n)*n; j < (col/n)*n + n; j++) {
                check[board[i][j]] = true;
            }
        }
        return IntStream.rangeClosed(1, n*n).filter(x -> !check[x]).boxed().collect(Collectors.toList());
    }

    private static double score() {
        for (int i = 0; i < n*n; i++) {
            if (!rowCheck(i) || !colCheck(i) || !cellCheck(i/n, i%n)) return 0;
        }
        int q = 0;
        for (int i = 0; i < n*n; i++) {
            for (int j = 0; j <n*n; j++) {
                if (board[i][j] == 0) q++;
            }
        }

        return 10 * ((double) p - (double) q)/((double) p);
    }

    private static int numCandidate() {
        int num = 0;
        for (int i = 0; i < n*n; i++) {
            for (int j = 0; j < n*n; j++) {
                num += candidate(i, j).size();
            }
        }
        return num;
    }

    private static boolean rowCheck(int row) {
        Arrays.fill(check, false);
        for (int i = 0; i < n*n; i++) {
            if (board[row][i] == 0) continue;
            if (check[board[row][i]]) return false;
            check[board[row][i]] = true;
        }
        return true;
    }

    private static boolean colCheck(int col) {
        Arrays.fill(check, false);
        for (int i = 0; i < n*n; i++) {
            if (board[i][col] == 0) continue;
            if (check[board[i][col]]) return false;
            check[board[i][col]] = true;
        }
        return true;
    } 

    private static boolean cellCheck(int cellRow, int cellCol) {
        Arrays.fill(check, false);
        for (int i = cellRow*n; i < n + cellRow*n; i++) {
            for (int j = cellCol*n; j < n + cellCol*n; j++) {
                if (board[i][j] == 0) continue;
                if (check[board[i][j]]) return false;
                check[board[i][j]] = true;
            }
        }
        return true;
    }
}
