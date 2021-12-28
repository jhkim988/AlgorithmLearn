import java.io.*;
import java.util.*;

public class BOJ15662 {
  final static int NUM_COG = 8;
  private static class Simulation {
    int[] ptr;
    char[][] data;
    Simulation(int numGear, char[][] data) {
      this.ptr = new int[numGear];
      this.data = data;
    }
    int numSpole() {
      int num = 0;
      for (int i = 0; i < data.length; i++) {
        if (data[i][ptr[i]] == '1') num++;
      }
      return num;
    }
    void rot(int idx, int direction) {
      char left = data[idx][(ptr[idx] + 6) % NUM_COG];
      char right = data[idx][(ptr[idx] + 2) % NUM_COG];
      int crntDi = direction;
      ptr[idx] -= crntDi;
      if (ptr[idx] < 0) ptr[idx] += NUM_COG;
      else if (ptr[idx] >= NUM_COG) ptr[idx] -= NUM_COG;
      crntDi = -direction;
      for (int i = idx - 1; i >= 0; i--) {
        char adjRight = data[i][(ptr[i] + 2) % NUM_COG];
        if (adjRight == left) break;
        left = data[i][(ptr[i] + 6) % NUM_COG];
        ptr[i] -= crntDi;
        if (ptr[i] < 0) ptr[i] += NUM_COG;
        else if (ptr[i] >= NUM_COG) ptr[i] -= NUM_COG;
        crntDi = -crntDi;
      }
      crntDi = -direction;
      for (int i = idx + 1; i < data.length; i++) {
        char adjLeft = data[i][(ptr[i] + 6) % NUM_COG];
        if (adjLeft == right) break;
        right = data[i][(ptr[i] + 2) % NUM_COG]; 
        ptr[i] -= crntDi;
        if (ptr[i] < 0) ptr[i] += NUM_COG;
        else if (ptr[i] >= NUM_COG) ptr[i] -= NUM_COG;
        crntDi = -crntDi;
      }
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numGear = Integer.parseInt(br.readLine());
    char[][] data = new char[numGear][];
    for (int i = 0; i < numGear; i++) {
      data[i] = br.readLine().toCharArray();
    }
    Simulation simul = new Simulation(numGear, data);
    int numRot = Integer.parseInt(br.readLine());
    for (int i = 0; i < numRot; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int idx = Integer.parseInt(st.nextToken());
      int isClockWise = Integer.parseInt(st.nextToken()); // 1, -1
      simul.rot(idx - 1, isClockWise);
    }
    bw.write(simul.numSpole() + "\n");
    bw.flush();
  }
}
