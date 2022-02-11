import java.io.*;
import java.util.*;

public class BOJ14426 {
  @SuppressWarnings({"unchecked", "rawtypes"})
  private static class Trie {
    HashMap<Character, HashMap> root = new HashMap<>();
    void insert(String str) {
      char[] charArr = str.toCharArray();
      HashMap<Character, HashMap> ptr = root;
      for (int i = 0; i < charArr.length; i++) {
        if (ptr.containsKey(charArr[i])) ptr = ptr.get(charArr[i]);
        else {
          HashMap<Character, HashMap> next = new HashMap<>();
          ptr.put(charArr[i], next);
          ptr = next;
        };
      }
    }
    boolean find(String str) {
      char[] charArr = str.toCharArray();
      HashMap<Character, HashMap> ptr = root;
      for (int i = 0; i < charArr.length; i++) {
        if (ptr.containsKey(charArr[i])) ptr = ptr.get(charArr[i]);
        else return false;
      }
      return true;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    Trie tr = new Trie();
    for (int i = 0; i < N; i++) tr.insert(br.readLine());
    int num = 0;
    for (int i = 0; i < M; i++) if (tr.find(br.readLine())) num++;
    bw.write(num + "\n");
    bw.flush();
  }
}
