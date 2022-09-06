import java.io.*;
import java.util.*;

public class BOJ13164 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    
    st = new StringTokenizer(br.readLine());
    int[] arr = new int[n-1];
    int prev = Integer.parseInt(st.nextToken());
    int start = prev;
    for (int i = 0; i < n-1; i++) {
      int input = Integer.parseInt(st.nextToken());
      arr[i] = input - prev;
      prev = input;
    }

    Arrays.sort(arr);
    long sum = prev-start;
    for (int i = 1; i < k; i++) {
      sum -= arr[n-1-i];
    }

    bw.write(Long.toString(sum));
    bw.flush();
  }
}
