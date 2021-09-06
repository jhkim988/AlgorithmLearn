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

    if (useKMP(A, B)) {
      bw.write("possible\n");
    } else {
      bw.write("impossible\n");
    }
    bw.flush();
  } 
  static boolean useKMP(int[] A, int[] B) {
    int[] pi = failure(B);
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
  static int[] failure(int[] pat) {
    int len = pat.length - 1;
    int[] pi = new int[len];
    int pt = 1;
    int pp = 0;
    while (pt < len) {
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
