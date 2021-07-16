import java.util.Scanner;

public class BOJ1011 {
  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    int numTest = scn.nextInt();
    for (int i = 0; i < numTest; i++) {
      int xPlanet = scn.nextInt();
      int yPlanet = scn.nextInt();
      System.out.println(numOperate(xPlanet, yPlanet));
    }
    scn.close();
  }

   static int numOperate(int x, int y) {
    int numX = 0;
    int numY = 0;

    int ptrX = x;
    int speedX = 0;
    int ptrY = y;
    int speedY = 0;

    while (ptrX != ptrY) {
      // move X
      if (ptrY - ptrX > speedX) {
        ptrX += (++speedX);
        numX++;
      } else if (ptrY - ptrX == speedX) {
        ptrX += speedX;
        numX++;
      } else {
        return numX + numY + 1;
      }
      
      // check
      if (ptrX == ptrY) {
        break;
      }

      // move Y
      if (ptrY - ptrX > speedY) {
        ptrY -= (++speedY);
        numY++;
      } else if (ptrY - ptrX == speedY) {
        ptrY -= speedY;
        numY++;
      } else {
        return numX + numY + 1;
      }
    }
    return numX + numY;
   }
}
