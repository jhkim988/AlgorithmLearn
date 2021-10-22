import java.io.*;

public class BOJ12904 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] start = br.readLine().toCharArray();
    char[] target = br.readLine().toCharArray();
    bw.write(answer(start, target) ? "1\n" : "0\n");
    // bw.write(answer1(start, target) ? "1\n" : "0\n");
    bw.flush();
  }
  static void test() {
    boolean flag = true;
    while (flag) {
      int len1 = 0;
      int len2 = 0;
      while (len1 == len2) {
        len1 = (int) (Math.random() * 5 + 1);
        len2 = (int) (Math.random() * 5 + 1);
        if (len1 >= len2) {
          int tmp = len1;
          len1 = len2;
          len2 = tmp;
        }
      }
  
      char[] start = new char[len1];
      char[] target = new char[len2];
      fill(start);
      fill(target);
      boolean a1 = answer(start.clone(), target.clone());
      boolean a2 = answer1(start.clone(), target.clone()); 
      if (a1 == a2) {
        System.out.println("continue");
      } else {
        flag = false;
        System.out.print("start: ");
        System.out.println(start);
        System.out.print("target: ");
        System.out.println(target);
        System.out.println("answer: " + a1);
        System.out.println("answer1: " + a2);
      }
    }
  }
  static void fill(char[] arr) {
    for (int i = 0; i < arr.length; i++) {
      if ((int) (Math.random() * 2) == 0) {
        arr[i] = 'A';
      } else {
        arr[i] = 'B';
      }
    }
  }
  static boolean answer(char[] start, char[] target) {
    int ptr0 = 0;
    int ptr1 = target.length - 1;
    int numflip = 0;
    while (ptr1 - ptr0 + 1 > start.length) {
      if (numflip % 2 == 0) {
        if (target[ptr1] == 'A') {
          ptr1--;
        } else if (target[ptr1] == 'B') {
          ptr1--;
          numflip++;
        } else {
          return false;
        }
      } else {
        if (target[ptr0] == 'A') {
          ptr0++;
        } else if (target[ptr0] == 'B') {
          ptr0++;
          numflip++;
        } else {
          return false;
        }
      }
    }
    if (numflip % 2 == 0) {
      for (int i = ptr0; i <= ptr1; i++) {
        if (target[i] != start[i - ptr0]) {
          return false;
        }
      }
    } else {
      for (int i = ptr1; i >= ptr0; i--) {
        if (target[i] != start[ptr1 - i]) {
          return false;
        }
      }
    }
    
    return true;
  }
  static boolean answer1(char[] start, char[] target) {
    for (int i = target.length - 1; i >= start.length; i--) {
      if (target[i] =='B') {
        for (int j = 0; j < i / 2; j++) {
          char tmp = target[j];
          target[j] = target[i - j - 1];
          target[i - j - 1] = tmp;
        }
      }
    }
    for (int i = 0; i < start.length; i++) {
      if (target[i] != start[i]) return false;
    }
    return true;
  }
}