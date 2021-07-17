import java.io.*;

public class BOJ10870 {
  public static void main(String[] args) {
    try {
      InputStreamReader isr = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(isr);
      OutputStreamWriter osw = new OutputStreamWriter(System.out);
      BufferedWriter bw = new BufferedWriter(osw);

      int input = Integer.parseInt(br.readLine());
      br.close();
      isr.close();

      bw.write(fibo(input) + "\n");
      bw.flush();
      bw.close();
      osw.close();

    } catch (IOException e) {
      // do nothing
    }
  }
  static int fibo(int num) {
    if (num == 0) {
      return 0;
    } else if (num == 1) {
      return 1;
    }
    return fibo(num - 1) + fibo(num - 2);
  }
}
