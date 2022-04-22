import java.io.*;

public class BOJ1013 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      if (br.readLine().matches("((100+1+)|(01))+")) {
        bw.write("YES\n");
      } else {
        bw.write("NO\n");
      }
    }
    bw.flush();
  }
}
