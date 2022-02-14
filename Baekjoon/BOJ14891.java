import java.io.*;
import java.util.*;

public class BOJ14891 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[][] wheel = new char[4][];
    for (int i = 0; i < 4; i++) {
      wheel[i] = br.readLine().toCharArray();
    }

    // ptr[i]: i-th wheel top pointer
    // left: ptr[(i + 6) % 8]
    // right: ptr[(i + 2) % 8]
    int[] ptr = new int[4];
    int q = Integer.parseInt(br.readLine());
    while (q-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int id = Integer.parseInt(st.nextToken()) - 1;
      int direction = Integer.parseInt(st.nextToken());
      rot(id, direction, wheel, ptr, 0);
    }

    int answer = 0;
    for (int i = 0; i < 4; i++) {
      if (wheel[i][ptr[i]] == '1') {
        answer |= (1 << i);
      }
    }
    bw.write(Integer.toString(answer));
    bw.newLine();
    bw.flush();
  }
  static void rot(int id, int direction, char[][] wheel, int[] ptr, int stat) {
    if (id < 3 && ((stat & (1 << (id+1))) == 0) && wheel[id][(ptr[id] + 2) % 8] != wheel[id+1][(ptr[id+1] + 6) % 8]) {
      rot(id+1, -direction, wheel, ptr, stat | 1<<id);
    }
    if (id > 0 && ((stat & (1 << (id-1))) == 0) && wheel[id][(ptr[id] + 6) % 8] != wheel[id-1][(ptr[id-1] + 2) % 8]) {
      rot(id-1, -direction, wheel, ptr, stat | 1<<id);
    }
    ptr[id] -= direction;
    if (ptr[id] < 0) ptr[id] += 8;
    ptr[id] %= 8;
  }
}
