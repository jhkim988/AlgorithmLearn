import java.io.*;
import java.util.*;

public class BOJ17143 {
  static int numRow;
  static int numCol;
  private static class Shark {
    int speed, direction, size;
    Shark(int speed, int direction, int size) {
      this.speed = speed;
      this.direction = direction;
      this.size = size;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    numRow = Integer.parseInt(st.nextToken());
    numCol = Integer.parseInt(st.nextToken());
    int numShark = Integer.parseInt(st.nextToken());
    HashMap<Integer, Shark> hm = new HashMap<>();

    for (int i = 0; i < numShark; i++) {
      st = new StringTokenizer(br.readLine());
      int row = Integer.parseInt(st.nextToken()) - 1;
      int col = Integer.parseInt(st.nextToken()) - 1;
      int speed = Integer.parseInt(st.nextToken());
      int direction = Integer.parseInt(st.nextToken());
      int size = Integer.parseInt(st.nextToken());
      hm.put(row * numCol + col, new Shark(speed, direction, size));
    }

    int num = 0;
    for (int fisher = 0; fisher < numCol; fisher++) {
      int checker = 0;
      while (checker < numRow && !hm.containsKey(checker * numCol + fisher)) {
        checker++;
      }
      if (checker < numRow) {
        num += hm.get(checker * numCol + fisher).size;
        hm.remove(checker * numCol + fisher);
      }
      HashMap<Integer, Shark> hmNewOne = new HashMap<>();
      for (Map.Entry<Integer, Shark> entry : hm.entrySet()) {
        int ptr = entry.getKey();
        Shark s = entry.getValue();
        int row = ptr/numCol;
        int col = ptr%numCol;
        switch (s.direction) {
          case 1: {
            if (row - s.speed >= 0) {
            row -= s.speed;
            } else {
              int diff = s.speed - row;
              int endToEnd = diff/(numRow - 1);
              int remainder = diff%(numRow - 1);
              if (endToEnd % 2 == 0) {
                s.direction = 2;
                row = remainder;
              } else {
                row = numRow - 1 - remainder;
              }
            }
            break;
          }
          case 2: {
            if (row + s.speed < numRow) {
              row += s.speed;
            } else {
              int diff = s.speed - (numRow - 1 - row);
              int endToEnd = diff/(numRow - 1);
              int remainder = diff%(numRow - 1);
              if (endToEnd % 2 == 0) {
                s.direction = 1;
                row = numRow - 1 - remainder;
              } else {
                row = remainder;
              }
            }
            break;
          }
          case 3: {
            if (col + s.speed < numCol) {
              col += s.speed;
            } else {
              int diff = s.speed - (numCol - 1 - col);
              int endToEnd = diff/(numCol - 1);
              int remainder = diff%(numCol - 1);
              if (endToEnd % 2 == 0) {
                s.direction = 4;
                col = numCol - 1 - remainder;
              } else {
                col = remainder;
              }
            }
            break;
          }
          default: {
          if (col - s.speed >= 0) {
            col -= s.speed;
            } else {
              int diff = s.speed - col;
              int endToEnd = diff/(numCol - 1);
              int remainder = diff%(numCol - 1);
              if (endToEnd % 2 == 0) {
                s.direction = 3;
                col = remainder;
              } else {
                col = numCol - 1 - remainder;
              }
            }
            break;
          }
        }
        if (hmNewOne.containsKey(row * numCol + col)) {
          if (hmNewOne.get(row * numCol + col).size < s.size) {
            hmNewOne.put(row * numCol + col, s);
          }
        } else {
          hmNewOne.put(row * numCol + col, s);
        }
      }
      hm = hmNewOne;
    }

    bw.write(num + "\n");
    bw.flush();
  }
}
