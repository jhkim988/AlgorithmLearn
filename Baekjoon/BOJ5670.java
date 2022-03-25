import java.io.*;
import java.util.*;

public class BOJ5670 {
  private static class Node {
    char ch;
    HashMap<Character, Node> hm;
    Node(char ch, HashMap<Character, Node> hm) {
      this.ch = ch;
      this.hm = hm;
    }
    boolean containsKey(char key) {
      return hm.containsKey(key);
    }
    Node get(char key) {
      return hm.get(key);
    }
    void put(char key, Node node) {
      hm.put(key, node);
    } 
    int size() {
      return hm.size();
    }
  }
  @SuppressWarnings({"unchecked", "rawtypes"})
  private static class Trie {
    int numClick = 0;
    Node root;
    Trie() {
      root = new Node('-', new HashMap<>());
    }
    void add(String str) {
      Node ptr = root;
      char[] chArr = str.toCharArray();
      for (char ch : chArr) {
        if (ptr.containsKey(ch)) {
          ptr = ptr.get(ch);
        } else {
          ptr.put(ch, ptr = new Node(ch, new HashMap<>()));
        }
      }
      ptr.put('\0', new Node('\0', new HashMap<>())); // end
    }
    void numClick(Node node, int num) {
      while (node.size() == 1) {
        for (char ch : node.hm.keySet()) node = node.get(ch);
      }
      if (node.ch == '\0') {
        System.out.println("num: " + num);
        numClick += num;
        return;
      }
      for (char ch : node.hm.keySet()) {
        Node next = node.get(ch);
        if (next.ch == '\0') {
          numClick += num;
          continue;
        }
        numClick(next, num+1);
      }
    }
    int numClick() {
      numClick(root, 0);
      return numClick;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    String input = br.readLine();
    while (input != null) {
      int numWord = Integer.parseInt(input);
      Trie trie = new Trie();
      for (int i = 0; i < numWord; i++) {
        trie.add(br.readLine());
      }
      System.out.println(((double) trie.numClick()) / numWord);
      input = br.readLine();
    }
  }
}
