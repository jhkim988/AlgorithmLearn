import java.io.*;
import java.util.*;

public class BOJ15686 {
  static int N;
  static int M;
  static int[][] city;
  static ArrayList<Pair> chiken;
  static int numChiken;
  static ArrayList<Pair> house;
  static ArrayList<Pair> choose;
  static int[] xDi = {-1, 0, 1, 0};
  static int[] yDi = {0, -1, 0, 1};
  static boolean[] marked;
  static int minSum;
  private static class Pair {
    int i;
    int j;
    Pair(int i, int j) {
      this.i = i;
      this.j = j;
    }
    int dist(Pair other) {
      return Math.abs(this.i - other.i) + Math.abs(this.j - other.j);
    }
    @Override
    public String toString() {
      return "<" + i + ", " + j + ">";
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    city = new int[N][N];
    chiken = new ArrayList<>();
    house = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        int input = Integer.parseInt(st.nextToken());
        city[i][j] = input;
        if (input == 2) {
          chiken.add(new Pair(i, j));
        }
        if (input == 1) {
          house.add(new Pair(i, j));
        }
      }
    }
    numChiken = chiken.size();
    choose = new ArrayList<>();
    minSum = 2 * N * N * M + 1;
    marked = new boolean[numChiken];
    backTracking(0, -1);

    bw.write(minSum + "\n");
    bw.flush();
  }
  static void backTracking(int depth, int idx) {
    if (depth == M) {
      if (calc() < minSum) {
        minSum = calc();
      }
      return;
    }
    for (int i = 0; i < numChiken; i++) {
      if (i <= idx) continue;
      choose.add(chiken.get(i));
      backTracking(depth + 1, i);
      choose.remove(chiken.get(i));
    }
  }

  static int calc() {
    int chikenDist = 0;
    for (Pair h : house) {
      int minDist = 2 * N * N * M + 1;
      for (Pair ch : choose) {
        int dist = h.dist(ch);
        if (dist < minDist) {
          minDist = dist;
        }
      }
      chikenDist += minDist;
    }
    return chikenDist;
  }
}
