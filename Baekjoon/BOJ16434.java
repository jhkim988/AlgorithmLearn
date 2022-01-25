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

    long lo = 0;
    long hi = 1 << 5;
    while (!clear(hi, initAtk, room)) hi <<= 5;
    while (lo + 1 < hi) {
      long mid = (lo + hi) / 2;
      if (!clear(mid, initAtk, room)) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    bw.write(Long.toString(hi));
    bw.newLine();
    bw.flush();
  }
  static boolean clear(long mid, int initAtk, Pair[] room) {
    long crntHP = mid;
    long crntAtk = initAtk;
    for (int i = 0; i < room.length; i++) {
      if (room[i].type == 1) { // monster
        long numTurnPlayer = room[i].hp / crntAtk + (room[i].hp % crntAtk == 0 ? 0 : 1);
        long numTurnMonster = crntHP / room[i].atk + (crntHP % room[i].atk == 0 ? 0 : 1);
        if (numTurnPlayer > numTurnMonster) return false;
        crntHP -= room[i].atk * (numTurnPlayer - 1);
      } else { // potion
        crntHP += room[i].hp;
        if (crntHP > mid) crntHP = mid;
        crntAtk += room[i].atk;
      }
    }
    return true;
  }
}
