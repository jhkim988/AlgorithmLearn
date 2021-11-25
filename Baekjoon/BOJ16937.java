import java.io.*;
import java.util.*;

public class BOJ16937 {
  static int H;
  static int W;
  private static class Pair implements Comparable<Pair> {
    int row;
    int col;
    Pair (int row, int col) {
      this.row = row;
      this.col = col;
    }
    int getArea() {
      return this.row*this.col;
    }
    Pair rotation() {
      return new Pair(this.col, this.row);
    }
    @Override
    public int compareTo(Pair other) { // Area
      return other.getArea() - this.getArea();
    } 
    @Override
    public String toString() {
      return "(" + row + ", " + col + ")";
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    H = Integer.parseInt(st.nextToken());
    W = Integer.parseInt(st.nextToken());
    int N = Integer.parseInt(br.readLine());
    ArrayList<Pair> sticker = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int row = Integer.parseInt(st.nextToken());
      int col = Integer.parseInt(st.nextToken());
      sticker.add(new Pair(row, col));
    }
    int maxValue = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (i == j) continue;
        Pair stck1 = sticker.get(i);
        Pair stck2 = sticker.get(j);
        Pair rot1 = stck1.rotation();
        Pair rot2 = stck2.rotation();
        // System.out.println("loop: " + stck1 + " & " + stck2);
        boolean flag = possible(stck1, stck2);
        flag = flag || possible(rot1, stck2);
        flag = flag || possible(stck1, rot2);
        flag = flag || possible(rot1, rot2);
        if (!flag) continue;
        if (maxValue < stck1.getArea() + stck2.getArea()) {
          maxValue = stck1.getArea() + stck2.getArea();
        }
      }
    }

    bw.write(maxValue + "\n");
    bw.flush();
  }  
  static boolean possible(Pair stck1, Pair stck2) {
    // System.out.println("possible call: " + stck1 + " & " + stck2);
    if (H < stck1.row || H < stck2.row || W < stck1.col || W < stck2.col) return false;
    if (H < stck1.row + stck2.row) {
      return stck1.col + stck2.col <= W;
    }
    if (W < stck1.col + stck2.col) {
      return stck1.row + stck2.row <= H;
    }
    return true;
  }
}
