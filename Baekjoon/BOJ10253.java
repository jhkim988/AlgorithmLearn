import java.io.*;
import java.util.*;

public class BOJ10253 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int numTest = Integer.parseInt(br.readLine());
        while (numTest-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long answer = calculateHenry(a, b);
            bw.write(Long.toString(answer));
            bw.newLine();
        }
        bw.flush();
    }
    static long calculateHenry(long a, long b) {
        long gcd = gcd(a, b);
        a /= gcd;
        b /= gcd;
        while (a != 1) {
            long divide = (int) Math.ceil((double) b / a);
            a = a*divide-b;
            b *= divide;
        
            gcd = gcd(a, b);
            a /= gcd;
            b /= gcd;
        }
        return b;
    }
    static long gcd(long a, long b) {
        long r = a%b;
        while (r != 0) {
            a = b;
            b = r;
            r = a%b;
        }
        return b;
    }
}