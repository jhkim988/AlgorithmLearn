import java.util.Scanner;

public class BOJ2941 {
  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    char[] input = scn.next().toCharArray();
    scn.close();

    int counter = 0;

    for (int i = 0; i < input.length; i++) {
      if (input[i] == 'c') {
        if (i + 1 < input.length && (input[i + 1] == '=' || input[i + 1] == '-')) {
          i++;
        }
      } else if (input[i] == 'd') {
        if ((i + 2) < input.length && input[i + 1] == 'z' && input[i + 2] == '=') {
          i += 2;
        } else if (i + 1 < input.length && input[i + 1] == '-') {
          i++;
        }
      } else if (i + 1 < input.length && input[i + 1] == 'j') {
        if (input[i] == 'l' || input[i] == 'n') {
          i++;
        }
      } else if (i + 1 < input.length && input[i + 1] == '=') {
        if (input[i] == 's' || input[i] == 'z') {
          i++;
        }
      }
      counter++;
    }

    System.out.println(counter);
  }
}
