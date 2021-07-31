import java.io.*;
import java.util.*;

public class BOJ11066 {
  static int greedy(int[] pages) {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int i = 0; i < pages.length; i++) {
      pq.add(pages[i]);
    }
    int cost = 0;
    while (!pq.isEmpty()) {
      int ch1 = pq.poll();
      int ch2 = 0;
      System.out.println("ch1: " + ch1);
      if (!pq.isEmpty()) {
        ch2 = pq.poll();
        cost += ch1 + ch2;
        System.out.println("ch2: " + ch2);
      } else {
        pq.add(ch1);
        break;
      }
      pq.add(ch1 + ch2);
    }
    return cost;
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int numTest = Integer.parseInt(br.readLine());
    int numCh;
    int[] pages;
    StringTokenizer st;
    for (int i = 0; i < numTest; i++) {
      numCh = Integer.parseInt(br.readLine());
      st = new StringTokenizer(br.readLine());
      pages = new int[numCh];
      for (int j = 0; j < numCh; j++) {
        pages[j] = Integer.parseInt(st.nextToken());
      }
      bw.write(greedy(pages) + "\n");
    }
    bw.flush();
  }
}
