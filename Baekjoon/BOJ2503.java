import java.io.*;
import java.util.*;

public class BOJ2503 {
  private static class Pitching {
    int key, strike, ball;
    Pitching(int key, int strike, int ball) {
      this.key = key;
      this.strike = strike;
      this.ball = ball;
    }
    boolean test(int other) {
      int numStrike = 0, numBall = 0;
      for (int i = 1; i < 1000; i *= 10) {
        for (int j = 1; j < 1000; j *= 10) {
          if ((key/i) % 10 == (other/j) % 10) {
            if (i == j) numStrike++;
            else numBall++;
          }
        }
      }
      return numStrike == strike && numBall == ball;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    Pitching[] plist = new Pitching[n];
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int key = Integer.parseInt(st.nextToken());
      int strike = Integer.parseInt(st.nextToken());
      int ball = Integer.parseInt(st.nextToken());
      plist[i] = new Pitching(key, strike, ball);
    }
    int num = 0;
    for (int i = 1; i <= 9; i++) {
      for (int j = 1; j <= 9; j++) {
        if (i == j) continue;
        next: for (int k = 1; k <= 9; k++) {
          if (i == k || j == k) continue;
          int val = i * 100 + j * 10 + k;
          for (int l = 0; l < n; l++) {
            if (!plist[l].test(val)) continue next; 
          }
          num++;
        }
      }
    }
    bw.write(Integer.toString(num));
    bw.newLine();
    bw.flush();
  }
}
