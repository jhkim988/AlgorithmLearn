import java.io.*;
import java.util.*;

public class BOJ2567 {
  private static class Pair {
    int x, y;
    boolean isStart;
    Pair (int x, int y, boolean isStart) {
      this.x = x;
      this.y = y;
      this.isStart = isStart;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    ArrayList<Pair> arr = new ArrayList<>();
    ArrayList<Pair> vertical = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      arr.add(new Pair(x, y, true));
      arr.add(new Pair(x, y+10, false));
      vertical.add(new Pair(x, y, true));
      vertical.add(new Pair(x+10, y, false));
    }
    Collections.sort(arr, (a, b) -> {
      if (a.y != b.y) return a.y-b.y;
      if (a.isStart) return -1;
      if (b.isStart) return 1;
      return a.x-b.x;
    });
    Collections.sort(vertical, (a, b) -> {
      if (a.x != b.x) return a.x-b.x;
      if (a.isStart) return -1;
      if (b.isStart) return 1;
      return a.y-b.y;
    });

    int total = 0;
    int prev = 0;
    int[] count = new int[101];
    for (Pair p: arr) {
      int num = 0;
      for (int i = 0; i < 10; i++) {
        count[i+p.x] += p.isStart ? 1 : -1;
      }
      for (int i = 0; i <= 100; i++) {
        if (count[i] > 0) num++;
      }
      total += num > prev ? num - prev : prev - num;
      prev = num;
    }
    prev = 0;
    count = new int[101];
    for (Pair p: vertical) {
      int num = 0;
      for (int i = 0; i < 10; i++) {
        count[i+p.y] += p.isStart ? 1 : -1;
      }
      for (int i = 0; i <= 100; i++) {
        if (count[i] > 0) num++;
      }
      total += num > prev ? num - prev : prev - num;
      prev = num;
    }

    bw.write(Integer.toString(total));
    bw.flush();
  }
}