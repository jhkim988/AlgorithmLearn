import java.io.*;
import java.util.*;

public class BOJ2490 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] list = {'E', 'A', 'B', 'C', 'D'};
    for (int i = 0; i < 3; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int numZero = 0;
      for (int j = 0; j < 4; j++) {
        int input = Integer.parseInt(st.nextToken());
        if (input == 0) numZero++;
      }
      bw.write(list[numZero]);
      bw.newLine();
    }
    bw.flush();
  }
}
