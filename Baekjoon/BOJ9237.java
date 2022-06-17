import java.io.*;
import java.util.*;

public class BOJ9237 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    Arrays.sort(arr);
    int max = 0;
    for (int i = arr.length-1; i >= 0; i--) {
      max = Integer.max(max, arr[i]+arr.length-i);
    }
    bw.write(Integer.toString(max+1));
    bw.flush();
  }
}
