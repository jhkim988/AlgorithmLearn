import java.io.*;
import java.util.*;

public class BOJ1700 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int capacity = Integer.parseInt(st.nextToken());
    int numTask = Integer.parseInt(st.nextToken());
    st = new StringTokenizer(br.readLine());
    int[] taskque = new int[numTask];
    int[] numPlug = new int[numTask+1];
    for (int i = 0; i < numTask; i++) {
      taskque[i] = Integer.parseInt(st.nextToken());
      numPlug[taskque[i]]++;
    }
    int numUnplug = 0;
    int totPlug = 0;
    boolean[] onPlug = new boolean[numTask+1];
    int[] rank = new int[numTask+1];
    for (int i = 0; i < numTask; i++) {
      if (!onPlug[taskque[i]]) {
        if (totPlug < capacity) { // there is free hole
          totPlug++;
          // System.out.println("plug in free hole: " + taskque[i]);
        } else { // no free hole(must unplug)
          int zero = -1;
          int last = i;
          Arrays.fill(rank, numTask+2);
          for (int j = i; j < numTask; j++) {
            if (!onPlug[taskque[j]]) continue;
            if (j < rank[taskque[j]]) {
              rank[taskque[j]] = j;
              last = taskque[j];
            }            
          }
          for (int j = 1; j <= numTask; j++) {
            if (!onPlug[j]) continue;
            if (numPlug[j] == 0) zero = j;
          }
          if (zero != -1) {
            // System.out.println("unplug(no use): " + zero);
            onPlug[zero] = false;
          } else {
            // System.out.println("unplug(last use): " + last);
            onPlug[last] = false;
          }
          numUnplug++;
          // System.out.println("plug after unplug: " + taskque[i]);
        }
        onPlug[taskque[i]] = true;
      } else {
        // System.out.println("already plug: " + taskque[i]);
      }
      numPlug[taskque[i]]--;
    }
    bw.write(Integer.toString(numUnplug));
    bw.newLine();
    bw.flush();
  }
}
