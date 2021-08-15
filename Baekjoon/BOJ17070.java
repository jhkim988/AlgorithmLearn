import java.io.*;
import java.util.*;

public class BOJ17070 {
  static int N;
  static int[][] house;
  private static class Pipe {
    // state:
    // horizontal = 1, vertival = -1, diagonal = 0    
    // (i, j) position(upper-left)
    int state;
    int i;
    int j;
    Pipe(int state, int i, int j) {
      this.state = state;
      this.i = i;
      this.j = j;
    }
    Iterable<Pipe> move() {
      ArrayList<Pipe> list = new ArrayList<>();
      if (this.state == 1) {
        if (j + 2 < N) { // horizontal
          if (house[i][j + 2] == 0) {
            list.add(new Pipe(1, i, j + 1));
          }
        }        
        if (j + 2 < N && i + 1 < N) { // diagonal
          if (house[i][j + 2] == 0 && house[i + 1][j + 1] == 0 && house[i + 1][j + 2] == 0) {
            list.add(new Pipe(0, i, j + 1));
          }
        }

      } else if (this.state == -1) {
        if (i + 2 < N) { // vertical
          if (house[i + 2][j] == 0) {
            list.add(new Pipe(-1, i + 1, j));
          }
        }
        if (i + 2 < N && j + 1 < N) { // diagonal
          if (house[i + 1][j + 1] == 0 && house[i + 2][j] == 0 && house[i + 2][j + 1] == 0) {
            list.add(new Pipe(0, i + 1, j));
          }
        }
      } else {
        if (i + 1 < N && j + 2 < N) { // horizontal
          if (house[i + 1][j + 2] == 0) {
            list.add(new Pipe(1, i + 1, j + 1));
          }
        }
        if (i + 2 < N && j + 1 < N) { // vertical
          if (house[i + 2][j + 1] == 0) {
            list.add(new Pipe(-1, i + 1, j + 1));
          }
        }
        if (i + 2 < N && j + 2 < N) { // diagonal
          if (house[i + 2][j + 2] == 0 && house[i + 1][j + 2] == 0 && house[i + 2][j + 1] == 0) {
            list.add(new Pipe(0, i + 1, j + 1));
          }
        }
      }
      return list;
    }
    boolean isEnd() {
      if (state == 1) {
        if (i == N - 1 && j + 1 == N - 1) {
          return true;
        }
      } else if (state == -1) {
        if (i + 1 == N - 1 && j == N - 1) {
          return true;
        }
      } else {
        if (i + 1 == N - 1 && j + 1 == N - 1) {
          return true;
        }
      }
      return false;
    }
    @Override
    public String toString() {
      return "<" + state + ": " + i + ", " + j + ">";
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    N = Integer.parseInt(br.readLine());
    house = new int[N][N];
    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        house[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    Pipe start = new Pipe(1, 0, 0);
    int count = DFS(start);
    bw.write(count + "\n");
    bw.flush();
  }
  static int DFS(Pipe crnt) {
    // System.out.println(crnt);
    if (crnt.isEnd()) {
      // System.out.println("end: " + crnt);
      return 1;
    }
    int result = 0;
    for (Pipe next : crnt.move()) {      
      result += DFS(next);
    }
    return result;
  }
}
