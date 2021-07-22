import java.io.*;

public class BOJ11053 {
  public static void main(String[] args) {
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
      int ptr;
      for (int i = 1; i < numData; i++) { // data
        ptr = 0;
        while (ptr < i && numIncrSubs[ptr] != 0 && numIncrSubs[ptr] < data[i]) {
          ptr++;
        }
        if (numIncrSubs[ptr] == 0 || numIncrSubs[ptr] > data[i]) {
          numIncrSubs[ptr] = data[i];
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
}
