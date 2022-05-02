import java.io.*;
import java.util.*;

public class BOJ21608 {
  private static class Student {
    int id, row, col;
    boolean onSeat = false;
    HashSet<Integer> love;
    Student(int id) {
      this.id = id;
      love = new HashSet<>();
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    Student[] std = new Student[n*n+1];
    Queue<Student> que = new LinkedList<>();
    for (int i = 0; i < n*n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int id = Integer.parseInt(st.nextToken());
      Student s = new Student(id);
      for (int j = 0; j < 4; j++) {
        s.love.add(Integer.parseInt(st.nextToken()));
      }
      std[id] = s;
      que.add(s);
    }

    int[][] nempty = new int[n][n];
    for (int i = 0; i < n; i++) {
      if (i == 0 || i == n-1) {
        nempty[i][0] = nempty[i][n-1] = 2;
        for (int j = 1; j < n-1; j++) {
          nempty[i][j] = 3;
        }
      } else {
        nempty[i][0] = nempty[i][n-1] = 3;
        for (int j = 1; j < n-1; j++) {
          nempty[i][j] = 4;
        }
      }
    }
    int[] rowDi = {-1, 0, 1, 0};
    int[] colDi = {0, -1, 0, 1};
    int[][] seats = new int[n][n];
    HashMap<Integer, Integer> hm = new HashMap<>();
    while (!que.isEmpty()) {
      hm.clear();
      int seatRow = -1, seatCol = -1;
      int maxLove = -1;
      Student s = que.poll();
      for (int love : s.love) {
        if (!std[love].onSeat) continue;
        for (int k = 0; k < 4; k++) {
          int adjRow = std[love].row + rowDi[k];
          int adjCol = std[love].col + colDi[k];
          if (adjRow < 0 || adjRow >= n || adjCol < 0 || adjCol >= n) continue;
          if (seats[adjRow][adjCol] != 0) continue;
          int ptr = adjRow*n+adjCol;
          int get = hm.containsKey(ptr) ? hm.get(ptr)+1 : 1;
          hm.put(ptr, get);
          if (maxLove < get) maxLove = get;
        }
      }
      int maxEmpty = -1;
      for (Map.Entry<Integer, Integer> es : hm.entrySet()) {
        if (es.getValue() != maxLove) continue;
        int ptr = es.getKey();
        int row = ptr/n, col = ptr%n;
        if (maxEmpty < nempty[row][col]) {
          maxEmpty = nempty[row][col];
          seatRow = row;
          seatCol = col;
        } else if (maxEmpty == nempty[row][col]) {
          if (row < seatRow || (row == seatRow && col < seatCol)) {
            seatRow = row;
            seatCol = col;
          }
        }
      }

      if (seatRow == -1 || seatCol == -1) {
        int max = -1;
        for (int i = 0; i < n; i++) {
          for (int j = 0; j < n; j++) {
            if (seats[i][j] != 0) continue;
            if (max < nempty[i][j]) {
              seatRow = i; seatCol = j;
              max = nempty[i][j];
            }
          }
        }
      }
      
      for (int k = 0; k < 4; k++) {
        int adjRow = seatRow + rowDi[k];
        int adjCol = seatCol + colDi[k];
        if (adjRow < 0 || adjRow >= n || adjCol < 0 || adjCol >= n) continue;
        nempty[adjRow][adjCol]--;
      }

      seats[seatRow][seatCol] = s.id;
      s.row = seatRow;
      s.col = seatCol;
      s.onSeat = true;
    }
    int sum = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        int num = 0;
        for (int k = 0; k < 4; k++) {
          int adjRow = i + rowDi[k];
          int adjCol = j + colDi[k];
          if (adjRow < 0 || adjRow >= n || adjCol < 0 || adjCol >= n) continue;
          if (std[seats[i][j]].love.contains(seats[adjRow][adjCol])) num++;
        }
        if (num == 0) continue;
        int add = 1;
        while (--num > 0) add *= 10;
        sum += add;
      }
    }
    bw.write(Integer.toString(sum));
    bw.flush();
  }
}
