import java.io.*;
import java.util.*;

public class BOJ1062 {
  static int numWord;
  static int numTeach;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    numWord = Integer.parseInt(st.nextToken());
    numTeach = Integer.parseInt(st.nextToken());
    char[][] data = new char[numWord][];
    for (int i = 0; i < numWord; i++) {
      data[i] = br.readLine().toCharArray();
    }
    
    bw.write(answer(data, numTeach) + "\n");
    bw.flush();
  }
  static int answer(char[][] data, int numTeach) {
    int[] bits = new int[numWord];
    for (int i = 0; i < numWord; i++) {
      bits[i] = wordToBit(data[i]);
    } 
    return recur(0, 0, 0, bits);
  }
  static int recur(int ptr, int numKnowChar, int knowBit, int[] bits) {
    // ptr: move a to z
    if (numKnowChar >= numTeach) {
      int numKnowWord = 0;
      for (int i = 0; i < numWord; i++) {
        if (bits[i] == (bits[i] & knowBit)) {
          numKnowWord++;
        }
      }
      return numKnowWord;
    }
    int max = 0;
    for (int i = ptr; i < 26; i++) {
      int bit = knowBit & (1 << i);
      if (bit != 0) continue;
      max = Math.max(max, recur(i, numKnowChar + 1, knowBit | (1 << i), bits));
    }
    return max;
  }
  static int wordToBit(char[] word) {
    int bit = 0;
    for (int i = 0; i < word.length; i++) {
      bit = bit | (1 << (word[i] - 'a'));
    }
    return bit;
  }
}
