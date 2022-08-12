import java.io.*;
import java.util.*;

public class BOJ1507 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[][] arr = new int[n][n];
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < n; j++) {
        arr[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    bw.write(Integer.toString(solution(arr)));
    bw.flush();
  }
  static int solution(int[][] arr) {
    int n = arr.length;
    boolean[][] disconnected = new boolean[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        for (int k = 0; k < n; k++) {
          if (k == i || k == j) continue;
          if (arr[i][k]+arr[k][j] == arr[i][j]) {
            disconnected[i][j] = true;
          } else if (arr[i][k]+arr[k][j] < arr[i][j]) {
            return -1;
          }
        }
      }
    }

    int sum = 0;
    for (int i = 0; i < n; i++) {
      for (int j = i+1; j < n; j++) {
        if (disconnected[i][j]) continue;
        sum += arr[i][j];
      }
    }
    return sum;
  }
}