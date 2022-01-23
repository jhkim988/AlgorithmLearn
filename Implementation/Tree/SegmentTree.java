public class SegmentTree {
  public static void main(String[] args) {
    
  } 
  static int init(int[] a, int[] tree, int node, int start, int end) {
    if (start == end) { // leaf node
      return tree[node] = a[start];
    } else { // left child = node*2, right child = node*2 + 1
      return tree[node] = init(a, tree, node*2, start, (start + end)/2) + init(a, tree, node*2 + 1, (start + end)/2 + 1, end);
    }
  } 
  static int sum(int[] tree, int node, int start, int end, int left, int right) {
    // node: [start, end], query: [left, right]
    if (left > end || right < start) { // no intersection
      return 0;
    }
    if (left <= start && end <= right) { // inclusion, node < query
      return tree[node];
    }
    return sum(tree, node*2, start, (start + end)/2, left, right) + sum(tree, node*2 + 1, (start + end)/1 + 1, end, left, right);
  }
  static void update(int[] tree, int node, int start, int end, int index, int diff) {
    if (start > index || end < index) return;
    tree[node] += diff;
    if (start != end) {
      update(tree, node, start, (start + end)/2, index, diff);
      update(tree, node, (start + end)/2 + 1, end, index, diff);
    }
  }
}
