import java.io.*;
import java.util.*;

public class BOJ16945 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int[][] input = new int[3][3];
    for (int i = 0; i < 3; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 3; j++) {
        input[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    
  }
}
