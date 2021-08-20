import java.io.*;
import java.util.*;

public class BOJ2473 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;
    int N = Integer.parseInt(br.readLine());
    st = new StringTokenizer(br.readLine());
    long[] data = new long[N];
    for (int i = 0; i < N; i++) {
      data[i] =Long.parseLong(st.nextToken());
    }
    Arrays.sort(data);
    long minSum = 3_000_000_001L;
    int ptr1 = 0;
    int ptr2 = 1;
    int ptr3 = N - 1;
    int resultPtr1 = 0;
    int resultPtr2 = 1;
    int resultPtr3 = 2;

    for (ptr1 = 0; ptr1 < N - 2; ptr1++) {
      ptr2 = ptr1 + 1;
      ptr3 = N - 1;
      while (ptr2 < ptr3) {
        long sum = data[ptr1] + data[ptr2] + data[ptr3];
        long sumABS = Math.abs(sum);
        if (sumABS < minSum) {
          resultPtr1 = ptr1;
          resultPtr2 = ptr2;
          resultPtr3 = ptr3;
          minSum = sumABS;
        }
        if (sum < 0) {
          ptr2++;
        } else if (sum > 0) {
          ptr3--;
        } else {
          bw.write(data[ptr1] + " " + data[ptr2] + " " + data[ptr3] + "\n");
          bw.flush();
          return;
        }
      }
    }
    bw.write(data[resultPtr1] + " " + data[resultPtr2] + " " + data[resultPtr3] + "\n");
    bw.flush();
  }
}
