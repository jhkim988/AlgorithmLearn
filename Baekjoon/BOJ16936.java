import java.io.*;
import java.util.*;

public class BOJ16936 {
  static int N;
  static int[] seq;
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static String answer;
  public static void main(String[] args) throws IOException {

    int N = Integer.parseInt(br.readLine());
    int[] seq = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      seq[i] = Integer.parseInt(st.nextToken());
    }

    boolean[] marked = new boolean[N];
    int[] find = new int[N];
    for (int i = 0; i < N; i++) {
      marked[i] = true;
      find[0] = seq[i];
      recur(0, marked, find, false);
      marked[i] = false;
    }

    bw.write(answer);
    bw.flush();
  }
  static boolean recur(int depth, boolean[] marked, int[] find, boolean flag) { 
    System.out.println("call");
    if (depth >= N) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < N; i++) {
        sb.append(find[i]).append(' ');
      }
      sb.append('\n');
      answer = sb.toString();
      return true;
    }

    for (int i = 0; i <= depth; i++) {
      System.out.print(find[i] + " ");
    }
    System.out.println("Here");

    for (int i = 0; i < N; i++) {
      if (marked[i]) continue;
      if ((find[depth] % 3 == 0 && find[depth] / 3 != seq[i]) && find[depth] * 2 != seq[i]) continue;
      marked[i] = true;
      find[depth + 1] = seq[i];
      flag = recur(depth + 1, marked, find, flag);
      if (flag) return flag;
      marked[i] = false;
    }

    return flag;
  }
}