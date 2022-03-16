import java.io.*;

public class BOJ9625 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int size = 46;
    int[] numA = new int[size];
    int[] numB = new int[size];
    numA[0] = 1;
    for (int i = 1; i < size; i++) {
      numA[i] = numB[i-1];
      numB[i] = numA[i-1] + numB[i-1];
    }
    bw.write(Integer.toString(numA[n]));
    bw.write(' ');
    bw.write(Integer.toString(numB[n]));
    bw.newLine();
    bw.flush();
  }
}
