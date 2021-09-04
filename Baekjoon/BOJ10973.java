import java.io.*;
import java.util.*;

public class BOJ10973 {
  static int N;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    N = Integer.parseInt(br.readLine());
    Integer[] crnt = new Integer[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      crnt[i] = Integer.parseInt(st.nextToken());
    }
    if (prevPerm(crnt)) {
      for (int i = 0; i < N; i++) {
        bw.write(crnt[i] + " ");
      }
      bw.write("\n");
    } else {
      bw.write("-1\n");
    }
    bw.flush();
  } 
  static boolean prevPerm(Integer[] crnt) {
    int ptr1 = N - 1;
    int ptr2 = N - 1;
    do {
      ptr1--;
    } while (ptr1 >= 0 && crnt[ptr1] < crnt[ptr1 + 1]);
    if (ptr1 == -1) {
      return false;
    }
    while (ptr2 > ptr1 + 1 && crnt[ptr2] > crnt[ptr1]) {
      ptr2--;
    }
    // XOR swap
    crnt[ptr1] ^= crnt[ptr2];
    crnt[ptr2] ^= crnt[ptr1];
    crnt[ptr1] ^= crnt[ptr2];

    Arrays.sort(crnt, ptr1 + 1, N, Collections.reverseOrder());
    return true;
  }
}
