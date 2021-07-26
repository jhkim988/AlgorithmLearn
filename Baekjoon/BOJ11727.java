import java.io.*;

public class BOJ11727 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int num = Integer.parseInt(br.readLine());
    
    if (num < 2) {
      bw.write(1 + "\n");
      bw.flush();
      return;
    }
    int[] table = new int[num + 1];
    
    table[0] = 1;
    table[1] = 1;
    for (int i = 2; i <= num; i++) {
      table[i] = (table[i - 1] + 2 * table[i - 2]) % 10007;
    }
    bw.write(table[num] + "\n");
    bw.flush();
  }
}
