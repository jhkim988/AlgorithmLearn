import java.io.*;

public class BOJ2010 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        long sum = -n;
        while (n-- > 0) {
            sum += Long.parseLong(br.readLine());
        }
        ++sum;
        bw.write(Long.toString(sum));
        bw.flush();
    }
}
