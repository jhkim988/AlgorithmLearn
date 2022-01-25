import java.io.*;

public class BOJ14939 {
  public static void main(String [] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[][] input = new char[10][10];
    for (int i = 0; i < 10; i++) input[i] = br.readLine().toCharArray();

    int[] bits = new int[10];
    for (int i = 0; i < 10; i++) {
      int b = 0;
      for (int j = 0; j < 10; j++) {
        if (input[i][j] == 'O') b = b | (1 << j);
      }
      bits[i] = b;
    }


  }
  // static int recur(int row, int col, int[] bits) {

  //   for (int c = col; c < 10; c++) {
      
  //   }
  // }
}