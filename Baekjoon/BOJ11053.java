import java.io.*;
import java.util.*;

public class BOJ11053 {
  public static void oldSolution(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      int numData = Integer.parseInt(br.readLine());
      int[] data = new int[numData];
      String[] tmp = br.readLine().split(" ");
      for (int i = 0; i < numData; i++) {
        data[i] = Integer.parseInt(tmp[i]);
      }

      int[] numIncrSubs = new int[numData]; // numIncrSubs[subseq len - 1] = end of subseq
      numIncrSubs[0] = data[0];
      int ptr = 0;
      int key;
      for (int i = 1; i < numData; i++) { // data
        // ptr = 0;
        // while (ptr < i && numIncrSubs[ptr] != 0 && numIncrSubs[ptr] < data[i]) {
        //   ptr++;
        // }

        // use Arrays.binarySearch
        key = Arrays.binarySearch(numIncrSubs, 0, ptr + 1, data[i]);
        key = key < 0 ? -(key + 1) : key;
        if (numIncrSubs[key] == 0 || numIncrSubs[key] > data[i]) {
          if (numIncrSubs[key] == 0) {
            ptr++;
          }
          numIncrSubs[key] = data[i];
        }
      }

      int result = -1;
      for (int i = 0; i < numData; i++) {
        if (numIncrSubs[i] == 0) {
          result = i;
          break;
        }
      }
      result = result == -1 ? numData : result;
      bw.write(result + "\n");
      bw.flush();

    } catch (IOException e) {
      // do nothing
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int len = Integer.parseInt(br.readLine());
    int[] seq = new int[len];

    StringTokenizer st = new StringTokenizer(br.readLine());

    for (int i = 0; i < len; i++) {
      seq[i] = Integer.parseInt(st.nextToken());
    } 

    // bw.write(useDoubleLoop(seq) + "\n");
    bw.write(useBinSearch(seq) + "\n");
    bw.flush();
  }
  static int useDoubleLoop(int[] seq) {
    int len = seq.length;
    int[] table = new int[len]; // table[i]: length of LIS seq[0 ... i]
    int max = 0;
    for (int i = 0; i < len; i++) {
      table[i] = 1; // itself
      for (int j = 0; j < i; j++) {
        if (seq[j] < seq[i] && table[i] < table[j] + 1) { // increasing?
          table[i] = table[j] + 1;
        }
      }
      if (max < table[i]) {
        max = table[i];
      }
    } 
    return max;
  }
  static int useBinSearch(int[] seq) {
    // Get Length of LIS
    // length of LIS: ptr - 1;
    int len = seq.length;
    int[] table = new int[len + 1];
    int ptr = 1;
    for (int  i = 0; i < len; i++) {
      if (table[ptr - 1] < seq[i]) {
        table[ptr++] = seq[i];
      } else {
        int find = Arrays.binarySearch(table, 0, ptr, seq[i]);
        if (find < 0) {
          find = -(find + 1);
        }
        table[find] = seq[i];
      }
    } 
    return ptr - 1;
  }
}
