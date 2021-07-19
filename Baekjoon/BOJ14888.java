import java.util.Scanner;

public class BOJ14888 {
  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    int numData = scn.nextInt();
    int data[] = new int[numData];
    for (int i = 0; i < numData; i++) {
      data[i] = scn.nextInt();
    }
    int[] operator = new int[4];
    for (int i = 0; i < 4; i++) {
      operator[i] = scn.nextInt(); // + - * /
    }
    scn.close();

    int[] result = new int[2];
    result[0] = Integer.MIN_VALUE;
    result[1] = Integer.MAX_VALUE;
    solve(data, operator, result, 0, data[0]);

    System.out.println(result[0]); // max;
    System.out.println(result[1]); // min
  }

  static void solve(int[] data, int[] operator, int[] result, int cdpt, int cval) {
    if (cdpt < data.length - 1) {
      for (int i = 0; i < 4; i++) {
        if (operator[i] == 0) {
          continue;
        }
        int calc = cval;
        if (i == 0) {
          calc += data[cdpt + 1];
        } else if (i == 1) {
          calc -= data[cdpt + 1];
        } else if (i == 2) {
          calc *= data[cdpt + 1];
        } else {
          calc /= data[cdpt + 1];
        }
        operator[i]--;
        solve(data, operator, result, cdpt + 1, calc);
        operator[i]++;
      }
    } else {
      if (result[0] < cval) {
        result[0] = cval;
      }
      if (result[1] > cval) {
        result[1] = cval;
      }
    }
  }

}
