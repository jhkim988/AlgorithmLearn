import java.io.*;
import java.util.*;

public class BOJ2467 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    int[] solution = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      solution[i] = Integer.parseInt(st.nextToken());
    }
    int ptr1 = -1;
    int ptr2 = -1;
    int minVal = 2_000_000_001 ;
    for (int i = 0; i < N - 1; i++) {
      int idx = Arrays.binarySearch(solution, i + 1, N, -solution[i]);
      if (idx < 0) {
        idx = -(idx + 1);
        if (i < idx && idx < N && Math.abs(solution[i] + solution[idx]) < minVal) {
          minVal = Math.abs(solution[i] + solution[idx]);
          ptr1 = i;
          ptr2 = idx;
        }
        if (i < idx - 1 && idx > 0 && Math.abs(solution[i] + solution[idx - 1]) < minVal) {
          minVal = Math.abs(solution[i] + solution[idx - 1]);
          ptr1 = i;
          ptr2 = idx - 1;
        }

      } else {
        if (i < idx && Math.abs(solution[i] + solution[idx]) < minVal) {
          minVal = solution[i] + solution[idx];
          ptr1 = i;
          ptr2 = idx;
        }
      }
    }
    if (solution[ptr1] < solution[ptr2]) {
      bw.write(solution[ptr1] + " " + solution[ptr2] + "\n");
    } else {
      bw.write(solution[ptr2] + " " + solution[ptr1] + "\n");
    }
    bw.flush();
  }
}
