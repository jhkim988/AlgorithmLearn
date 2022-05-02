import java.io.*;
import java.util.*;

public class BOJ21608 {
  private static class Student {
    int id, row, col;
    boolean onSeat = false;
    ArrayList<Integer> love;
    Student(int id) {
      this.id = id;
      love = new ArrayList<>();
    }
    void add(int other) {
      love.add(other);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    Student[] std = new Student[n];
    Queue<Student> que = new LinkedList<>();
    for (int i = 0; i < n; i++) {
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
      int seatRow, seatCol;
      int maxLove = 0;
      int maxEmpty = 0;
      Student s = que.poll();
      for (int love : s.love) {
        if (!std[love].onSeat) continue;
        int ptr = std[love].row*std[love].col;
        int get = hm.get(ptr)+1;
        if (maxLove < get) {
          maxLove = get;
          hm.clear();
          maxEmpty = nempty[std[love].row][std[love].col];
        } else if (maxLove == get) {
          if (maxEmpty < nempty[std[love].row][std[love].col]) {

          }
        }
      }

    }
  }
}
