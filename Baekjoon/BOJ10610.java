import java.io.*;
import java.util.*;

public class BOJ10610 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] data = br.readLine().toCharArray();
    ArrayList<Character> wrapper = new ArrayList<>();

    for (int i = 0; i < data.length; i++) {
      wrapper.add(data[i]);
    }

    boolean hasZero = false;
    int sum = 0;
    for (int i = 0; i < data.length; i++) {
      if (data[i] == '0') {
        hasZero = true;
      }
      sum += data[i] - '0';
    }
    if (!hasZero || sum % 3 != 0) {
      bw.write("-1\n");
    } else {
      Collections.sort(wrapper, Collections.reverseOrder());
      StringBuilder sb = new StringBuilder();
      for (char ch : wrapper) {
        sb.append(ch);
      }
      sb.append('\n');
      bw.write(sb.toString());
    }
    bw.flush();
  }
}
