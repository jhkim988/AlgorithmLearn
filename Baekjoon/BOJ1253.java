import java.io.*;
import java.util.*;

public class BOJ1253 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] input = new int[N];
    for (int i = 0; i < N; i++) {
      input[i] = Integer.parseInt(st.nextToken());
    }    
    int answer = useTwoPointer(input);
    bw.write(Integer.toString(answer));
    bw.newLine();
    bw.flush();
  }
  static int useTwoPointer(int[] arr) {
    if (arr.length <= 2) return 0;
    Arrays.sort(arr);
    int count = 0;
    for (int i = 0; i < arr.length; i++) {
      int loPtr = 0;
      int hiPtr = arr.length - 1;
      while (loPtr < hiPtr) {
        if (loPtr == i) {
          loPtr++;
          continue;
        }
        if (hiPtr == i) {
          hiPtr--;
          continue;
        }
        int sum = arr[loPtr] + arr[hiPtr];
        if (sum > arr[i]) {
          hiPtr--;
        } else if (sum < arr[i]) {
          loPtr++;
        } else {
          count++;
          break;
        }
      }
    }
    return count;
  }
  static int useBinarySearch(int[] arr) {
    if (arr.length <= 2) return 0;
    Arrays.sort(arr);
    int num = 0;
    outer: for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr.length; j++) {
        if (i == j) continue;
        int key = arr[i] - arr[j];
        int lowerbound = lowerbound(key, arr);
        int upperbound = upperbound(key, arr);
        if (lowerbound < 0 || lowerbound >= arr.length || arr[lowerbound] != key) continue; // not found
        int numFound = upperbound - lowerbound;
        if (lowerbound <= i && i < upperbound) numFound--;
        if (lowerbound <= j && j < upperbound) numFound--;
        if (numFound > 0) {
          num++;
          continue outer;
        }
      }
    }
    return num;
  }
  static int upperbound(int key, int[] arr) {
    // min {i : key < arr[i]}
    int lo = -1;
    int hi = arr.length;
    while (lo + 1 < hi) {
      int mid = (lo + hi) / 2;
      if (arr[mid] <= key) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    return hi;
  }
  static int lowerbound(int key, int[] arr) {
    // max {i + 1: arr[i] < key}
    int lo = -1;
    int hi = arr.length;
    while (lo + 1 < hi) {
      int mid = (lo + hi) / 2;
      if (arr[mid] < key) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    return hi;
  }
}
