import java.io.*;
import java.util.*;

public class BOJ1043 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    boolean[][] attendance = new boolean[M][N + 1];
    for (int i = 0; i < M; i++) {
      attendance[i][0] = true;
    }
    st = new StringTokenizer(br.readLine());
    int numKnowTruth = Integer.parseInt(st.nextToken());
    HashSet<Integer> knowTruth = new HashSet<>();
    for (int i = 0; i < numKnowTruth; i++) {
      knowTruth.add(Integer.parseInt(st.nextToken()));
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int numPartyPeople = Integer.parseInt(st.nextToken());
      for (int j = 0; j < numPartyPeople; j++) {
        int people = Integer.parseInt(st.nextToken());
        attendance[i][people] = true;
      }
    }

    boolean flag = true;
    while (flag) {
      HashSet<Integer> tmp = new HashSet<>();
      flag = false;
      for (int people : knowTruth) {
        for (int i = 0; i < M; i++) {
          if (attendance[i][0] && attendance[i][people]) {
            flag = true;
            for (int j = 1; j <= N; j++) {
              if (attendance[i][j]) {
                tmp.add(j);
              }
            }
            attendance[i][0] = false;
          }
        }
      }
      knowTruth.addAll(tmp);
    }
    int maxLie = 0;
    for (int i = 0; i < M; i++) {
      if (attendance[i][0]) {
        maxLie++;
      }
    }
    bw.write(maxLie + "\n");
    bw.flush();
  }
}
