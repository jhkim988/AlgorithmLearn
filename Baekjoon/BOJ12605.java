import java.io.*;
import java.util.*;

public class BOJ12605 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    Stack<String> stk = new Stack<>();
    StringBuilder sb = new StringBuilder();
    int caseNum = 1;
    while (N-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      while (st.hasMoreTokens()) {
        stk.add(st.nextToken());
      }
      sb.append("Case #").append(caseNum++).append(':');
      while (!stk.isEmpty()) {
        sb.append(' ').append(stk.pop());
      }
      sb.append('\n');
    }
    bw.write(sb.toString());
    bw.flush();
  }
}
