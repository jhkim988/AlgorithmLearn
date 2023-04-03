import java.io.*;
import java.util.*;

public class BOJ1633 {
    private static class Pair {
        int white, black;
        Pair(int white, int black) {
            this.white = white;
            this.black = black;
        }
    }
    private static List<Pair> list = new ArrayList<>();
    private static long[][][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String input = null;
        while ((input = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(input);
            int white = Integer.parseInt(st.nextToken());
            int black = Integer.parseInt(st.nextToken());
            list.add(new Pair(white, black));
        }
        dp = new long[list.size()][16][16];
        bw.write(Long.toString(recur(list.size()-1, 15, 15)));
        bw.flush();
    }
    
    private static long recur(int idx, int nBlack, int nWhite) {
        if (nWhite < 0 || nBlack < 0) return Integer.MIN_VALUE >> 1;
        if (idx < 0) return 0;
        if (nBlack == 0 && nWhite == 0) return 0;
        if (dp[idx][nBlack][nWhite] != 0) return dp[idx][nBlack][nWhite];
        
        long ret = recur(idx-1, nBlack-1, nWhite) + list.get(idx).black;
        ret = Long.max(ret, recur(idx-1, nBlack, nWhite-1) + list.get(idx).white);
        ret = Long.max(ret, recur(idx-1, nBlack, nWhite));
        return dp[idx][nBlack][nWhite] = ret;
    }
}
