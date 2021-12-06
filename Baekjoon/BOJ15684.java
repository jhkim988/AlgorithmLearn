import java.io.*;
import java.util.*;

public class BOJ15684 {
  static int min = 4;
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
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int H = Integer.parseInt(st.nextToken());

    int[] lines = new int[N];
    for (int i = 0; i < N; i++) {
      lines[i] = i;
    }
    boolean[][] marked = new boolean[H][N - 1];
    ArrayList<Pair> fn = new ArrayList<>();
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int height = Integer.parseInt(st.nextToken());
      int line = Integer.parseInt(st.nextToken());
      marked[height - 1][line - 1] = true;
      fn.add(new Pair(height - 1, line - 1));
    }
    Collections.sort(fn, new Comparator<Pair>(){
      @Override
      public int compare(Pair o1,Pair o2) {
        return o1.height == o2.height ? o1.line - o2.line : o1.height - o2.height;
      }      
    });
    int[] origin = new int[N];
    for (int i = 0; i < N; i++) origin[i] = i;
    ArrayList<int[]> front = new ArrayList<>();
    ArrayList<int[]> inverse = new ArrayList<>();    
    for (int i = 0; i <= M; i++) {
      int[] tmp1 = new int[N];
      int[] tmp2 = new int[N];
      System.arraycopy(origin, 0, tmp1, 0, N);
      System.arraycopy(origin, 0, tmp2, 0, N);
      for (int j = 0; j < i; j++) {
        int line = fn.get(j).line;
        swap(tmp1, line, line + 1);
      }
      front.add(tmp1);
      for (int j = 0; j < i; j++) {
        int line = fn.get(i - j - 1).line;
        swap(tmp2, line, line + 1);
      }
      inverse.add(tmp2);
    }

    recur(0, 0, 0, origin, marked, front, inverse);
    bw.write(min + "\n");
    bw.flush();
  }
  static void swap(int[] lines, int idx1, int idx2) {
    int tmp = lines[idx1];
    lines[idx1] = lines[idx2];
    lines[idx2] = tmp;
  }
  static int[] action(int[] before, int[] after) {
    int[] result = new int[before.length];
    for (int i = 0; i < result.length; i++) {
      result[i] = before[after[i]];
    } 
    return result;
  }
  static int[] partial(ArrayList<int[]> front, ArrayList<int[]> inverse, int from, int to) {
    // [from, to]
    if (from == 0) return front.get(to);
    return action(inverse.get(from - 1), front.get(to));
  }
  static void recur(int depth, int markedPtr, int fnPtr, int[] fn, boolean[][] marked, ArrayList<int[]> front, ArrayList<int[]> inverse) {
    if (depth >= min) return;
    int h = markedPtr / marked.length;
    int l = markedPtr % marked.length;
    swap(fn, l, l + 1);
    int[] tmp = action(fn, partial(front, inverse, fnPtr, front.size() - 1));
    boolean flag = true;
    for (int i = 0; i < tmp.length; i++) flag = flag && tmp[i] == i;
    if (flag) min = Math.min(min, depth);

    for (int cur = markedPtr; cur < marked.length * marked[0].length; cur++) {
      int height = cur / marked.length;
      int line = cur % marked.length;
      if (marked[height][line]) {
        fnPtr++;
        continue;
      }
      if (line > 0 && marked[height][line - 1] || line < marked[0].length - 1 && marked[height][line + 1]) continue;
      recur(depth + 1, height * marked.length + line, fnPtr, fn, marked, front, inverse);
    }
    swap(fn, l, l + 1);
  }
}
