import java.io.*;
import java.util.*;

public class BOJ11000 {
  private static class Lecture {
    int start, end;
    Lecture(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    Lecture[] lectures = new Lecture[n];
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      lectures[i] = new Lecture(start, end);
    }
    
  }
} 