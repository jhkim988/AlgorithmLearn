import java.io.*;
import java.util.*;

public class BOJ2012 {
  // https://stats.stackexchange.com/questions/108527/re-arrange-elements-in-two-vectors-to-minimize-the-elementwise-difference-betwee
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(br.readLine());
    }
    Arrays.sort(arr);
    long sum = 0;
    for (int i = 0; i < n; i++) {
      sum += Math.abs(arr[i] - i - 1);
    }
    bw.write(Long.toString(sum));
    bw.flush();
  }
}
