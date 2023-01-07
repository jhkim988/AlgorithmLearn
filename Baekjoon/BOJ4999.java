import java.io.*;

public class BOJ4999 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int val1 = br.readLine().length();
        int val2 = br.readLine().length();
        bw.write(val1 < val2 ? "no":"go");
        bw.flush();
    }
}