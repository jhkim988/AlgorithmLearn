import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class BOJ1271 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    BigInteger a = new BigInteger(st.nextToken());
    BigInteger b = new BigInteger(st.nextToken());
    
    StringBuilder sb = new StringBuilder();
    sb.append(a.divide(b)).append('\n');
    sb.append(a.remainder(b)).append('\n');
    bw.write(sb.toString());
    bw.flush();
  }  
}
