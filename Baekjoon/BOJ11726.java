import java.util.Scanner;

public class BOJ11726 {
  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    int num = scn.nextInt();
    scn.close();

    if (num < 3) {
      System.out.println(num);
      return;
    }

    long[] table = new long[num]; // table[i - 1]: num = i
    table[0] = 1L;
    table[1] = 2L;
    for (int i = 2; i < num; i++) {
      table[i] = (table[i - 1] + table[i - 2]) % 10007;
    }
    System.out.println(table[num - 1]);
  }
}
