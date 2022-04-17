import java.io.*;

public class BOJ2576 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int sum = 0;
    int minOdd = 101;
    for (int i = 0; i < 7; i++) {
      int input = Integer.parseInt(br.readLine());
      if (input % 2 == 0) continue;
      sum += input;
      if (input < minOdd) minOdd = input;
    }
    bw.write(Integer.toString(sum));
    bw.newLine();
    bw.write(Integer.toString(minOdd == 101 ? -1 : minOdd));
    bw.flush();
  }
}