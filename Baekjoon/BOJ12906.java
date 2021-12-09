import java.io.*;
import java.util.*;

public class BOJ12906 {
  private static class Status {
    StringBuffer[] strs;
    int[] lens;
    int move;

    Status(StringBuffer[] strs, int[] lens, int move) {
      this.strs = new StringBuffer[3];
      this.lens = new int[3];
      for (int i = 0; i < 3; i++) {
        this.strs[i] = new StringBuffer(strs[i].toString());
        this.lens[i] = lens[i];
      }
      this.move = move;
    }
    boolean isComplete() {
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < strs[i].length(); j++) if (strs[i].charAt(j) != ('A' + i)) return false;
      }
      return true;      
    }
    @Override
    public boolean equals(Object o){
      if (o == this) return true;
      if (this.getClass() != o.getClass()) return false;
      Status other = (Status) o;
      for (int i = 0; i < 3; i++) {
        if (this.lens[i] != other.lens[i]) return false;
        for (int j = 0; j < lens[i]; j++)
          if (this.strs[i].charAt(j) != other.strs[i].charAt(j)) return false;
      } 
      return true;
    }
    @Override
    public int hashCode() {
      int result = 7;
      for (int i = 0; i < 3; i++) {
        result = 31 * result + lens[i];
        for (int j = 0; j < strs[i].length(); j++) {
          result = 31 * result + strs[i].charAt(j);
        }
      }
      return result;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringBuffer[] strs = new StringBuffer[3];
    int[] lens = new int[3];
    for (int i = 0; i < 3; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int len = Integer.parseInt(st.nextToken());
      if (len != 0) {
        strs[i] = new StringBuffer(st.nextToken());
      } else {
        strs[i] = new StringBuffer("");
      }
      lens[i] = len;
    }

    // use BFS:
    Queue<Status> que = new LinkedList<>();
    HashSet<Status> hs = new HashSet<>();
    Status start = new Status(strs, lens, 0);
    que.add(start);
    hs.add(start);
    while (!que.isEmpty()) {
      Status crnt = que.poll();
      // System.out.println("crnt: " + crnt.move);
      // for (int i = 0; i < 3; i++) System.out.println(((char) ('A' + i) + ": ") + crnt.strs[i] + ", len: " + crnt.lens[i]);
      if (crnt.isComplete()) {
        bw.write(crnt.move + "\n");
        bw.flush();
        break;
      }

      outer: for (int i = 0; i < 3; i++) { // i -> j
        for (int j = 0; j < 3; j++) {
          if (i == j) continue;
            Status next = new Status(crnt.strs, crnt.lens, crnt.move + 1);
            if (next.lens[i] == 0) continue outer;
            next.lens[i]--;
            next.lens[j]++;
            next.strs[j].append(next.strs[i].charAt(next.strs[i].length() - 1));
            next.strs[i].setLength(next.strs[i].length() - 1);
            if (hs.contains(next)) continue;
            hs.add(next);
            que.add(next);
        }
      }
    }
  }
}