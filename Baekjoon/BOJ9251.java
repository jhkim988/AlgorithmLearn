import java.io.*;

public class BOJ9251 {
  public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      char[] str1 = br.readLine().toCharArray();
      char[] str2 = br.readLine().toCharArray();
      int[][] table = new int[str1.length + 1][str2.length + 1];

      for (int i = 1; i <= str1.length; i++) {
        for (int j = 1; j <= str2.length; j++) {
          if (str1[i - 1] == str2[j - 1]) {
            table[i][j] = table[i - 1][j - 1] + 1;
          } else {
            table[i][j] = Math.max(table[i - 1][j], table[i][j - 1]);
          }          
        }
      }

      int result = table[str1.length][str2.length];
      bw.write(result + "\n");
      bw.flush();
    } catch (IOException e) {
      // do nothing
    }
  }
}
