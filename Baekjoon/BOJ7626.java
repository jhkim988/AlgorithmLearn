import java.io.*;
import java.util.*;
import java.util.stream.*;

public class BOJ7626 {
  private static class Line {
    int x1, x2, y;
    boolean isStart;
    Line(int x1, int x2, int y, boolean isStart) {
      this.x1 = x1;
      this.x2 = x2;
      this.y = y;
      this.isStart = isStart;
    }
    public String toString() {
      return "[" + x1 + ":" + x2 + "]/" + y + "/" + isStart;
    }
  }
  private static class SegTree {
    int n, treeSize;
    int[] compress;
    long[] count, sum;
    SegTree(int[] compress) {
      this.compress = compress;
      n = compress.length-1;
      treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      count = new long[treeSize];
      sum = new long[treeSize];
    }
    void update(int node, int start, int end, int left, int right, boolean isStart) {
      if (start > right || end < left) return;
      if (left <= start && end <= right) {
        if (isStart) count[node]++;
        else count[node]--;
      } else {
        int mid = (start + end) >> 1;
        update(node<<1, start, mid, left, right, isStart);
        update(node<<1|1, mid+1, end, left, right, isStart);
      } 
      if (count[node] > 0) {
        sum[node] = compress[end+1] - compress[start];
      } else {
        if (start == end) sum[node] = 0;
        else sum[node] = sum[node<<1] + sum[node<<1|1];
      }
    }
    long get(int node, int start, int end, int left, int right) {
      if (start > right || end < left) return 0;
      if (left <= start && end <= end) return sum[node];
      int mid = (start + end) >> 1;
      return get(node<<1, start, mid, left, right) + get(node<<1|1, mid+1, end, left, right);
    }
    long get() {
      return get(1, 0, n-1, 0, n-1);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] xcordi = new int[n<<1];
    int[] ycordi = new int[n<<1];
    ArrayList<Line> line = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int x1 = Integer.parseInt(st.nextToken());
      int x2 = Integer.parseInt(st.nextToken());
      int y1 = Integer.parseInt(st.nextToken());
      int y2 = Integer.parseInt(st.nextToken());
      xcordi[i<<1] = x1;
      xcordi[i<<1|1] = x2;
      ycordi[i<<1] = y1;
      ycordi[i<<1|1] = y2;
      line.add(new Line(x1, x2, y1, true));
      line.add(new Line(x1, x2, y2, false));
    }
    int[] compressX = IntStream.of(xcordi).sorted().distinct().toArray();
    int[] compressY = IntStream.of(ycordi).sorted().distinct().toArray();
    Collections.sort(line, (a, b) -> a.y==b.y ? (a.x2==b.x2 ? a.x1-b.x1 : a.x2-b.x2) : a.y-b.y);
    int len = compressX.length;
    SegTree sg = new SegTree(compressX);
    int ptr = 0;
    long area = 0;
    for (int y = 0; y < compressY.length; y++) {
      while (ptr < line.size() && line.get(ptr).y == compressY[y]) {
        Line l = line.get(ptr);
        System.out.println(l);
        sg.update(1, 0, len-1, l.x1, l.x2-1, l.isStart);
        ptr++;
      }
      System.out.println("add: " + sg.get());
      area += sg.get() * (compressY[y]);
    }
    bw.write(Long.toString(area));
    bw.newLine();
    bw.flush();
  }
}
