import java.util.*;

public class AhoCorasick {
  private static class Trie {
    TrieNode root = new TrieNode(true);
    void add(char[] str) {
      TrieNode ptr = root;
      for (char ch : str) {
        if (ptr.child.containsKey(ch)) {
          ptr = ptr.child.get(ch);
        } else {
          TrieNode tmp = new TrieNode(false);
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
    void failure() {
      Queue<TrieNode> que = new LinkedList<>();
      root.fail = root;
      que.add(root);

      // Find prefix == suffix
      while (!que.isEmpty()) {
        TrieNode crnt = que.poll();

        for (Map.Entry<Character, TrieNode> entry : crnt.child.entrySet()) {
          TrieNode next = entry.getValue();
          if (crnt.isRoot) {
            next.fail = root;
          } else {
            TrieNode failure = crnt.fail;
            while (!failure.isRoot && failure.child.size() == 0) {
              failure = failure.fail;
            }
            if (failure.child.containsKey(entry.getKey())) {
              failure = failure.child.get(entry.getKey());
            }
            next.fail = failure;
          }
          if (next.fail.isEnd) {
            next.isEnd = true;
          }
          que.add(next);
        }
      }
    }
    boolean kmp(char[] str) {
      TrieNode crnt = root;
      for (char ch : str) {
        while (!crnt.isRoot && crnt.child.containsKey(ch)) {
          crnt = crnt.fail;
        }
        if (crnt.child.containsKey(ch)) {
          crnt = crnt.child.get(ch);
        }
        if (crnt.isEnd) return true;
      }
      return false;
    }
  }
  private static class TrieNode {
    HashMap<Character, TrieNode> child;
    TrieNode fail;
    boolean isEnd;
    boolean isRoot;
    TrieNode(boolean isRoot) {
      child = new HashMap<>();
      this.isRoot = isRoot;
    }
  }
}
