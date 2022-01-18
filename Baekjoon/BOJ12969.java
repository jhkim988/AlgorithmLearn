import java.io.*;
import java.util.*;

public class BOJ12969 {
  static int N;
  static int K;
  static char[] answer;
  static boolean[][][][] cache;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    answer = new char[N];
    cache = new boolean[N][N][N][N*N/2];
    String print = recur(0, 0, 0, 0) ? new String(answer) : "-1";
    bw.write(print);
    bw.newLine();
    bw.flush();
  }
  static boolean recur(int depth, int numA, int numB, int numPair) {
    if (depth >= N) {
      return numPair == K;
    }
    if (cache[depth][numA][numB][numPair]) return false;
    cache[depth][numA][numB][numPair] = true;
    answer[depth] = 'A';
    if (recur(depth + 1, numA + 1, numB, numPair)) return true;
    answer[depth] = 'B';
    if (recur(depth + 1, numA, numB + 1, numPair + numA)) return true;
    answer[depth] = 'C';
    if (recur(depth + 1, numA, numB, numPair + numA + numB)) return true;
    return false;
  }
}
