import java.io.*;
import java.util.*;

public class BOJ2621 {
  static char[] color;
  static int[] number, count;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    color = new char[5];
    number = new int[5];
    for (int i = 0; i < 5; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      color[i] = st.nextToken().charAt(0);
      number[i] = Integer.parseInt(st.nextToken());
    }

    count = new int[10];
    for (int i = 0; i < 5; i++) {
      count[number[i]]++;
    }

    int max = 0;
    int val = filter1();
    if (max < val) max = val;
    val = filter2();
    if (max < val) max = val;
    val = filter3();
    if (max < val) max = val;
    val = filter4();
    if (max < val) max = val;
    val = filter5();
    if (max < val) max = val;
    val = filter6();
    if (max < val) max = val;
    val = filter7();
    if (max < val) max = val;
    val = filter8();
    if (max < val) max = val;
    val = filter9();
    if (max < val && max == 0) max = val;

    bw.write(Integer.toString(max));
    bw.flush();
  }
  static int filter1() {
    for (int i = 0; i < 5; i++) {
      if (color[i] != color[0]) return 0;
    }
    int ptr = 0;
    while (ptr < 10 && count[ptr] == 0) ptr++;
    for (int i = 0; i < 5; i++) {
      if (count[ptr+i] != 1) return 0;
    }
    return ptr+904;
  }
  static int filter2() {
    for (int i = 0; i < 10; i++) {
      if (count[i] == 4) return 800+i;
    }
    return 0;
  }
  static int filter3() {
    int count3 = -1, count2 = -1;
    for (int i = 0; i < 10; i++) {
      if (count[i] == 3) count3 = i;
      else if (count[i] == 2) count2 = i;
    }
    if (count3 == -1 || count2 == -1) return 0;
    return count3*10+count2+700;
  }
  static int filter4() {
    int max = 0;
    for (int i = 0; i < 5; i++) {
      if (color[i] != color[0]) return 0;
      if (max < number[i]) max = number[i]; 
    }
    return max+600;
  }
  static int filter5() {
    int max = 0;
    int ptr = 0;
    while (ptr < 10 && count[ptr] == 0) ptr++;
    for (int i = 0; i < 5; i++) {
      if (count[ptr+i] != 1) return 0;
      if (max < number[i]) max = number[i];
    }
    return max+500;
  }
  static int filter6() {
    int count3 = -1;
    for (int i = 0; i < 10; i++) {
      if (count[i] == 3) count3 = i;
    }
    if (count3 == -1) return 0;
    return 400+count3;
  }
  static int filter7() {
    int sum = 0;
    int numCount2 = 0;
    for (int i = 0; i < 10; i++) {
      if (count[i] == 2) {
        if (numCount2 == 0) sum += i;
        else sum += i*10;
        numCount2++;
      }
    }
    if (numCount2 == 2) return sum+300;
    return 0;
  }
  static int filter8() {
    for (int i = 0; i < 10; i++) {
      if (count[i] == 2) return 200+i;
    }
    return 0;
  }
  static int filter9() {
    int max = 0;
    for (int i = 0; i < 5; i++) {
      if (max < number[i]) max = number[i];
    }
    return max+100;
  }
}