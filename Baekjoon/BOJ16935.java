import java.io.*;
import java.util.*;

public class BOJ16935 {
  static int N;
  static int M;
  static int[] rowDi = {0, 0, N/2, N/2, 0};
  static int[] colDi = {0, M/2, M/2, 0, 0};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    int numOper = Integer.parseInt(st.nextToken());

    int[][] origin = new int[N][M];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        origin[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    st = new StringTokenizer(br.readLine());
    Queue<Integer> operators = new LinkedList<>();
    for (int i = 0; i < numOper; i++) {
      operators.add(Integer.parseInt(st.nextToken()));
    }

    while (!operators.isEmpty()) {
      int operator = operators.poll();
      switch (operator) {
        case 1:
          upDownInversion(origin);
          break;
        case 2:
          leftRightInversion(origin);
          break;
        case 3:
          origin = rightRotation(origin);
          break;
        case 4:
          origin = leftRotation(origin);
          break;
        case 5:
          origin = groupRightRotation(origin);
          break;
        case 6:
          origin = groupLeftRotation(origin);
          break;
        }
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < origin.length; i++) {
      sb.append(origin[i][0]);
      for (int j = 1; j < origin[0].length; j++) {
        sb.append(' ').append(origin[i][j]);
      }
      sb.append('\n');
    }

    bw.write(sb.toString());
    bw.flush();
  }
  static void upDownInversion(int[][] origin) {
    for (int col = 0; col < M; col++) {
      for (int row = 0; row < N/2; row++) {
        int tmp = origin[row][col];
        origin[row][col] = origin[N - row - 1][col];
        origin[N - row - 1][col] = tmp;
      }
    }
  }
  static void leftRightInversion(int[][] origin) {
    for (int row = 0; row < N; row++) {
      for (int col = 0; col < M/2; col++) {
        int tmp = origin[row][col];
        origin[row][col] = origin[row][M - col - 1];
        origin[row][M - col - 1] = tmp;
      }
    }
  }
  static int[][] copy(int[][] origin) {
    int[][] copy = new int[N][M];
    for (int i = 0; i < N; i++) {
      System.arraycopy(origin[i], 0, copy[i], 0, M);
    }
    return copy;
  }
  static int[][] rightRotation(int[][] origin) {
    int[][] copy = new int[M][N];
    for (int i = 0; i < M; i++) {
      for (int j = 0; j < N; j++) {
        copy[i][j] = origin[N - 1 - j][i];
      }
    }
    return copy;
  }
  static int[][] leftRotation(int[][] origin) {
    int[][] copy = new int[M][N];
    for (int i = 0; i < M; i++) {
      for (int j = 0; j < N; j++) {
        copy[i][j] = origin[j][M - 1 - i];
      }
    }
    return copy;
  }
  static int[][] groupRightRotation(int[][] origin) {
    int[][] copy = copy(origin);
    for (int i = 0; i < N/2; i++) {
      for (int j = 0; j < M/2; j++) {
        for (int k = 0; k < 4; k++) {
          origin[i + rowDi[k + 1]][j + colDi[k + 1]] = copy[i + rowDi[k]][j + colDi[k]];
        }
      }
    }
    return origin;
  }
  static int[][] groupLeftRotation(int[][] origin) {
    int[][] copy = copy(origin);
    for (int i = 0; i < N/2; i++) {
      for (int j = 0; j < M/2; j++) {
        for (int k = 5; k > 0; k--) {
          origin[i + rowDi[k]][j + colDi[k]] = copy[i + rowDi[k - 1]][j + colDi[k - 1]];
        }
      }
    }
    return origin;
  }
}
