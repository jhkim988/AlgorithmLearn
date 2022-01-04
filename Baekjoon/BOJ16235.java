import java.io.*;
import java.util.*;

public class BOJ16235 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    
    ArrayList<ArrayList<TreeMap<Integer, Integer>>> plant = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      ArrayList<TreeMap<Integer, Integer>> row = new ArrayList<>();
      plant.add(row);
      for (int j = 0; j < N; j++) {
        row.add(new TreeMap<>());
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
      TreeMap<Integer, Integer> cell = plant.get(row).get(col);
      if (cell.containsKey(age)) cell.put(age, cell.get(age) + 1);
      else cell.put(age, 1);
    }
    for (int i = 0; i < N; i++) Arrays.fill(foodStat[i], 5);

    while (k-- > 0) {
      
    }
  }
}
