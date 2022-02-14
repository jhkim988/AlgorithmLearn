import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class BOJ17131 {
  static final long divisor = 1_000_000_007;
  private static class Pair {
    int x, y;
    Pair(int x, int y) {
      this.x = x;
      this.y = y;
    }
    public String toString() {
      return "(" + x + ", " + y + ")";
    }
  }
  private static class SegTree {
    int treeSize;
    long[] tree;
    SegTree(int n) {
      treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      tree = new long[treeSize];
    }
    void update(int node, int start, int end, int idx, int add) {
      if (start > idx || end < idx) return;
      if (start == end) {
        tree[node] += add;
        return;
      }
      int mid = (start + end) >> 1;
      update(node<<1, start, mid, idx, add);
      update(node<<1|1, mid+1, end, idx, add);
      tree[node] = tree[node<<1] + tree[node<<1|1];
    }
    long get(int node, int start, int end, int left, int right) {
      if (start > right || end < left) return 0;
      if (left <= start && end <= right) return tree[node];
      int mid = (start + end) >> 1;
      return get(node<<1, start, mid, left, right) + get(node<<1|1, mid+1, end, left, right);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    ArrayList<Pair> stars = new ArrayList<>(n);
    int[] xcordi = new int[n];
    int[] ycordi = new int[n];
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      xcordi[i] = x;
      ycordi[i] = y;
      stars.add(new Pair(x, y));
    }
    long answer = useCoordinateCompression(xcordi, ycordi, stars);
    bw.write(Long.toString(answer));
    bw.newLine();
    bw.flush();
  }
  static long useCoordinateCompression(int[] xcordi, int[] ycordi, ArrayList<Pair> stars) {
    int[] compressionX = IntStream.of(xcordi).sorted().distinct().toArray();
    int[] compressionY = IntStream.of(ycordi).sorted().distinct().toArray();
    Collections.sort(stars, (a, b) -> a.y == b.y ? a.x-b.x : b.y-a.y);
    Queue<Pair> que = new LinkedList<>();
    int len = compressionX.length;
    SegTree sg = new SegTree(len);
    int ptr = 0;
    long count = 0;
    for (int y = compressionY.length-1; y >= 0; y--) {
      while (!que.isEmpty()) {
        Pair star = que.poll();
        sg.update(1, 0, len-1, getCompressionCordi(star.x, compressionX), 1);
      }
      while (ptr < stars.size() && stars.get(ptr).y == compressionY[y]) {
        Pair star = stars.get(ptr++);
        int idx = getCompressionCordi(star.x, compressionX);
        long add = (sg.get(1, 0, len-1, 0, idx-1) * sg.get(1, 0, len-1, idx+1, len-1)) % divisor;
        count = (count + add) % divisor;
        que.add(star);
      }
    }
    return count;
  }
  static int getCompressionCordi(int val, int[] arr) {
    int lo = -1, hi = arr.length;
    while (lo + 1 < hi) {
      int mid = (lo + hi) >> 1;
      if (arr[mid] < val) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    return hi;
  }
}
