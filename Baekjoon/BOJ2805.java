import java.io.*;
import java.util.*;

public class BOJ2805 {
  public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(br.readLine());
      int[] data = new int[N];
      for (int i = 0; i < N; i++) {
        data[i] = Integer.parseInt(st.nextToken());
      }
      Arrays.sort(data);

      long sum = 0;
      int left = 0;
      int right = data[N - 1];
      int center = 0;
      boolean flag = false;
      while(left < right) {
        // System.out.println("[" + left + ", " + right + "]");
        center = (left + right) / 2;
        sum = 0;
        int idx = Arrays.binarySearch(data, center);
        idx = idx < 0 ? -(idx + 1) : idx;
        while (idx > 0 && data[idx - 1] == center) {
          idx--;
        }
        for (int i = idx; i < N; i++) {
          sum += data[i] - center;
        }
        // System.out.println("cut: " + (sum[idx] - (N - idx)*center));
        if (sum < M) {
          // cut more = height DOWN
          if (center == right) {
            break;
          }
          right = center;
          continue;
        } else if (sum > M) {
          // cut little = height UP
          if (center == left) {
            break;
          }
          left = center;
          flag = true;
          continue;
        } else {
          break;
        }
      }
      if (flag) {
        // Find exact sol
        bw.write(center + "\n");
      } else {
        // Can't Find exact sol
        bw.write(left + "\n");
      }

      bw.flush();
    } catch (IOException e) {

    }
  }
}
