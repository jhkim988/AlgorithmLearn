import java.io.*;
import java.util.*;
import java.util.stream.*;

public class BOJ16357 {
  private static class Rectangle {
    int y1, y2;
    Rectangle(int y1, int y2) {
      this.y1 = y1;
      this.y2 = y2;
    }
  }
  private static class Line {
    int y, id;
    boolean isStart;
    Line(int y, int id, boolean isStart) {
      this.y = y;
      this.id = id;
      this.isStart = isStart;
    }
  }
  private static class SegTree {
    int n;
    int[] tree, lazy;
    SegTree(int n) {
      this.n = n;
      int treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      tree = new int[treeSize];
      lazy = new int[treeSize];
    }
    void updateLazy(int node, int start, int end) {
      if (lazy[node] == 0) return;
      tree[node] += lazy[node];
      if (start != end) {
        lazy[node<<1] += lazy[node];
        lazy[node<<1|1] += lazy[node];
      }
      lazy[node] = 0;
    }
    void update(int node, int start, int end, int left, int right, int val) {
      updateLazy(node, start, end);
      if (right < start || end < left) return;
      if (left <= start && end <= right) {
        tree[node] += val;
        if (start != end) {
          lazy[node<<1] += val;
          lazy[node<<1|1] += val;
        }
        return;
      }
      int mid = (start+end)>>1;
      update(node<<1, start, mid, left, right, val);
      update(node<<1|1, mid+1, end, left, right, val);
      tree[node] = Integer.max(tree[node<<1], tree[node<<1|1]);
    }
    void update(int left, int right, int val) {
      update(1, 0, n-1, left, right, val);
    }
    int get(int node, int start, int end, int left, int right) {
      updateLazy(node, start, end);
      if (right < start || end < left) return 0;
      if (left <= start && end <= right) return tree[node];
      int mid = (start+end)>>1;
      return Integer.max(get(node<<1, start, mid, left, right), get(node<<1|1, mid+1, end, left, right));
    }
    int get() {
      return get(1, 0, n-1, 0, n-1);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] ycordi = new int[2*n];
    Rectangle[] rects = new Rectangle[n];
    Line[] lines = new Line[2*n];
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      Integer.parseInt(st.nextToken());
      int y1 = Integer.parseInt(st.nextToken());
      Integer.parseInt(st.nextToken());
      int y2 = Integer.parseInt(st.nextToken());
      rects[i] = new Rectangle(y1, y2);
      ycordi[i<<1] = y1;
      ycordi[i<<1|1] = y2;
      lines[i<<1] = new Line(y1, i, false);
      lines[i<<1|1] = new Line(y2, i, true);
    }

    int[] ycomp = IntStream.of(ycordi).sorted().distinct().toArray();
    for (int i = 0; i < n; i++) {
      rects[i].y1 = Arrays.binarySearch(ycomp, rects[i].y1);
      rects[i].y2 = Arrays.binarySearch(ycomp, rects[i].y2);
    }
    SegTree sg = new SegTree(ycomp.length);
    for (int i = 0; i < n; i++) {
      sg.update(rects[i].y2, rects[i].y1, 1);
    }

    Arrays.sort(lines, new Comparator<Line>() {
      @Override
      public int compare(Line l1, Line l2) {
        if (l1.y != l2.y) return l1.y-l2.y;
        if (l1.isStart == l2.isStart) return 0;
        if (l1.isStart) return -1;
        return 1;
      }
    });

    int max = sg.get();
    int crnt = 0;
    int ptr = 0;
    int yval = lines[ptr].y;
    while (ptr < lines.length) {
      while (ptr < lines.length && lines[ptr].y == yval && lines[ptr].isStart == true) {
        Rectangle r = rects[lines[ptr].id];
        sg.update(r.y2, r.y1, -1);
        ptr++;
        crnt++;
      }
      max = Integer.max(max, sg.get()+crnt);
      while (ptr < lines.length && lines[ptr].y == yval && lines[ptr].isStart == false) {
        Rectangle r = rects[lines[ptr].id];
        sg.update(r.y2, r.y1, 1);
        ptr++;
        crnt--;
      }
      max = Integer.max(max, sg.get()+crnt);
      if (ptr < lines.length) yval = lines[ptr].y;
    }
    bw.write(Integer.toString(max));
    bw.flush();
  }
}