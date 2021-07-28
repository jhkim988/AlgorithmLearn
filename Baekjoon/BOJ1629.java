import java.io.*;
import java.util.HashMap;

public class BOJ1629 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    String[] tmp = br.readLine().split(" ");
    long A = Long.parseLong(tmp[0]);
    long B = Long.parseLong(tmp[1]);
    long C = Long.parseLong(tmp[2]);

    HashMap<Long, Long> hm = new HashMap<>();
    bw.write(calc(A, B, C, hm) + "\n");
    bw.flush();
  }
  static long calc(long A, long B, long C, HashMap<Long, Long> hm) {
    if (hm.containsKey(B)) {
      return hm.get(B);
    }
    if (B == 1) {
      return A % C;
    } else if (B == 0) {
      return 1;
    }
    
    if (B % 2 == 0) {
      long tmp1 = calc(A, B / 2, C, hm);
      long tmp2 = (tmp1 * tmp1) % C;
      hm.put(B, tmp2);
      return tmp2; 
    } else {
      long tmp1 = calc(A, B - B / 2, C, hm);
      long tmp2 = calc(A, B / 2, C, hm);
      long tmp3 = (tmp1 * tmp2) % C;
      hm.put(B, tmp3);
      return tmp3;
    }
  }
}
