import java.io.*;
import java.util.*;

public class BOJ23077 {
  private static class Pair {
    int happy, day;
    Pair(int happy, int day) {
      this.happy = happy;
      this.day = day;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numTest = Integer.parseInt(br.readLine());
    for (int testId = 1; testId <= numTest; testId++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int d = Integer.parseInt(st.nextToken());
      int n = Integer.parseInt(st.nextToken());
      int k = Integer.parseInt(st.nextToken());
      ArrayList<Pair> attraction = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        attraction.add(new Pair(h, s));
        attraction.add(new Pair(-h, e));
      }
      Collections.sort(attraction, (a, b) -> a.day!=b.day ? a.day-b.day : a.happy-b.happy);
      
      // sweeping:
      long max = 0;
      long sum = 0;
      TreeSet<Integer> ts = new TreeSet<>();
      for (int i = 0; i < attraction.size(); i++) {
        Pair p = attraction.get(i);
        if (p.happy > 0) {
          ts.add(p.happy);
          sum += p.happy;
          if (ts.size() > k) {
            sum -= ts.pollFirst();
          }
        } else {
          if (ts.remove(-p.happy)) {
            sum += p.happy;
          }
        } 
        max = Long.max(max, sum);
      }
      bw.write("Case #");
      bw.write(Integer.toString(testId));
      bw.write(": ");
      bw.write(Long.toString(max));
      bw.newLine();
    }
    bw.flush();
  }
}
