import java.io.*;
import java.util.*;

public class BOJ1052 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    int answer = minAdd(n, k);
    bw.write(Integer.toString(answer));
    bw.flush();
  }
  static int numOne(int x) {
    int num = 0;
    while (x != 0) {
      if ((x&1) != 0) num++;
      x >>= 1;
    }
    return num;
  }
  static int minAdd(int n, int k) {
    int num = 0;
    while (numOne(n) > k) {
      num += n&-n;
      n += n&-n;
    }
    return num;
  }
}