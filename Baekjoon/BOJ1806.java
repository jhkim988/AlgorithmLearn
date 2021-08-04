import java.io.*;
import java.util.*;

public class BOJ1806 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int len = Integer.parseInt(st.nextToken());
    long upper = Long.parseLong(st.nextToken());

    long[] data = new long[len];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < len; i++) {
      data[i] = Long.parseLong(st.nextToken());
    }

    int ptr1 = 0;
    int ptr2 = -1;
    Long partialSum = 0L; // [ptr1, ptr2]
    int minLen = Integer.MAX_VALUE;
    boolean flag = false;
    while (ptr2 < len) {
      if (partialSum >= upper) {  
        minLen = (ptr2 - ptr1 < minLen) ? (ptr2 - ptr1 + 1) : minLen;
        partialSum -= data[ptr1];
        ptr1++;
        flag = true;
      } else {
        ptr2++;
        partialSum += ptr2 < len ? data[ptr2] : 0;    
      }
    }
    if (!flag) {
      bw.write(0 + "\n");
    } else {
      bw.write(minLen + "\n");
    }
    bw.flush();
  }
}
