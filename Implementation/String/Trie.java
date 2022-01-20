import java.util.*;

public class Trie {
  HashMap<Character, HashMap> root = new HashMap<>();
  static int size = 0;
  void add(char[] str) {
    HashMap<Character, HashMap> ptr = root;
    for (char ch : str) {
      if (ptr.containsKey(ch)) {
        ptr = ptr.get(ch);
      } else {
        HashMap<Character, HashMap> tmp = new HashMap<>();
        ptr.put(ch, tmp);
        ptr = tmp;
        size++;
      }
    }
  }
  boolean search(char[] str) {
    HashMap<Character, HashMap> ptr = root;
    for (char ch : str) {
      if (!ptr.containsKey(ch)) return false;
      ptr = ptr.get(ch);
    }
    return true;
  }
}
