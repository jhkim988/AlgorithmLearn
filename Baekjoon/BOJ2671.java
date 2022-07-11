import java.io.*;

public class BOJ2671 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    String str = br.readLine();
    bw.write(str.matches("((100+1+)|(01))+") ? "SUBMARINE" : "NOISE");
    bw.flush();
  }  
}
