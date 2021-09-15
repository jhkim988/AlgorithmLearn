import java.io.*;
import java.util.*;

public class BOJ11055 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    int len = Integer.parseInt(br.readLine());
    int[] seq = new int[len];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < len; i++) {
      seq[i] = Integer.parseInt(st.nextToken());
    }

    int[][] table = new int[len + 1][len + 1];
    int ptrRow = 0;
    int ptrCol = 1;

    for (int i = 0; i < len; i++) {
      if (table[ptrRow][ptrCol - 1] < seq[i]) {
        ptrCol++;
        // for (int j = 0; j <= ptrRow; j++) {
        //   table[j][ptrCol] = seq[i]; 
        // }
      } else {
        int find = Arrays.binarySearch(table[ptrRow], 0, ptrCol, seq[i]);
        if (find < 0) {
          find = -(find + 1);
        }
        ptrRow++;
        for (int j = 0; j < ptrCol; j++) {
          table[ptrRow][j] = table[ptrRow - 1][j];
        }
        table[ptrRow][find] = seq[i];
      }
    }

    for (int i = 0; i <= len; i++) {
      System.out.println(Arrays.toString(table[i]));
    }

    int max = 0;
    for (int i = 0; i <= len; i++) {
      int sum = 0;
      for (int j = 0; j <= len; j++) {
        sum += table[i][j];
      }
      if (max < sum) {
        max = sum;
      }
    }

    bw.write(max + "\n");
    bw.flush();
  }
}
