import java.io.*;
import java.util.*;

public class BOJ10986 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int len = Integer.parseInt(st.nextToken());
    int divisor = Integer.parseInt(st.nextToken());
    
    st = new StringTokenizer(br.readLine());
    int[] pSum = new int[len];
    long[] map = new long[divisor];
    pSum[0] = Integer.parseInt(st.nextToken()) % divisor;
    map[pSum[0]] = 1L;
    long count = pSum[0] % divisor == 0 ? 1 : 0;
    for (int i = 1; i < len; i++) {
      int input = Integer.parseInt(st.nextToken()) % divisor;
      pSum[i] = (pSum[i - 1] + input) % divisor;
      if (pSum[i] == 0) count++;
      if (map[pSum[i]] > 0) count += map[pSum[i]];
      map[pSum[i]]++;
    }
    bw.write(Long.toString(count));
    bw.newLine();
    bw.flush();
  }
}
