import java.io.*;
import java.util.*;

public class BOJ23077 {
  private static class Pair {
    int happy, day;
    Pair(int happy, int day) {
      this.happy = happy;
      this.day = day;
    }
  }
  private static class SegTree {
    int n, treeSize;
    int[] count;
    long[] sum;
    SegTree(int n) {
      this.n = n;
      treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      count = new int[treeSize];
      sum = new long[treeSize];
    }
    void update(int node, int start, int end, int idx, int value) {
      if (start > idx || end < idx) return;
      if (start == end) {
        count[node] += value > 0 ? 1 : -1;
        sum[node] += value;
        return;
      }
      int mid = (start + end) >> 1;
      update(node<<1, start, mid, idx, value);
      update(node<<1|1, mid+1, end, idx, value);
      count[node] = count[node<<1] + count[node<<1|1];
      sum[node] = sum[node<<1] + sum[node<<1|1];
    }
    long get(int node, int start, int end, int left, int right) {
      if (start > right || end < left) return 0;
      if (left <= start && end <= right) return sum[node];
      int mid = (start + end) >> 1;
      return get(node<<1, start, mid, left, right) + get(node<<1|1, mid+1, end, left, right);
    }
    int getCount(int node, int start, int end, int left, int right) {
      if (start > right || end < left) return 0;
      if (left <= start && end <= right) return count[node];
      int mid = (start + end) >> 1;
      return getCount(node<<1, start, mid, left, right) + getCount(node<<1|1, mid+1, end, left, right);
    }
    int find(int node, int start, int end, int k) {
      if (start == end) return start;
      int mid = (start + end) >> 1;
      if (count[node<<1|1] < k) {
        return find(node<<1, start, mid, k - count[node<<1|1]);
      } else {
        return find(node<<1|1, mid+1, end, k);
      }
    }
    void update(int value) {
      int idx = value > 0 ? value : -value;
      update(1, 0, n-1, idx, value);
    }
    long get(int k) {
      int idx = find(1, 0, n-1, k);
      int countAfterIdx = getCount(1, 0, n-1, idx+1, n-1);
      return get(1, 0, n-1, idx+1, n-1) + (k-countAfterIdx) * idx;
    }
    void clear() {
      Arrays.fill(count, 0);
      Arrays.fill(sum, 0);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numTest = Integer.parseInt(br.readLine());
    for (int testId = 1; testId <= numTest; testId++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int d = Integer.parseInt(st.nextToken());
      int n = Integer.parseInt(st.nextToken());
      int k = Integer.parseInt(st.nextToken());
      ArrayList<Pair> attraction = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        attraction.add(new Pair(h, s));
        attraction.add(new Pair(-h, e));
      }
      Collections.sort(attraction, (a, b) -> a.day!=b.day ? a.day-b.day : b.happy-a.happy);
      
      // sweeping:
      long max = 0;
      SegTree sg = new SegTree(300_001);
      for (Pair p : attraction) {
        sg.update(p.happy);
        long sum = sg.get(k);
        max = Long.max(max, sum);
      }
      bw.write("Case #");
      bw.write(Integer.toString(testId));
      bw.write(": ");
      bw.write(Long.toString(max));
      bw.newLine();
      sg = null;
      attraction = null;
      System.gc();
    }
    bw.flush();
  }
}
