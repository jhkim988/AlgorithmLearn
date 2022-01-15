import java.io.*;
import java.util.*;

public class BOJ20055 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int len = Integer.parseInt(st.nextToken());
    int limNumZero = Integer.parseInt(st.nextToken());
    int[] durability = new int[len * 2];
    int numZero = 0;
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < 2 * len; i++) if ((durability[i] = Integer.parseInt(st.nextToken())) == 0) numZero++;  
    int upPtr = 0;
    int downPtr = len - 1;
    int numRobot = 0;
    // Queue<Integer> que = new LinkedList<>();
    boolean[] occupied = new boolean[len * 2];
    int process = 0;
    while (limNumZero > numZero) {
      process++;
      downPtr--;
      upPtr--;
      if (downPtr < 0) downPtr = 2 * len - 1;
      if (upPtr < 0) upPtr = 2 * len - 1;
      if (occupied[downPtr]) occupied[downPtr] = false;
      for (int pos = downPtr; pos != upPtr; pos = pos != 0 ? pos - 1 : 2 * len - 1) {
        if (!occupied[pos]) continue;
        int nextPos = pos == 2 * len - 1 ? 0 : pos + 1;
        if (occupied[nextPos] || durability[nextPos] < 1) continue;
        occupied[pos] = false;
        durability[nextPos]--;
        if (nextPos != downPtr) occupied[nextPos] = true;
        if (durability[nextPos] == 0) numZero++;
      }
      if (durability[upPtr] > 0) {
        durability[upPtr]--;
        occupied[upPtr] = true;
        if (durability[upPtr] == 0) numZero++;
      }
    }
    bw.write(Integer.toString(process));
    bw.newLine();
    bw.flush();    
  }
}
