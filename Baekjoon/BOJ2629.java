import java.io.*;
import java.util.*;

public class BOJ2629 {
  static int numWeight;
  static int numBead;
  static int[] weight;
  static int[] bead;
  static int[][] dp;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    numWeight = Integer.parseInt(br.readLine());
    weight = new int[numWeight];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < numWeight; i++) {
      weight[i] = Integer.parseInt(st.nextToken());
    }

    numBead = Integer.parseInt(br.readLine());
    bead = new int[numBead];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < numBead; i++) {
      bead[i] = Integer.parseInt(st.nextToken());
    }

    init();
  }
  static void init() {

  }
}
