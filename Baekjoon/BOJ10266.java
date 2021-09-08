import java.io.*;
import java.util.*;

public class BOJ10266 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N = Integer.parseInt(br.readLine());

    int[] A = new int[N];
    int[] B = new int[N];    
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      A[i] = Integer.parseInt(st.nextToken()); 
    }
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      B[i] = Integer.parseInt(st.nextToken());
    }    
    Arrays.sort(A);
    Arrays.sort(B);

    int[] gapA = new int[2 * N - 1];
    int[] gapB = new int[2 * N - 1];
    for (int i = 0; i < N - 1; i++) {
      gapA[N + i] = gapA[i] = A[i + 1] - A[i];
      gapB[N + i] = B[i + 1] - B[i];
    }
    gapA[N - 1] = 360_000 - A[N - 1] + A[0];
    gapB[N - 1] = 360_000 - B[N - 1] + B[0];

    if (useKMP(A, B, gapA, gapB)) {
      bw.write("possible\n");
    } else {
      bw.write("impossible\n");
    }
    bw.flush();
  } 
  static boolean useKMP(int[] A, int[] B, int[] gapA, int[] gapB) {
    int[] pi = failure(B, gapB);
    int pA = 0; // pointer of A, text
    int pB = 0; // pointer of B, pattern
    int lenA = A.length;
    int lenB = B.length;
    int diff = A[pA] - B[pB];
    boolean flag = false;
    while (pA < lenA) {
      diff = A[pA] - B[pB];
      if ((B[pB] + diff) % 360_000 == A[pA]) {
        pA++;
        pB++;
      }
      if (pB == lenB) {
        return true;
      } else if (pA < lenA && A[pA] != (B[pB] + diff) % 360_000) {
        pB = 0;
      }
      if (!flag && pA >= lenA) {
        pA %= lenA;
        flag = true;
      }
      if (flag && pA >= lenA) {
        break;
      }
    }
    return false;
  }
  static int[] failure(int[] pat, int[] txt) {
    int[] pi = new int[pat.length];
    int pt = 1;
    int pp = 0;
    while (pt < pat.length) {
      if (txt[pt] == pat[pp]) {
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
