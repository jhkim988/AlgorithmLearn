import java.io.*;
import java.util.*;

public class BOJ16927 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int R = Integer.parseInt(st.nextToken());
    int[][] origin = new int[N][M];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        origin[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    StringBuilder sb = new StringBuilder();
    int[][] answer = new int[N][M];
    int numOrbit = Math.min(N, M) / 2;
    Queue<Integer> que = new LinkedList<>();
    for (int orbit = 0; orbit < numOrbit; orbit++) {
      int count = 0;
      // System.out.println("new orbit");
      for (int idx = orbit; idx < M - orbit; idx++) {
        // System.out.println("(" + orbit + ", " + idx + ")");
        count++;
        que.add(origin[orbit][idx]);
      }
      for (int idx = orbit + 1; idx < N - orbit; idx++) {
        // System.out.println("(" + idx + ", " + (M - orbit - 1) + ")");
        count++;
        que.add(origin[idx][M - orbit - 1]);
      }
      for (int idx = M - orbit - 2; idx >= orbit; idx--) {
        // System.out.println("(" + (M - orbit - 1) + ", " + idx + ")");
        count++;
        que.add(origin[N - orbit - 1][idx]);
      }
      for (int idx = N - orbit - 2; idx > orbit; idx--) {
        // System.out.println("(" + idx + ", " + orbit + ")");
        count++;
        que.add(origin[idx][orbit]);
      }

      for (int rot = 0; rot < R%count; rot++) {
        que.add(que.poll());
      }

      for (int idx = orbit; idx < M - orbit; idx++) {
        answer[orbit][idx] = que.poll();
      }
      for (int idx = orbit + 1; idx < N - orbit; idx++) {
        answer[idx][M - orbit - 1] = que.poll();
      }
      for (int idx = M - orbit - 2; idx >= orbit; idx--) {
        answer[N - orbit - 1][idx] = que.poll();
      }
      for (int idx = N - orbit - 2; idx > orbit; idx--) {
        answer[idx][orbit] = que.poll();
      }
    }

    for (int i = 0; i < N; i++) {
      sb.append(answer[i][0]);
      for (int j = 1; j < M; j++) {
        sb.append(' ').append(answer[i][j]);
      }
      sb.append('\n');
    }

    bw.write(sb.toString());
    bw.flush();
  }
}
