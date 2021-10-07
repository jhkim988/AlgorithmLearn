import java.io.*;
import java.util.*;

public class BOJ12886_test {
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
    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (this.getClass() != o.getClass()) return false;
      Pair other = (Pair) o;
      if (this.min == other.min && this.mid == other.mid && this.max == other.max) return true;
      return false;
    }
    @Override
    public int hashCode() {
      int result = this.min;
      result = result * 100 + this.mid;
      result = result * 100 + this.max;
      return result;
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
    HashSet<Pair> hs = new HashSet<>();

    Pair start = new Pair(A, B, C);
    que.add(start);
    hs.add(start);

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
        if (!hs.contains(next)) {
          que.add(next);
          hs.add(next);
        }
      }
      if (crnt.min < crnt.max) {
        Pair next = new Pair(crnt.min * 2, crnt.max - crnt.min, crnt.mid, crnt.move + 1);
        if (!hs.contains(next)) {
          que.add(next);
          hs.add(next);
        }
      }
      if (crnt.mid < crnt.max) {
        Pair next = new Pair(crnt.mid * 2, crnt.max - crnt.mid, crnt.min, crnt.move + 1);
        if (!hs.contains(next)) {
          que.add(next);
          hs.add(next);
        }
      }
    }

    bw.write("0\n");
    bw.flush();
  }
}
