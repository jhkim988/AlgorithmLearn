import java.io.*;

public class BOJ1475 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] input = br.readLine().toCharArray();
    int[] statistic = new int[10];
    for (char ch : input) {
      statistic[ch - '0']++;
    }
    int average = (statistic[6] + statistic[9] + 1) / 2;
    int max = 0;
    for (int i = 0; i < 10; i++) {
      if (i == 6 || i == 9) continue;
      if (max < statistic[i]) max = statistic[i];
    }
    if (max < average) max = average;
    bw.write(Integer.toString(max));
    bw.newLine();
    bw.flush();
  }
}
