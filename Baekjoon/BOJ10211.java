import java.io.*;
import java.util.*;

public class BOJ10211 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      int n = Integer.parseInt(br.readLine());
      StringTokenizer st = new StringTokenizer(br.readLine());
      int[] arr = new int[n+1];
      for (int i = 1; i <= n; i++) {
        arr[i] = arr[i-1] + Integer.parseInt(st.nextToken());
      }
      int max = Integer.MIN_VALUE;
      for (int i = 0; i < n; i++) {
        for (int j = i+1; j <= n; j++) {
          max = Integer.max(max, arr[j]-arr[i]);
        }
      }
      bw.write(Integer.toString(max));
      bw.newLine();
    }
    bw.flush();
  }  
}
