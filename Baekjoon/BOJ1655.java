import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ1655 {
  private static class DynamicMedian {
    int size;
    int med;
    PriorityQueue<Integer> maxPQ = new PriorityQueue<>(new Comparator() {
      @Override
      public int compare(Object o1, Object o2) {
        return Integer.compare((Integer) o2, (Integer) o1);
      }
      
    });
    PriorityQueue<Integer> minPQ = new PriorityQueue<Integer>(new Comparator() {
      public int compare(Object o1, Object o2) {
        return Integer.compare((Integer) o1, (Integer) o2);
      }
    });
    
    DynamicMedian() {
    }
    void insert(int x) {
      if (size == 0) {
        maxPQ.add(x);
        med = x;
        size++;
        return;
      }
      if (x > med) {
        minPQ.add(x);
      } else {
        maxPQ.add(x);
      }
      size++;
      while (maxPQ.size() < minPQ.size()) {
        maxPQ.add(minPQ.poll());
      }
      while (minPQ.size() < maxPQ.size()) {
        minPQ.add(maxPQ.poll());
      }
      if (size % 2 == 0) {
        med = maxPQ.peek();
      } else {
        med = minPQ.peek();
      }
      // for (int el : maxPQ) {
      //   System.out.printf(el + " ");
      // }
      // System.out.println();
      // for (int el : minPQ) {
      //   System.out.printf(el + " ");
      // }
      // System.out.println();
      // System.out.printf("size: %d, maxPQ: %d, minPQ: %d, med: %d\n", size, maxPQ.size(), minPQ.size(), med);
    }
    int median() {
      return med;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N = Integer.parseInt(br.readLine());
    DynamicMedian dm = new DynamicMedian();
    for (int i = 0; i < N; i++) {
      dm.insert(Integer.parseInt(br.readLine()));
      bw.write(dm.median() + "\n");
    }
    bw.flush();
  }
}
