import java.io.*;
import java.util.*;

public class BOJ5373 {
  private static class Cube {
    char[][][] stat;
    char[] tmp = new char[3];
    Cube() {
      stat = new char[6][3][3];
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          stat[0][i][j] = 'w';
          stat[1][i][j] = 'r';
          stat[2][i][j] = 'b';
          stat[3][i][j] = 'g';
          stat[4][i][j] = 'o';
          stat[5][i][j] = 'y';
        }
      }
    }
    void action(char surface) {
      switch (surface) {
        case 'U': {
          upRot();
          break;
        } case 'D': {
          downRot();
          break;
        } case 'F': {
          frontRot();
          break;
        } case 'B': {
          backRot();
          break;
        } case 'L': {
          leftRot();
          break;
        } case 'R': {
          rightRot();
          break;
        }
      }
    }
    void surfaceRot(int x) {
      for (int i = 0; i < 3; i++) tmp[i] = stat[x][0][i];
      for (int i = 0; i < 3; i++) stat[x][0][i] = stat[x][2-i][0];
      for (int i = 0; i < 3; i++) stat[x][i][0] = stat[x][2][i];
      for (int i = 0; i < 3; i++) stat[x][2][i] = stat[x][2-i][2];
      for (int i = 0; i < 3; i++) stat[x][i][2] = tmp[i];
    }
    void upRot() { // clock wise
      surfaceRot(0);
      for (int i = 0; i < 3; i++) tmp[i] = stat[4][0][i];
      for (int i = 0; i < 3; i++) stat[4][0][i] = stat[3][0][i];
      for (int i = 0; i < 3; i++) stat[3][0][i] = stat[1][0][i];
      for (int i = 0; i < 3; i++) stat[1][0][i] = stat[2][0][i];
      for (int i = 0; i < 3; i++) stat[2][0][i] = tmp[i];
    }
    void downRot() {
      surfaceRot(5);
      for (int i = 0; i < 3; i++) tmp[i] = stat[1][2][i];
      for (int i = 0; i < 3; i++) stat[1][2][i] = stat[3][2][i];
      for (int i = 0; i < 3; i++) stat[3][2][i] = stat[4][2][i];
      for (int i = 0; i < 3; i++) stat[4][2][i] = stat[2][2][i];
      for (int i = 0; i < 3; i++) stat[2][2][i] = tmp[i];
    }
    void leftRot() {
      surfaceRot(3);
      for (int i = 0; i < 3; i++) tmp[i] = stat[0][i][0];
      for (int i = 0; i < 3; i++) stat[0][i][0] = stat[4][2-i][2];
      for (int i = 0; i < 3; i++) stat[4][i][2] = stat[5][2-i][0];
      for (int i = 0; i < 3; i++) stat[5][i][0] = stat[1][i][0];
      for (int i = 0; i < 3; i++) stat[1][i][0] = tmp[i];
    }
    void rightRot() {
      surfaceRot(2);
      for (int i = 0; i < 3; i++) tmp[i] = stat[0][i][2];
      for (int i = 0; i < 3; i++) stat[0][i][2] = stat[1][i][2];
      for (int i = 0; i < 3; i++) stat[1][i][2] = stat[5][i][2];
      for (int i = 0; i < 3; i++) stat[5][i][2] = stat[4][2-i][0];
      for (int i = 0; i < 3; i++) stat[4][i][0] = tmp[2-i];
    }
    void frontRot() {
      surfaceRot(1);
      for (int i = 0; i < 3; i++) tmp[i] = stat[0][2][i];
      for (int i = 0; i < 3; i++) stat[0][2][i] = stat[3][2-i][2];
      for (int i = 0; i < 3; i++) stat[3][i][2] = stat[5][0][i];
      for (int i = 0; i < 3; i++) stat[5][0][i] = stat[2][2-i][0];
      for (int i = 0; i < 3; i++) stat[2][i][0] = tmp[i];
    }
    void backRot() {
      surfaceRot(4);
      for (int i = 0; i < 3; i++) tmp[i] = stat[0][0][i];
      for (int i = 0; i < 3; i++) stat[0][0][i] = stat[2][i][2];
      for (int i = 0; i < 3; i++) stat[2][i][2] = stat[5][2][2-i];
      for (int i = 0; i < 3; i++) stat[5][2][i] = stat[3][i][0];
      for (int i = 0; i < 3; i++) stat[3][i][0] = tmp[2-i];
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      int numRot = Integer.parseInt(br.readLine());
      StringTokenizer st = new StringTokenizer(br.readLine());
      Queue<Character> que = new LinkedList<>();
      while (numRot-- > 0) {
        String command = st.nextToken();
        char surface = command.charAt(0);
        int times = command.charAt(1) == '+' ? 1 : 3;
        while (times-- > 0) que.add(surface);
      }
      Cube cube = new Cube();
      while (!que.isEmpty()) {
        cube.action(que.poll());
      }
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          bw.write(cube.stat[0][i][j]);
        }
        bw.newLine();
      }
    }
    bw.flush();
  }
}
