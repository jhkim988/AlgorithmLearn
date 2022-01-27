import java.io.*;

public class BOJ18108 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    bw.write(Integer.toString(Integer.parseInt(br.readLine()) - 543));
    bw.newLine();
    bw.flush();
  }
}
