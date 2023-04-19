import java.io.*;
import java.util.*;

public class BOJ2550 {
    private static class Pair {
        int idx, val;
        public Pair(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] switchArr = new int[n+1];
        int[] lightArr = new int[n+1];
        int[] switchMap = new int[n+1];
        int[] lightMap = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            switchArr[i] = Integer.parseInt(st.nextToken());
            switchMap[switchArr[i]] = i;
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            lightArr[i] = Integer.parseInt(st.nextToken());
            lightMap[lightArr[i]] = i;
        }

        int[] dp = new int[n+1];
        Pair[] idxMap = new Pair[n+1];
        int ptr = 1;
        for (int i = 1; i <= n; i++) {
            if (dp[ptr-1] < switchMap[lightArr[i]]) {
                idxMap[i] = new Pair(ptr, lightArr[i]);
                dp[ptr++] = switchMap[lightArr[i]];
            } else {
                int idx = Arrays.binarySearch(dp, 1, ptr, switchMap[lightArr[i]]);
                if (idx < 0) {
                    idx = -idx - 1;
                }
                idxMap[i] = new Pair(idx, lightArr[i]);
                dp[idx] = switchMap[lightArr[i]];
            }
        }
        int max = ptr-1;
        List<Integer> answer = new ArrayList<>();
        for (int i = n; i > 0; i--) {
            if (idxMap[i].idx == max) {
                answer.add(idxMap[i].val);
                max--;
            }
        }
        Collections.sort(answer);
        bw.write(Integer.toString(ptr-1));
        bw.newLine();
        for (int num : answer) {
            bw.write(Integer.toString(num));
            bw.write(" ");
        }
        bw.newLine();
        bw.flush();
    }
}
