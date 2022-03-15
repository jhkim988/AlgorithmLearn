import java.io.*;
import java.util.*;

public class BOJ8979 {
  private static class Record implements Comparable<Record> {
    int id, g, s, c, rank;
    Record(int id, int g, int s, int c) {
      this.id = id;
      this.g = g;
      this.s = s;
      this.c = c;
    }
    @Override
    public int compareTo(Record other) {
      if (this.g != other.g) {
        return other.g - this.g;
      } else {
        if (this.s != other.s) {
          return other.s - this.s;
        } else {
          return other.c - this.c;
        }
      }
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int t = Integer.parseInt(st.nextToken());
    ArrayList<Record> idSort = new ArrayList<>();
    ArrayList<Record> rSort = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      int id = Integer.parseInt(st.nextToken());
      int g = Integer.parseInt(st.nextToken());
      int s = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      Record r = new Record(id, g, s, c);
      idSort.add(r);
      rSort.add(r);
    }
    Collections.sort(idSort, (a, b) -> a.id-b.id);
    Collections.sort(rSort);
    for (int i = 0; i < n; i++) {
      Record ith = rSort.get(i);
      ith.rank = binSearch(ith, rSort) + 1;
    }
    bw.write(Integer.toString(idSort.get(t-1).rank));
    bw.newLine();
    bw.flush();
  }
  static int binSearch(Record key, ArrayList<Record> list) {
    int lo = -1, hi = list.size();
    while (lo + 1 < hi) {
      int mid = (lo + hi) >> 1;
      if (list.get(mid).compareTo(key) < 0) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    return hi;
  }
}