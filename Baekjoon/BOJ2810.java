import java.io.*;

public class BOJ2810 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    char[] arr = br.readLine().toCharArray();
    int numL = 0;
    for (int i = 0; i < n; i++) {
      if (arr[i] == 'L') numL++;
    }
    bw.write(Integer.toString(Integer.min(n+1-numL/2, n)));
    bw.flush();
  }  
}
