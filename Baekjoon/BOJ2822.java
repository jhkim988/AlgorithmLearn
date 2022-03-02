import java.io.*;
import java.util.*;

public class BOJ2822 {
  private static class Pair {
    int idx, val;
    Pair(int idx, int val) {
      this.idx = idx;
      this.val = val;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    Pair[] arr = new Pair[8];
    for (int i = 0; i < 8; i++) {
      int val = Integer.parseInt(br.readLine());
      arr[i] = new Pair(i+1, val);
    }
    Arrays.sort(arr, (a, b) -> b.val-a.val);
    Arrays.sort(arr, 0, 5, (a, b) -> a.idx - b.idx);
    int sum = 0;
    for (int i = 0; i < 5; i++) {
      sum += arr[i].val;
    }
    bw.write(Integer.toString(sum));
    bw.newLine();
    bw.write(Integer.toString(arr[0].idx));
    for (int i = 1; i < 5; i++) {
      bw.write(' ');
      bw.write(Integer.toString(arr[i].idx));
    }
    bw.newLine();
    bw.flush();
  }
}
