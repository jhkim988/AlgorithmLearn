import java.io.*;
import java.util.*;

public class BOJ19941 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    char[] arr = br.readLine().toCharArray();
    boolean[] visit = new boolean[n];
    int num = 0;
    for (int i = 0; i < n; i++) {
      if (arr[i] == 'H') continue;
      for (int j = i-k; j <= i+k; j++) {
        if (j < 0 || j >= n || arr[j] == 'P' || visit[j]) continue;
        visit[j] = true;
        num++;
        break;
      }
    }
    bw.write(Integer.toString(num));
    bw.flush();
  }
}
