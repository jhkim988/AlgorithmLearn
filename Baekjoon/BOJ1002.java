import java.util.Scanner;

public class BOJ1002 {
  private static class Circle {
    int r;
    int x;
    int y;
    Circle(int x, int y, int r) {
      this.x = x;
      this.y = y;
      this.r = r;
    }

    double dist(Circle other) {
      return Math.sqrt((x - other.x)*(x - other.x) + (y - other.y)*(y - other.y));
    }
  }
  public static void main(String[] args) {
    // Consider both cases: outer meet, inner meet
    Scanner scn = new Scanner(System.in);
    int numTest = scn.nextInt();

    for (int i = 0; i < numTest; i++) {
      Circle c1 = new Circle(scn.nextInt(), scn.nextInt(), scn.nextInt());
      Circle c2 = new Circle(scn.nextInt(), scn.nextInt(), scn.nextInt());

      // larger circle: c1
      if (c1.r < c2.r) {
        Circle tmp = c1;
        c1 = c2;
        c2 = tmp;
      }

      // determine: inner circle or outer circle
      // where is center of c2?
      if (c1.dist(c2) < c1.r) {
        // c2 is in c1
        if (c1.x == c2.x && c1.y == c2.y) {
          if (c1.r == c2.r) {
            System.out.println(-1);
          } else {
            System.out.println(0);
          }
        } else {
          if (c2.r + c1.dist(c2) < c1.r) {
            System.out.println(0);
          } else if (c2.r + c1.dist(c2) == c1.r) {
            System.out.println(1);
          } else {
            System.out.println(2);
          }
        }
      } else {
        // c2 is out of c1 or boundary of c1 
        if (c1.dist(c2) > c1.r + c2.r) {
          System.out.println(0);
        } else if (c1.dist(c2) == c1.r + c2.r) {
          System.out.println(1);
        } else {
          System.out.println(2);
        }
      }
    }
    scn.close();
  }
  static int dist(int x1, int y1, int x2, int y2) {
    return ((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
  }
}
