import java.io.*;
import java.util.*;

public class BOJ2564 {
  static int width, height;
  private static class Pair {
    int direction, dist;
    Pair(int direction, int dist) {
      this.direction = direction;
      this.dist = dist;
    }
    int distTo(Pair other) {
      if (this.direction == other.direction) {
        return this.dist-other.dist > 0 ? this.dist-other.dist : other.dist-this.dist;
      } else if (this.direction < 3 && other.direction < 3) {
        return Integer.min(this.dist+other.dist, 2*width-this.dist-other.dist) + height;
      } else if (this.direction > 2 && other.direction > 2) {
        return Integer.min(this.dist+other.dist, 2*height-this.dist-other.dist) + width;
      } else {
        switch (this.direction) {
          case 1: {
            if (other.direction == 3) return this.dist+other.dist;
            return width-this.dist+other.dist;
          } case 2: {
            if (other.direction == 3) return this.dist+height-other.dist;
            return width-this.dist+height-other.dist;
          } case 3: {
            if (other.direction == 1) return this.dist+other.dist;
            return height-this.dist+other.dist;
          } default: {
            if (other.direction == 1) return this.dist+height-other.dist;
            return height-this.dist+width-other.dist;
          }
        }
      }
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    width = Integer.parseInt(st.nextToken());
    height = Integer.parseInt(st.nextToken());
    int n = Integer.parseInt(br.readLine());
    Pair[] list = new Pair[n];
    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      int direction = Integer.parseInt(st.nextToken());
      int dist = Integer.parseInt(st.nextToken());
      list[i] = new Pair(direction, dist);
    }
    st = new StringTokenizer(br.readLine());
    Pair start = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    int sum = 0;
    for (int i = 0; i < n; i++) {
      sum += start.distTo(list[i]);
    }
    bw.write(Integer.toString(sum));
    bw.flush();
  }
}
