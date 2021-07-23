import java.io.*;
import java.util.Arrays;

public class BOJ2565 {
  private static class Pair implements Comparable<Pair> {
    int x;
    int y;
    
    Pair(String[] input) {
      this.x = Integer.parseInt(input[0]);
      this.y = Integer.parseInt(input[1]);
    }

    @Override
    public int compareTo(Pair other) {
      return Integer.compare(this.x, other.x);
    }
  }
	public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      int num = Integer.parseInt(br.readLine());
      Pair[] data = new Pair[num];
      for (int i = 0; i < num; i++) {
        data[i] = new Pair(br.readLine().split(" "));
      }
      Arrays.sort(data);
    
      // Find LIS of data[i].y
      int[] table = new int[num];
      table[0] = 1;
      int maxIdx;
      boolean flag = false;
      for (int i = 1; i < num; i++) {
        maxIdx = -1;
        for (int j = 0; j < i; j++) {
          if (data[j].y < data[i].y) {
            maxIdx = maxIdx == -1 ? j : maxIdx;
            if (table[maxIdx] <= table[j]) {
              maxIdx = j;
              flag = true;
            }
          }
        }
        table[i] = flag ? table[maxIdx] + 1 : 1;
        flag = false;
      }

      int lis = 0;
      for (int i = 0; i < num; i++) {
        if (lis < table[i]) {
          lis = table[i];
        }
      }
      bw.write((num - lis) + "\n");
      bw.flush();
    } catch (IOException e) {
      
    }
  }
}
