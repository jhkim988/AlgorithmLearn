import java.util.Scanner;

public class BOJ4153 {
  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    
    while (true) {
      int[] data = new int[3];
      for (int i = 0; i < 3; i++) {
        data[i] = scn.nextInt();
      }
      
      // 0. loop escape condition
      if (data[0] == 0 && data[1] == 0 && data[2] == 0) {
        break;
      }

      // 1. set data[2] to Max
      if (data[2] < data[0]) {
        if (data[0] < data[1]) {
          swap(data, 1, 2);
        } else {
          swap(data, 0, 2);
        }
      } else if (data[2] < data[1]) {
        if (data[0] < data[1]) {
          swap(data, 1, 2);
        } else {
          swap(data, 0, 2);
        }
      } else {
        // c is max, do nothing
      }
      // System.out.println(data[0] + " " + data[1] + " " +  data[2]);
      System.out.println(check(data) ? "right" : "wrong");
    }

    scn.close();
  }
  static void swap(int[] data, int idx1, int idx2) {
    int tmp = data[idx1];
    data[idx1] = data[idx2];
    data[idx2] = tmp;
  }
  static boolean check(int[] data) {
    return (data[0]*data[0] + data[1]*data[1] == data[2]*data[2]) ? true : false; 
  }
}
