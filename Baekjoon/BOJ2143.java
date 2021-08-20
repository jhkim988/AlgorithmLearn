import java.io.*;
import java.util.*;

public class BOJ2143 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;
    long T = Long.parseLong(br.readLine());
    int N = Integer.parseInt(br.readLine());
    long[] A = new long[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      A[i] = Long.parseLong(st.nextToken());
    }
    int M = Integer.parseInt(br.readLine());
    long[] B = new long[M];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < M; i++) {
      B[i] = Long.parseLong(st.nextToken());
    }

    // long count = useHashMap(A, B, T, N, M);
    // long count = useBinSearch(A, B, T, N, M);
    long count = useTwoPointer(A, B, T, N, M);
    bw.write(count + "\n");
    bw.flush();
  }
  static long useHashMap(long[] A, long[] B, long T, int N, int M) {
    HashMap<Long, Long> pA = new HashMap<>();
    HashMap<Long, Long> pB = new HashMap<>();

    for (int i = 0; i < N; i++) {
      long sum = 0;
      for (int j = i; j < N; j++) {
        sum += A[j];
        if (pA.containsKey(sum)) {
          pA.put(sum, pA.get(sum) + 1L);
        } else {
          pA.put(sum, 1L);
        }
      }
    }
    for (int i = 0; i < M; i++) {
      long sum = 0;
      for (int j = i; j < M; j++) {
        sum += B[j];
        if (pB.containsKey(sum)) {
          pB.put(sum, pB.get(sum) + 1L);
        } else {
          pB.put(sum, 1L);
        }
      }
    }

    long count = 0;
    for (long pa : pA.keySet()) {
      if (pB.containsKey(T - pa)) {
        count += pB.get(T- pa)*pA.get(pa);
      }
    }
    return count;
  }
  static long useBinSearch(long[] A, long[] B, long T, int N, int M) {
    ArrayList<Long> pA = new ArrayList<>();
    ArrayList<Long> pB = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      long sum = 0;
      for (int j = i; j < N; j++) {
        sum += A[j];
        pA.add(sum);
      }
    }
    for (int i = 0; i < M; i++) {
      long sum = 0;
      for (int j = i; j < M; j++) {
        sum += B[j];
        pB.add(sum);
      }
    }
    Collections.sort(pB);
    long count = 0;
    for (long pa : pA) {
      // upper bound
      int ptr1 = 0;
      int ptr2 = pB.size();
      while (ptr1 < ptr2) {
        int mid = (ptr1 + ptr2) / 2;
        if (pB.get(mid) <= T - pa) {
          ptr1 = mid + 1;
        } else {
          ptr2 = mid;
        }
      }
      int upperBound = ptr2;
    
      // lower bound
      ptr1 = 0;
      ptr2 = pB.size();
      while (ptr1 < ptr2) {
        int mid = (ptr1 + ptr2) / 2;
        if (pB.get(mid) >= T - pa) {
          ptr2 = mid;
        } else {
          ptr1 = mid + 1;
        }
      }
      int lowerBound = ptr2;
      count += upperBound - lowerBound;
    }
    return count;
  }
  static long useTwoPointer(long[] A, long[] B, long T, int N, int M) {
    ArrayList<Long> pA = new ArrayList<>();
    ArrayList<Long> pB = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      long sum = 0;
      for (int j = i; j < N; j++) {
        sum += A[j];
        pA.add(sum);
      }
    }
    for (int i = 0; i < M; i++) {
      long sum = 0;
      for (int j = i; j < M; j++) {
        sum += B[j];
        pB.add(sum);
      }
    }
    Collections.sort(pA);
    Collections.sort(pB);
    int ptr1 = 0;
    int ptr2 = pB.size() - 1;
    long count = 0;
    while (ptr1 < pA.size() && ptr2 >= 0) {
      long pAGet = pA.get(ptr1);
      long pBGet = pB.get(ptr2);
      long val = pAGet + pBGet;
      if (val < T) {
        ptr1++;
      } else if (val > T) {
        ptr2--;
      } else {
        long leftCount = 0;
        long rightCount = 0;
        while (ptr1 < pA.size() && pA.get(ptr1) == pAGet) {
          leftCount++;
          ptr1++;
        }
        while (ptr2 >= 0 && pB.get(ptr2) == pBGet) {
          rightCount++;
          ptr2--;
        }
        count += leftCount * rightCount;
      }
    }
    return count;
  }
}
