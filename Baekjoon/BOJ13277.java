import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class BOJ13277 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    BigInteger a = new BigInteger(st.nextToken());
    BigInteger b = new BigInteger(st.nextToken());
    bw.write(a.multiply(b) + "\n");
    bw.flush();
  }
}
