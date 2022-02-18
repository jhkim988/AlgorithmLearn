import java.io.*;
import java.util.*;
import java.util.stream.*;

public class BOJ10167 {
  private static class Point {
    long x, y, w;
    Point(long x, long y, long w) {
      this.x = x;
      this.y = y;
      this.w = w;
    }
  }
  private static class SegTree {
    int treeSize;
    long[] maxL, maxR, max, sum;
    SegTree(int n) {
      treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      maxL = new long[treeSize];
      maxR = new long[treeSize];
      max = new long[treeSize];
      sum = new long[treeSize];
    }
    void update(int node, int start, int end, long idx, long val) {
      if (start > idx || end < idx) return;
      if (start == end) {
        maxL[node] += val;
        maxR[node] += val;
        max[node] += val;
        sum[node] += val;
        return;
      }
      int mid = (start + end) >> 1;
      update(node<<1, start, mid, idx, val);
      update(node<<1|1, mid+1, end, idx, val);
      maxL[node] = Long.max(maxL[node<<1], sum[node<<1] + maxL[node<<1|1]);
      maxR[node] = Long.max(maxR[node<<1|1], sum[node<<1|1] + maxR[node<<1]);
      max[node] = Long.max(Long.max(max[node<<1], max[node<<1|1]), maxL[node<<1|1] + maxR[node<<1]);
      sum[node] = sum[node<<1] + sum[node<<1|1];
    }
    long get() {
      return max[1];
    }
    void clear() {
      Arrays.fill(maxL, 0);
      Arrays.fill(maxR, 0);
      Arrays.fill(max, 0);
      Arrays.fill(sum, 0);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    long[] xCordi = new long[n];
    long[] yCordi = new long[n];
    ArrayList<Point> goldMine = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      long x = Long.parseLong(st.nextToken());
      long y = Long.parseLong(st.nextToken());
      long w = Long.parseLong(st.nextToken());
      xCordi[i] = x;
      yCordi[i] = y;
      goldMine.add(new Point(x, y, w));
    }
    xCordi = LongStream.of(xCordi).sorted().distinct().toArray();
    yCordi = LongStream.of(yCordi).sorted().distinct().toArray();
    for (Point p : goldMine) {
      p.x = compress(p.x, xCordi);
      p.y = compress(p.y, yCordi);
    } 
    Collections.sort(goldMine, (a, b) -> {
      if (a.y < b.y) return -1;
      else if (a.y > b.y) return 1;
      else if (a.x < b.x) return -1;
      else if (a.x > b.x) return 1;
      else return 0;
    });
    int len = xCordi.length;
    long max = 0;
    SegTree sg = new SegTree(len);
    for (int i = 0; i < yCordi.length; i++) {
      int ptr = binSearch(i, goldMine);
      for (int j = i; j < yCordi.length; j++) {
        while (ptr < goldMine.size() && goldMine.get(ptr).y == j) {
          Point p = goldMine.get(ptr++);
          sg.update(1, 0, len-1, p.x, p.w);
        }
        max = Long.max(max, sg.get());
      }
      sg.clear();
    }
    bw.write(Long.toString(max));
    bw.newLine();
    bw.flush();
  }
  static int compress(long origin, long[] compress) {
    int lo = -1, hi = compress.length;
    while (lo + 1 < hi) {
      int mid = (lo + hi) >> 1;
      if (compress[mid] < origin) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    return hi;
  }
  static int binSearch(long y, ArrayList<Point> goldMine) {
    int lo = -1, hi = goldMine.size();
    while (lo + 1 < hi) {
      int mid = (lo + hi) >> 1;
      if (goldMine.get(mid).y < y) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    return hi;
  }
}
