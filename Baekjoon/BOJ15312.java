import java.io.*;
import java.util.*;

public class BOJ15312 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        char[] a = br.readLine().toCharArray();
        char[] b = br.readLine().toCharArray();
        int len = a.length + b.length;
        int[] val = {3, 2, 1, 2, 3, 3, 2, 3, 3, 2, 2, 1, 2, 2, 1, 2, 2, 2, 1, 2, 1, 1, 1, 2, 2, 1};
        Queue<Integer> que = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            if ((i&1) == 0) que.add(val[a[i>>1]-'A']);
            else que.add(val[b[i>>1]-'A']);
        }

        int numIter = 0;
        while (que.size() > 2) {
            if (numIter == len-1) {
                len--;
                numIter = 0;
                que.poll();
                continue;
            }
            int x = que.poll();
            int y = que.peek();
            que.add((x+y)%10);
            numIter++;
        }
        bw.write(Integer.toString(que.poll()));
        bw.write(Integer.toString(que.poll()));
        bw.flush();
    }
}
