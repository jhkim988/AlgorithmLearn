import java.util.Scanner;
import java.io.*;

public class BOJ11653 {
  public static void main(String[] args) {
    try {
      Scanner scn = new Scanner(System.in);
      int input = scn.nextInt();
      scn.close();
  
      OutputStreamWriter osw = new OutputStreamWriter(System.out);
      BufferedWriter bw = new BufferedWriter(osw);
  
      int tmp = input;
      if (tmp == 1) {
        return;
      }
      
      while (tmp != 0) {
        boolean flag = false;
        for (int i = 2; i * i <= tmp; i++) {
          if (isPrime(i) && tmp % i == 0) {
            bw.write(i + "\n");
            tmp /= i;
            flag = true;
            break;
          }
        }
        if (!flag) {
          bw.write(tmp + "\n");
          break;
        }
      }
      bw.flush();
      bw.close();
      osw.close();
    } catch (IOException e) {
      // do nothing...
    }

  }

  static boolean isPrime(int num) {
    if (num == 1) {
      return false;
    }
    if (num < 4) {
      // 2, 3
      return true;
    }

    for (int i = 2; i * i <= num; i++) {
      if (num % i == 0) {
        return false;
      }
    }
    return true;
  }
}
