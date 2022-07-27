import java.io.*;
import java.util.*;

public class BOJ7795 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  public static void main(String[] args) throws IOException {
    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int numA = Integer.parseInt(st.nextToken());
      int numB = Integer.parseInt(st.nextToken());
      int[] arrA = input(numA);
      int[] arrB = input(numB);
      
      // bw.write(Integer.toString(useBinarySearch(arrA, arrB)));
      bw.write(Integer.toString(useTwoPointer(arrA, arrB)));
      bw.newLine();
    }
    bw.flush();
  }
  static int[] input(int num) throws IOException {
    int[] arr = new int[num];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < num; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    return arr;
  }
  static int binarySearch(int[] arr, int key) {
    int lo = -1, hi = arr.length;
    while (lo+1 < hi) {
      int mid = (lo+hi) >> 1;
      if (arr[mid] < key) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    return lo;
  }
  static int useBinarySearch(int[] arrA, int[] arrB) {
    Arrays.sort(arrA);
    Arrays.sort(arrB);
    int answer = 0;
    for (int i = 0; i < arrA.length; i++) {
      answer += binarySearch(arrB, arrA[i])+1;
    }
    return answer;
  }
  static int useTwoPointer(int[] arrA, int[] arrB) {
    Arrays.sort(arrA);
    Arrays.sort(arrB);
    int ptrA = 0, ptrB = 0;
    int answer = 0;
    
    while (ptrA < arrA.length) {
      while (ptrB < arrB.length && arrB[ptrB] < arrA[ptrA]) ptrB++;
      answer += ptrB;
      ptrA++;
    }

    return answer;
  }
}