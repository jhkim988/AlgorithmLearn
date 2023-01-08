import java.io.*;

public class BOJ8394 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        bw.write(Integer.toString(answer(n)));
        bw.flush();
    }
    private static int answer(int n) {
        if (n <= 1) return 1;
        int pprev = 1, prev = 1, crnt = 2;
        int ptr = 3;
        while (ptr <= n) {
            for (int k = 0; k < 40 && ptr <= n; k++, ptr++) {
                pprev = prev;
                prev = crnt;
                crnt = pprev + prev;
            }
            pprev %= 10;
            prev %= 10;
            crnt %= 10;
        }
        return crnt;
    }
}