import java.io.*;
import java.util.*;

public class BOJ8983 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int numShootSite = Integer.parseInt(st.nextToken());
    int numAnimal = Integer.parseInt(st.nextToken());
    int range = Integer.parseInt(st.nextToken());
    
    st = new StringTokenizer(br.readLine());
    int[] shootSite = new int[numShootSite];
    for (int i = 0; i < numShootSite; i++) {
      shootSite[i] = Integer.parseInt(st.nextToken());
    }

    mergeSort(shootSite, 0, numShootSite-1);
    
    int num = 0;
    while (numAnimal-- > 0) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      
      int lower = lower(shootSite, x);
      int upper = upper(shootSite, x);
      
      boolean possible = (0 <= lower && lower < numShootSite && x-shootSite[lower]+y <= range) || (0 <= upper && upper < numShootSite && shootSite[upper]-x+y <= range);
      if (possible) num++;
    }
    bw.write(Integer.toString(num));
    bw.flush();
  }
  static void mergeSort(int[] arr, int lo, int hi) {
    if (hi <= lo) return;
    int mid = (lo+hi) >> 1;
    mergeSort(arr, lo, mid);
    mergeSort(arr, mid+1, hi);

    int[] tmp = new int[mid-lo+1];
    System.arraycopy(arr, lo, tmp, 0, mid-lo+1);

    int ptr1 = lo, ptr2 = mid+1;
    for (int i = 0; i < tmp.length; i++) {
      while (ptr2 <= hi && tmp[i] >= arr[ptr2]) {
        arr[ptr1] = arr[ptr2];
        ptr1++; ptr2++;
      }
      arr[ptr1] = tmp[i];
      ptr1++;
    }
  }
  static int lower(int[] arr, int key) {
    int lo = -1, hi = arr.length;
    while (lo+1 < hi) {
      int mid = (lo+hi) >> 1;
      if (arr[mid] <= key) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    return lo;
  }
  static int upper(int[] arr, int key) {
    int lo = -1, hi = arr.length;
    while (lo+1 < hi) {
      int mid = (lo+hi) >> 1;
      if (arr[mid] < key) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    return hi;
  }
}
