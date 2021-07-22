import java.io.*;

public class BOJ9095 {
  public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      int numTest = Integer.parseInt(br.readLine());
      int[] table = new int[11];
      table[1] = 1;
      table[2] = 2;
      table[3] = 4;
      int input;
      for (int i = 0; i < numTest; i++) {
        input = Integer.parseInt(br.readLine());
        bw.write(onetwothree(input, table) + "\n");
      }
      bw.flush();
    } catch (IOException e) {
      // do nothing
    }
  }
  static int onetwothree(int input, int[] table) {
    if (input != 0 && table[input] == 0) {
      table[input] = onetwothree(input - 1, table) + onetwothree(input - 2, table) + onetwothree(input - 3, table);
    }    
    return table[input];
  }
}
