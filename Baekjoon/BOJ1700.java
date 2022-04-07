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
    int[] task = new int[numTask];
    int[] numAppear = new int[numTask+1];
    for (int i = 0; i < numTask; i++) {
      task[i] = Integer.parseInt(st.nextToken());
    }
    for (int i = 0; i < capacity; i++) {
      numAppear[task[i]]++;
    }
    int current = 0;
    int numUnplug = 0;
    boolean[] plug = new boolean[numTask+1];
    for (int i = 0; i < numTask; i++) {
      if (plug[task[i]]) {
        // System.out.println("already plug: " + task[i]);
        continue;
      } else if (current < capacity) {
        plug[task[i]] = true;
        current++;
      } else {
        int minIdx = numTask+1;
        for (int j = 1; j <= numTask; j++) {
          if (!plug[j]) continue;
          if (numAppear[j] < minIdx) minIdx = j;
        }
        numUnplug++;
        plug[minIdx] = false;
        plug[task[i]] = true;
        // System.out.println("unplug: " + minIdx);
      }
      // System.out.println("plug: " + task[i]);
      // System.out.println(Arrays.toString(numAppear));
      numAppear[task[i]]--;
      if (i + capacity < numTask) numAppear[task[i + capacity]]++;
    }
    bw.write(Integer.toString(numUnplug));
    bw.newLine();
    bw.flush();
  }
}
