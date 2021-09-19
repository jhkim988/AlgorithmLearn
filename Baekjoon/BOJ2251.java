import java.io.*;
import java.util.*;

public class BOJ2251 {
  private static class Simulation {
    static int[] lim = new int[3];
    int[] water;
    int count;
    Simulation(int A, int B, int C, int count) {
      water = new int[3];
      water[0] = A;
      water[1] = B;
      water[2] = C;
      this.count = count;
    }

    Simulation move(int from, int to) {
      int[] nextWater = water.clone();
      if (water[from] < lim[to] - water[to]) {
        nextWater[from] = 0;
        nextWater[to] += water[from];
      } else {
        nextWater[from] -= lim[to] - water[to];
        nextWater[to] = lim[to];
      }
      return new Simulation(nextWater[0], nextWater[1], nextWater[2], count + 1);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (this.getClass() != o.getClass()) return false;
      Simulation other = (Simulation) o;
      for (int i = 0; i < 3; i++) {
        if (water[i] != other.water[i]) {
          return false;
        }
      }
      return true;
    }

    @Override
    public int hashCode() {
      final int prime = 7;
      int result = 1;
      for (int i = 0; i < 3; i++) {
        result = result * prime + (int) (water[i] ^ (water[i] >>> 1));
      }
      return result;
    }
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    for (int i = 0; i < 3; i++) {
      Simulation.lim[i] = Integer.parseInt(st.nextToken());
    }
    
    ArrayList<Integer> result = BFS();
    StringBuilder sb = new StringBuilder();
    for (int ans : result) {
      sb.append(ans).append(' ');
    }
    sb.append('\n');
    bw.write(sb.toString());
    bw.flush();
  }
  static ArrayList<Integer> BFS() {
    ArrayList<Integer> result = new ArrayList<>();

    Queue<Simulation> que = new LinkedList<>();
    Simulation start = new Simulation(0, 0, Simulation.lim[2], 0);
    HashSet<Simulation> hs = new HashSet<>();
    que.add(start);
    hs.add(start);
    while (!que.isEmpty()) {
      Simulation crnt = que.poll();
      
      if (crnt.water[0] == 0) {
        result.add(crnt.water[2]);
      }

      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          // move i -> j
          if (i == j) continue;
          if (crnt.water[i] == 0) continue;
          Simulation next = crnt.move(i, j);
          if (hs.contains(next)) continue;
          que.add(next);
          hs.add(next);
        }
      }
    }

    Collections.sort(result);
    return result;
  }
}