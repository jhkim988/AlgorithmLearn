import java.io.*;
import java.util.*;

public class BOJ2470 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int len = Integer.parseInt(br.readLine());
    int[] seq = new int[len];
    StringTokenizer st = new StringTokenizer(br.readLine());    
    for (int i = 0; i < len; i++) {
      seq[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(seq);

    int ptr1 = 0;
    int ptr2 = len - 1;
    int minSum = Integer.MAX_VALUE;
    int val1 = 0;
    int val2 = 0;

    while (ptr1 < ptr2) {
      if (Math.abs(seq[ptr1] + seq[ptr2]) < minSum) {
        val1 = seq[ptr1];
        val2 = seq[ptr2];
        minSum = Math.abs(seq[ptr1] + seq[ptr2]);
      }
      if (Math.abs(seq[ptr1 + 1] + seq[ptr2]) < Math.abs(seq[ptr1] + seq[ptr2 - 1])) {
        ptr1++;
      } else {
        ptr2--;
      }
    }

    bw.write(val1 + " " + val2 + "\n");
    bw.flush();
  }
}
