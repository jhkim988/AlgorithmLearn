import java.util.*;

public class Trie {
  private static class TrieNode {
    HashMap<Character, TrieNode> child;
    boolean isEnd;
    TrieNode() { child = new HashMap<>(); }
  }
  TrieNode root = new TrieNode();
  void add(char[] str) {
    TrieNode ptr = root;
    for (char ch : str) {
      if (ptr.child.containsKey(ch)) {
        ptr = ptr.child.get(ch);
      } else {
        TrieNode tmp = new TrieNode();
        ptr.child.put(ch, tmp);
        ptr = tmp;
      }
    }
    ptr.isEnd = true;
  }
  boolean search(char[] str) {
    TrieNode ptr = root;
    for (char ch : str) {
      if (!ptr.child.containsKey(ch)) return false;
      ptr = ptr.child.get(ch);
    }
    return ptr.isEnd;
  }
}
