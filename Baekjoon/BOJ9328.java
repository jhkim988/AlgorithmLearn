import java.io.*;
import java.util.*;

public class BOJ9328 {
  private static class Pair {
    int row;
    int col;
    Pair(int row, int col) {
      this.row = row;
      this.col = col;
    }
    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (this.getClass() != o.getClass()) {
        return false;
      }
      Pair other = (Pair) o;
      if (this.row == other.row && this.col == other.col) {
        return true;
      }
      return false;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int h = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      char[][] map = new char[h][w];
      boolean[] hasKey = new boolean['z' - 'a' + 1]; 
      for (int i = 0; i < h; i++) {
        map[i] = br.readLine().toCharArray();
      }
      String key = br.readLine();
      if (key.charAt(0) != '0') {
        for (int i = 0; i < key.length(); i++) {
          hasKey[key.charAt(i) - 'a'] = true;
        }
      }

      // bfs
      int[] rowDi = {-1, 0, 1, 0};
      int[] colDi = {0, -1, 0, 1};
      boolean[][] marked = new boolean[h][w];
      Queue<Pair> que = new LinkedList<>();
      HashMap<Character, Queue<Pair>> lockedDoor = new HashMap<>();
      int count = 0;
      for (int i = 0; i < h; i++) {
        for (int j = 0; j < w; j++) {
          if (i != 0 && i != h - 1) {
            if (j != 0 && j != w - 1) {
              continue;
            } 
          }
          Pair start = new Pair(i, j);
          que.add(start);
          // System.out.println("start: " + i + ", " + j);
          marked[i][j] = true;
          if (map[i][j] == '$') {
            count++;
            map[i][j] = '.';
          }
          while (!que.isEmpty()) {
            Pair crnt = que.poll();
            char crntChar = map[crnt.row][crnt.col];
            if (crntChar == '*') {
              continue;
            }
            if ('A' <= crntChar && crntChar <= 'Z') {
              if (hasKey[crntChar - 'A']) {
                map[crnt.row][crnt.col] = '.';
              } else {
                if (!lockedDoor.containsKey((char) (crntChar + 32))) {
                  Queue<Pair> door = new LinkedList<>();
                  door.add(crnt);
                  lockedDoor.put((char) (crntChar + 32), door);
                } else {
                  lockedDoor.get((char) (crntChar + 32)).add(crnt);
                }
                continue;
              }
            }
            // System.out.println("crnt: " + crnt.row + ", " + crnt.col);
            for (int k = 0; k < 4; k++) {
              int nextRow = crnt.row + rowDi[k];
              int nextCol = crnt.col + colDi[k];
              if (nextRow < 0 || nextRow >= h || nextCol < 0 || nextCol >= w) continue;
              if (marked[nextRow][nextCol]) continue;
              char nextChar = map[nextRow][nextCol];
              Pair next = new Pair(nextRow, nextCol);
              if (nextChar == '*') {
                marked[nextRow][nextCol] = true;
                continue;
              }
              if ('A' <= nextChar && nextChar <= 'Z') {
                if (hasKey[nextChar - 'A']) {
                  que.add(next);
                  map[nextRow][nextCol] = '.';
                } else {
                  // System.out.println("Find LOCK DOOR: " + nextRow + ", " + nextCol);
                  if (!lockedDoor.containsKey((char) (nextChar + 32))) {
                    Queue<Pair> door = new LinkedList<>();
                    door.add(next);
                    lockedDoor.put((char) (nextChar + 32), door);
                  } else {
                    lockedDoor.get((char) (nextChar + 32)).add(next);
                  }
                }
              }
              if (map[nextRow][nextCol] == '.') {
                que.add(next);
                marked[nextRow][nextCol] = true;
              }
              if ('a' <= nextChar && nextChar <= 'z'){
                // System.out.println("Get Key: " + nextRow + ", " + nextCol);
                que.add(next);
                map[nextRow][nextCol] = '.';
                hasKey[nextChar - 'a'] = true;
                if (lockedDoor.containsKey(nextChar)) {
                  // System.out.println("OPEN DOOR:" + nextRow + ", " + nextCol);
                  for (Pair door : lockedDoor.get(nextChar)) {
                    que.add(door);
                  }
                }
              }
              if (nextChar == '$') {
                que.add(next);
                map[nextRow][nextCol] = '.';
                marked[nextRow][nextCol] = true;
                count++;
              }
            }
          }
        }
      }
      bw.write(count + "\n");
    }
    bw.flush();
  }  
}
