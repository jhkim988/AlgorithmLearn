import java.io.*;
import java.util.*;

public class BOJ16435 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int l = Integer.parseInt(st.nextToken());
    int[] arr = new int[n];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    Arrays.sort(arr);
    for (int i = 0; i < n; i++) {
      if (l < arr[i]) break;
      l++;
    }
    bw.write(Integer.toString(l));
    bw.flush();
  }
}
