import java.io.*;
import java.util.*;

public class BOJ11478 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] str = br.readLine().toCharArray();
    HashSet<String> hs = new HashSet<>();
    for (int i = 0; i < str.length; i++) {
      for (int j = str.length-i; j >= 1; j--) {
        hs.add(new String(str, i, j));
      }
    }
    bw.write(Integer.toString(hs.size()));
    bw.flush();
  }
}
