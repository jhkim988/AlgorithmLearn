import java.io.*;
import java.util.*;

public class BOJ14725 {
  private static class Pair {
    TreeMap<String, TreeMap> ptr;
    int level;
    Pair(TreeMap<String, TreeMap> ptr, int level) {
      this.ptr = ptr;
      this.level = level;
    }
  }
  private static class PairKeyLevel {
    String key;
    int level;
    PairKeyLevel(String key, int level) {
      this.key = key;
      this.level = level;
    }
  }
  private static class Trie {
    static int totNum = 0;
    static int ptr = 0;
    static PairKeyLevel[] result;
    TreeMap<String, TreeMap> root = new TreeMap<>();
    void add(String[] strs) {
      TreeMap<String, TreeMap> ptr = root;
      for (String str : strs) {
        if (ptr.containsKey(str)) {
          ptr = ptr.get(str);
        } else {
          TreeMap<String, TreeMap> tmp = new TreeMap<>();
          ptr.put(str, tmp);
          ptr = tmp;
          totNum++;
        }
      }
    } 
    PairKeyLevel[] dfs() {
      result = new PairKeyLevel[totNum];
      ptr = 0;
      recur(new Pair(root, 0));
      return result;
    }
    static void recur(Pair crnt) {
      for (Map.Entry<String, TreeMap> entry : crnt.ptr.entrySet()) {
        result[ptr++] = new PairKeyLevel(entry.getKey(), crnt.level + 1);
        recur(new Pair(entry.getValue(), crnt.level + 1));
      }
    }
  }
  static ArrayList<String> store = new ArrayList<>();
  static String getDash(int level) {
    if (level >= store.size()) {
      while (level >= store.size()) {
        store.add(store.get(store.size() - 1) + "--");
      }
    }
    return store.get(level - 1);
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    Trie trie = new Trie();
    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      String[] strs = new String[Integer.parseInt(st.nextToken())];
      for (int j = 0; j < strs.length; j++) {
        strs[j] = st.nextToken();
      }
      trie.add(strs);
    }
    PairKeyLevel[] result = trie.dfs();
    store.add("");
    for (PairKeyLevel p : result) {
      bw.write(getDash(p.level));
      bw.write(p.key);
      bw.newLine();
    }
    bw.flush();
  }
}
