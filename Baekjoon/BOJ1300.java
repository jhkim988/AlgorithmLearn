import java.io.*;

public class BOJ1300 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    long N = Integer.parseInt(br.readLine());
    long k = Integer.parseInt(br.readLine());

    long minVal = 1;
    long maxVal = N * N;
    long mid = 0;
    long sol = 0;
    while (minVal <= maxVal) {
      mid = (minVal + maxVal) / 2;
      long numSmall = 0;
      long numSame = 0;
      long i = 0;
      for (i = 1; i <= N; i++) {
        long num = 0;
        if (mid > i * N) {
          num = N;
        } else if (mid < i) {
          num = 0;
        } else {
          num = mid % i == 0 ? mid / i - 1 : mid / i;
        }
        numSmall += num;

        if (mid % i == 0 && mid <= N * i) {
          numSame++;
        }
      }
      // System.out.printf("interval: [%d, %d]\n", minVal, maxVal);
      // System.out.println("mid: " + mid);
      // System.out.println("numSmall: " + numSmall);
      // System.out.println("numSame: " + numSame);
      if (k <= numSmall) {
        maxVal = mid - 1;
      } else if (k <= numSmall + numSame) {
        sol = mid;
        break;
      } else {
        minVal = mid + 1;
      }
    }
    bw.write(sol + "\n");
    bw.flush();
  }
}
