import java.io.*;
import java.util.*;

public class BOJ20056 {
  static int n;
  static int[] rowDi = {-1, -1, 0, 1, 1, 1, 0, -1};
  static int[] colDi = {0, 1, 1, 1, 0, -1, -1, -1};
  private static class FireBall {
    int row, col, mass, speed, direction;
    FireBall(int row, int col, int mass, int speed, int direction) {
      this.row = row;
      this.col = col;
      this.mass = mass;
      this.speed = speed;
      this.direction = direction;
    }
  }
  static Queue<FireBall> que, tmp;
  static HashMap<Integer, HashSet<FireBall>> hm;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    que = new LinkedList<>();
    tmp = new LinkedList<>();
    hm = new HashMap<>();
  
    while (m-- > 0) {
      st = new StringTokenizer(br.readLine());
      int row = Integer.parseInt(st.nextToken())-1;
      int col = Integer.parseInt(st.nextToken())-1;
      int mass = Integer.parseInt(st.nextToken());
      int speed = Integer.parseInt(st.nextToken());
      int direction = Integer.parseInt(st.nextToken());
      que.add(new FireBall(row, col, mass, speed, direction));
    }
    while (k-- > 0) {
      hm.clear();
      while (!que.isEmpty()) {
        FireBall crnt = que.poll();
        int ptr = move(crnt);
        if (hm.containsKey(ptr)) {
          hm.get(ptr).add(crnt);
        } else {
          HashSet<FireBall> hs = new HashSet<>();
          hs.add(crnt);
          hm.put(ptr, hs);
        }
      }
      for (Map.Entry<Integer, HashSet<FireBall>> e : hm.entrySet()) {
        if (e.getValue().size() == 1) {
          for (FireBall fb : e.getValue()) tmp.add(fb);
        } else {
          int numOdd = 0, numEven = 0, sumMass = 0, sumSpeed = 0;
          for (FireBall fb : e.getValue()) {
            if (fb.direction % 2 == 0) numEven++;
            else numOdd++;
            sumMass += fb.mass;
            sumSpeed += fb.speed;
          }
          if (sumMass/5 == 0) continue;
          int d = (numOdd == 0 || numEven == 0) ? 0 : 1;
          for (;d < 8; d += 2) {
            tmp.add(new FireBall(e.getKey()/n, e.getKey()%n, sumMass/5, sumSpeed/e.getValue().size(), d));
          }
        }
      }
      while (!tmp.isEmpty()) que.add(tmp.poll());
    }
    int answer = 0;
    while (!que.isEmpty()) answer += que.poll().mass;
    bw.write(Integer.toString(answer));
    bw.flush();
  }
  static int move(FireBall fireball) {
    fireball.row += fireball.speed*rowDi[fireball.direction];
    fireball.col += fireball.speed*colDi[fireball.direction];
    if (fireball.row < 0) fireball.row += (-fireball.row/n+1)*n;
    if (fireball.col < 0) fireball.col += (-fireball.col/n+1)*n;
    fireball.row %= n;
    fireball.col %= n;
    return fireball.row*n+fireball.col;
  }

}
