import java.io.*;
import java.util.*;

public class BOJ1027 {
    private static final Long INF = 10_000_001L*10_000_001L;
    private static class Pair implements Comparable<Pair> {
        private long width, height;
        
        Pair(long width, long height) {
            this.width = width;
            this.height = height;
        }

        @Override
        public int compareTo(Pair o) {
            return Long.compare(height*o.width, o.height*width);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] height = new int[n+1];
        for (int i = 1; i <= n; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }

        int maxNum = 0;
        for (int i = 1; i <= n; i++) {
            int num = 0;
            Pair max = new Pair(1, INF);
            for (int j = i-1; j > 0; j--) {
                Pair crnt = new Pair(i-j, height[i]-height[j]);
                if (max.compareTo(crnt) <= 0) continue;
                num++;
                max = crnt;
            }
            max = new Pair(1, -INF);
            for (int j = i+1; j <= n; j++) {
                Pair crnt = new Pair(j-i, height[j]-height[i]);
                if (max.compareTo(crnt) >= 0) continue;
                num++;
                max = crnt;
            }
            if (maxNum < num) maxNum = num;
        }

        bw.write(Integer.toString(maxNum));
        bw.flush();
    }
}