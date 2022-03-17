import java.io.*;
import java.util.*;

public class BOJ2559 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    st = new StringTokenizer(br.readLine());
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    int lo = 0;
    int hi = 0;
    int sum = arr[0];
    while (hi - lo + 1 != k) {
      sum += arr[++hi];
    }
    int max = sum;
    while (hi + 1 < n) {
      sum -= arr[lo++];
      sum += arr[++hi];
      if (max < sum) max = sum;
    }
    bw.write(Integer.toString(max));
    bw.newLine();
    bw.flush();
  }
}
