import java.io.*;

public class BOJ10886 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int cute = 0, not = 0;
    for (int i = 0; i < n; i++) {
      if (Integer.parseInt(br.readLine()) == 0) not++;
      else cute++;
    }
    if (cute < not) {
      bw.write("Junhee is not cute!\n");
    } else {
      bw.write("Junhee is cute!\n");
    }
    bw.flush();
  }
}
