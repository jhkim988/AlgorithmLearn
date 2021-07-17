import java.util.Scanner;

public class BOJ7568 {
  private static class body{
    int w;
    int h;
    body (int w, int h) {
      this.w = w;
      this.h = h;
    }
    boolean isSmallerThan(body other) { // this < other
      if (w < other.w && h < other.h) {
        return true;
      }
      return false;
    }
  }
  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    int numTest = scn.nextInt();
    body[] data = new body[numTest];
    for (int i = 0; i < numTest; i++) {
      data[i] = new body(scn.nextInt(), scn.nextInt());
    }
    scn.close();

    int count;
    for (int i = 0; i < numTest; i++) {
      count = 1; // data[i]
      for (int j = 0; j < numTest; j++) {
        if (i != j && data[i].isSmallerThan(data[j])) {
          count++;
        }
      }
      System.out.print(count + " ");
    }

  }
}
