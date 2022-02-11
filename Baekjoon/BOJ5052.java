import java.io.*;
import java.util.*;

@SuppressWarnings({"unchecked", "rawtypes"})
public class BOJ5052 {
  private static class Trie {
    HashMap<Character, HashMap> root = new HashMap<>();
    ArrayList<String> store = new ArrayList<>();
    void add(String str) {
      store.add(str);
      char[] charArray = str.toCharArray();
      HashMap<Character, HashMap> traverse = root; 
      for (char ch : charArray) {
        if (traverse.containsKey(ch)) {
          traverse = traverse.get(ch);
        } else {
          HashMap<Character, HashMap> hm = new HashMap<>();
          traverse.put(ch, hm);
          traverse = hm;
        }
      }
    }
    boolean isPrefixOfAnother(String str) {
      char[] charArray = str.toCharArray();
      HashMap<Character, HashMap> traverse = root;
      for (char ch : charArray) {
        if (!traverse.containsKey(ch)) return false;
        traverse = traverse.get(ch);
      }
      return traverse.size() > 0; // 0 means itself.
    }
    boolean answer() {
      for (String str : store) {
        if (isPrefixOfAnother(str)) return false;
      }
      return true;
    }
  }  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- > 0) {
      int numString = Integer.parseInt(br.readLine());
      Trie trie = new Trie();
      while (numString-- > 0) {
        trie.add(br.readLine());
      }
      bw.write(trie.answer() ? "YES\n" : "NO\n");
    }
    bw.flush();
  }
} 
