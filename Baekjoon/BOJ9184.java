import java.io.*;

public class BOJ9184 {
  static int[][][] data = new int[21][21][21];
  static boolean[][][] check = new boolean[21][21][21];
  public static void main(String[] args) {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    try {
      while (true){
        String[] tmp = br.readLine().split(" ");
        int[] input = new int[3];
        for (int i = 0; i < 3; i++) {
          input[i] = Integer.parseInt(tmp[i]);
        }
        if (input[0] == input[1] && input[1] == input[2] && input[0] == -1) {
          break;
        }
        bw.write("w(" + input[0] + ", " + input[1] + ", " + input[2] + ") = " + w(input[0], input[1], input[2]) + "\n");
      }
      bw.flush();
    } catch (IOException e) {

    }
  }
  static int w(int a, int b, int c) {
    if (a <= 0 || b <= 0 || c <= 0) {
      return 1;
    } 
    if (a > 20 || b > 20 || c > 20) {
      return w(20, 20, 20);
    }
    if (check[a][b][c]) {
      return data[a][b][c];
    }
    // System.out.println("Call: " + a + ", " + b + ", " + c);
    check[a][b][c] = true;
    if (a < b && b < c) {      
      data[a][b][c] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
      return data[a][b][c];
    }
    data[a][b][c] = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1);
    return data[a][b][c];
  }
}
