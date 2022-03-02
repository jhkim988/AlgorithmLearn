import java.io.*;
import java.util.*;
import java.util.stream.*;

public class BOJ10867 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  public static void main(String[] args) throws IOException {
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    useStream(arr);
    // handmade(arr);
  }
  static void useStream(int[] arr) throws IOException {
    int[] result = IntStream.of(arr).sorted().distinct().toArray();
    bw.write(Integer.toString(result[0]));
    for (int i = 1; i < result.length; i++) {
      bw.write(' ');
      bw.write(Integer.toString(result[i]));
    }
    bw.newLine();
    bw.flush();
  }
  static void handmade(int[] arr) throws IOException {
    ArrayList<Integer> result = new ArrayList<>();
    sort(0, arr.length-1, arr);
    int idx = 0;
    int val = arr[idx];
    while (idx < arr.length) {
      val = arr[idx];
      result.add(val);
      idx = find(val+1, arr, 0, arr.length);
    }
    bw.write(Integer.toString(result.get(0)));
    for (int i = 1; i < result.size(); i++) {
      bw.write(' ');
      bw.write(Integer.toString(result.get(i)));
    }
    bw.newLine();
    bw.flush();
  }
  static void sort(int lo, int hi, int[] arr) {
    if (lo == hi) return;
    int mid = (lo + hi) >> 1;
    sort(lo, mid, arr);
    sort(mid+1, hi, arr);
    int[] aux = new int[mid-lo+1];
    System.arraycopy(arr, lo, aux, 0, aux.length);
    int ptr1 = 0; // aux pointer
    int ptr2 = mid+1; // arr pointer
    int ptr = lo; // arr pointer
    while (ptr1 < aux.length && ptr2 <= hi) {
      while (ptr1 < aux.length && ptr2 <= hi && aux[ptr1] <= arr[ptr2]) arr[ptr++] = aux[ptr1++];
      while (ptr1 < aux.length && ptr2 <= hi && arr[ptr2] <= aux[ptr1]) arr[ptr++] = arr[ptr2++];
    }
    while (ptr1 < aux.length) arr[ptr++] = aux[ptr1++];
    while (ptr2 <= hi) arr[ptr++] = arr[ptr2++];
  }
  static int find(int key, int[] arr, int lo, int hi) {
    while (lo + 1 < hi) {
      int mid = (lo + hi) >> 1;
      if (arr[mid] < key) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    return hi;
  }
}
