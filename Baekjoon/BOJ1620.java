import java.io.*;
import java.util.*;

public class BOJ1620 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    String[] tmp = br.readLine().split(" ");
    int numDict = Integer.parseInt(tmp[0]);
    int numProblem = Integer.parseInt(tmp[1]);

    TreeMap<Integer, String> tmInt = new TreeMap<>();
    TreeMap<String, Integer> tmStr = new TreeMap<>();
    String str;
    for (int i = 1; i <= numDict; i++) {
      str = br.readLine();
      tmInt.put(i, str);
      tmStr.put(str, i);
    }

    for (int i = 0; i < numProblem; i++) {
      str = br.readLine();
      if (Character.isDigit(str.charAt(0))) {
        bw.write(tmInt.get(Integer.parseInt(str)) + "\n");
      } else {
        bw.write(tmStr.get(str) + "\n");
      }
    }
    bw.flush();
  }
}
