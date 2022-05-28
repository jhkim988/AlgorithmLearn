import java.io.*;
import java.util.*;

public class BOJ19585 {
  static int maxColor = 0, minName = 10_000;
  private static class Node {
    boolean isEnd;
    Node prev;
    Node[] charr = new Node['z'-'a'+1];
    Node(Node prev) {
      this.prev = prev;
    }
  }
  private static class Trie {
    Node head = new Node(null);
    void add(char[] str, boolean rev) {
      Node ptr = head;
      if (!rev) {
        for (int i = 0; i < str.length; i++) {
          if (ptr.charr[str[i]-'a'] == null) {
            ptr = ptr.charr[str[i]-'a'] = new Node(ptr);
          } else {
            ptr = ptr.charr[str[i]-'a'];
          }
        }
      } else {
        for (int i = str.length-1; i >= 0; i--) {
          if (ptr.charr[str[i]-'a'] == null) {
            ptr = ptr.charr[str[i]-'a'] = new Node(ptr);
          } else {
            ptr = ptr.charr[str[i]-'a'];
          }
        }
      }
      ptr.isEnd = true;
    }
  }
  public static void main(String[] args) throws IOException {
    useTrieHash();
  }
  public static void useTrieHash() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int c = Integer.parseInt(st.nextToken());
    int n = Integer.parseInt(st.nextToken());
    Trie color = new Trie();
    HashSet<String> name = new HashSet<>();
    while (c-- > 0) {
      char[] add = br.readLine().toCharArray();
      color.add(add, false);
      maxColor = Integer.max(maxColor, add.length);
    }
    while (n-- > 0) {
      String add = br.readLine();
      name.add(add);
      minName = Integer.min(minName, add.length());
    }

    int q = Integer.parseInt(br.readLine());
    while (q-- > 0) {
      String query = br.readLine();
      bw.write(query(query, color, name) ? "Yes\n" : "No\n");
    }
    bw.flush();
  }
  static boolean query(String query, Trie color, HashSet<String> name) {
    char[] qarr = query.toCharArray();
    Node ptr = color.head;
    for (int i = 0; i < qarr.length-1; i++) {
      if (i > maxColor || qarr.length-1-i < minName) return false;
      if (ptr.charr[qarr[i]-'a'] == null) return false;
      ptr = ptr.charr[qarr[i]-'a'];
      if (ptr.isEnd && name.contains(query.substring(i+1))) return true;
    }
    return false;
  }
  public static void useTwoTrie() throws IOException {
    // Sad Java...
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int c = Integer.parseInt(st.nextToken());
    int n = Integer.parseInt(st.nextToken());
    Trie color = new Trie();
    Trie name = new Trie();
    while (c-- > 0) {
      char[] add = br.readLine().toCharArray();
      color.add(add, false);
      maxColor = Integer.max(maxColor, add.length);
    }
    while (n-- > 0) {
      char[] add = br.readLine().toCharArray();
      name.add(add, true);
    }

    int q = Integer.parseInt(br.readLine());
    while (q-- > 0) {
      char[] query = br.readLine().toCharArray();
      bw.write(query(query, color, name) ? "Yes\n" : "No\n");
    }
    bw.flush();
  }
  static boolean query(char[] query, Trie color, Trie name) {
    Node ptr_c = color.head;
    int idx_c = -1;
    for (int i = 0; i < query.length; i++) {
      if (ptr_c.charr[query[i]-'a'] != null) {
        ptr_c = ptr_c.charr[query[i]-'a'];
        if (ptr_c.isEnd) {
          idx_c = i;
          break;
        }
      } else break;
    }
    if (idx_c == -1) return false;

    Node ptr_n = name.head;
    for (int i = query.length-1; i > idx_c; i--) {
      if (ptr_n.charr[query[i]-'a'] != null) {
        ptr_n = ptr_n.charr[query[i]-'a'];
      } else {
        return false;
      }
    }

    for (int i = idx_c+1; i < query.length-1; i++) {
      if (ptr_c.isEnd && ptr_n.isEnd) return true;
      if (ptr_c.charr[query[i]-'a'] == null) return false;
      ptr_c = ptr_c.charr[query[i]-'a'];
      ptr_n = ptr_n.prev;
    }
    return false;
  }
}
