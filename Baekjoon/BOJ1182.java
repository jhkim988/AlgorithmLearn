import java.io.*;
import java.util.*;

public class BOJ1182 {
  static int N;
  static int S;
  static int[] data;
  static long count;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    S = Integer.parseInt(st.nextToken());
    data = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      data[i] = Integer.parseInt(st.nextToken());
    }
    boolean[] marked = new boolean[N];
    backtraking(0, 0L, false, marked);
    bw.write(count + "\n");
    bw.flush();
  }
  static void backtraking(int index, long sum, boolean picked, boolean[] marked) {
    for (int i = 0; i < N; i++) {
      if (marked[i]) {
        System.out.print(1);
      } else {
        System.out.print(0);
      }
    }
    System.out.println();
    if (picked && sum == S) {
      count++;
    }
    if (index >= N) return;
    if (marked[index]) return;
    marked[index] = true;
    backtraking(index + 1, sum + data[index], true, marked);
    marked[index] = false;
    backtraking(index + 1, sum, picked, marked);
  }
}