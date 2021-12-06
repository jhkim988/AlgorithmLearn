import java.io.*;
import java.util.*;

public class BOJ17088 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine()); 
    int[] seq = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      seq[i] = Integer.parseInt(st.nextToken());
    }
    if (N == 1) {
      bw.write("0\n");
      bw.flush();
      return;
    } 
    int[] operator = {0, 1, -1};
    int[] operatorCount = {0, 1, 1};
    int[] countList = new int[N];
    boolean possible = false;
    int totCount = N;
    for (int i = 0; i < 3; i++) {
      loop: for (int j = 0; j < 3; j++) {
        countList[0] = operator[i];
        countList[1] = operator[j];
        int count = operatorCount[i] + operatorCount[j];
        int diff = seq[1] + operator[j] - seq[0] - operator[i];
        for (int k = 2; k < N; k++) {
          if (seq[k] - seq[k - 1] - countList[k - 1] == diff) {
            countList[k] = 0;
            continue;
          } else if (seq[k] - seq[k - 1] - countList[k - 1] + operator[1] == diff) {
            countList[k] = 1;  
            count++;
          } else if (seq[k] - seq[k - 1] - countList[k - 1] + operator[2] == diff) {
            countList[k] = -1;
            count++;
          } else continue loop; 
        }
        possible = true;
        if (count < totCount) totCount = count;
      }
    }
    if (possible) bw.write(totCount + "\n");
    else bw.write("-1\n");
    bw.flush();
  }
}
