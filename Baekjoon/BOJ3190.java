import java.io.*;
import java.util.*;

public class BOJ3190 {
  static int n;
  static boolean[][] map;
  static int[] rowDi = {0, 1, 0, -1};
  static int[] colDi = {1, 0, -1, 0};
  private static class Order {
    int time;
    char direction;
    Order(int time, char direction) {
      this.time = time;
      this.direction = direction;
    }
  }
  private static class Pair {
    int row, col;
    Pair(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }
  private static class Snake {
    Pair head;
    int direction; // right: 0, down: 1, left: 2, up: 3
    Deque<Pair> tail;
    boolean[][] occupied;
    Snake() {
      head = new Pair(0, 0);
      direction = 0;
      occupied = new boolean[n][n];
      occupied[0][0] = true;
      tail = new LinkedList<>();
      tail.add(head);
    }
    boolean move() {
      int nextRow = head.row + rowDi[direction];
      int nextCol = head.col + colDi[direction];
      if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) return false;
      if (occupied[nextRow][nextCol]) return false;
      Pair nextHead = new Pair(nextRow, nextCol);
      tail.addFirst(nextHead);
      occupied[nextRow][nextCol] = true;
      head = nextHead;
      if (map[nextRow][nextCol]) {
        map[nextRow][nextCol] = false;
      } else {
        Pair t = tail.removeLast();
        occupied[t.row][t.col] = false;
      }
      return true;
    }
    void changeDirection(char d) {
      if (d == 'L') {
        direction--;
        if (direction < 0) direction += 4;
      } else { // 'D'
        direction++;
        if (direction > 3) direction -= 4;
      }
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    n = Integer.parseInt(br.readLine());
    map = new boolean[n][n];
    int numApple = Integer.parseInt(br.readLine());
    for (int i = 0; i < numApple; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int row = Integer.parseInt(st.nextToken()) -1;
      int col = Integer.parseInt(st.nextToken()) -1;
      map[row][col] = true;
    }

    int numOrder = Integer.parseInt(br.readLine());
    Queue<Order> order = new LinkedList<>();
    for (int i = 0; i < numOrder; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int time = Integer.parseInt(st.nextToken());
      char direction = st.nextToken().charAt(0);
      order.add(new Order(time, direction));
    }

    int time = 0;
    Snake s = new Snake();
    while (true) {
      time++;
      if (!s.move()) break;
      if (!order.isEmpty() && order.peek().time == time) {
        Order o = order.poll();
        s.changeDirection(o.direction);
      }
    }
    bw.write(Integer.toString(time));
    bw.newLine();
    bw.flush();
  }
}
