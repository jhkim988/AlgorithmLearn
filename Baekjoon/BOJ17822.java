import java.io.*;
import java.util.*;

public class BOJ17822 {
  static int[] rowDi = {-1, 0, 1, 0};
  static int[] colDi = {0, -1, 0, 1};
  static int maxRadius;
  static int numOnDisk;
  private static class Pair {
    int row, col, value;
    Pair(int row, int col, int value) {
      this.row = row;
      this.col= col;
      this.value = value;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    maxRadius = Integer.parseInt(st.nextToken());
    numOnDisk = Integer.parseInt(st.nextToken());
    int numRot = Integer.parseInt(st.nextToken());

    int totSum = 0;
    int num = numOnDisk * maxRadius;
    int[][] diskInfo = new int[maxRadius][numOnDisk];
    int[]rotInfo = new int[maxRadius];
    for (int i = 0; i < maxRadius; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < numOnDisk; j++) {
        diskInfo[i][j] = Integer.parseInt(st.nextToken());
        totSum += diskInfo[i][j];
      }
    }

    while(numRot-- > 0) {
      st = new StringTokenizer(br.readLine());
      int disk = Integer.parseInt(st.nextToken());
      boolean ccw = Integer.parseInt(st.nextToken()) == 1;
      int count = Integer.parseInt(st.nextToken());

      boolean hasElimination = false;
      for (int i = disk; i <= maxRadius; i += disk) {
        rotInfo[i - 1] += ccw ? count : -count;
      }
      // BFS
      boolean[][] visit = new boolean[maxRadius][numOnDisk];
      for (int i = 0; i < maxRadius; i++) {
        for (int j = 0; j < numOnDisk; j++) {
          int jIdx = getIdx(j, rotInfo[i]);
          if (visit[i][j] || diskInfo[i][jIdx] == 0) continue;
          visit[i][j] = true;
          Queue<Pair> que = new LinkedList<>();
          que.add(new Pair(i, j, diskInfo[i][jIdx]));
          while (!que.isEmpty()) {
            Pair crnt = que.poll();
            int crntJIdx = getIdx(crnt.col, rotInfo[crnt.row]);
            for (int k = 0; k < 4; k++) {
              int adjRadius = crnt.row + rowDi[k];
              int adjIdx = crnt.col + colDi[k];
              if (adjRadius < 0 || adjRadius >= maxRadius) continue;
              if (adjIdx < 0) adjIdx += numOnDisk;
              else if (adjIdx >= numOnDisk) adjIdx -= numOnDisk;
              if (visit[adjRadius][adjIdx]) continue;
              int adjJIdx = getIdx(adjIdx, rotInfo[adjRadius]);
              if (crnt.value != diskInfo[adjRadius][adjJIdx]) continue;
              visit[adjRadius][adjIdx] = true;
              que.add(new Pair(adjRadius, adjIdx, crnt.value));

              if (diskInfo[crnt.row][crntJIdx] != 0) {
                num--;
                totSum -= crnt.value;
                diskInfo[crnt.row][crntJIdx] = 0;
              }
              num--;
              totSum -= crnt.value;
              diskInfo[adjRadius][adjJIdx] = 0;
              hasElimination = true;
            }
          }
        }
      }

      if (!hasElimination) {
        double average = ((double) totSum) / num;
        for (int i = 0; i < maxRadius; i++) {
          for (int j = 0; j < numOnDisk; j++) {
            if (diskInfo[i][j] == 0) continue;
            if (diskInfo[i][j] > average) {
              diskInfo[i][j]--;
              totSum--;
            }
            else if (diskInfo[i][j] < average) {
              diskInfo[i][j]++;
              totSum++;
            }
          }
        }
      }
    }
    bw.write(totSum + "\n");
    bw.flush();
  }
  static int getIdx(int idx, int sum) {
    int result = idx + sum;
    int q = result / numOnDisk + (result < 0 ? -1 : 0);
    result -= q * numOnDisk;
    return result % numOnDisk;
  }
}
