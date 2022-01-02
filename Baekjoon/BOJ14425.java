import java.io.*;
import java.util.*;

public class BOJ14425 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    HashSet<String> hs = new HashSet<>();
    for (int i = 0; i < N; i++) hs.add(br.readLine());
    int num = 0;
    for (int i = 0; i < M; i++) if (hs.contains(br.readLine())) num++;
    bw.write(num + "\n");
    bw.flush();
  }
}
