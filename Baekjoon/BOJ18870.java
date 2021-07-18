import java.io.*;
import java.util.Arrays;

public class BOJ18870 {
  public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      int numData = Integer.parseInt(br.readLine());
      int[] data = new int[numData];
      int[] copy = new int[numData];
      String[] tmp = br.readLine().split(" ");
      for (int i = 0; i < numData; i++) {        
        data[i] = Integer.parseInt(tmp[i]);
        copy[i] = data[i];
      }
      br.close();
      
      copy = sort(copy); // delete duplicate value;
      
      for (int i = 0; i < data.length; i++) {
        int key = Arrays.binarySearch(copy, data[i]);
        bw.write(key + " ");
      }

      bw.flush();
      bw.close();
    } catch (IOException e) {
      // do nothing
    }
  }
  static int[] sort(int[] arr) {
    Arrays.sort(arr);
    int[] tmp = new int[arr.length];
    int k = 0; // pointer of tmp
    int count = 0;
    for (int i = 0; i < arr.length; i++) {
      int j = i;
      while (j < arr.length && arr[i] == arr[j]) {
        j++;
      }
      tmp[k++] = arr[i];
      count++;
      i = j - 1;
    }
    int[] tmp1 = new int[count];
    for (int i = 0; i < count; i++) {
      tmp1[i] = tmp[i];
    }
    return tmp1;
  }
}
