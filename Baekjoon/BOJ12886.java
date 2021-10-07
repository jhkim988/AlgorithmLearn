import java.io.*;
import java.util.*;

public class BOJ12886 {
  private static class Pair {
    int min;
    int mid;
    int max;
    int move = 0;
    Pair(int a, int b, int c) {
      if (a < b) {
        if (b < c) {
          this.min = a;
          this.mid = b;
          this.max = c;
        } else {
          if (a < c) {
            this.min = a;
            this.mid = c;
            this.max = b;
          } else {
            this.min = c;
            this.mid = a;
            this.max = b;
          }
        }
      } else {
        if (a < c) {
          this.min = b;
          this.mid = a;
          this.max = c;
        } else {
          if (b < c) {
            this.min = b;
            this.mid = c;
            this.max = a;
          } else {
            this.min = c;
            this.mid = b;
            this.max = a;
          }
        }
      }
    }
    Pair(int min, int mid, int max, int move) {
      this(min, mid, max);
      this.move = move;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int A = Integer.parseInt(st.nextToken());
    int B = Integer.parseInt(st.nextToken());
    int C = Integer.parseInt(st.nextToken());

    Queue<Pair> que = new LinkedList<>();
    boolean[][] marked = new boolean[1501][1501];

    Pair start = new Pair(A, B, C);
    que.add(start);
    marked[start.min][start.max] = true;

    while (!que.isEmpty()) {
      Pair crnt = que.poll();
      // System.out.println(crnt.min + ", " + crnt.mid + ", " + crnt.max);
      if (crnt.min == crnt.max) {
        bw.write("1\n");
        bw.flush();
        return;
      }

      if (crnt.min < crnt.mid) {
        Pair next = new Pair(crnt.min * 2, crnt.mid - crnt.min, crnt.max, crnt.move + 1);
        if (!marked[next.min][next.max]) {
          que.add(next);
          marked[next.min][next.max] = true;
        }
      }
      if (crnt.min < crnt.max) {
        Pair next = new Pair(crnt.min * 2, crnt.max - crnt.min, crnt.mid, crnt.move + 1);
        if (!marked[next.min][next.max]) {
          que.add(next);
          marked[next.min][next.max] = true;
        }
      }
      if (crnt.mid < crnt.max) {
        Pair next = new Pair(crnt.mid * 2, crnt.max - crnt.mid, crnt.min, crnt.move + 1);
        if (!marked[next.min][next.max]) {
          que.add(next);
          marked[next.min][next.max] = true;
        }
      }
    }

    bw.write("0\n");
    bw.flush();
  }
}
