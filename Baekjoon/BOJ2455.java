import java.io.*;
import java.util.*;

public class BOJ2455 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int val = 0;
    int max = 0;
    for (int i = 0; i < 4; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      val -= Integer.parseInt(st.nextToken());
      val += Integer.parseInt(st.nextToken());
      max = Integer.max(max, val);
    }
    bw.write(Integer.toString(max));
    bw.flush();
  }
}
