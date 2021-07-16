import java.util.Scanner;

public class BOJ3053 {
  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    int radius = scn.nextInt();
    scn.close();

    System.out.println(radius*radius*Math.PI);
    System.out.println(2 * radius * radius);
  }
}
