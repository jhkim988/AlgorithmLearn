import java.io.*;
import java.util.*;

public class BOJ13414 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        Deque<String> deque = new ArrayDeque<>();
        Deque<String> tmp = new ArrayDeque<>();
        Set<String> set = new HashSet<>();
        while (L-- > 0) {
            deque.addFirst(br.readLine());
        }

        while (!deque.isEmpty()) {
            String s = deque.pollFirst();
            if (!set.contains(s)) {
                set.add(s);
                tmp.addFirst(s);
            }
        }

        while (!tmp.isEmpty() && K-- > 0) {
            bw.write(tmp.pollFirst());
            bw.newLine();
        }
        bw.flush();
    }
}