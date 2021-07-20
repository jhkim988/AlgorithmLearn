import java.io.*;

public class BOJ14889 {
  public static void main(String[] args) {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    try {
      int numMember = Integer.parseInt(br.readLine());
      int[][] data = new int[numMember][numMember];
      
      String[] tmp;
      for (int i = 0 ; i < numMember; i++) {
        tmp = br.readLine().split(" ");
        for (int j = 0; j < numMember; j++) {
          data[i][j] = Integer.parseInt(tmp[j]);
        }
      }
      tmp = null;

      System.out.print(solve(data));
    } catch (IOException e) {
      // do nothing
    }    
  }
  static int solve(int[][] data) {
    int numMember = data.length;
    int[] mem = new int[numMember];
    int[] min = new int[1];
    min[0] = Integer.MAX_VALUE;
    solve(data, 0, 0, min, mem);
    
    return min[0];
  }
  static void solve(int[][] data, int cdpt, int start, int[] min, int[] mem) {
    if (cdpt < data.length / 2) {
      for (int i = 0; i < data.length; i++) {
        // team 1
        if (mem[i] != 0 || start > i) {
          continue;
        }
        mem[i] = 1;
        solve(data, cdpt + 1, i, min, mem);
        mem[i] = 0;
      }
    } else {
      // System.out.println("---------");
      // for (int i = 0; i < mem.length; i++) {
      //   System.out.print(mem[i] + " ");
      // }
      // System.out.println();

      int[] score = calcScore(mem, data);
      int diff = score[0] > score[1] ? score[0] - score[1] : score[1] - score[0];
      if (diff < min[0]) {
        min[0] = diff;
      }
    }
  }
  static int[] calcScore(int[] mem, int[][] data) {
    int[] score = new int[2];
    for (int i = 0; i < mem.length; i++) {
      for (int j = 0; j < mem.length; j++) {
        if (mem[i] == mem[j]) {
          if (mem[i] == 1) {
            score[0] += data[i][j];
          } else {
            score[1] += data[i][j];
          }          
        }
      }
    }
    return score;
  }
}
