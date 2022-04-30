import java.io.*;
import java.util.*;

public class BOJ13458 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    st = new StringTokenizer(br.readLine());
    int b = Integer.parseInt(st.nextToken());
    int c = Integer.parseInt(st.nextToken());

    long answer = n;
    for (int i = 0; i < n; i++) {
      arr[i] -= b;
    }
    for (int i = 0; i < n; i++) {
      if (arr[i] <= 0) continue;
      answer += ((arr[i]-1)/c) + 1;
    }
    bw.write(Long.toString(answer));
    bw.flush();
  }
}
