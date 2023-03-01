import java.io.*;
import java.util.*;

public class BOJ2643 {
    private static class Pair {
        int width, height;
        Pair(int width, int height) {
            if (height < width) {
                int tmp = height;
                height = width;
                width = tmp;
            }
            this.width = width;
            this.height = height;
        }
        @Override
        public String toString() {
            return "[" + width + ", " + height + "]";
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        Pair[] inputs = new Pair[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            inputs[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(inputs, (p1, p2) -> p2.height != p1.height ? Integer.compare(p2.height, p1.height) : Integer.compare(p2.width, p1.width));
        int[] dp = new int[n]; // i 가 바닥에 있을 때 가장 많이 쌓을 수 있는 개수
        Arrays.fill(dp, 1);
        for (int i = n-2; i >= 0; i--) {
            for (int j = n-1; j > i; j--) {
                if (inputs[j].width <= inputs[i].width) {
                    dp[i] = Integer.max(dp[i], dp[j]+1);
                }
            }
        }
        int max = 0;
        for (int i = 0; i < n; i++) max = Integer.max(max, dp[i]);
        bw.write(Integer.toString(max));
        bw.flush();
    }

}
