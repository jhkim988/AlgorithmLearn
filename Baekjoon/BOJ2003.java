import java.io.*;
import java.util.*;

public class BOJ2003 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
  
    int[] data = new int[N + 1];
    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      data[i] = data[i - 1] + Integer.parseInt(st.nextToken());
    }

    int ptrHead = 1;
    int ptrTail = N;
    int numCase = 0;
    int prevStat = 0;
    while (ptrHead >= 1 && ptrTail <= N) {
      int tmp = data[ptrTail] - data[ptrHead - 1];
      // System.out.println("ptrHead: " + ptrHead + ", ptrTail: " + ptrTail + ", tmp: " + tmp);
      if (tmp > M) {
        ptrHead++;
        prevStat = 1;
      } else if (tmp < M) {
        ptrHead--;
        if (prevStat == 1) ptrTail--; 
        prevStat = 2;
      } else {
        numCase++;
        ptrTail--;
        prevStat = 3;
      }
    }
    bw.write(numCase + "\n");
    bw.flush();
  }
}
