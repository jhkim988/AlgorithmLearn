import java.io.*;
import java.util.Arrays;

public class BOJ17626 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int num = Integer.parseInt(br.readLine());
    int len = 230;
    int[][] table = new int[len][len];

    for (int i = 0; i < len; i++) {
      table[0][i] = i * i;
      if (table[0][i] == num) {
        bw.write(1 + "\n");
        bw.flush();
        return;
      }
    }

    for (int i = 1; i < len; i++) {
      for (int j = i; j < len; j++) {
        table[i][j] = table[0][i] + j * j;
        if (table[i][j] == num) {
          bw.write(2 + "\n");
          bw.flush();
          return;
        }
      }
    }

    for (int i = 1; i < len; i++) {
      for (int j = i; j < len; j++){
        if (Arrays.binarySearch(table[0], num - i * i - j * j) >= 0) {
          bw.write(3 + "\n");
          bw.flush();
          return;
        }
      }
    }
    bw.write(4 + "\n");
    bw.flush();
    return;
  }
}
