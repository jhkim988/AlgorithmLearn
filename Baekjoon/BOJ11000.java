import java.io.*;
import java.util.*;

public class BOJ11000 {
  private static class Time {
    int time, isStart;
    Time(int time, int isStart) {
      this.time = time;
      this.isStart = isStart;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    ArrayList<Time> time = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      time.add(new Time(start, 1));
      time.add(new Time(end-1, 0));
    }
    Collections.sort(time, (a, b) -> {
      if (a.time != b.time) return a.time-b.time;
      return b.isStart-a.isStart;
    });
    int max = 0;
    int numRoom = 0;
    for (int i = 0; i < time.size(); i++) {
      Time t = time.get(i);
      if (t.isStart == 1) {
        numRoom++;
        if (max < numRoom) max = numRoom;
      } else {
        numRoom--;
      }
    }
    bw.write(Integer.toString(max));
    bw.newLine();
    bw.flush();
  }
} 