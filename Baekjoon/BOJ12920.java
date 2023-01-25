import java.io.*;
import java.util.*;

public class BOJ12920 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int lim = Integer.parseInt(st.nextToken());
        
        List<Integer> weight = new ArrayList<>();
        List<Integer> utility = new ArrayList<>();
        List<Integer> count = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            for (int j = 1; c > 0; j <<= 1) {
                int x = Integer.min(j, c);
                weight.add(w*x);
                utility.add(u*x);
                count.add(c*x);
                c -= j;
            }
        }

        int[] dp = new int[lim+1];
        for (int i = 0; i < weight.size(); i++) {
            for (int j = lim; j >= weight.get(i); j--) {
                dp[j] = Integer.max(dp[j], dp[j - weight.get(i)] + utility.get(i));
         }
        }
        bw.write(Integer.toString(dp[lim]));
        bw.flush();
    }
}
