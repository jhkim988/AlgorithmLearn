import java.io.*;
import java.util.*;

public class BOJ12100 {
  private static class Simulation {
    int N;
    int[][] stat;
    int max;
    Simulation(int[][] stat) {
      this.N = stat.length;
      this.stat = stat.clone();
      this.max = 0;
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (max < stat[i][j]) {
            max = stat[i][j];
          }
        }
      }
    }
    Simulation act(int action) {
      int[][] statNew = new int[N][N];
      Deque<Integer> deque = new LinkedList<>();
      boolean mergeflag = false;
      if (action == 0) { // up
        for (int j = 0; j < N; j++) {
          for (int i = 0; i < N; i++) {
            if (stat[i][j] == 0) continue;
            if (!deque.isEmpty()) {
              if (!mergeflag && deque.peekLast() == stat[i][j]) {
                deque.addLast(2 * deque.removeLast());
                mergeflag = true;
              } else {
                deque.addLast(stat[i][j]);
                mergeflag = false;
              }
            } else {
              deque.addLast(stat[i][j]);
              mergeflag = false;
            }
          }
          int k = 0;
          while (!deque.isEmpty()) {
            statNew[k++][j] = deque.removeFirst();
          }
        }
      } else if (action == 1) { // left
        for (int i = 0; i < N; i++) {
          for (int j = 0; j < N; j++) {
            if (stat[i][j] == 0) continue;
            if (!mergeflag && !deque.isEmpty()) {
              if (deque.peekLast() == stat[i][j]) {
                deque.addLast(2 * deque.removeLast());
                mergeflag = true;
              } else {
                deque.addLast(stat[i][j]);
                mergeflag = false;
              }
            } else {
              deque.addLast(stat[i][j]);
              mergeflag = false;
            }
          }
          int k = 0;
          while (!deque.isEmpty()) {
            statNew[i][k++] = deque.removeFirst();
          }
        }
      } else if (action == 2) { // down
        for (int j = 0; j < N; j++) {
          for (int i = N - 1; i >= 0; i--) {
            if (stat[i][j] == 0) continue;
            if (!deque.isEmpty()) {
              if (!mergeflag && deque.peekLast() == stat[i][j]) {
                deque.addLast(2 * deque.removeLast());
                mergeflag = true;
              } else {
                deque.addLast(stat[i][j]);
                mergeflag = false;
              }
            } else {
              deque.addLast(stat[i][j]);
              mergeflag = false;
            }
          }
          int k = N - 1;
          while (!deque.isEmpty()) {
            statNew[k--][j] = deque.removeFirst();
          }
        }
      } else if (action == 3) { // right
        for (int i = 0; i < N; i++) {
          for (int j = N - 1; j >= 0; j--) {
            if (stat[i][j] == 0) continue;
            if (!deque.isEmpty()) {
              if (!mergeflag && deque.peekLast() == stat[i][j]) {
                deque.addLast(2 * deque.removeLast());
                mergeflag = true;
              } else {
                deque.addLast(stat[i][j]);
                mergeflag = false;
              }
            } else {
              deque.addLast(stat[i][j]);
              mergeflag = false;
            }
          }
          int k = N - 1;
          while (!deque.isEmpty()) {
            statNew[i][k--] = deque.removeFirst();
          }
        }
      }
      return new Simulation(statNew);
    }
  }
  private static class Action {
    int act;
    int count;
    Simulation simulation;
    Action(int act, int count, Simulation simulation) {
      this.act = act;
      this.count = count;
      this.simulation = simulation;
    }
  }
  public static void main(String[] args) throws IOException {
    final int SIMULATION_LIMIT = 5;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;
    int N = Integer.parseInt(br.readLine());
    int[][] stat =  new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        stat[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    Simulation simul = new Simulation(stat);
    Queue<Action> que = new LinkedList<>();
    for (int i = 0; i < 4; i++) {
      que.add(new Action(i, 1, simul));
    }
    int max = 0;
    while (!que.isEmpty()) {
      Action crnt = que.poll();
      Simulation simulNew = crnt.simulation.act(crnt.act);
      if (crnt.count > SIMULATION_LIMIT) {
        break;
      }
      if (max < simulNew.max) {
        max = simulNew.max;
      }
      for (int i = 0; i < 4; i++) {
        que.add(new Action(i, crnt.count + 1, simulNew));
      }
    }
    bw.write(max + "\n");
    bw.flush();
  }
}
