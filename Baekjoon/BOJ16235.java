import java.io.*;
import java.util.*;

public class BOJ16235 {
  public static void main(String[] args) throws IOException {
    int[] rowDi = {-1, -1, -1, 0, 0, 1, 1, 1};
    int[] colDi = {-1, 0, 1, -1, 1, -1, 0, 1};
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    
    ArrayList<ArrayList<PriorityQueue<Integer>>> plant = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      ArrayList<PriorityQueue<Integer>> row = new ArrayList<>();
      plant.add(row);
      for (int j = 0; j < N; j++) {
        row.add(new PriorityQueue<>());
      }
    }
    int[][] foodStat = new int[N][N];
    int[][] foodAdd = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        foodAdd[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int row = Integer.parseInt(st.nextToken()) - 1;
      int col = Integer.parseInt(st.nextToken()) - 1;
      int age = Integer.parseInt(st.nextToken());
      PriorityQueue<Integer> cell = plant.get(row).get(col);
      cell.add(age);
    }
    for (int i = 0; i < N; i++) Arrays.fill(foodStat[i], 5);

    while (k-- > 0) {
      Queue<Integer> treeAdd = new LinkedList<>();
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          Queue<Integer> tmp = new LinkedList<>();
          PriorityQueue<Integer> cell = plant.get(i).get(j);
          while (!cell.isEmpty() && foodStat[i][j] >= cell.peek()) {
            foodStat[i][j] -= cell.peek();
            tmp.add(cell.remove());
          }
          while (!cell.isEmpty()) {
            foodStat[i][j] += cell.remove()/2;
          }
          while(!tmp.isEmpty()) {
            int age = tmp.remove() + 1;
            cell.add(age);
            if (age % 5 == 0) treeAdd.add(i * N + j);
          }
        }
      }

      while (!treeAdd.isEmpty()) {
        int ptr = treeAdd.remove();
        int crntRow = ptr/N;
        int crntCol = ptr%N;
        for (int l = 0; l < 8; l++) {
          int adjRow = crntRow + rowDi[l];
          int adjCol = crntCol + colDi[l];
          if (adjRow < 0 || adjRow >= N || adjCol < 0 || adjCol >= N) continue;
          plant.get(adjRow).get(adjCol).add(1);
        }
      }

      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          foodStat[i][j] += foodAdd[i][j];
        }
      }
    }

    int sum = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j <N; j++) {
        sum += plant.get(i).get(j).size();
      }
    }

    bw.write(sum + "\n");
    bw.flush();
  }
}
