import java.io.*;
import java.util.Arrays;

public class BOJ2108 {
  public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int numData = Integer.parseInt(br.readLine());
      int[] data = new int[numData];
      
      int max = -4001;
      int min = 4001;
      int sum = 0;

      for (int i = 0; i < numData; i++) {
        data[i] = Integer.parseInt(br.readLine());
        max = max < data[i] ? data[i] : max;
        min = data[i] < min ? data[i] : min;
        sum += data[i];
      }

      int avg = (int) Math.round((double) sum / numData);

      Arrays.sort(data);
      int mid = data[numData/2];

      int maxfreq = 0;
      int maxfreqMinval = 4001;
      int maxfreqPrevMinval = 4001;
      for (int i = 0; i < numData; i++) {
        int freq = 0;
        int j = i;
        while (j < numData && data[i] == data[j]) {
          freq++;
          j++;
        }
        if (maxfreq == freq) {
          if (data[i] < maxfreqPrevMinval) {
            if (data[i] < maxfreqMinval) {
              maxfreqPrevMinval = maxfreqMinval;
              maxfreqMinval = data[i];
            } else {
              maxfreqPrevMinval = data[i];
            }
          }
        }
        if (maxfreq < freq) {
          maxfreq = freq;
          maxfreqMinval = data[i];
          maxfreqPrevMinval = 4001;
        }
        i = j - 1;
      }
      maxfreqPrevMinval = maxfreqPrevMinval == 4001 ? maxfreqMinval : maxfreqPrevMinval;

      // System.out.println("----------"); // input ----- output
      System.out.println(avg);
      System.out.println(mid);
      System.out.println(maxfreqPrevMinval);
      System.out.println(max - min);

    } catch (IOException e) {
      // do nothing
    }
  }
}
