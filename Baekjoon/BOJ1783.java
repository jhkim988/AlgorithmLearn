import java.io.*;
import java.util.*;

public class BOJ1783 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    
    if (N == 1) {
      bw.write("1\n");
    } else if (N == 2) {
      int val = (M - 1) / 2 + 1;
      bw.write(Math.min(val, 4) + "\n");
    } else {
      if (M > 6) {
        bw.write((M - 2) + "\n");
      } else if (M > 4) {
        bw.write("4\n");
      } else {
        bw.write(M + "\n");
      }
    }
    bw.flush();
  }
}
