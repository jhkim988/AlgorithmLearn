import java.io.*;
import java.util.*;

public class BOJ2828 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    int j = Integer.parseInt(br.readLine());
    int lo = 1, hi = m;
    int move = 0;
    for (int i = 0; i < j; i++) {
      int down = Integer.parseInt(br.readLine());
      if (hi < down) {
        move += down-hi;
        lo += down-hi;
        hi = down;
      } else if (down < lo) {
        move += lo-down;
        hi -= lo-down;
        lo = down;
      }
    }
    bw.write(Integer.toString(move));
    bw.flush();
  }
}