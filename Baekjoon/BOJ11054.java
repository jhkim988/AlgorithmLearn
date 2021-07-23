import java.io.*;

public class BOJ11054 {
  public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      int numData = Integer.parseInt(br.readLine());
      String[] tmp = br.readLine().split(" ");
      int[] data = new int[numData];
      for (int i = 0; i < numData; i++) {
        data[i] = Integer.parseInt(tmp[i]);
      }

      int[] table1 = new int[numData];
      int[] table2 = new int[numData];
      // table1[i] = length of LIS of A[0], ..., A[i] with last element A[i]
      // table2[i] = length of LDS of A[i], ..., A[n-1] with first element A[i]
      // Find i that satisfy table1[i] + table2[i] is maximum 
      
      // Use O(n^2) algorithm
      table1[0] = 1;
      table2[numData - 1] = 1;

      int maxIdx1;
      int maxIdx2;
      boolean flag1 = false;
      boolean flag2 = false;
      for (int i = 1; i < numData; i++) {
        // find max A[j] s.t. A[j] < A[i]
        // table1[i] = table[j] + 1
        maxIdx1 = -1;
        maxIdx2 = -1;
        for (int j = 0; j < i; j++) {          
          if (data[j] < data[i]) {
            maxIdx1 = maxIdx1 == -1 ? j : maxIdx1;
            if (table1[maxIdx1] <= table1[j]) {
              flag1 = true;
              maxIdx1 = j;
            }
          }
          if (data[numData - 1 - i] > data[numData - 1 - j]){
            maxIdx2 = maxIdx2 == -1 ? numData - 1 - j : maxIdx2;
            if (table2[maxIdx2] <= table2[numData - 1 - j]) {
              flag2 = true;
              maxIdx2 = numData - 1 - j;
            }
          }
        }
        table1[i] = flag1 ? table1[maxIdx1] + 1 : 1;
        table2[numData - 1 - i] = flag2 ? table2[maxIdx2] + 1 : 1; 
        flag1 = false;
        flag2 = false;
      }

      int max = 0;
      for (int i = 0; i < numData; i++) {
        // System.out.println(table1[i] + ", " + table2[i]);
        if (max < table1[i] + table2[i]) {
          max = table1[i] + table2[i];
        }
      }
      max--; // max value duplicated
      bw.write(max + "\n");
      bw.flush();
    } catch (IOException e) {
      // do nothing
    }
  }
}
