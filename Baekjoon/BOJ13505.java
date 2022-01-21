import java.io.*;
import java.util.*;

public class BOJ13505 {
  private static class Node {
    Node zero;
    Node one;
    int key;
    Node(int key) {
      this.key = key;
    }
  }
  private static class Trie {
    Node root = new Node(0);
    int startBit = 1;
    Trie(int maxNumber) {
      while (startBit < maxNumber) startBit <<= 1;
    }
    void add(int value) {
      Node ptr = root;
      for (int bit = startBit; bit != 0; bit >>= 1) {
        if ((value & bit) == 0) {
          if (ptr.zero == null) ptr.zero = new Node(ptr.key);
          ptr = ptr.zero;
        } else {
          if (ptr.one == null) ptr.one = new Node(ptr.key | bit);
          ptr = ptr.one;
        }
      }
    }
    int maxXOR(int query) {
      Node ptr = root;
      for (int bit = startBit; bit != 0; bit >>= 1) {
        if (ptr.zero == null && ptr.one == null) break;
        int bitCompare = bit & query;
        if (ptr.zero == null || (bitCompare == 0 && ptr.one != null)) {
          ptr = ptr.one;
        } else {
          ptr = ptr.zero;
        }
      }
      while (!(ptr.zero == null && ptr.one == null)) {
        if (ptr.one != null) ptr = ptr.one;
        else ptr = ptr.zero;
      }
      return ptr.key ^ query;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    int[] input = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) input[i] = Integer.parseInt(st.nextToken()); 
    st = null;
    Trie trie = new Trie(1_000_000_000);
    for (int i = 0; i < N; i++) trie.add(input[i]);
    int max = 0;
    for (int i = 0; i < N; i++) {
      int val = trie.maxXOR(input[i]);
      // System.out.println("query: " + input[i] + " / value: " + val);
      if (max < val) max = val;
    }
    bw.write(Integer.toString(max));
    bw.newLine();
    bw.flush();
  }
}
