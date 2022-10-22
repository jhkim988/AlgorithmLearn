import java.io.*;
import java.util.*;

public class BOJ1456 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        boolean[] isPrime = new boolean[10_000_001];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i < isPrime.length; i++) {
            if (!isPrime[i]) continue;
            for (int j = i<<1; j < isPrime.length; j += i) {
                isPrime[j] = false;
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        HashSet<Long> hs = new HashSet<>();
        int num = 0;
        for (int i = 2; i < isPrime.length; i++) {
            if (!isPrime[i]) continue;
            long val = i;
            while (val <= Long.MAX_VALUE / i && val * i <= b) {
                if (val * i >= a && !hs.contains(val * i)) {
                    num++;
                    hs.add(val * i);
                    // System.out.println(val * i);
                }
                val *= i;
            }
        }
        bw.write(Integer.toString(num));
        bw.flush();
    }
}