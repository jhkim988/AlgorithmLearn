import java.io.*;
import java.util.*;

public class BOJ17140 {
  private static class Pair implements Comparable<Pair> {
    int key, count;
    Pair(int key, int count) {
      this.key = key;
      this.count = count;
    }
    @Override
    public int compareTo(Pair other) {
      return this.count != other.count ? this.count - other.count : this.key - other.key;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int targetRow = Integer.parseInt(st.nextToken()) - 1;
    int targetCol = Integer.parseInt(st.nextToken()) - 1;
    int targetValue = Integer.parseInt(st.nextToken());
    int[][] arr = new int[3][3];
    for (int i = 0; i < 3; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 3; j++) {
        arr[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int time = 0;
    while (targetRow >= arr.length || targetCol >= arr[0].length || arr[targetRow][targetCol] != targetValue) {
      ArrayList<ArrayList<Pair>> arrList = new ArrayList<>();
      int[] count = new int[101];
      int rowLen = arr.length;
      int colLen = arr[0].length;
      if (rowLen >= colLen) { // R operator
        for (int i = 0; i < rowLen; i++) arrList.add(new ArrayList<>());
        for (int i = 0; i < rowLen; i++) {
          for (int j = 0; j < colLen; j++) {
            if (arr[i][j] == 0) continue;
            count[arr[i][j]]++;
          }
          ArrayList<Pair> ithRow = arrList.get(i);
          for (int k = 0; k <= 100; k++) {
            if (count[k] == 0) continue;
            ithRow.add(new Pair(k, count[k]));
          }
          Collections.sort(ithRow);
          Arrays.fill(count, 0);
        }
        int maxCol = 0;
        for (ArrayList<Pair> al : arrList) {
          if (maxCol < al.size()) maxCol = al.size();
        }
        maxCol = Math.min(100, maxCol * 2);
        arr = new int[rowLen][maxCol];
        for (int i = 0; i < rowLen; i++) {
          ArrayList<Pair> ithRow = arrList.get(i);
          int len = ithRow.size();
          for (int j = 0; j < len; j++) {
            if (2*j < 100) arr[i][2*j] = ithRow.get(j).key;
            if (2*j + 1 < 100) arr[i][2*j + 1] = ithRow.get(j).count;
          }
        }
      } else { // C operator
        for (int j = 0; j < colLen; j++) arrList.add(new ArrayList<>());
        for (int j = 0; j < colLen; j++) {
          for (int i = 0; i < rowLen; i++) {
            if (arr[i][j] == 0) continue;
            count[arr[i][j]]++;
          }
          ArrayList<Pair> jthCol = arrList.get(j);
          for (int k = 0; k <= 100; k++) {
            if (count[k] == 0) continue;
            jthCol.add(new Pair(k, count[k]));
          }
          Collections.sort(jthCol);
          Arrays.fill(count, 0);
        }
        int maxRow = 0;
        for (ArrayList<Pair> al : arrList) {
          if (maxRow < al.size()) maxRow = al.size();
        }
        maxRow = Math.min(100, maxRow * 2);
        arr = new int[maxRow][colLen];
        for (int j = 0; j < colLen; j++) {
          ArrayList<Pair> jthCol = arrList.get(j);
          int len = jthCol.size();
          for (int i = 0; i < len; i++) {
            if (2*i < 100) arr[2*i][j] = jthCol.get(i).key;
            if (2*i + 1 < 100) arr[2*i + 1][j] = jthCol.get(i).count;
          }
        }
      }
      time++;
      if (time > 100) {
        bw.write("-1\n");
        bw.flush();
        return;
      }
    }
    bw.write(time + "\n");
    bw.flush();
  }
}
