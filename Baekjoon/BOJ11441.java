import java.io.*;
import java.util.*;

public class BOJ11441 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] arr = new int[n+1];
    arr[0] = 0;
    for (int i = 1; i <= n; i++) {
      arr[i] = arr[i-1] + Integer.parseInt(st.nextToken());
    }
    int q = Integer.parseInt(br.readLine());
    while (q-- > 0) {
      st = new StringTokenizer(br.readLine());
      int lo = Integer.parseInt(st.nextToken());
      int hi = Integer.parseInt(st.nextToken());
      bw.write(Integer.toString(arr[hi] - arr[lo-1]));
      bw.newLine();
    }
    bw.flush();
  }  
}
