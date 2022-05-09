import java.io.*;
import java.util.*;

public class BOJ4358 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    TreeMap<String, Integer> tm = new TreeMap<>();
    String input;
    int num = 0;
    while ((input = br.readLine()) != null) {
      if (tm.containsKey(input)) {
        tm.put(input, tm.get(input)+1);
      } else {
        tm.put(input, 1);
      }
      num++;
    }
    for (Map.Entry<String, Integer> e : tm.entrySet()) {
      bw.write(e.getKey());
      bw.write(' ');
      bw.write(String.format("%.4f\n", (double) e.getValue() / num * 100));
    }
    bw.flush();
  }
}
