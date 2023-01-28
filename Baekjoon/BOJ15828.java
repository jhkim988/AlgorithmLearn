import java.io.*;
import java.util.*;

public class BOJ15828 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        Queue<Integer> que = new ArrayDeque<>();
        int input = -1;
        while ((input = Integer.parseInt(br.readLine())) != -1) {
            if (input == 0) {
                que.poll();
            } else {
                if (que.size() < n) {
                    que.add(input);
                }
            }
        }

        if (que.isEmpty()) {
            bw.write("empty");
        } else {
            while (!que.isEmpty()) {
                bw.write(Integer.toString(que.poll()));
                bw.write(' ');
            }
        }
        bw.flush();
    }
}
