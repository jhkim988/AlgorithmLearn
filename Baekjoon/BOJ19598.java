import java.io.*;
import java.util.*;

public class BOJ19598 {
    private static class Pair {
        int val, isStart;
        Pair (int val, int isStart) {
            this.val = val;
            this.isStart = isStart;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        List<Pair> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list.add(new Pair(start, 1));
            list.add(new Pair(end, 0));
        }
        Collections.sort(list, (p1, p2) -> p1.val == p2.val ? Integer.compare(p1.isStart, p2.isStart) : Integer.compare(p1.val, p2.val));

        int crnt = 0;
        int max = 0;
        for (Pair start : list) {
            if (start.isStart == 1) crnt++;
            else crnt--;
            max = Integer.max(max, crnt);
        }

        bw.write(Integer.toString(max));
        bw.flush();
    }
}
