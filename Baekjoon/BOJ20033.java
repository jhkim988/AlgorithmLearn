import java.io.*;
import java.util.*;

public class BOJ20033 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    int[] height = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      height[i] = Integer.parseInt(st.nextToken());
    }

    int lo = 0;
    int hi = N + 1;
    while (lo + 1 < hi) {
      int mid = (lo + hi) / 2;
      if (check(mid, height)) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    bw.write(Integer.toString(lo));
    bw.newLine();
    bw.flush();
  } 
  static boolean check(int size, int[] height) {
    int count = 0;
    for (int i = 0; i < height.length; i++) {
      if (height[i] < size) {
        count = 0;
      } else {
        count++;
      }
      if (count >= size) return true;
    }
    return false;
  }
}
