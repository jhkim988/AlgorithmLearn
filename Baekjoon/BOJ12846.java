import java.io.*;
import java.util.*;

public class BOJ12846 {
  private static class SegTree {
    int n, size;
    int[] arr, min;
    SegTree(int[] arr) {
      this.n = arr.length;
      this.arr = arr;
      this.size = 1;
      while (this.size < n) this.size <<= 1;
      this.size <<= 1;
      min = new int[this.size];
      init(1, 0, n-1);
    }
    void init(int node, int start, int end) {
      if (start == end) {
        min[node] = start;
        return;
      }
      int mid = (start + end)>>1;
      init(node<<1, start, mid);
      init(node<<1|1, mid+1, end);
      min[node] = Integer.min(min[node<<1], min[node<<1|1]);
      if (arr[min[node<<1]] < arr[min[node<<1|1]]) {
        min[node] = min[node<<1];
      } else {
        min[node] = min[node<<1|1];
      }
    }
    int min(int node, int start, int end, int left, int right) {
      if (start > right || end < left) return -1;
      if (left <= start && end <= right) return min[node];
      int mid = (start + end)>>1;
      int idx1 = min(node<<1, start, mid, left, right);
      int idx2 = min(node<<1|1, mid+1, end, left, right);
      if (idx1 == -1) {
        return idx2;
      } else if (idx2 == -1) {
        return idx1;
      } else {
        if (arr[idx1] < arr[idx2]) return idx1;
        else return idx2;
      }
    }
    int findMax(int left, int right) {
      int idx = min(1, 0, n-1, left, right);
      int area = arr[idx] * (right - left + 1);
      if (left <= idx - 1) {
        int leftMin = findMax(left, idx-1);
        if (area < leftMin) area = leftMin;
      }
      if (right >= idx + 1) {
        int rightMin = findMax(idx+1, right);
        if (area < rightMin) area = rightMin;
      }
      return area;
    }
    int findMax() {
      return findMax(0, n-1);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    SegTree sg = new SegTree(arr);
    int answer = sg.findMax();
    bw.write(Integer.toString(answer));
    bw.newLine();
    bw.flush();
  }  
}
