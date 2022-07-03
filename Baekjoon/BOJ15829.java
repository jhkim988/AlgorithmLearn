import java.io.*;

public class BOJ15829 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int l = Integer.parseInt(br.readLine());
    char[] input = br.readLine().toCharArray();
    long r = 31;
    long M = 1234567891;
    long exp = 1;
    long ret = 0;

    for (int i = 0; i < l; i++) {
      int tmp = (int) input[i] - 96;
      ret = (exp*tmp%M + ret)%M;
      exp = (exp*r)%M;
    }
    bw.write(Long.toString(ret));
    bw.flush();
  }
}
