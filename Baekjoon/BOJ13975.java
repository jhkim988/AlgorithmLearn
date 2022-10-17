import java.io.*;
import java.util.*;

public class BOJ13975 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int numTest = Integer.parseInt(br.readLine());
        while (numTest-- > 0) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            PriorityQueue<Long> pq = new PriorityQueue<>();
            while (n-- > 0) {
                pq.add(Long.parseLong(st.nextToken()));
            }

            long cost = 0;
            while (pq.size() > 1) {
                long x = pq.poll();
                long y = pq.poll();
                cost += x + y;
                pq.add(x+y);
            }

            bw.write(Long.toString(cost));
            bw.newLine();
        }
        bw.flush();
    }
}