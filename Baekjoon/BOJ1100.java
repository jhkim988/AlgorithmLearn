import java.io.*;

public class BOJ1100 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int sum = 0;
    for (int i = 0; i < 8; i++) {
      char[] input = br.readLine().toCharArray();
      for (int j = 0; j < 8; j++) {
        if ((i+j)%2 == 0 && input[j] == 'F') sum++;
      }
    }
    bw.write(Integer.toString(sum));
    bw.flush();
  }
}
