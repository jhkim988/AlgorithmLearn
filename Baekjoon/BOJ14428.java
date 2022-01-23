import java.io.*;
import java.util.*;

public class BOJ14428 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int len = Integer.parseInt(br.readLine());
    int[] arr = new int[len];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < len; i++) arr[i] = Integer.parseInt(st.nextToken());
    
    int height = 0;
    while ((1 << height) < len) height++;
    int treeSize = 1 << (height + 1);
    int[] tree = new int[treeSize];
    init(arr, tree, 1, 0, len - 1);
    
    int numQuery = Integer.parseInt(br.readLine());
    while (numQuery-- > 0) {
      st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      if (type == 1) { // update
        int index = Integer.parseInt(st.nextToken()) - 1;
        int value = Integer.parseInt(st.nextToken());
        arr[index] = value;
        update(arr, tree, 1, 0, len - 1, index);
      } else { // get Min index
        int lo = Integer.parseInt(st.nextToken()) - 1;
        int hi = Integer.parseInt(st.nextToken()) - 1;
        int index = getMin(arr, tree, 1, 0, len - 1, lo, hi);
        bw.write(Integer.toString(index + 1));
        bw.newLine();
      }
    }
    bw.flush();
  }
  static int init(int[] arr, int[] tree, int node, int start, int end) {
    if (start == end) return tree[node] = start; 
    int mid = (start + end) / 2;
    int leftChild = init(arr, tree, node*2, start, mid);
    int rightChild = init(arr, tree, node*2+1, mid+1, end);
    if (arr[leftChild] < arr[rightChild]) return tree[node] = leftChild;
    else if (arr[rightChild] < arr[leftChild]) return tree[node] = rightChild;
    else return tree[node] = Integer.min(leftChild, rightChild);
  }
  static int update(int[] arr, int[] tree, int node, int start, int end, int index) {
    if (start > index || end < index) return tree[node];
    if (start != end) {
      int mid = (start + end) / 2;
      int left = update(arr, tree, node*2, start, mid, index);
      int right = update(arr, tree, node*2+1, mid+1, end, index);
      if (arr[left] < arr[right]) return tree[node] = left;
      else if (arr[right] < arr[left]) return tree[node] = right;
      else return tree[node] = left < right ? left : right;
    } else {
      return tree[node];
    }
  }
  static int getMin(int[] arr, int[] tree, int node, int start, int end, int left, int right) {
    if (start > right || end < left) return -1;
    if (left <= start && end <= right) return tree[node];
    int mid = (start + end) / 2;
    int leftChild = getMin(arr, tree, node*2, start, mid, left, right);
    int rightChild = getMin(arr, tree, node*2+1, mid+1, end, left, right);
    if (leftChild == -1) {
      return rightChild;
    } else if (rightChild == -1) {
      return leftChild;
    } else {
      if (arr[leftChild] < arr[rightChild]) return leftChild;
      else if (arr[rightChild] < arr[leftChild]) return rightChild;
      else return leftChild < rightChild ? leftChild : rightChild;
    }
  }
}
