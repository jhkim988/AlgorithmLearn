import java.io.*;
import java.util.*;

public class BOJ1759 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static int L;
  static int C;
  static char[] candidate;
  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    L = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    candidate = new char[C];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < C; i++) {
      candidate[i] = st.nextToken().charAt(0); 
    }

    Arrays.sort(candidate);
    boolean[] marked = new boolean[C];
    backtraking(0, -1, false, 0, marked);
    bw.flush();
  }
  static void backtraking(int depth, int prev, boolean hasVowel, int numConsonants, boolean[] marked) throws IOException {
    if (depth == L) {
      if (hasVowel && numConsonants >= 2) {
        for (int i = 0; i < C; i++) {
          if (marked[i]) {
            bw.write(candidate[i]);
          }
        }
        bw.write("\n");
      }
      return;
    }

    for (int i = prev + 1; i < C; i++) {
      if (marked[i]) continue;
      marked[i] = true;
      if (hasVowel) {
        if (!(candidate[i] == 'a' || candidate[i] == 'e' || candidate[i] == 'i' || candidate[i] == 'o' || candidate[i] == 'u')) {
          backtraking(depth + 1, i, hasVowel, numConsonants + 1, marked);
        } else {
          backtraking(depth + 1, i, hasVowel, numConsonants, marked);
        }
      } else {
        if (candidate[i] == 'a' || candidate[i] == 'e' || candidate[i] == 'i' || candidate[i] == 'o' || candidate[i] == 'u') {
          hasVowel = true;
        }
        if (!(candidate[i] == 'a' || candidate[i] == 'e' || candidate[i] == 'i' || candidate[i] == 'o' || candidate[i] == 'u')) {
          backtraking(depth + 1, i, hasVowel, numConsonants + 1, marked);
        } else {
          backtraking(depth + 1, i, hasVowel, numConsonants, marked);
        }
        hasVowel = false;
      }
      marked[i] = false;
    }
  }
}
