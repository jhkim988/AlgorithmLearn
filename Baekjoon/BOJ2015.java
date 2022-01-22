import java.io.*;
import java.util.*;

public class BOJ2015 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int len = Integer.parseInt(st.nextToken());
    long K = Long.parseLong(st.nextToken());
    long[] pSum = new long[len];
    HashMap<Long, Long> hm = new HashMap<>(); // <pSum, count>
    
    st = new StringTokenizer(br.readLine());
    pSum[0] = Integer.parseInt(st.nextToken());
    hm.put(pSum[0], 1L);
    long count = (pSum[0] == K) ? 1 : 0;
    for (int i = 1; i < len; i++) {
      pSum[i] = pSum[i - 1] + Integer.parseInt(st.nextToken());
      if (pSum[i] == K) count++;
      if (hm.containsKey(pSum[i] - K)) count += hm.get(pSum[i] - K);
      if (hm.containsKey(pSum[i])) {
        hm.put(pSum[i], hm.get(pSum[i]) + 1);
      } else {
        hm.put(pSum[i], 1L);
      }
    }
    bw.write(Long.toString(count));
    bw.newLine();
    bw.flush();
  }
}
