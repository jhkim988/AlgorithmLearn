import java.io.*;

public class BOJ1463 {
  public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      int num = Integer.parseInt(br.readLine());
      int[] memo = new int[num]; // memo[i]: i + 1

      bw.write(minNumOper(num, memo) + "\n");
      bw.flush();
    } catch (IOException e) {

    }
  }
  static int minNumOper(int num, int[] memo) {
    if (num <= 0) {
      return 1000000;
    }
    if (num == 1) {
      return 0;
    }
    if (memo[num - 1] != 0) {
      return memo[num - 1];
    } else {
      int tmp1 = num + 1;
      int tmp2 = tmp1;
      int tmp3 = tmp2;
      if (num % 3 == 0) {
        tmp1 = minNumOper(num / 3, memo) + 1;
      }
      if (num % 2 == 0) {
        tmp2 = minNumOper(num / 2, memo) + 1;
      }
      tmp3 = minNumOper(num - 1, memo) + 1;
      memo[num - 1] = Math.min(Math.min(tmp1, tmp2), tmp3);
      return memo[num - 1];
    }
  }
}
