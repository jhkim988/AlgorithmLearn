import java.io.*;
import java.util.*;

public class BOJ16978 {
  private static class Query_Change {
    int id, index, value;
    Query_Change(int id, int index, int value) {
      this.id = id;
      this.index = index;
      this.value = value;
    }
  }
  private static class Query_GetSum {
    int id, reqId, left, right;
    Query_GetSum(int id, int reqId, int left, int right) {
      this.id = id;
      this.reqId = reqId;
      this.left = left;
      this.right = right;
    }
  }
  private static class SegTree {
    int treeSize;
    int[] arr;
    long[] tree;
    SegTree(int[] arr) {
      this.arr = arr;
      treeSize = 1;
      while (treeSize < arr.length) treeSize <<= 1;
      treeSize <<= 1;
      tree = new long[treeSize];
      init(1, 0, arr.length-1);
    }
    void init(int node, int start, int end) {
      if (start == end) {
        tree[node] = arr[start];
        return;
      }
      int mid = (start + end) >> 1;
      init(node<<1, start, mid);
      init(node<<1|1, mid+1, end);
      tree[node] = tree[node<<1] + tree[node<<1|1];
    }
    void update(int node, int start, int end, int idx, int val) {
      if (start > idx || end < idx) return;
      if (start == end) {
        tree[node] = arr[idx] = val;
        return;
      }
      int mid = (start + end) >> 1;
      update(node<<1, start, mid, idx, val);
      update(node<<1|1, mid+1, end, idx, val);
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
    int[] arr = new int[n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    
    PriorityQueue<Query_Change> pq1 = new PriorityQueue<>((a, b) -> a.id - b.id);
    PriorityQueue<Query_GetSum> pq2 = new PriorityQueue<>((a, b) -> a.reqId - b.reqId);
    int q = Integer.parseInt(br.readLine());
    int idChange = 1;
    int idGetSum = 0;
    while (q-- > 0) {
      st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      if (type == 1) {
        int index = Integer.parseInt(st.nextToken()) - 1;
        int value = Integer.parseInt(st.nextToken());
        pq1.add(new Query_Change(idChange++, index, value));
      } else {
        int reqId = Integer.parseInt(st.nextToken());
        int left = Integer.parseInt(st.nextToken()) - 1;
        int right = Integer.parseInt(st.nextToken()) - 1;
        pq2.add(new Query_GetSum(idGetSum++, reqId, left, right));
      }
    }
    long[] answer = new long[idGetSum];
    SegTree sg = new SegTree(arr);
    while (!pq1.isEmpty() && !pq2.isEmpty()) {
      int reqId = pq2.peek().reqId;
      while (!pq1.isEmpty() && pq1.peek().id <= reqId) {
        Query_Change query = pq1.poll();
        sg.update(1, 0, n-1, query.index, query.value);
      }
      Query_GetSum query = pq2.poll();
      answer[query.id] = sg.get(1, 0, n-1, query.left, query.right);
    }
    while (!pq2.isEmpty()) {
      Query_GetSum query = pq2.poll();
      answer[query.id] = sg.get(1, 0, n-1, query.left, query.right);
    }
    for (long a : answer) {
      bw.write(Long.toString(a));
      bw.newLine();
    }
    bw.flush();
  }
}
