import java.io.*;
import java.util.*;

public class BOJ9093 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      StringBuilder sb = new StringBuilder();
      Stack<Character> stk = new Stack<>();

      while (st.hasMoreTokens()) {
        char[] tmp = st.nextToken().toCharArray();
        for (int i = 0; i < tmp.length; i++) {
          stk.push(tmp[i]);
        }
        while (!stk.isEmpty()) {
          sb.append(stk.pop());
        }
        sb.append(' ');
      }
      sb.append('\n');
      bw.write(sb.toString());
    }
    bw.flush();
  }
}
