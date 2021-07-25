import java.io.*;

public class BOJ10828 {
  private static class Stack {
    private int[] arr = new int[10];
    private int ptr = 0;
    void push(int x) {
      if (ptr == arr.length) {
        resize(2 * arr.length);
      }
      arr[ptr++] = x;
    }
    int pop() {
      if (ptr == 0) {
        return -1;
      }
      int result = arr[--ptr];
      arr[ptr] = 0;
      if (ptr > 0 && ptr == arr.length / 4) {
        resize(arr.length / 2);
      }
      return result;
    }
    int size() {
      return ptr;
    }
    int empty() {
      return ptr == 0 ? 1 : 0;
    }
    int top() {
      return ptr > 0 ? arr[ptr - 1] : -1;
    }
    private void resize(int cap) {
      int[] copy = new int[cap];
      for (int i = 0; i < arr.length && i < cap; i++) {
        copy[i] = arr[i];
      }
      arr = copy;
    }
  }
  public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
      Stack stk = new Stack();
      int numTest = Integer.parseInt(br.readLine());
      for (int i = 0; i < numTest; i++) {
        String[] tmp = br.readLine().split(" ");
        if (tmp[0].equals("push")) {
          stk.push(Integer.parseInt(tmp[1]));
        } else if (tmp[0].equals("pop")) {
          bw.write(stk.pop() + "\n");
        } else if (tmp[0].equals("size")) {
          bw.write(stk.size() + "\n");
        } else if (tmp[0].equals("empty")) {
          bw.write(stk.empty() + "\n");
        } else {
          bw.write(stk.top() + "\n");
        }
      }      
      bw.flush();
    } catch (IOException e) {

    }
  } 
}
