import java.io.*;

public class BOJ14607 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        long n = Long.parseLong(br.readLine());
        bw.write(Long.toString(n*(n-1)/2));
        bw.flush();
    }
}