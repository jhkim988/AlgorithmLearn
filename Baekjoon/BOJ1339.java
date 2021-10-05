import java.io.*;
import java.util.*;

public class BOJ1339 {
  static int numChar;
  static ArrayList<Character> codeList;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numWord = Integer.parseInt(br.readLine());
    char[][] words = new char[numWord][];
    int[] code = new int[26];
    codeList = new ArrayList<>();
    Arrays.fill(code, -1);
    numChar = 0;
    for (int i = 0; i < numWord; i++) {
      char[] tmp = words[i] = br.readLine().toCharArray();
      int len = words[i].length;
      for (int j = 0; j < len; j++) {
        char ch = tmp[j];
        int idx = ch - 'A';
        if (code[idx] == 0) continue;
        code[idx] = 0;
        codeList.add(ch);
        numChar++;
      }
    }

    // bw.write(bruteForce(words, code) + "\n");
    bw.write(greedy(words) + "\n");
    bw.flush();
  }
  static int bruteForce(char[][] words, int[] code) {
    boolean[] marked = new boolean[10];
    return recur(0, words, code, marked);
  }
  static int recur(int depth, char[][] words, int[] code, boolean[] marked) {
    if (depth >= numChar) {
      int result = 0;
      for (int i = 0; i < words.length; i++) {
        result += calc(words[i], code);
      }
      return result;
    }

    int max = 0;
    char ch = codeList.get(depth);
    for (int j = 0; j < 10; j++) {
      if (marked[j]) continue;
      marked[j] = true;
      code[ch - 'A'] = j;
      max = Math.max(max, recur(depth + 1, words, code, marked));
      marked[j] = false;
    }
    
    return max;
  }
  static int calc(char[] str, int[] code) {
    int result = 0;
    int len = str.length;
    for (int i = 0; i < len; i++) {
      result = result * 10 + code[str[i] - 'A'];
    }
    return result;
  }
  static int greedy(char[][] words) {
    Integer[] freq = new Integer[26];
    Arrays.fill(freq, 0);
    for (int i = 0; i < words.length; i++) {
      for (int j = 0; j < words[i].length; j++) {
        int idx = words[i][j] - 'A'; 
        freq[idx] += (int) Math.pow(10, words[i].length - j -1);
      }
    }
    Arrays.sort(freq, Collections.reverseOrder());
    int result = 0;
    int ptr = 9;
    for (int i = 0; i < numChar; i++, ptr--) {
      result += freq[i] * ptr;
    } 
    return result;
  }
}