import java.io.*;
import java.util.*;

public class BOJ1822 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    new StringTokenizer(br.readLine());
    HashSet<Integer> a = new HashSet<>();
    HashSet<Integer> b = new HashSet<>();
    input(new StringTokenizer(br.readLine()), a);
    input(new StringTokenizer(br.readLine()), b);
    
    ArrayList<Integer> intersection = new ArrayList<>();
    for (int val : a) {
      if (b.contains(val)) continue;
      intersection.add(val);
    }
    Collections.sort(intersection);
    bw.write(Integer.toString(intersection.size()));
    bw.newLine();
    for (int val : intersection) {
      bw.write(Integer.toString(val));
      bw.write(' ');
    }
    bw.flush();
  }
  static void input(StringTokenizer st, HashSet<Integer> hs) {
    while (st.hasMoreTokens()) {
      hs.add(Integer.parseInt(st.nextToken()));
    }
  }
}
