import java.io.*;
import java.util.*;

public class BOJ1713 {
  private static class Pair {
    int id, time, get;
    Pair(int id, int time, int get) {
      this.id = id;
      this.time = time;
      this.get = get;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int k = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    ArrayList<Pair> list = new ArrayList<>();
    for (int t = 0; t < k; t++) {
      int id = Integer.parseInt(st.nextToken());
      int findIdx = -1;
      int removeIdx = 0;
      for (int i = 0; i < list.size(); i++) {
        if (list.get(i).id == id) {
          findIdx = i;
          break;
        }
        if (list.get(i).get < list.get(removeIdx).get) {
          removeIdx = i;
        } else if (list.get(i).get == list.get(removeIdx).get) {
          if (list.get(i).time < list.get(removeIdx).time) {
            removeIdx = i;
          }
        }
      }
      if (findIdx == -1) {
        if (list.size() == n) {
          list.set(removeIdx, new Pair(id, t, 1));
        } else {
          list.add(new Pair(id, t, 1));
        }
      } else {
        list.get(findIdx).get++;
      }
    }
    Collections.sort(list, (a, b) -> a.id-b.id);
    for (Pair p : list) {
      bw.write(Integer.toString(p.id));
      bw.write(' ');
    }
    bw.flush();
  }
}
