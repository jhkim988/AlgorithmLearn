import java.io.*;

public class BOJ1032 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    char[][] input = new char[n][];
    for (int i = 0; i < n; i++) {
      input[i] = br.readLine().toCharArray();
    }
    char[] answer = new char[input[0].length];
    for (int i = 0; i < input[0].length; i++) {
      boolean allSame = true;
      for (int j = 1; j < n; j++) {
        allSame = allSame && (input[j-1][i] == input[j][i]);
      }
      if (allSame) answer[i] = input[0][i];
      else answer[i] = '?';
    }
    bw.write(answer);
    bw.flush();
  }
}
