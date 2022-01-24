import java.io.*;
import java.util.*;

public class BOJ16434 {
  private static class Pair {
    int type;
    int atk;
    int hp;
    Pair(int type, int atk, int hp) {
      this.type = type;
      this.atk = atk;
      this.hp = hp;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    int numRoom = Integer.parseInt(st.nextToken());
    int initAtk = Integer.parseInt(st.nextToken());
    Pair[] room = new Pair[numRoom];
    for (int i = 0; i < numRoom; i++) {
      st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      int atk = Integer.parseInt(st.nextToken());
      int hp = Integer.parseInt(st.nextToken());
      room[i] = new Pair(type, atk, hp);
    }

    
  }
}
