import java.io.*;
import java.util.*;

public class BOJ1495 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int len = Integer.parseInt(st.nextToken());
    int startVol = Integer.parseInt(st.nextToken());
    int lim = Integer.parseInt(st.nextToken());
    int[] volList = new int[len];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < len; i++) {
      volList[i] = Integer.parseInt(st.nextToken());
    }

    // use double array
    // boolean[][] dp = new boolean[len][lim + 1];
    // if (startVol + volList[0] <= lim) dp[0][startVol + volList[0]] = true;
    // if (startVol - volList[0] >= 0) dp[0][startVol - volList[0]] = true;
    // for (int i = 1; i < len; i++) {
    //   for (int j = 0; j <= lim; j++) {
    //     if (dp[i - 1][j]) {
    //       if (j + volList[i] <= lim) dp[i][j + volList[i]] = true;
    //       if (j - volList[i] >= 0) dp[i][j - volList[i]] = true;
    //     }
    //   }
    // }
    // int answer = lim;
    // for (; answer >= 0; answer--) if (dp[len - 1][answer]) break;
    // bw.write(Integer.toString(answer));
    // bw.newLine();
    // bw.flush();
  
    // use two 1D array
    // boolean[] dp = new boolean[lim + 1];
    // boolean[] newOne = new boolean[lim + 1];
    // if (startVol + volList[0] <= lim) dp[startVol + volList[0]] = true;
    // if (startVol - volList[0] >= 0) dp[startVol - volList[0]] = true;
    // for (int i = 1; i < len; i++) {
    //   for (int j = 0; j <= lim; j++) {
    //     if (dp[j]) {
    //       if (j + volList[i] <= lim) newOne[j + volList[i]] = true;
    //       if (j - volList[i] >= 0) newOne[j - volList[i]] = true;
    //     }
    //   }
    //   System.arraycopy(newOne, 0, dp, 0, lim + 1);
    //   Arrays.fill(newOne, false);
    // }
    // int answer = lim;
    // for (; answer >= 0; answer--) if (dp[answer]) break;
    // bw.write(Integer.toString(answer));
    // bw.newLine();
    // bw.flush();

    // use HashSet
    HashSet<Integer> dp = new HashSet<>();
    HashSet<Integer> newOne = new HashSet<>();
    if (startVol + volList[0] <= lim) dp.add(startVol + volList[0]);
    if (startVol - volList[0] >= 0) dp.add(startVol - volList[0]);
    for (int i = 1; i < len; i++) {
      for (int j = 0; j <= lim; j++) {
        if (dp.size() == 0) {
          bw.write("-1\n");
          bw.flush();
          return;
        }
        if (dp.contains(j)) {
          if (j + volList[i] <= lim) newOne.add(j + volList[i]);
          if (j - volList[i] >= 0) newOne.add(j - volList[i]);
        }
      }
      dp.clear();
      for (int vol : newOne) dp.add(vol);
      newOne.clear();
    }
    if (dp.size() == 0) {
      bw.write("-1\n");
      bw.flush();
      return;
    } else {
      int answer = lim;
      for (; answer >= 0; answer--) if (dp.contains(answer)) break;
      bw.write(Integer.toString(answer));
      bw.newLine();
      bw.flush();
    }
  }
}
