import java.io.*;
import java.util.*;

public class BOJ14720 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    int num = 0;
    int x = 0;
    for (int i = 0; i < n; i++) {
      if (arr[i] != x) continue;
      num++;
      x++;
      x %= 3;
    }
    bw.write(Integer.toString(num));
    bw.flush();
  }
}
