import java.io.*;
import java.util.*;

public class BOJ6064 {
  private static class Date {
    int first;
    int second;
    int firstLim;
    int secondLim;
    Date(int first, int second, int firstLim, int secondLim) {
      this.first = first;
      this.second = second;
      this.firstLim = firstLim;
      this.secondLim = secondLim;
    }
    void next() {
      first++;
      second++;
      if (first > firstLim) {
        first = 1;
      }
      if (second > secondLim) {
        second = 1;
      }
    }
    boolean isEnd() {
      return first == firstLim && second == secondLim;
    }
    public String toString() {
      return "<" + first + ":" + second + ">";
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int numTest = Integer.parseInt(br.readLine());

    while (numTest-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int M = Integer.parseInt(st.nextToken());
      int N = Integer.parseInt(st.nextToken());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());

      int lcm = lcm(M, N);
      int range1 = lcm / M;
      int range2 = lcm / N;
      int k = -1;
      int[] multiM = new int[range1 + 1];
      int[] multiN = new int[range2 + 1];
      for (int i = 0; i <= range1; i++) {
        multiM[i] = i * M + x;
      }
      for (int j = 0; j <= range2; j++) {
        multiN[j] = j * N + y;
      }

      if (range1 > range2) {
        for (int j = 0; j <= range2; j++) {
          int idx = Arrays.binarySearch(multiM, multiN[j]);
          if (idx >= 0) {
            k = multiN[j];
            break;
          }
        } 
      } else {
        for (int i = 0; i <= range1; i++) {
          int idx = Arrays.binarySearch(multiN, multiM[i]);
          if (idx >= 0) {
            k = multiM[i];
            break;
          }
        }
      }
      bw.write(k + "\n");
    }
    bw.flush();
  }
  static int lcm(int x, int y) {
    return x * y / gcd(x, y);
  }
  static int gcd (int x, int y) {
    if (y == 0) {
      return x;
    }
    return gcd (y, x % y);
  }
  public static void linearSearch(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int numTest = Integer.parseInt(br.readLine());

    while (numTest-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int M = Integer.parseInt(st.nextToken());
      int N = Integer.parseInt(st.nextToken());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());

      Date date = new Date(1, 1, M, N);
      int k = 1;
      boolean find = false;
      while (!date.isEnd()) {
        date.next();
        k++;
        if (date.first == x && date.second == y) {
          find = true;
          break;
        }
      }
      if (find) {
        bw.write(k + "\n");
      } else {
        bw.write("-1\n");
      }      
    }
    bw.flush();
  }
}
