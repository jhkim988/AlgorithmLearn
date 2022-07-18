import java.io.*;
import java.util.*;

public class BOJ2436 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int g = Integer.parseInt(st.nextToken());
    int l = Integer.parseInt(st.nextToken());
    int x = l/g;
    int rx = (int) Math.sqrt(x);
    for (int i = rx; i > 0; i--) {
      if (x % rx != 0) continue;
    }
  }
}
