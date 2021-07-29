import java.io.*;
import java.util.StringTokenizer;

public class BOJ6549 {
  public static void mySolution() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    StringTokenizer st;
    int num = -1;
    long[] data;

    while (true) {
      st = new StringTokenizer(br.readLine());
      num = Integer.parseInt(st.nextToken());
      if (num == 0) {
        break;
      }
      data = new long[num];
      for (int i = 0; i < num; i++) {
        data[i] = Long.parseLong(st.nextToken());
      }
      bw.write(maxSquare(data) + "\n");
    }
    bw.flush();
  }
  static long maxSquare(long[] data) {
    return recur(data, 0, data.length);
  }
  static long recur(long[] data, int start, int end) {
    // [start, end)
    if (end - start <= 1) {
      return data[start];
    }
    if (end - start == 2) {
      long max = Math.max(data[start], data[start + 1]);
      long min = Math.min(data[start], data[start + 1]);
      return Math.max(max, 2 * min);
    }
    int mid = (start + end) / 2;
    long left = recur(data, start, mid);
    long right = recur(data, mid, end);
    long result = Math.max(left, right);

    // merge:
    long midHeight = Math.min(data[mid - 1], data[mid]);
    int ptrLeft = mid - 1;
    int ptrRight = mid;
    long midRecArea = 2 * midHeight;
    while (ptrLeft > start || ptrRight < end - 1) {
      if (ptrRight < end - 1 && (ptrLeft == start || data[ptrLeft - 1] < data[ptrRight + 1])) {
        ptrRight++;
        midHeight = Math.min(midHeight, data[ptrRight]);
      } else {
        ptrLeft--;
        midHeight = Math.min(midHeight, data[ptrLeft]);
      }
      midRecArea = Math.max(midRecArea, midHeight * (ptrRight - ptrLeft + 1));
    }
    // System.out.printf("Merge: [%d, %d) [%d, %d)\n", start, mid, mid, end);
    // System.out.printf("left: %d, right: %d\n", left, right);
    // System.out.printf("ptrLeft : %d, ptrRight : %d\n", ptrLeft, ptrRight);
    // System.out.printf("midHeight: %d\n", midHeight);
    // System.out.printf("midRecArea: %d\n", midRecArea);
    return Math.max(midRecArea, result);
  }
// ------------------------------------------------------------------------------------------------------------------------------------------------------  
// Construct Segment Tree(Sum Version)  
static long init(long[] a, long[] tree, int node, int start, int end) {
    // sum version:
    if (start == end) {
      return tree[node] = a[start];
    } else {      
      return tree[node] = init(a, tree, 2 * node, start, (start + end) / 2) + init(a, tree, 2 * node + 1, (start + end) / 2 + 1, end);
    }
  }
  static long sum(long[] tree, int node, int start, int end, int left, int right) {
    // node interval [start, end]
    // sum interval [left, right]
    if (end < left || right < start) {
      return 0;
    } // intervals are disjoint
    if (left <= start && end <= right) {
      return tree[node];
    } // [start, end] is in [left, right]
    return sum(tree, node * 2, start, (start + end) / 2, left, right) + sum(tree, node * 2 + 1, (start + end) / 2, end, left, right);
  }
  static void update(long[] tree, int node, int start, int end, int index, long diff) {
    if (index < start || index > end) {
      return;
    }
    tree[node] = tree[node] + diff; // because Sum Value...
    if (start != end) { // If this node is not leaf, Update child nodes.
      update(tree, node * 2, start, (start + end) / 2, index, diff);
      update(tree, node * 2 + 1, (start + end) / 2, end, index, diff);
    }
  }
// ------------------------------------------------------------------------------------------------------------------------------------------------------  
  static void init(long[] a, int[] tree, int node, int start, int end) {
    // Minimum Index version:
    if (start == end) {
      tree[node] = start;
    } else {
      init(a, tree, node * 2, start, (start + end) / 2);
      init(a, tree, node * 2 + 1, (start + end) / 2, end);
      if (a[tree[node * 2]] < a[tree[node * 2 + 1]]) {
        tree[node] = tree[node * 2];
      } else {
        tree[node] = tree[node * 2 + 1];
      }
    }

}
  static int query(long[] a, int[] tree, int node, int start, int end, int i, int j) {
    // For interval [i, j], Find minimum value and its index
    if (start > j || end < i) {
      return -1;
    } 
    if (i <= start && end <= j) {
      return tree[node];
    }
    int m1 = query(a, tree, 2 * node, start, (start + end) / 2, i, j);
    int m2 = query(a, tree, 2 * node + 1, (start + end) / 2, end, i, j);
    if (m1 == -1) {
      return m2;
    } else if (m2 == -1) {
      return m1;
    } else {
      if (a[m1] <= a[m2]) {
        return m1;
      } else {
        return m2;
      }
    }
  }
  static long largest(long[] a, int[] tree, int start, int end) {
    // In [start, end], Find max area
    int n = a.length;
    int m = query(a, tree, 1, 0, n - 1, start, end);
    long area = (long) (end - start + 1) * a[m];
    if (start <= m - 1) {
      long tmp = largest(a, tree, start, m - 1);
      if (area < tmp) {
        area = tmp;
      }
    }
    if (m + 1 <= end) {
      long tmp = largest(a, tree, m + 1, end);
      if (area < tmp) {
        area = tmp;
      }
    }
    return area;
  }
  static void segmentTree() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    StringTokenizer st;
    int num;
    long[] data;

    while (true) {
      st = new StringTokenizer(br.readLine());
      num = Integer.parseInt(st.nextToken());
      if (num == 0) {
        break;
      }
      data = new long[num];
      for (int i = 0; i < num; i++) {
        data[i] = Long.parseLong(st.nextToken());
      }
      int treeHeight = (int) (Math.ceil(Math.log(num)/Math.log(2)) + 1e-9);
      int treeSize = 1 << (treeHeight + 1); // 2^height
      int[] tree = new int[treeSize];
      init(data, tree, 1, 0, num - 1);
      bw.write(largest(data, tree, 0, num - 1) + "\n");
    }
    bw.flush();
  }
// ------------------------------------------------------------------------------------------------------------------------------------------------------  
  static void stack() throws IOException {

  }
  public static void main(String[] args) throws IOException {
    segmentTree();
  }  
}
