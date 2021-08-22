import java.io.*;
import java.util.*;

public class BOJ9527 {
  static long[] memoNumOne = new long[55];

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    long A = Long.parseLong(st.nextToken());
    long B = Long.parseLong(st.nextToken());
    memoNumOne[0] = 0;
    memoNumOne[1] = 1;
    long result = numOneAnswer(B) - numOneAnswer(A - 1);
    bw.write(result + "\n");
    bw.flush();
  }
  static long numOneAnswer(long x) {
    char[] bin = Long.toBinaryString(x).toCharArray();
    int len = bin.length;
    long count = 0;
    for (int i = 0; i < len; i++) {
      if (bin[i] == '0') continue;
      long sum = numOne(len - 1 - i) + (x - (1L << (len - 1 - i)) + 1);
      count += sum;
      x -= 1L << (len - 1 - i);
    }
    return count;
  }
  static long numOne(int x) {
    // the number of 1 between 0 ~ 111...1 (the number of 1 = x)
    if (x == 0) {
      return 0;
    }
    if (memoNumOne[x] != 0) {
      return memoNumOne[x];
    }
    return memoNumOne[x] = numOne(x - 1) * 2 + pow2(x - 1);
  }
  static long pow2(int x) {
    if (x == 0) {
      return 1;
    }
    if (x == 1) {
      return 2;
    }
    long prev = pow2(x / 2);
    if (x % 2 == 0) {
      return prev * prev;
    } else {
      return prev * prev * 2;
    }
  }
}
