import java.io.*;
import java.util.*;

public class BOJ16943 {
  static char[] input1;
  static int input2;
  static int maxResult = 0;
  static boolean flag = false;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    input1 = st.nextToken().toCharArray();
    input2 = Integer.parseInt(st.nextToken());
    boolean[] marked = new boolean[input1.length];
    recur(0, 0, marked);
    if (flag) {
      bw.write(maxResult + "\n");
    } else {
      bw.write("-1\n");
    }
    bw.flush();
  }
  static void recur(int depth, int max, boolean[] marked) {
    // System.out.println("call: " + depth + ", " + max);
    if (depth >= input1.length) {
      if (maxResult < max) {
        maxResult = max;
        boolean f = true;
        for (int i = 0; i < marked.length; i++) {
          f = f && marked[i];
        }
        flag = flag || f;
      }
    }
    for (int i = 0; i < input1.length; i++) {
      if (marked[i]) continue;
      int val = pow(10, input1.length - depth - 1) * (input1[i] - '0');
      if (max + val < input2) {
        if (depth == 0 && val == 0) continue;
        marked[i] = true;
        recur(depth + 1, max + val, marked);
        marked[i] = false;
      } 
    }    
  }
  static int pow(int base, int exp) {
    if (exp == 0) return 1;
    if (exp == 1) return base;
    int half = pow(base, exp/2);
    if (exp % 2 == 0) return half * half;
    else return half * half * base;
  }
}