import java.io.*;
import java.util.*;

public class BOJ10817 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] input = new int[3];
    for (int i = 0; i < 3; i++) {
      input[i] = Integer.parseInt(st.nextToken());
    }
    Arrays.sort(input);
    bw.write(input[1] + "\n");
    bw.flush();
  }
}
