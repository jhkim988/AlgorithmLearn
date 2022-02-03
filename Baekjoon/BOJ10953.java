import java.io.*;
import java.util.*;

public class BOJ10953 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine(), ",");
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      bw.write(Integer.toString(a + b));
      bw.newLine();
    }
    bw.flush();
  }
}