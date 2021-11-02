import java.io.*;
import java.util.*;

public class BOJ13397 {
  static final int negativeINF = Integer.MIN_VALUE / 2;
  static int N;
  static int M;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    st = new StringTokenizer(br.readLine());

    int[] data = new int[N];
    for (int i = 0; i < N; i++) {
      data[i] = Integer.parseInt(st.nextToken());
    }

    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < N; i++) {
      if (data[i] < min) min = data[i];
      if (max < data[i]) max = data[i];
    }

    // use binary search:
    int lo = 0;
    int hi = max - min;
    while (lo < hi) {
      int mid = (lo + hi) / 2;
      if (possible(data, mid)) {
        hi = mid;
      } else {
        lo = mid + 1;
      }
    }

    bw.write(hi + "\n");
    bw.flush();
  }

  static boolean possible(int[] data, int key) {
    int min = data[0];
    int max = data[0];
    int count = 1;
    for (int i = 0; i < data.length; i++) {
      if (data[i] < min) min = data[i];
      if (max < data[i]) max = data[i];
      if (max - min > key) {
        count++;
        max = min = data[i];
      }
    }
    return count <= M;
  }
}
