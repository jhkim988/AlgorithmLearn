import java.util.Scanner;

public class BOJ1085 {
  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    int x = scn.nextInt();
    int y = scn.nextInt();
    int w = scn.nextInt();
    int h = scn.nextInt();
    scn.close();

    System.out.println(Math.min(Math.min(w - x, x), Math.min(h - y, y)));
  }
}
