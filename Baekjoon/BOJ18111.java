import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class BOJ18111 {
  public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
      StringTokenizer st = new StringTokenizer(br.readLine());

      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());
      int B = Integer.parseInt(st.nextToken());

      int[][] data = new int[N][M];
      int minHeight = 257;
      int maxHeight = 0;
      int now;
      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < M; j++) {
          data[i][j] = now = Integer.parseInt(st.nextToken());
          if (now < minHeight) {
            minHeight = now;
          }
          if (maxHeight < now) {
            maxHeight = now;
          }
        }
      }

      int time = 0;
      int minTime = Integer.MAX_VALUE;
      int height = 0;
      int bag = B;
      int diff = 0;

      for (int h = minHeight; h <= maxHeight; h++) {
        time = 0;
        bag = B;
        for (int i = 0; i < N; i++) {
          for (int j = 0; j < M; j++) {
            if (data[i][j] == h) {
              continue;
            } else {
              diff = data[i][j] - h;
              if (diff > 0) {
                bag += diff;
                time += 2 * diff;
              } else {
                bag += diff;
                time -= diff;
              }
            }
          }
        }
        if (bag < 0) {
          continue;
        }
        if (time == minTime && height < h) {
          height = h;
          continue;
        }
        if (time < minTime) {
          minTime = time;
          height = h;
        }
      }
      bw.write(minTime + " " + height + "\n");
      bw.flush();
    } catch (IOException e) {

    }
  }
}
