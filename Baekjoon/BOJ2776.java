import java.io.*;
import java.util.*;

public class BOJ2776 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    HashSet<Integer> hs = new HashSet<>();
    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- > 0) {
      hs.clear();
      int n = Integer.parseInt(br.readLine());
      StringTokenizer st = new StringTokenizer(br.readLine());
      while (n-- > 0) {
        hs.add(Integer.parseInt(st.nextToken()));
      }
      int m = Integer.parseInt(br.readLine());
      st = new StringTokenizer(br.readLine());
      while (m-- > 0) {
        if (hs.contains(Integer.parseInt(st.nextToken()))) bw.write("1\n");
        else bw.write("0\n");
      }
    }
    bw.flush();
  }
}
