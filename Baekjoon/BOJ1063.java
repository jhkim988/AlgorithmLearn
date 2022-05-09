import java.io.*;
import java.util.*;

public class BOJ1063 {
  static HashMap<String, Integer> hm;
  static int[] rowDi = {0, 0, -1, 1, 1, 1, -1, -1};
  static int[] colDi = {1, -1, 0, 0, 1, -1, 1, -1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] king , stone;
    StringTokenizer st = new StringTokenizer(br.readLine());
    king = st.nextToken().toCharArray();
    stone = st.nextToken().toCharArray();
    int n = Integer.parseInt(st.nextToken());
    hm = new HashMap<>();
    hm.put("R", 0);
    hm.put("L", 1);
    hm.put("B", 2);
    hm.put("T", 3);
    hm.put("RT", 4);
    hm.put("LT", 5);
    hm.put("RB", 6);
    hm.put("LB", 7);
    while (n-- > 0) {
      int idx = hm.get(br.readLine());
      move(king, stone, idx);
    }
    bw.write(king);
    bw.newLine();
    bw.write(stone);
    bw.flush();
  }
  static boolean move(char[] king, char[] stone, int direction) {
    int nextRow = king[1] + rowDi[direction];
    int nextCol = king[0] + colDi[direction];
    if (nextRow < '1' || nextRow > '8' || nextCol < 'A' || nextCol > 'H') return false;
    if (stone[1] == nextRow && stone[0] == nextCol) {
      int nextStoneRow = stone[1] + rowDi[direction];
      int nextStoneCol = stone[0] + colDi[direction];
      if (nextStoneRow < '1' || nextStoneRow > '8' || nextStoneCol < 'A' || nextStoneCol > 'H') return false;
      stone[1] = (char) nextStoneRow;
      stone[0] = (char) nextStoneCol;
    }
    king[1] = (char) nextRow;
    king[0] = (char) nextCol;
    return true;
  }
}
