import java.io.*;
import java.util.*;

public class BOJ17085 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int numRow = Integer.parseInt(st.nextToken());
    int numCol = Integer.parseInt(st.nextToken());
    char[][] data = new char[numRow][];
    for (int i = 0; i < numRow; i++) {
      data[i] = br.readLine().toCharArray();
    }
  }
}
