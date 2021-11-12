import java.io.*;

public class BOJ6749 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int A = Integer.parseInt(br.readLine());
    int B = Integer.parseInt(br.readLine());
    bw.write(B + (B - A) + "\n");
    bw.flush();
  }  
}
