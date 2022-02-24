import java.io.*;
import java.util.*;

public class BOJ1057 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int a = Integer.parseInt(st.nextToken());
    int b = Integer.parseInt(st.nextToken());
    if (b < a) {
      int tmp = a;
      a = b;
      b = tmp;
    }
    int round = 1;
    for (int jump = 2; jump <= (n<<1); jump <<= 1) {
      for (int start = 1; start <= n; start += jump) {
        if (start <= a && b < start + jump) {
          bw.write(Integer.toString(round));
          bw.newLine();
          bw.flush();
          return;
        }
      }      
      round++;
    }
    bw.write("0\n");
    bw.flush();
  }
}
