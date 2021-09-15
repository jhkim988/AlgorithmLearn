import java.io.*;
import java.util.*;

public class BOJ11055 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    int len = Integer.parseInt(br.readLine());
    int[] seq = new int[len + 1];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= len; i++) {
      seq[i] = Integer.parseInt(st.nextToken());
    }

    int[] table = new int[len + 1];
    int max = 0;

    for (int i = 1; i <= len; i++) {
      table[i] = seq[i];
      for (int j = 1; j < i; j++) {
        if (seq[j] < seq[i] && table[i] < table[j] + seq[i]) {
          table[i] = table[j] + seq[i];
        }
      }

      if (max < table[i]) {
        max = table[i];
      }
    }

    bw.write(max + "\n");
    bw.flush();
  }
}
