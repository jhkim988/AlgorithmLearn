import java.io.*;

public class BOJ14889 {
  public static void main(String[] args) {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    try {
      int numMember = Integer.parseInt(br.readLine());
      int[][] data = new int[numMember][numMember];
      
      for (int i = 0 ; i < numMember; i++) {
        String[] tmp = br.readLine().split(" ");
        for (int j = 0; j < numMember; j++) {
          data[i][j] = Integer.parseInt(tmp[j]);
        }
      }

      System.out.print(solve(data));
    } catch (IOException e) {
      // do nothing
    }    
  }
  static int solve(int[][] data) {
    int numMember = data.length;
    int[] team1 = new int[numMember / 2];
    int[] team2 = new int[numMember / 2];
    int[] min = new int[1];
    min[0] = Integer.MAX_VALUE;
    solve(data, 0, min, team1, team2);
    
    return min[0];
  }
  static void solve(int[][] data, int cdpt, int[] min, int[] team1, int[] team2) {
    if (cdpt < data.length / 2) {
      for (int i = 1; i <= data.length; i++) {
        
      }
    } else {
      int team1Score = calcScore(team1, data);
      int team2Score = calcScore(team2, data);
      int diff = team1Score > team2Score ? team1Score - team2Score : team2Score - team2Score;
      if (diff < min[0]) {
        min[0] = diff;
      }
    }
  }
  static int calcScore(int[] team, int[][] data) {
    int sum = 0;
    for (int i = 0; i < team.length; i++) {
      for (int j = 0; j < team.length; j++) {
        sum += data[team[i] - 1][team[j] - 1];
      }
    }
    return sum;
  }
}
