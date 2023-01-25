import java.io.*;

public class BOJ1652 {
    private static int[] answer = {0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < n; i++) {
            int numDot = 0;
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.') {
                    numDot++;
                } else {
                    if (numDot > 1) answer[0]++;
                    numDot = 0;
                }
            }
            if (numDot > 1) answer[0]++;
        }

        for (int j = 0; j < n; j++) {
            int numDot = 0;
            for (int i = 0; i < n; i++) {
                if (board[i][j] == '.') {
                    numDot++;
                } else {
                    if (numDot > 1) answer[1]++;
                    numDot = 0;
                }
            }
            if (numDot > 1) answer[1]++;
        }

        bw.write(Integer.toString(answer[0]));
        bw.write(' ');
        bw.write(Integer.toString(answer[1]));
        bw.flush();
    }
}
