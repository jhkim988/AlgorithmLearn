import java.io.*;
import java.util.*;

public class BOJ1138 {
  static int n;
  static int[] arr;
  static boolean[] visit;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    n = Integer.parseInt(br.readLine());
    arr = new int[n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    int[] answer = new int[n];
    visit = new boolean[n];
    recur(0, answer);
    for (int a : answer) {
      bw.write(Integer.toString(a));
      bw.write(' ');
    }
    bw.newLine();
    bw.flush();
  } 
  static boolean recur(int depth, int[] answer) {
    if (depth >= answer.length) return true;
    for (int i = 1; i <= n; i++) {
      if (visit[i-1]) continue;
      answer[depth] = i;
      if (!check(answer, depth)) continue;
      visit[i-1] = true;
      if (recur(depth + 1, answer)) return true;
      visit[i-1] = false;
    }
    return false;
  } 
  static boolean check(int[] answer, int depth) {
    int num = 0;
    for (int ptr = depth; ptr >= 0; ptr--) {
      if (answer[depth] < answer[ptr]) num++;
    }
    return arr[answer[depth]-1] == num;
  }
}
