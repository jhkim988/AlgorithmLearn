import java.io.*;
import java.util.*;

public class BOj10797 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int date = Integer.parseInt(br.readLine());
    int num = 0;
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < 5; i++) {
      if (Integer.parseInt(st.nextToken()) % 10 == date) num++;
    }
    bw.write(Integer.toString(num));
    bw.flush();
  }
}
