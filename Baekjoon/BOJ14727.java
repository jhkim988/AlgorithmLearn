import java.io.*;
import java.util.*;

public class BOJ14727 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Deque<Integer> deq = new ArrayDeque<>(); // store decreasing..
        int n = Integer.parseInt(br.readLine());
        long[] height = new long[n];
        long max = 0;
        for (int i = 0; i < n; i++) {
            height[i] = Integer.parseInt(br.readLine());
            while (!deq.isEmpty() && height[deq.peekLast()] > height[i]) {
                int right = i-1;
                int left = 0;
                long h = height[deq.removeLast()];
                if (!deq.isEmpty()) {
                    left = deq.peekLast() + 1;
                }
                max = Long.max(max, (right-left+1)*h);
            }
            deq.addLast(i);
        }

        while (!deq.isEmpty()) {
            int right = n-1;
            int left = 0;
            long h = height[deq.removeLast()];
            if (!deq.isEmpty()) {
                left = deq.peekLast() + 1;
            }
            max = Long.max(max, (right-left+1)*h);
        }

        bw.write(Long.toString(max));
        bw.flush();
    }
}