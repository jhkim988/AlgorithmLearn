import java.io.*;

public class BOJ3687 {
    private static int[] cost;
    private static String[] mindp, maxdp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int nTest = Integer.parseInt(br.readLine());
        cost = new int[10];
        cost[0] = 6;
        cost[1] = 2;
        cost[2] = 5;
        cost[3] = 5;
        cost[4] = 4;
        cost[5] = 5;
        cost[6] = 6;
        cost[7] = 3;
        cost[8] = 7;
        cost[9] = 6;

        mindp = new String[101];
        maxdp = new String[101];
        mindp[0] = maxdp[0] = "";
        while (nTest-- > 0) {
            int n = Integer.parseInt(br.readLine());
            bw.write(minRecur(n, true));
            bw.write(' ');
            bw.write(maxRecur(n, true));
            bw.newLine();
        }
        bw.flush();
    }
    private static String minRecur(int n, boolean isFirst) {
        String min = "9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999";
        if (!isFirst && mindp[n] != null) return mindp[n];
        for (int i = 0; i <= 9; i++) {
            if ((isFirst && i == 0) || n < cost[i]) continue;
            String val = minRecur(n-cost[i], false);
            String candidate = i + val;
            if (compare(candidate, min) < 0) min = candidate;
        }
        if (!isFirst) mindp[n] = min;
        return min;
    }

    private static String maxRecur(int n, boolean isFirst) {
        String max = "";
        if (!isFirst && maxdp[n] != null) return maxdp[n];
        for (int i = 0; i <= 9; i++) {
            if ((isFirst && i == 0) || n < cost[i]) continue;
            String val = maxRecur(n-cost[i], false);
            String candidate = i + val;
            if (compare(max, candidate) < 0) max = candidate;
        }
        if (!isFirst) maxdp[n] = max;
        return max;
    }

    private static int compare(String a, String b) {
        if (a.length() != b.length()) return Integer.compare(a.length(), b.length());
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) return Character.compare(a.charAt(i), b.charAt(i));
        }
        return 0;
    }
}
