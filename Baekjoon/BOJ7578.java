import java.io.*;
import java.util.*;

public class BOJ7578 {
  private static class SegmentTree {
    int treeSize;
    long[] tree;
    SegmentTree(int n) {
      treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      tree = new long[treeSize];
    }
    long update(int node, int start, int end, int idx) {
      if (start > idx || end < idx) return tree[node];
      if (start == end) return ++tree[node];
      int mid = (start + end) >> 1;
      return tree[node] = update(node*2, start, mid, idx) + update(node*2+1, mid+1, end, idx);
    }
    long get(int node, int start, int end, int left, int right) {
      if (start > right || end < left) return 0;
      if (left <= start && end <= right) return tree[node];
      int mid = (start + end) >> 1;
      return get(node*2, start, mid, left, right) + get(node*2+1, mid+1, end, left, right);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    HashMap<Integer, Integer> hm = new HashMap<>(); // <Value, Index>
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      hm.put(Integer.parseInt(st.nextToken()), i);
    }
    int[] arr = new int[n];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr[i] = hm.get(Integer.parseInt(st.nextToken()));
    }

    long answer = useSegmentTree(arr);
    bw.write(Long.toString(answer));
    bw.newLine();
    bw.flush();
  } 
  static long useSegmentTree(int[] arr) {
    long count = 0;
    SegmentTree sg = new SegmentTree(arr.length);
    for (int i = 0; i < arr.length; i++) {
      count += sg.get(1, 0, arr.length - 1, arr[i] + 1, arr.length - 1);
      sg.update(1, 0, arr.length - 1, arr[i]);
    }
    return count;
  }
}
