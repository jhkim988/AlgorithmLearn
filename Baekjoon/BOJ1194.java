import java.io.*;
import java.util.*;

public class BOJ1194 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int row = Integer.parseInt(st.nextToken());
    int col = Integer.parseInt(st.nextToken());
    char[][] map = new char[row][];
    for (int i = 0; i < row; i++) {
      map[i] = br.readLine().toCharArray();
    }
    
  }
}
