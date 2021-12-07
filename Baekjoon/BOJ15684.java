import java.io.*;
import java.util.*;

public class BOJ15684 {
  static int min = 4;
  static int[] origin;
  private static class Pair {
    int height;
    int line;
    Pair (int height, int line) {
      this.height = height;
      this.line = line;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int numLine = Integer.parseInt(st.nextToken());
    int numSwap = Integer.parseInt(st.nextToken());
    int height = Integer.parseInt(st.nextToken());

    ArrayList<Pair> fns = new ArrayList<>();
    int[][] marked = new int[height][numLine - 1];
    for (int i = 0; i < numSwap; i++) {
      st = new StringTokenizer(br.readLine());
      int h = Integer.parseInt(st.nextToken());
      int l = Integer.parseInt(st.nextToken());
      fns.add(new Pair(h - 1, l - 1));
      marked[h - 1][l - 1] = 1;
    }
    Collections.sort(fns, new Comparator<Pair>(){
      @Override
      public int compare(Pair o1, Pair o2) {
        return o1.height == o2.height ? o1.line - o2.line : o1.height - o2.height;
      }
    });
    ArrayList<int[]> after = new ArrayList<>(numSwap);
    for (int i = 0; i < numSwap; i++) after.add(origin);
    origin = new int[numLine];
    for (int i = 0; i < numLine; i++) origin[i] = i;
    for (int i = numSwap - 1; i >= 0; i--) {
      int[] tmp = new int[numLine];
      System.arraycopy(origin, 0, tmp, 0, numLine);
      Pair swp = fns.get(i);
      swap(tmp, swp.line, swp.line + 1);
      if (i == numSwap - 1) {
        after.set(i, tmp);
      } else {
        after.set(i, composite(tmp, after.get(i + 1)));
      }
    }
    int[] fn = new int[numLine];
    System.arraycopy(origin, 0, fn, 0, numLine);
    recur(0, 0, 0, marked, fn, fns, after);

    bw.write(min == 4 ? "-1\n" : (min + "\n"));
    bw.flush();
  }
  static void swap(int[] arr, int idx1, int idx2) {
    int tmp = arr[idx1];
    arr[idx1] = arr[idx2];
    arr[idx2] = tmp;
  }
  static int[] composite(int[] before, int[] after) {
    int[] result = new int[before.length];
    for (int i = 0; i < result.length; i++) result[i] = before[after[i]];
    return result;
  }
  static void recur(int depth, int ptr, int fnPtr, int[][] marked, int[] fn, ArrayList<Pair> fns, ArrayList<int[]> after) {
    if (depth >= min || ptr > marked.length * marked[0].length) return;
    // System.out.println("call depth: " + depth + ", ptr: " + ptr + ", fnPtr: " + fnPtr);
    // for (int i = 0; i < marked.length; i++) System.out.println(Arrays.toString(marked[i]));

    // check:
    boolean check = true;
    int[] tmp = (fnPtr >= after.size()) ? fn : composite(fn, after.get(fnPtr));
    for (int i = 0; i < fn.length; i++) check = check && tmp[i] == i;
    if (check) min = Math.min(min, depth);
    // if (check) System.out.println("PASS");

    // loop:
    for (int cur = ptr; cur < marked.length * marked[0].length; cur++) {
      int line = cur % marked[0].length;
      int height = cur / marked[0].length;
      if ((line > 0 && marked[height][line - 1] != 0) || (line < marked[0].length - 1 && marked[height][line + 1] != 0)) continue;
      int[] nextFn = new int[fn.length];
      System.arraycopy(fn, 0, nextFn, 0, fn.length);
      if (marked[height][line] == 1) {
        Pair swp = fns.get(fnPtr++);
        swap(nextFn, swp.line, swp.line + 1);
        fn = nextFn;
        continue;
      }
      marked[height][line] = 2;
      swap(nextFn, line, line + 1);
      recur(depth + 1, cur + 1, fnPtr, marked, nextFn, fns, after);
      marked[height][line] = 0;
    }
  }
}
