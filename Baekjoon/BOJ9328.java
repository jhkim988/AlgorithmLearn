import java.io.*;
import java.util.*;

public class BOJ9328 {
  static int[] rowDi = {-1, 0, 1, 0};
  static int[] colDi = {0, -1, 0, 1};
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
      char[] key = br.readLine().toCharArray();
      if (key[0] != '0') {
        for (int i = 0; i < key.length; i++) {
          hasKey[key[i] - 'a'] = true;
        }
      }

      // bfs
      boolean[][] marked = new boolean[h][w];
      Queue<Pair> que = new LinkedList<>();
      HashMap<Character, Queue<Pair>> lockedDoor = new HashMap<>();
      int count = 0;

      for (int i = 0; i < h; i++) {
        count += enqueue(w, h, map, i, 0, marked, que, hasKey, lockedDoor);
        count += enqueue(w, h, map, i, w - 1, marked, que, hasKey, lockedDoor);
      }
      for (int j = 0; j < w; j++) {
        count += enqueue(w, h, map, 0, j, marked, que, hasKey, lockedDoor);
        count += enqueue(w, h, map, h - 1, j, marked, que, hasKey, lockedDoor);
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

      bw.write(count + "\n");
    }
    bw.flush();
  }  
  static int enqueue(int w, int h, char[][] map, int i, int j, boolean[][] marked, Queue<Pair> que, boolean[] hasKey, HashMap<Character, Queue<Pair>> lockedDoor) {
    int count = 0;
    if (map[i][j] == '.') {
      marked[i][j] = true;
      que.add(new Pair(i, j));
    } else if (map[i][j] == '$') {
      count++;
      map[i][j] = '.';
      marked[i][j] = true;
      que.add(new Pair(i, j));
    } else if ('a' <= map[i][j] && map[i][j] <= 'z') {
      hasKey[map[i][j] - 'a'] = true;
      map[i][j] = '.';
      marked[i][j] = true;
      que.add(new Pair(i, j));
      if (lockedDoor.containsKey(map[i][j])) {
        for (Pair door : lockedDoor.get(map[i][j])) {
          que.add(door);
        }
      }
    } else if ('A' <= map[i][j] && map[i][j] <= 'Z') {
      if (hasKey[map[i][j] - 'A']) {
        map[i][j] = '.';
        marked[i][j] = true;
        que.add(new Pair(i, j));
      } else {
        if (!lockedDoor.containsKey((char) (map[i][j] + 32))) {
          Queue<Pair> door = new LinkedList<>();
          door.add(new Pair(i, j));
          lockedDoor.put((char) (map[i][j] + 32), door);
        } else {
          lockedDoor.get((char) (map[i][j] + 32)).add(new Pair(i, j));
        }
      }
    }
    return count;
  }
}


