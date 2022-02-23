package Test;

import java.io.*;
import java.util.*;

public class Test {
  private static class Node {
    int address, memory;
    Node(int address, int memory) {
      this.address = address;
      this.memory = memory;
    }
  }
  private static class SegTree {
    int n = 100_000;
    int[] arr, tree, count;
    SegTree() {
      int size = 1;
      while (size < n) size <<= 1;
      size <<= 1;
      arr = new int[n];
      tree = new int[size];
      count = new int[size];
      update(1, 0, n-1, 0, n);
    }
    void update(int node, int start, int end, int idx, int val) {
      if (start > idx || end < idx) return;
      if (start == end) {
        tree[node] = arr[idx] = val;
        count[node] = val == 0 ? 0 : 1;
        return;
      }
      int mid = (start + end) >> 1;
      update(node<<1, start, mid, idx, val);
      update(node<<1|1, mid+1, end, idx, val);
      tree[node] = Integer.max(tree[node<<1], tree[node<<1|1]);
      count[node] = count[node<<1] + count[node<<1|1];
    }
    int getCount(int node, int start, int end, int left, int right) {
      if (start > right || end < left) return 0;
      if (left <= start && end <= right) return count[node];
      int mid = (start + end) >> 1;
      return getCount(node<<1, start, mid, left, right) + getCount(node<<1|1, mid+1, end, left, right);
    }
    int find(int k) {
      return find(1, 0, n-1, k);
    }
    int find(int node, int start, int end, int k) {
      if (tree[node] < k) return -1;
      if (start == end) return start;
      int mid = (start + end) >> 1;
      if (tree[node<<1] >= k) {
        return find(node<<1, start, mid, k);
      } else {
        return find(node<<1|1, mid+1, end, k);
      } 
    }
    int findForward(int node, int start, int end, int k) {
      if (count[node] < k) return -1;
      if (start == end) return start;
      int mid = (start + end) >> 1;
      if (count[node<<1] >= k) {
        return findForward(node<<1, start, mid, k);
      } else {
        return findForward(node<<1|1, mid+1, end, k-count[node<<1]);
      }
    }
    int malloc(int memory) {
      int idx = find(memory);
      if (idx < 0 || idx+memory > n) return -1;
      int len = arr[idx];
      update(1, 0, n-1, idx, 0);
      if (len > memory)
        update(1, 0, n-1, idx+memory, len-memory);
      return idx;
    }
    void free(int idx, int memory) {
      update(1, 0, n-1, idx, memory);
      if (idx + arr[idx] < n && arr[idx + arr[idx]] != 0) {
        merge(idx, idx+arr[idx]);
      }
      int k = getCount(1, 0, n-1, 0, idx);   
      int forward = findForward(1, 0, n-1, k-1);
      if (forward >= 0 && forward + arr[forward] == idx) {
        merge(forward, idx);
      }
    }
    void merge(int i, int j) {
      // i < j
      int len = arr[i] + arr[j];
      update(1, 0, n-1, j, 0);
      update(1, 0, n-1, i, len);      
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    SegTree sg = new SegTree();
    HashMap<String, Node> store = new HashMap<>(); // <VarName, Node(Address, Memory Size)>
    while (n-- > 0) {
      String command = br.readLine();
      int len = command.length();
      if (command.charAt(4) == '=') { // variable assign
        String varName = command.substring(0, 4);
        int memory = Integer.parseInt(command.substring(12, len-2));
        int address = sg.malloc(memory);
        if (address < 0) {
          if (store.containsKey(varName)) store.remove(varName);
          continue;
        }
        store.put(varName, new Node(address, memory));
      } else {
        if (command.charAt(0) == 'p') { // print
          String varName = command.substring(6, len-2);
          if (store.containsKey(varName)) {
            Node node = store.get(varName);
            bw.write(Integer.toString(node.address+1));
          } else {
            bw.write('0');
          }
          if (n!=0) bw.newLine();
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