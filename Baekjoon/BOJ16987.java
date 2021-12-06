import java.io.*;
import java.util.*;

public class BOJ16987 {
  static int max = 0;
  private static class Pair {
    int durability;
    int weight;
    Pair (int durability, int weight) {
      this.durability = durability;
      this.weight = weight;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    ArrayList<Pair> eggs = new ArrayList<>(N);
    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int durability = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());
      eggs.add(new Pair(durability, weight));
    }
    boolean[] isBroken = new boolean[N];
    recur(0, eggs, isBroken);

    bw.write(max + "\n");
    bw.flush();
  }
  static void recur(int depth, ArrayList<Pair> eggs, boolean[] isBroken) {
    if (depth >= eggs.size()) {
      int sum = 0;
      for (int i = 0; i < isBroken.length; i++) if (isBroken[i]) sum++;
      max = Math.max(max, sum);
      return;
    }
    if (isBroken[depth]) {
      recur(depth + 1, eggs, isBroken);
      return;
    }

    Pair depthEgg = eggs.get(depth);
    int depthDurability = depthEgg.durability;
    boolean recurFlag = false;
    for (int i = 0; i < eggs.size(); i++) {
      if (depth == i) continue;
      if (isBroken[i]) continue;
      recurFlag = true;
      Pair iEgg = eggs.get(i);
      int iDurability = iEgg.durability;
      depthEgg.durability -= iEgg.weight;
      iEgg.durability -= depthEgg.weight;
      if (depthEgg.durability <= 0) isBroken[depth] = true;
      if (iEgg.durability <= 0) isBroken[i] = true;
      recur(depth + 1, eggs, isBroken);
      if (depthEgg.durability <= 0) isBroken[depth] = false;
      if (iEgg.durability <= 0) isBroken[i] = false;
      depthEgg.durability = depthDurability;
      iEgg.durability = iDurability;
    }
    if (!recurFlag) recur(depth + 1, eggs, isBroken);
  }
}
