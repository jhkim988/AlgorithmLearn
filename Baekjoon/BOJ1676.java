import java.io.*;

public class BOJ1676 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int num = Integer.parseInt(br.readLine());
    int[] table2 = new int[num + 1];
    int[] table5 = new int[num + 1];

    if (num >= 5) {
      table2[2] = 1;
    }
    if (num >= 5) {
      table5[5] = 1;
    }

    if (num >= 3) {
      for (int i = 3; i <= num; i++) {
        if (i % 2 == 0) {
          table2[i] = table2[i/2] + 1;
        }
      }
    }

    if (num >= 6) {
      for (int i = 6; i <= num; i++) {
        if (i % 5 == 0) {
          table5[i] = table5[i/5] + 1;
        }
      }
    }

    int sum2 = 0;
    int sum5 = 0;
    for (int i = 0; i <= num; i++) {
      sum2 += table2[i];
      sum5 += table5[i];
    }
    bw.write(Math.min(sum2, sum5) + "\n");
    bw.flush();
  } 
}
