import java.io.*;
import java.util.*;

public class BOJ1202 {
  private static class Pair implements Comparable<Pair> {
    int weight;
    int value;
    Pair(int weight, int value) {
      this.weight = weight;
      this.value = value;
    }
    @Override
    public int compareTo(Pair other) {
      return this.value != other.value ? other.value - this.value : other.weight - this.weight;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());
    Pair[] jewelry = new Pair[N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int weight = Integer.parseInt(st.nextToken());
      int value = Integer.parseInt(st.nextToken());
      jewelry[i] = new Pair(weight, value);
    }
    Integer [] bag = new Integer[K];
    for (int i = 0; i < K; i++) {
      bag[i] = Integer.parseInt(br.readLine());
    } 
    PriorityQueue<Pair> que = new PriorityQueue<>();
    Arrays.sort(jewelry, new Comparator<Pair>() {
      @Override
      public int compare(Pair p1, Pair p2) {
        return p1.weight != p2.weight ? p1.weight - p2.weight : p2.value - p1.value;
      }
    });
    Arrays.sort(bag);
    long sum = 0;
    int ptr = 0;
    for (int i = 0; i < K; i++) {
      for (int j = ptr; j < N; j++) {
        if (jewelry[j].weight <= bag[i]) {
          // System.out.println("input: " + jewelry[j].value);
          que.add(jewelry[j]);
          ptr++;
        } else {
          break;
        }
      }
      // System.out.println("output: " + que.peek().value);
      if (que.isEmpty()) {
        continue;
      }
      sum += (long) que.poll().value;
    }
    bw.write(sum + "\n");
    bw.flush();
  }
}
