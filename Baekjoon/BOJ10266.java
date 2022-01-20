import java.io.*;
import java.util.*;

public class BOJ10266 {
  public static void main(String[] args) throws IOException {
    // Find B in A + A
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    int[] A = new int[N];
    int[] B = new int[N];    
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken()); 
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) B[i] = Integer.parseInt(st.nextToken());    
    Arrays.sort(A);
    Arrays.sort(B);

    int[] gapA = new int[2 * N];
    int[] gapB = new int[N];
    for (int i = 0; i < N - 1; i++) {
      gapA[N + i] = gapA[i] = A[i + 1] - A[i];
      gapB[i] = B[i + 1] - B[i];
    }
    gapA[2 * N - 1] = gapA[N - 1] = 360_000 - A[N - 1] + A[0];
    gapB[N - 1] = 360_000 - B[N - 1] + B[0];

    if (kmpMatch(gapA, gapB)) {
      bw.write("possible\n");
    } else {
      bw.write("impossible\n");
    }
    bw.flush();
  } 
  static boolean kmpMatch(int[] text, int[] pattern) {
    int[] pi = failure(pattern);
    int pt = 0;
    int pp = 0;
    while (pt < text.length) {
      if (text[pt] == pattern[pp]) {
        pt++; pp++;
        if (pp == pattern.length) return true;
      } else if (pp == 0) {
        pt++;
      } else {
        pp = pi[pp - 1];
      }
    }
    return false;
  }
  static int[] failure(int[] pat) {
    int[] pi = new int[pat.length];
    int pt = 1;
    int pp = 0;
    while (pt < pat.length) {
      if (pat[pt] == pat[pp]) {
        pp++;
        pi[pt] = pp;
        pt++;
      } else if (pp == 0) {
        pi[pt] = pp;
        pt++;
      } else {
        pp = pi[pp - 1];
      }
    }
    return pi;
  }
}
