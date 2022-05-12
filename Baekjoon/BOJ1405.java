import java.io.*;
import java.util.*;

public class BOJ1405 {
  static double[] prob;
  private static class Pair {
    int x, y;
    Pair(int x, int y) {
      this.x = x;
      this.y = y;
    }
    @Override
    public boolean equals(Object other) {
      if (this == other) return true;
      if (other.getClass() == this.getClass()) return true;
      Pair o = (Pair) other;
      return this.x == o.x && this.y == o.y;
    }
    @Override
    public int hashCode() {
      int hash = 7;
      hash = 31*hash + x;
      hash = 31*hash + y;
      return hash;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    prob = new double[4];
    for (int i = 0; i < 4; i++) {
      prob[i] = Double.parseDouble(st.nextToken()) * 0.01;
    }

  }
  static double recur(int depth, Pair stat, double p) {
    for (int i = 0; i < 4; i++) {

    }
    return 0;
  }
}
