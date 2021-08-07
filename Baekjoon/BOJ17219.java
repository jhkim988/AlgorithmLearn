import java.io.*;
import java.util.*;

public class BOJ17219 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    HashMap<String, String> hs = new HashMap<>();
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      hs.put(st.nextToken(), st.nextToken());
    }
    for (int i = 0; i < M; i++) {
      bw.write(hs.get(br.readLine()) + "\n");
    }
    bw.flush();
  }
}
