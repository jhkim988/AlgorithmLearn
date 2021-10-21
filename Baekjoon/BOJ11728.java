import java.io.*;
import java.util.*;

public class BOJ11728 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int sizeA = Integer.parseInt(st.nextToken());
    int sizeB = Integer.parseInt(st.nextToken());
    int[] A = new int[sizeA];
    int[] B = new int[sizeB];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < sizeA; i++) {
      A[i] = Integer.parseInt(st.nextToken());
    }
    st= new StringTokenizer(br.readLine());
    for (int i = 0; i < sizeB; i++) {
      B[i] = Integer.parseInt(st.nextToken());
    }

    StringBuilder sb = new StringBuilder();
    int ptrA = 0;
    int ptrB = 0;
    while (ptrA < sizeA || ptrB < sizeB) {
      while (ptrA < sizeA && ptrB < sizeB && A[ptrA] <= B[ptrB]) {
        sb.append(A[ptrA]).append(' ');
        ptrA++;
      }
      while (ptrA < sizeA && ptrB < sizeB && B[ptrB] <= A[ptrA]) {
        sb.append(B[ptrB]).append(' ');
        ptrB++;
      }

      if (ptrA >= sizeA) {
        while (ptrB < sizeB) {
          sb.append(B[ptrB]).append(' ');
          ptrB++;
        }
      }
      if (ptrB >= sizeB) {
        while (ptrA < sizeA) {
          sb.append(A[ptrA]).append(' ');
          ptrA++;
        }
      }
    }
    sb.append('\n');
    bw.write(sb.toString());
    bw.flush();
  }
}