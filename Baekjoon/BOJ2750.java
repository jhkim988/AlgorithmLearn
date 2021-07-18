import java.io.*;

public class BOJ2750 {
  public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
      
      int numData = Integer.parseInt(br.readLine());
      int[] data = new int[numData];

      for (int i = 0; i < numData; i++) {
        data[i] = Integer.parseInt(br.readLine());
      }
      br.close();

      sort(data);
      
      for (int i = 0; i < numData; i++) {
        bw.write(data[i] + "\n");
      }
      bw.flush();
      bw.close();
    } catch (IOException e) {
      // do nothing
    }
  }
  static void sort(int[] data) {
    int[] buff = new int[data.length];
    mergeSort(data, 0, data.length - 1, buff);


  }
  static void mergeSort(int[] data, int left, int right, int[] buff) {
    if (left < right) {
      int i;
      int p = 0;
      int center = (left + right) / 2;
      int k = left;
      int j = 0;
      mergeSort(data, left, center, buff);
      mergeSort(data, center + 1, right, buff);

      for(i = left; i <= center; i++) {
        buff[p++] = data[i];
      }
      while (i <= right && j < p) {
        data[k++] = (buff[j] <= data[i]) ? buff[j++] : data[i++];
      }
      while (j < p) {
        data[k++] = buff[j++];
      }
    }
  }
}