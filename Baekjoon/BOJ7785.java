import java.io.*;
import java.util.*;

public class BOJ7785 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    HashSet<String> hs = new HashSet<>();
    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      String name = st.nextToken();
      String record = st.nextToken();
      if (record.charAt(0) == 'e') {
        hs.add(name);
      } else {
        hs.remove(name);
      }
    }
    String[] list = new String[hs.size()];
    Iterator<String> iter = hs.iterator();
    for (int i = 0; i < list.length; i++) {
      list[i] = iter.next();
    }
    Arrays.sort(list, Collections.reverseOrder());
    for (int i = 0; i < list.length; i++) {
      bw.write(list[i]);
      bw.newLine();
    }
    bw.flush();
  }
}
