import java.io.*;
import java.util.*;

public class BOJ1120 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    char[] p = st.nextToken().toCharArray();
    char[] t = st.nextToken().toCharArray();
    int min = t.length;
    for (int start = 0; start + p.length <= t.length; start++) {
      int count = 0;
      for (int i = 0; i < p.length; i++) {
        if (p[i] != t[start + i]) count++;
      }
      min = Integer.min(min, count);
    }
    bw.write(Integer.toString(min));
    bw.newLine();
    bw.flush();
  }
}
