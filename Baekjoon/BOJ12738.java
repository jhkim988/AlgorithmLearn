import java.io.*;
import java.util.*;

public class BOJ12738 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int len = Integer.parseInt(br.readLine());
    int[] data = new int[len];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < len; i++) {
      data[i] = Integer.parseInt(st.nextToken());
    }

    int[] sorted = new int[len];
    sorted[0] = data[0];
    int LISlen = 1;
    for (int i = 1; i < len; i++) {
      int idx = Arrays.binarySearch(sorted, 0, LISlen, data[i]);
      if (idx > -1) {
        // do nothing
      } else {
        idx = -(idx + 1);
        sorted[idx] = data[i];
        if (idx == LISlen) {
          LISlen++;
        }
      }
    }
    bw.write(LISlen + "\n");
    bw.flush();
  }
}
