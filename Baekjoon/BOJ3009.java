import java.util.Scanner;

public class BOJ3009 {
  private static class Point {
    int x, y;
    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
    public String toString() {
      return x + " " + y;
    }
  }
  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    Point p1 = new Point(scn.nextInt(), scn.nextInt());
    Point p2 = new Point(scn.nextInt(), scn.nextInt());
    Point p3 = new Point(scn.nextInt(), scn.nextInt());
    scn.close();

    Point p4;

    if (p1.x == p2.x) {
      if (p3.y == p1.y) {
        p4 = new Point(p3.x, p2.y);
      } else {
        // p3.y == p2.y
        p4 = new Point(p3.x, p1.y);
      }
    } else if (p1.x == p3.x) {
      if (p2.y == p1.y){
        p4 = new Point(p2.x, p3.y);
      } else {
        // p2.y == p3.y
        p4 = new Point(p2.x, p1.y);
      }
    } else {
      // p2.x == p3.x
      if (p1.y == p2.y) {
        p4 = new Point(p1.x, p3.y);
      } else {
        // p1.y == p3.y
        p4 = new Point(p1.x, p2.y);
      }
    }
    System.out.println(p4);
  }
}


