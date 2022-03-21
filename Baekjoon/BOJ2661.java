import java.io.*;

public class BOJ2661 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] answer = new int[n];
    recur(0, answer);
    for (int a : answer) {
      bw.write(Integer.toString(a));
    }
    bw.newLine();
    bw.flush();
  }
  static boolean recur(int depth, int[] answer) {
    if (depth >= answer.length) {
      return true;
    }
    for (int i = 1; i <= 3; i++) {
      answer[depth] = i;
      if (!check(answer, depth)) continue;
      if (recur(depth + 1, answer)) return true;
    }
    return false;
  }
  static boolean check(int[] answer, int end) {
    for (int len = 1; len <= (end+1)/2; len++) {
      int ptr1 = end;
      int ptr2 = ptr1 - len;
      boolean allSame = true;
      for (int j = 0; j < len; j++) {
        allSame = allSame && (answer[ptr1 - j] == answer[ptr2 - j]);
      }
      if (allSame) return false;
    }
    return true;
  }
}
