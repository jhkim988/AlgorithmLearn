import java.io.*;
import java.util.*;

public class BOJ11652 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int len = Integer.parseInt(br.readLine());
    long[] card = new long[len];
    for (int i = 0; i < len; i++) {
      card[i] = Long.parseLong(br.readLine());
    }
    Arrays.sort(card);
    int ptrlo = 0;
    int maxCount = 0;
    long maxNum = card[0];
    while (ptrlo < len) {
      int ptrhi = ptrlo;
      while (ptrhi < len && card[ptrlo] == card[ptrhi]) {
        ptrhi++;
      }
      if (maxCount < ptrhi - ptrlo) {
        maxCount = ptrhi - ptrlo;
        maxNum = card[ptrlo];
      }
      ptrlo = ptrhi;
    }

    bw.write(maxNum + "\n");
    bw.flush();
  }
}
