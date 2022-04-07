import java.io.*;
import java.util.*;

public class BOJ1269 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    HashSet<Integer> a = new HashSet<>();
    HashSet<Integer> b = new HashSet<>();
    HashSet<Integer> d = new HashSet<>();
    StringTokenizer st = new StringTokenizer(br.readLine());
    int na = Integer.parseInt(st.nextToken());
    int nb = Integer.parseInt(st.nextToken());
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < na; i++) {
      a.add(Integer.parseInt(st.nextToken()));
    }
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < nb; i++) {
      b.add(Integer.parseInt(st.nextToken()));
    }
    for (int el : a) {
      if (!b.contains(el)) d.add(el);
    }
    for (int el : b) {
      if (!a.contains(el)) d.add(el);
    }
    bw.write(Integer.toString(d.size()));
    bw.newLine();
    bw.flush();
  }
}
