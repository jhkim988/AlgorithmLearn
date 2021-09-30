import java.io.*;
import java.util.*;

public class BOJ14391 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int height = Integer.parseInt(st.nextToken());
    int width = Integer.parseInt(st.nextToken());
    int[][] table = new int[height][width];
    for (int i = 0; i < height; i++) {
      String input = br.readLine();
      for (int j = 0; j < width; j++) {
        table[i][j] = input.charAt(j) - '0';
      }
    }
    bw.write(answer(table, width, height) + "\n");
    bw.flush();
  }
  static int answer(int[][] table, int width, int height) {
    // bit status, table: max 4 * 4 array, max(bit) = 2 ^ 17 - 1
    return recur(0, 0, table, width, height); 
  }
  static int recur(int bit, int val, int[][] table, int width, int height) {
    // System.out.println("bit: " + Integer.toBinaryString(bit));
    // System.out.println("val: " + val);
    if (bit == (1 << (width * height)) - 1) {
      return val;
    }
    int max = 0;
    int ptr = 1;
    int crntI = 0;
    int crntJ = 0;
    while ((bit & ptr) == ptr) {
      ptr <<= 1;
      crntI++;
      crntJ++;
    }
    crntI /= width;
    crntJ %= width;
    int sum = table[crntI][crntJ];

    // 1 size
    bit = bit | ptr;
    max = Math.max(max, recur(bit, val + sum, table, width, height));
    
    int copyBit = bit;
    int tmpPtr = ptr;
    for (int i = crntI + 1; i < height; i++) {
      tmpPtr = tmpPtr << width;
      copyBit = copyBit | tmpPtr;
      if ((bit & tmpPtr) != 0) break;
      sum = 10 * sum + table[i][crntJ];
      max = Math.max(max, recur(copyBit, val + sum, table, width, height));
    }
    copyBit = bit;
    tmpPtr = ptr;
    sum = table[crntI][crntJ];
    for (int j = crntJ + 1; j < width; j++) {
      tmpPtr = tmpPtr << 1;
      copyBit = copyBit | tmpPtr;
      if ((bit & tmpPtr) != 0) break;
      sum = 10 * sum + table[crntI][j];
      max = Math.max(max, recur(copyBit, val + sum, table, width, height));
    }
    return max;
  }
}
