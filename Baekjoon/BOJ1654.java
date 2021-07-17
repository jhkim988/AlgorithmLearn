import java.io.*;

public class BOJ1654 {
  public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String[] tmp = br.readLine().split(" ");
      int K = Integer.parseInt(tmp[0]);
      long N = Integer.parseInt(tmp[1]);

      long[] data = new long[K];
      long maxLen = data[0] = Long.parseLong(br.readLine());
      for (int i = 1; i < K; i++) {
        data[i] = Integer.parseInt(br.readLine());
        if (maxLen < data[i]) {
          maxLen = data[i];
        }
      }

      // find opt-len in [1, maxLen]
      long ptr1 = 1;
      long ptr2 = maxLen;
      do {
        long ptr3 = (ptr1 + ptr2) / 2;
        // System.out.println("[" + ptr1 + ", " + ptr2 + "]");
        // System.out.println("cut: " + ptr3);
        // System.out.println("num piece: " + howMany(data, ptr3) + "\n");
        if (howMany(data, ptr3) >= N) {
          ptr1 = ptr3 + 1;
        } else {
          ptr2 = ptr3 - 1;
        }
      } while (ptr1 <= ptr2);

      System.out.println(ptr2);

    } catch (IOException e) {
      // do nothing
    }
  }
  static long howMany(long[] data, long len) {
    long count = 0;
    for (int i = 0; i < data.length; i++) {
      count += data[i] / len;
    }
    return count;
  }
}
