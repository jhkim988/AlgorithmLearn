import java.io.*;
import java.util.*;

public class BOJ2457 {
  private static class Date implements Comparable<Date> {
    int m, d;
    Date(int m, int d) {
      this.m = m;
      this.d = d;
    }
    @Override
    public int compareTo(Date other) {
      return this.m!=other.m ? this.m-other.m : this.d-other.d;
    }
  }
  private static class Period {
    Date start, end;
    Period(Date start, Date end) {
      this.start = start;
      this.end = end;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    ArrayList<Period> arr = new ArrayList<>(n);
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int m1 = Integer.parseInt(st.nextToken());
      int d1 = Integer.parseInt(st.nextToken());
      int m2 = Integer.parseInt(st.nextToken());
      int d2 = Integer.parseInt(st.nextToken());
      Date start = new Date(m1, d1);
      Date end = new Date(m2, d2);
      arr.add(new Period(start, end));
    }

    bw.write(Integer.toString(getMin(arr, n)));
    bw.flush();
  }
  static int getMin(ArrayList<Period> arr, int n) {
    Collections.sort(arr, new Comparator<Period>(){
      @Override
      public int compare(Period p1, Period p2) {
        return p1.start.compareTo(p2.start);
      }
    });
    PriorityQueue<Period> pq = new PriorityQueue<>(new Comparator<>(){
      @Override
      public int compare(Period p1, Period p2) {
        return p2.end.compareTo(p1.end);
      }
    });
    int ptr = 0;
    int num = 0;
    Date endValue = new Date(3, 1);
    Date END = new Date(12, 1);
    while (endValue.compareTo(END) < 0) {
      for (; ptr < n; ptr++) {
        if (arr.get(ptr).start.compareTo(endValue) > 0) break;
        pq.add(arr.get(ptr));
      }
      if (pq.isEmpty()) return 0;
      Date poll = pq.poll().end;
      if (poll.compareTo(endValue) <= 0) return 0;
      endValue = poll;
      num++;
    }
    return num;
  }
}