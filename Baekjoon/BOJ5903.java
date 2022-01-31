import java.io.*;
import java.util.*;

public class BOJ5903 {
  private static class Square {
    int x1, y1, x2, y2;
    Square(int x1, int y1, int x2, int y2) {
      this.x1 = x1;
      this.y1 = y1;
      this.x2 = x2;
      this.y2 = y2;
    }
    int getArea() {
      return (x2 - x1) * (y1 - y2);
    }
    Square intersect(Square other) {
      if (other.x2 < x1 || x2 < other.x1) return new Square(0,0,0,0);
      if (other.y2 > y1 || y2 > other.y1) return new Square(0,0,0,0);
      return new Square(
        Integer.max(x1, other.x1),
        Integer.min(y1, other.y1),
        Integer.min(x2, other.x2),
        Integer.max(y2, other.y2)
        );
    }
    public String toString() {
      return x1 + ", " + y1 + "/" + x2 + ", " + y2;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    Square[] input = new Square[n];
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int x1 = Integer.parseInt(st.nextToken());
      int y1 = Integer.parseInt(st.nextToken());
      int x2 = Integer.parseInt(st.nextToken());
      int y2 = Integer.parseInt(st.nextToken());
      input[i] = new Square(x1, y1, x2, y2);
    }
    Square whole = new Square(-10_000, 10_000, 10_000, -10_000);
    int answer = recur(0, input, whole);
    bw.write(Integer.toString(answer));
    bw.newLine();
    bw.flush();

  }
  static int recur(int depth, Square[] arr, Square prev) {
    if (depth >= arr.length) {
      return 0;
    }
    Square intersect = prev.intersect(arr[depth]);
    int ret = intersect.getArea();
    ret += recur(depth + 1, arr, prev);
    ret -= recur(depth + 1, arr, prev.intersect(arr[depth]));
    return ret;
  }
}
