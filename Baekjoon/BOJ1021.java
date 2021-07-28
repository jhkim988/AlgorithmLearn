import java.io.*;
import java.util.*;

public class BOJ1021 {
  static int countSol = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    
    Deque<Integer> deq = new LinkedList<>();
    for (int i = 1; i <= N; i++) {
      deq.addLast(i);
    }

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < M; i++) {
      int input = Integer.parseInt(st.nextToken());
      int ptr = 0;
      int size = deq.size();
      
      if (size == 1) {
        method1(deq);
        break;
      }

      Iterator<Integer> iter = deq.iterator();
      while (iter.hasNext()) {
        int el = iter.next();
        // System.out.println("el: " + el);
        if (el == input) {
          break;
        }
        ptr++;
      }
      // System.out.println("ptr: " + ptr);
      if (ptr <= size / 2) {
        while (ptr > 0) {
          method2(deq);
          ptr--;
        }
        method1(deq);
      } else {
        while (ptr < size) {
          method3(deq);
          ptr++;
        }
        method1(deq);
      }
    }
    bw.write(countSol + "\n");
    bw.flush();
  }
  static void method1(Deque<Integer> deq) {
    deq.removeFirst();
  }
  static void method2(Deque<Integer> deq) {    
    // System.out.println("method2");
    deq.addLast(deq.removeFirst());
    countSol++;
  }
  static void method3(Deque<Integer> deq) {    
    // System.out.println("method3");
    deq.addFirst(deq.removeLast());
    countSol++;
  }
}