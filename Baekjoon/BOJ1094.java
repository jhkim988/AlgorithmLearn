import java.io.*;

public class BOJ1094 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int x = Integer.parseInt(br.readLine());
    int num = 0;
    for (int i = 0; i <= 6; i++) {
      if ((x & (1 << i)) != 0) num++;
    }
    bw.write(Integer.toString(num));
    bw.newLine();
    bw.flush();
  }  
}
