import java.io.*;
import java.util.*;

public class BOJ5624 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] seq = new int[n];
        for (int i = 0; i < n; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (set.contains(seq[i] - seq[j])) {
                    answer++;
                    break;
                }
            }
            for (int j = 0; j <= i; j++) {
                set.add(seq[i] + seq[j]);
            }
        }
        bw.write(Integer.toString(answer));
        bw.flush();
    }
}