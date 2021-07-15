import java.util.Scanner;

public class BOJ1316 {
  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    int N = scn.nextInt();
    
    int counter = 0;
    for (int i = 0; i < N; i++) {
      String str = scn.next();
      if (groupWordChecker(str)) {
        counter++;
      }
    }
    scn.close();  
    System.out.print(counter);
  }

  static boolean groupWordChecker(String word) {
    char[] input = word.toCharArray();
    int ptr = 0;

    for (int i = 0; i < input.length; i++) {
      ptr = i;
      while (++ptr < input.length && input[i] == input[ptr]);
      i = ptr - 1;
      for (ptr = i + 1; ptr < input.length; ptr++) {
        if (input[i] == input[ptr]) {
          return false;
        }
      }
    }

    return true;
  }
}
