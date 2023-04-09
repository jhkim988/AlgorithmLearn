import java.io.*;
import java.util.*;

public class BOJ2002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            map.put(br.readLine(), i);
        }
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = map.get(br.readLine());
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (arr[i] > arr[j]) {
                    answer++;
                    break;
                }
            }
        }

        bw.write(Integer.toString(answer));
        bw.flush();
    }
}