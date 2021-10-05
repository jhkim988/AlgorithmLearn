import java.io.*;
import java.util.*;

public class BOJ14225 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    int[] data = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      data[i] = Integer.parseInt(st.nextToken());
    }

    bw.write(answer(data) + "\n");
    bw.flush();
  }
  static int answer(int[] data) {
    Arrays.sort(data);
    int sum = 0;
    for (int el : data) {
      sum += el; // sum <= 20_000_000;
    }
  }
}
