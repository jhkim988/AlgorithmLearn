import java.io.*;
import java.util.*;

public class BOJ9250 {
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
    void failure() {
      Queue<TrieNode> que = new LinkedList<>();
      root.fail = root;
      que.add(root);

      while (!que.isEmpty()) {
        TrieNode crnt = que.poll();

        for (Map.Entry<Character, TrieNode> entry : crnt.child.entrySet()) {
          char nextKey = entry.getKey();
          TrieNode nextNode = entry.getValue();
          if (crnt.isRoot) {
            nextNode.fail = root;
          } else {
            TrieNode failure = crnt.fail;
            while (!failure.isRoot && !failure.child.containsKey(nextKey)) {
              failure = failure.fail;
            }
            if (failure.child.containsKey(nextKey)) {
              failure = failure.child.get(nextKey);
            }
            nextNode.fail = failure;
          }
          if (nextNode.fail.isEnd) {
            nextNode.isEnd = true;
          }
          que.add(nextNode);
        }
      }
    }
    boolean kmp(char[] str) {
      TrieNode crnt = root;
      for (char ch : str) {
        while (!crnt.isRoot && !crnt.child.containsKey(ch)) {
          crnt = crnt.fail;
        }
        if (crnt.child.containsKey(ch)) {
          crnt = crnt.child.get(ch);
        }
        if (crnt.isEnd) {
          return true;
        }
      }
      return false;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    Trie trie = new Trie();
    int numMatch = Integer.parseInt(br.readLine());
    for (int i = 0; i < numMatch; i++) trie.add(br.readLine().toCharArray());
    int numQuery = Integer.parseInt(br.readLine());
    String[] match = new String[numQuery];
    for (int i = 0; i < numQuery; i++) match[i] = br.readLine();
    trie.failure();
    for (String str : match) {
      bw.write(trie.kmp(str.toCharArray()) ? "YES\n" : "NO\n");
    }
    bw.flush();
  }
}
