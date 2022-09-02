import java.io.*;
import java.util.*;

public class BOJ25304 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int x = Integer.parseInt(br.readLine());
    int n = Integer.parseInt(br.readLine());
    int sum = 0;
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      sum += a * b;
    }

    if (sum == x) bw.write("Yes");
    else bw.write("No");
    bw.flush();
  }
}
