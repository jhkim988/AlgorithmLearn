import java.io.*;
import java.util.*;

public class BOJ17144 {
  static int R;
  static int C;
  static int[][] room;
  static int clnpos; // clnpos - 1, clnpos
  static int[] xDi = {-1, 0, 1, 0};
  static int[] yDi = {0, -1, 0, 1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    int T = Integer.parseInt(st.nextToken());
    room = new int[R][C];
    for (int i = 0; i < R; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < C; j++) {
        room[i][j] = Integer.parseInt(st.nextToken());
        if (room[i][j] == -1) {
          clnpos = i;
        }
      }
    }

    while (T-- > 0) {
      diffusion();
      // print();
      cleanor();
      // print();
    }

    int sum = 0;
    for (int i = 0; i < R; i++) {
      for (int j = 0; j < C; j++) {
        if (room[i][j] == -1) continue;
        sum += room[i][j];
      }
    }
    bw.write(sum + "\n");
    bw.flush();
  }
  static void diffusion() {
    int[][] tmp = new int[R][C];
    tmp[clnpos - 1][0] = -1;
    tmp[clnpos][0] = -1;
    for (int i = 0; i < R; i++) {
      for (int j = 0; j < C; j++) {
        if (room[i][j] > 0) {
          int numDiffusionDirection = 0;
          for (int k = 0; k < 4; k++) {
            int nextI = i + xDi[k];
            int nextJ = j + yDi[k];
            if (nextI < 0 || nextI >= R || nextJ < 0 || nextJ >= C) continue;
            if (room[nextI][nextJ] == -1) continue;
            tmp[nextI][nextJ] += room[i][j] / 5;
            numDiffusionDirection++;
          }
          tmp[i][j] += room[i][j] - numDiffusionDirection * (room[i][j] / 5);
        }
      }
    }
    room = tmp;
  }
  static void cleanor() {
    for (int i = clnpos - 2; i > 0; i--) {
      room[i][0] = room[i - 1][0];
    }
    for (int i = clnpos + 1; i < R - 1; i++) {
      room[i][0] = room[i + 1][0];
    }

    for (int i = 0; i < C - 1; i++) {
      room[0][i] = room[0][i + 1];
      room[R - 1][i] = room[R- 1][i + 1];
    }

    for (int i = 0; i < clnpos - 1; i++) {
      room[i][C - 1] = room[i + 1][C - 1];
    }
    for (int i = R - 1; i > clnpos; i--) {
      room[i][C - 1] = room[i - 1][C - 1];
    }

    for (int i = C - 1; i > 1; i--) {
      room[clnpos - 1][i] = room[clnpos - 1][i - 1];
      room[clnpos][i] = room[clnpos][i - 1];
    } 
    room[clnpos - 1][1] = 0;
    room[clnpos][1] = 0;
  }
  static void print() {
    System.out.println("###############");
    for (int i = 0; i < R; i++) {
      for (int j = 0; j < C; j++) {
        System.out.print(room[i][j] + " ");
      }
      System.out.println();
    }
  }
}
