import java.io.*;
import java.util.*;

public class BOJ3078 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        int[] numLen = new int[21];
        Queue<Integer> que = new LinkedList<>();
        long sum = 0;
        while (n-- > 0) {
            int len = br.readLine().length();
            if (que.size() == k+1) {
                numLen[que.poll()]--;
            }
            sum += numLen[len];
            numLen[len]++;
            que.add(len);
        }

        bw.write(Long.toString(sum));
        bw.flush();
    }
}
