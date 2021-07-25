import java.io.*;
import java.util.*;

public class BOJ1966 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static StringTokenizer st;
  private static class Doc {
    int prior;
    int index;
    Doc(int prior, int index) {
      this.prior = prior;
      this.index = index;
    }
  }
  public static void main(String[] args) {
    try {
      st = new StringTokenizer(br.readLine());
      int numTest = Integer.parseInt(st.nextToken());
      
      for (int i = 0; i < numTest; i++) {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        Doc[] data = new Doc[N];
        for (int j = 0; j < N; j++) {
          data[j] = new Doc(Integer.parseInt(st.nextToken()), j);
        }
        sol(N, M, data);
      } 
    } catch (IOException e) {

    } 
  }
  private static void sol(int N, int M, Doc[] data) {
    try {
      Queue<Doc> que = new LinkedList<Doc>();
      Integer[] priors = new Integer[N];
      int ptrPriors = 0;

      for (int i = 0; i < N; i++) {
        que.add(data[i]);
        priors[i] = data[i].prior;
      }
      
      Arrays.sort(priors, new Comparator<Integer>(){
        @Override
        public int compare(Integer o1, Integer o2) {
          return Integer.compare(o2.intValue(), o1.intValue());
        }          
      });

      while (!que.isEmpty()) {
        while (que.peek().prior < priors[ptrPriors].intValue()) {
          Doc doc = que.poll();
          que.add(doc);
        }
        if (que.poll().index == M) {
          bw.write((ptrPriors + 1) + "\n");
          que = null;
          break;
        } else {
          ptrPriors++;
        }
      } 
      bw.flush();
    } catch (IOException e) {

    }
  }
}
