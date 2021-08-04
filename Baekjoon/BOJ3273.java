import java.io.*;
import java.util.*;

public class BOJ3273 {
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
    int target = Integer.parseInt(br.readLine());

    int ptr1 = 0;
    int ptr2 = len - 1;
    int count = 0;
    while (ptr1 < ptr2) {
      if (seq[ptr1] + seq[ptr2] < target) {
        ptr1++;
      } else if (seq[ptr1] + seq[ptr2] > target) {
        ptr2--;
      } else {
        // seq: distinct
        count++;
        ptr1++;
        ptr2--;
      }
    }

    bw.write(count + "\n");
    bw.flush();
  }
}
