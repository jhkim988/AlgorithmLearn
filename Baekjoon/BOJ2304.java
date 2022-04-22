import java.io.*;
import java.util.*;

public class BOJ2304 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] height = new int[1_001];
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int p = Integer.parseInt(st.nextToken());
      int h = Integer.parseInt(st.nextToken());
      height[p] = h;
    }

    int maxIdx = 0;
    for (int i = 0; i < height.length; i++) {
      if (height[maxIdx] < height[i]) maxIdx = i;
    }
    int area = 0;
    int prev = 0;
    for (int i = 0; i <= maxIdx; i++) {
      if (height[i] < prev) {
        area += prev;
      } else {
        area += height[i];
        prev = height[i];
      }
    }
    prev = height[1_000];
    for (int i = 1_000; i > maxIdx; i--) {
      if (height[i] < prev) {
        area += prev;
      } else {
        area += height[i];
        prev = height[i];
      }
    }
    bw.write(Integer.toString(area));
    bw.flush();
  }
}
