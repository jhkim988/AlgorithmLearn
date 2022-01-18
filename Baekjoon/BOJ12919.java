import java.io.*;

public class BOJ12919 {
  static char[] start;
  static char[] target;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    start = br.readLine().toCharArray();
    target = br.readLine().toCharArray();
    String answer = recur(target, 0, target.length - 1, false) ? "1\n" : "0\n";
    bw.write(answer);
    bw.flush();
  }
  static boolean recur(char[] target, int lo, int hi, boolean reverse) {
    if (hi - lo + 1 == start.length) {
      if (!reverse) {
        for (int i = lo; i <= hi; i++) {
          if (target[i] != start[i - lo]) return false;
        }
      } else {
        for (int i = hi; i >= lo; i--) {
          if (target[i] != start[hi - i]) return false; 
        }
      }
      return true;
    }
    if (!reverse) {
      if (target[hi] == 'A' && recur(target, lo, hi - 1, false)) return true;
      if (target[lo] == 'B' && recur(target, lo + 1, hi, true)) return true;
    } else {
      if (target[lo] == 'A' && recur(target, lo + 1, hi, true)) return true;
      if (target[hi] == 'B' && recur(target, lo, hi - 1, false)) return true;
    }
    return false;
  }
}
