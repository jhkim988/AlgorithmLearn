import java.io.*;
import java.util.*;

public class BOJ16903 {
  private static class Node {
    int count = 0;
    Node[] child = new Node[2];
  }
  private static class Trie {
    Node head = new Node();
    void add(int x) {
      Node ptr = head;
      for (int d = 1<<30; d > 0; d >>= 1) {
        int val = (x&d) == 0 ? 0 : 1;
        if (ptr.child[val] == null) {
          ptr.child[val] = new Node();
        } 
        ptr = ptr.child[val];
        ptr.count++;
      }
    }
    void remove(int x) {
      Node ptr = head;
      for (int d = 1<<30; d > 0; d >>= 1) {
        int val = (x&d) == 0 ? 0 : 1;
        ptr = ptr.child[val];
        ptr.count--;
      }
    }
    int getMax(int x) {
      Node ptr = head;
      int max = 0;
      for (int d = 1<<30; d > 0; d >>= 1) {
        int val1 = (~x&d) == 0 ? 0 : 1;
        int val2 = (x&d) == 0 ? 0 : 1;
        if (ptr.child[val1] != null && ptr.child[val1].count > 0) {
          ptr = ptr.child[val1];
          max |= (~x&d);
        } else {
          if (ptr.child[val2] == null || ptr.child[val2].count == 0) return max^x;
          ptr = ptr.child[val2];
          max |= (x&d);
        }
      }
      return max^x;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    Trie trie = new Trie();
    trie.add(0);
    int n = Integer.parseInt(br.readLine());
    while (n-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      int x = Integer.parseInt(st.nextToken());
      if (type == 1) {
        trie.add(x);
      } else if (type == 2) {
        trie.remove(x);
      } else {
        bw.write(Integer.toString(trie.getMax(x)));
        bw.newLine();
      }
    }
    bw.flush();
  }
}
