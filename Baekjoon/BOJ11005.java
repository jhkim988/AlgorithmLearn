import java.io.*;
import java.util.*;

public class BOJ11005 {
  public static void main(String[] args) throws IOException {
    StringBuilder sb = new StringBuilder();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int B = Integer.parseInt(st.nextToken());
    Stack<Character> stk = new Stack<>();
    while (N != 0) {
      int remainder = N % B;
      if (remainder < 10) {
        stk.push((char) ('0' + remainder));
      } else {
        stk.push((char) ('A' + remainder - 10));
      }
      N /= B;
    }

    while (!stk.isEmpty()) {
      sb.append(stk.pop());
    }
    sb.append('\n');
    bw.write(sb.toString());
    bw.flush();
  }
}
