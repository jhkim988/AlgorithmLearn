import java.io.*;
import java.util.*;

public class BOJ17299 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    int len = Integer.parseInt(br.readLine());    
    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] seq = new int[len];
    for (int i = 0; i < len; i++) {
      seq[i] = Integer.parseInt(st.nextToken());
    }

    // bw.write(useHashMap(seq, len));
    bw.write(useArray(seq, len));
    bw.flush();
  }
  static String useHashMap(int[] seq, int len) {
    HashMap<Integer, Integer> hm = new HashMap<>();
    for (int i = 0; i < len; i++) {
      if (hm.containsKey(seq[i])) {
        hm.put(seq[i], hm.get(seq[i]) + 1);
      } else {
        hm.put(seq[i], 1);
      }
    }

    int[] result = new int[len];
    Arrays.fill(result, -1);
    Stack<Integer> stk = new Stack<>(); // push index of seq.
    stk.push(0);
    for (int i = 1; i < len; i++) {
      int getI = hm.get(seq[i]);
      while (!stk.isEmpty() && hm.get(seq[stk.peek()]) < getI) {
        result[stk.pop()] = seq[i];
      }
      stk.push(i);
    }
    return print(result);    
  }
  static String useArray(int[] seq, int len) {
    final int MAX = 1_000_000;
    int[] F = new int[MAX + 1];
    for (int i = 0; i < len; i++) {
      F[seq[i]]++;
    }

    int[] result = new int[len];
    Arrays.fill(result, -1);
    Stack<Integer> stk = new Stack<>();
    stk.push(0);
    for (int i = 1; i < len; i++) {
      while (!stk.isEmpty() && F[seq[stk.peek()]] < F[seq[i]]) {
        result[stk.pop()] = seq[i];
      }
      stk.push(i);
    }
    return print(result);    
  }
  static String print(int[] arr) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < arr.length; i++) {
      sb.append(arr[i]).append(' ');
    }
    sb.append('\n');
    return sb.toString();
  }
}
