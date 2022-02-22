import java.io.*;
import java.util.*;

public class BOJ16347 {
  private static class Node {
    int address, memory;
    Node(int address, int memory) {
      this.address = address;
      this.memory = memory;
    }
  }
  private static class SegTree {
    int n = 100_000;
    int[] tree, maxL, maxR, free, malloc;
    SegTree() {
      int size = 1;
      while (size < n) size <<= 1;
      size <<= 1;
      tree = new int[size];
      maxL = new int[size];
      maxR = new int[size];
      free = new int[size]; // free[node] = 1: free child nodes.(not itself)
      malloc = new int[size];
      init(1, 0, n-1);
    }
    void update(int node, int start, int end) {
      if (start == end) return;
      int mid = (start + end) >> 1;
      maxL[node] = tree[node<<1] == (mid-start+1) ? tree[node<<1] + maxL[node<<1|1] : maxL[node<<1]; 
      maxR[node] = tree[node<<1|1] == (end - mid) ? tree[node<<1|1] + maxR[node<<1] : maxR[node<<1|1];
      tree[node] = Integer.max(Integer.max(tree[node<<1], tree[node<<1|1]), maxR[node<<1] + maxL[node<<1|1]);
    }
    void freeLazy(int node, int start, int end) {
      if (free[node] == 0) return;
      if (start != end) {
        int mid = (start + end) >> 1;
        maxL[node<<1] = maxR[node<<1] = tree[node<<1] = mid-start+1;
        maxL[node<<1|1] = maxR[node<<1|1] = tree[node<<1|1] = end-mid;
        free[node<<1] = free[node<<1|1] = 1;
      }
      free[node] = 0;
    }
    void mallocLazy(int node, int start, int end) {
      if (malloc[node] == 0) return;
      
      malloc[node] = 0;
    }
    void init(int node, int start, int end) {
      if (start == end) {
        maxL[node] = maxR[node] = tree[node] = 1;
        return;
      }
      int mid = (start + end) >> 1;
      init(node<<1, start, mid);
      init(node<<1|1, mid+1, end);
      maxL[node] = maxR[node] = tree[node] = tree[node<<1] + tree[node<<1|1];
    }
    int malloc(int memory) {
      return malloc(1, 0, n-1, memory);
    }
    int malloc(int node, int start, int end, int memory) {
      freeLazy(node, start, end);
      mallocLazy(node, start, end);
      if (tree[node] < memory) return -1;
      if (start == end) {
        maxL[node] = maxR[node] = tree[node] = 0;
        return start;
      }
      int mid = (start + end) >> 1;
      int address = -1;
      if (tree[node<<1] >= memory) { // left
        address = malloc(node<<1, start, mid, memory); 
      } else if (maxR[node<<1] + maxL[node<<1|1] >= memory) { // merge
        malloc[node<<1] = maxR[node<<1];
        malloc[node<<1|1] = memory-maxR[node<<1];
        address = mid - maxR[node<<1] + 1;
      } else if (tree[node<<1|1] >= memory) { // right
        address = malloc(node<<1|1, mid+1, end, memory);
      }
      update(node, start, end);
      return address;
    }
    void free(int idx, int memory) {
      int left = idx;
      int right = idx + memory - 1;
      free(1, 0, n-1, left, right);
    }
    void free(int node, int start, int end, int left, int right) {
      freeLazy(node, start, end);
      if (start > right || end < left) return;
      if (left <= start && end <= right) {
        maxL[node] = maxR[node] = tree[node] = end-start+1;
        free[node] = 1;
        return;
      }
      int mid = (start + end) >> 1;
      free(node<<1, start, mid, left, right);
      free(node<<1|1, mid+1, end, left, right);
      update(node, start, end);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    SegTree sg = new SegTree();
    HashMap<String, Node> store = new HashMap<>();
    while (n-- > 0) {
      String command = br.readLine();
      int len = command.length();
      if (command.charAt(4) == '=') { // variable assign
        String varName = command.substring(0, 4);
        int memory = Integer.parseInt(command.substring(12, len-2));
        int address = sg.malloc(memory);
        if (address < 0) {
          if (store.containsKey(varName)) {
            store.remove(varName);
          }
          continue;
        }
        Node node = new Node(address, memory);
        store.put(varName, node);
      } else {
        if (command.charAt(0) == 'p') { // print
          String varName = command.substring(6, len-2);
          if (store.containsKey(varName)) {
            Node node = store.get(varName);
            bw.write(Integer.toString(node.address+1));
          } else {
            bw.write('0');
          }
          bw.newLine();
        } else { // free
          String varName = command.substring(5, len-2);
          if (store.containsKey(varName)) {
            Node node = store.get(varName);
            sg.free(node.address, node.memory);  
            store.remove(varName);
          } 
        }
      }
    }
    bw.flush();
  }
}