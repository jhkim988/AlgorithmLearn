import java.io.*;

public class BOJ13392 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    char[] start = br.readLine().toCharArray();
    char[] end = br.readLine().toCharArray();
    int[][] num = new int[n][2]; // 0: num, 1: numLeft
    if (start[0] <= end[0]) { // rot left
      num[0][0] = end[0]-start[0];
      num[0][1] = num[0][0];
    } else {
      num[0][0] = start[0]-end[0];
    }
    for (int i = 1; i < n; i++) {
      
    }
  }
}
