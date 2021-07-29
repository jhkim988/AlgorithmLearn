import java.io.*;
import java.util.StringTokenizer;

public class BOJ6549 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    StringTokenizer st;
    int num = -1;
    long[] data;

    while (true) {
      st = new StringTokenizer(br.readLine());
      num = Integer.parseInt(st.nextToken());
      if (num == 0) {
        break;
      }
      long maxHeight = 0;
      data = new long[num];
      for (int i = 0; i < num; i++) {
        data[i] = Long.parseLong(st.nextToken());
        maxHeight = data[i] > maxHeight ? data[i] : maxHeight;
      }
      bw.write(maxSquare(data, maxHeight) + "\n");
    }
    bw.flush();
  }
  static long maxSquare(long[] data) {

  }
  static long[] recur(long[] data, int start, int end) {
    // [value, left, right]
    if (end - start <= 1) {
      return new long[] {data[start], 1, 1};
    }
    if (end - start == 2) {
      if (data[start] < data[start + 1]) {
        if (2 * data[start] < data[start + 1]) {
          return new long[] {data[start + 1], 0, 1};
        } else {
          return new long[] {2 * data[start], 1, 1};
        }
      } else if (data[start] > data[start + 1]) {
        if (2 * data[start + 1] < data[start]) {
          return new long[] {data[start], 1, 0};
        } else {
          return new long[] {data[start], 1, 1};
        }
      } else {
        return new long[] {data[start] * 2, 1, 1};
      }
    }

    long[] left = recur(data, start, (start + end) / 2);
    long[] right = recur(data, (start + end) / 2, end);
    if (left[2] == 0)
  }
}
