import java.io.*;
import java.util.*;

public class BOJ2014 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int k = Integer.parseInt(st.nextToken());
    int n = Integer.parseInt(st.nextToken());
    
    st = new StringTokenizer(br.readLine());    
    long[] prime = new long[k];
    for (int i = 0; i < k; i++) {
      prime[i] = Integer.parseInt(st.nextToken());
    }
    HashSet<Long> hs = new HashSet<>();
    PriorityQueue<Long> pq = new PriorityQueue<>();
    int pollCount = 0;
    long poll = 0;
    for (int i = 0; i < k; i++) {
      hs.add(prime[i]);
      pq.add(prime[i]);
    }
    while (pollCount + pq.size() < n) {
      poll = pq.poll();
      pollCount++;
      for (int i = 0; i < k && pollCount+pq.size() < n; i++) {
        if (hs.contains(prime[i]*poll)) continue;
        pq.add(prime[i]*poll);
        hs.add(prime[i]*poll);
      }
    }
    
    PriorityQueue<Long> max = new PriorityQueue<>(Collections.reverseOrder());
    for (long val : pq) max.add(val);
    while (pollCount < n) {
      poll = pq.poll();
      pollCount++;
      for (int i = 0; i < k; i++) {
        if (max.peek() <= prime[i]*poll || hs.contains(prime[i]*poll)) continue;
        pq.add(prime[i]*poll);
        max.poll();
        max.add(prime[i]*poll);
        hs.add(prime[i]*poll);
      }
    }
    bw.write(Long.toString(poll));
    bw.flush();
  }
}