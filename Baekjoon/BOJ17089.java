import java.io.*;
import java.util.*;

public class BOJ17089 {
  static int min = 12_000;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int numPeople = Integer.parseInt(st.nextToken());
    int numRelation = Integer.parseInt(st.nextToken());
    ArrayList<HashSet<Integer>> relation = new ArrayList<>(numPeople + 1);
    for (int i = 0; i <= numPeople; i++) relation.add(new HashSet<>());
    for (int i = 0; i < numRelation; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      relation.get(a).add(b);
      relation.get(b).add(a);
    }
    int[] pick = new int[3];
    recur(0, pick, 0, relation, numPeople);
    
    bw.write(min == 12_000 ? "-1\n" : (min + "\n"));
    bw.flush();
  }
  static void recur(int depth, int[] pick, int cost, ArrayList<HashSet<Integer>> relation, int numPeople) {
    if (cost > min) return;
    if (depth >= 3) {
      min = Math.min(min, cost);
      return;
    }

    switch (depth) {
      case 0: {
        for (int i = 1; i <= numPeople; i++) {
          HashSet<Integer> r = relation.get(i);
          if (r.size() < 2) continue;
          pick[depth] = i;
          recur(depth + 1, pick, r.size() - 2, relation, numPeople);
        }
        break;
      }
      case 1: {
        HashSet<Integer> r1 = relation.get(pick[0]);
        for (int friend : r1) {
          HashSet<Integer> r2 = relation.get(friend);
          if (r2.size() < 2) continue;
          if (pick[0] == friend) continue;
          pick[depth] = friend;
          recur(depth + 1, pick, cost + r2.size() - 2, relation, numPeople);
        }
        break;
      }
      case 2: {
        HashSet<Integer> r1 = relation.get(pick[1]);
        for (int friend : r1) {
          HashSet<Integer> r2 = relation.get(friend);
          if (r2.size() < 2) continue;
          if (pick[0] == friend || pick[1] == friend) continue;
          if (!relation.get(friend).contains(pick[0])) continue;
          pick[depth] = friend;
          recur(depth + 1, pick, cost + r2.size() - 2, relation, numPeople);
        } 
        break;
      }
    }
  }
}