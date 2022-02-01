import java.io.*;
import java.util.*;

public class BOJ15732 {
  private static class Rule {
    int start, end, gap;
    Rule(int start, int end, int gap) {
      this.start = start;
      this.end = end;
      this.gap = gap; 
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    int d = Integer.parseInt(st.nextToken());

    Rule[] rules = new Rule[k];
    for (int i = 0; i < k; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      int gap = Integer.parseInt(st.nextToken());
      rules[i] = new Rule(start, end, gap);
    }

    int lo = 0;
    int hi = n + 1;
    while (lo + 1 < hi) {
      int mid = (lo + hi) / 2;
      if (check(mid, d, rules)) {
        lo = mid;
      } else {
        hi = mid;
      }
    }

    bw.write(Integer.toString(hi));
    bw.newLine();
    bw.flush();
  }
  static boolean check(int key, int dotori, Rule[] rules) {
    for (Rule r : rules) {
      if (r.start > key) continue;
      int diff = 0;
      if (r.end > key) {
        diff = (key - r.start);
      } else {
        diff = (r.end - r.start);
      }
      dotori -= 1 + diff / r.gap;
      if (dotori <= 0) return false;
    }
    return dotori > 0;
  }
}
