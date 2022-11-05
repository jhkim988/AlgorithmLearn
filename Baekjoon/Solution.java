import java.io.*;
import java.util.*;

/*

3
6 1
0 400 300 200 100 500 700 900 500 500 400 300 700 400 800 900 200 300 300 400
6 1
0 500 400 500 600 700 800 900 1000 600 400 300 700 1500 400 100 0 700 200 500 
11 2
0 500 100 200 300 500 700 500 600 300 400 500 100 200 800 100 500 600 400 300 200 0 400 100 300 800 700 200 300 400 700 600 0 300 200 500 400 300 1000 2000

*/

public class Solution {
    private static long INF = Long.MAX_VALUE/2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int numTest = Integer.parseInt(br.readLine());
        for (int testId = 1; testId <= numTest; testId++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[] cost = new int[4*(n-1)];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < cost.length; i++) {
                cost[i] = Integer.parseInt(st.nextToken());
            }
            int[] remainCount = new int[7];
            Arrays.fill(remainCount, m);
            long answer = recur(0, cost, remainCount);
            bw.write("#" + testId + " ");
            bw.write(Long.toString(answer));
            bw.newLine();
        }
        bw.flush();
    }
    private static long recur(int pos, int[] cost, int[] remainCount) {
        if (pos >= cost.length) {
            return 0;
        }
        
        boolean dice = false;
        long min = INF;
        for (int i = 1; i <= 6; i++) {
            if (remainCount[i] == 0) continue;
            dice = true;
            remainCount[i]--;
            min = Long.min(recur(pos + i, cost, remainCount), min);
            remainCount[i]++;
        }

        return dice ? min + cost[pos] : INF;
    }
}
