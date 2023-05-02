import java.io.*;
import java.util.*;

public class BOJ15965 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int k = Integer.parseInt(br.readLine());
        boolean[] isPrime = new boolean[10_000_000];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i*i <= isPrime.length; i++) {
            for (int j = i<<1; j < isPrime.length; j+=i) {
                isPrime[j] = false;
            }
        }

        int count = 0;
        for (int i = 0; i < isPrime.length; i++) {
            if (isPrime[i]) {
                count++;
                if (count == k) {
                    bw.write(i + "\n");
                    break;
                }
            }
        }

        bw.flush();
    }
}
