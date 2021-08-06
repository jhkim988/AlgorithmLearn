import java.io.*;
import java.util.*;

public class BOJ1764 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    TreeSet<String> hear = new TreeSet<>();
    TreeSet<String> look = new TreeSet<>();

    StringTokenizer st = new StringTokenizer(br.readLine());
    int numHear = Integer.parseInt(st.nextToken());
    int numLook = Integer.parseInt(st.nextToken());

    while (numHear-- > 0) {
      hear.add(br.readLine());
    }
    while (numLook-- > 0) {
      look.add(br.readLine());
    }

    hear.retainAll(look);

    bw.write(hear.size() + "\n");
    for (String p : hear) {
      bw.write(p + "\n");
    }
    bw.flush();
  }
}
