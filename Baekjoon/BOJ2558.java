import java.io.*;

public class BOJ2558 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int A = Integer.parseInt(br.readLine());
    int B = Integer.parseInt(br.readLine());
    bw.write((A + B) + "\n");
    bw.flush();
  }  
}
