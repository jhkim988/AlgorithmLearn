import java.io.*;

public class BOJ5597 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    boolean[] check = new boolean[31];
    for (int i = 0; i < 28; i++) {
      check[Integer.parseInt(br.readLine())] = true;
    }
    for (int i = 1; i < 31; i++) {
      if (check[i]) continue;
      bw.write(Integer.toString(i));
      bw.newLine();
    }
    bw.flush();
  }
}
