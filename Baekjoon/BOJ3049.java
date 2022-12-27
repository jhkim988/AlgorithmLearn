import java.io.*;

public class BOJ3049 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        int sum = 0;
        for (int i = 1; i <= n-3; i++) {
            sum += i*(n-2-i);
        }
        bw.write(Integer.toString(sum*n/4));
        bw.flush();
    }    
}
