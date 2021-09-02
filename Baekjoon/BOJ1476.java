import java.io.*;
import java.util.*;

public class BOJ1476 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int E = Integer.parseInt(st.nextToken());
    int S = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    final int limitE = 15;
    final int limitS = 28;
    final int limitM = 19;

    int ptr = 1;
    while (!(((ptr - E) % limitE == 0) && ((ptr - S) % limitS == 0) && ((ptr - M) % limitM == 0))) {
      ptr++;
    }
    bw.write(ptr + "\n");
    bw.flush();
  }
}
