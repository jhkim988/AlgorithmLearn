import java.io.*;

public class BOJ10845 {
  private static class Queue {
    int back = 0;
    int front = 0;
    int size = 0;
    int[] arr = new int[1];
    void push(int x) {
      if (size == arr.length) {
        resize(2 * size);
      }
      size++;
      arr[back++] = x;
      back = back == arr.length ? 0 : back;
    }
    int pop() {
      if (size == 0) {
        return -1;
      }
      if (size != 0 && size == arr.length / 4) {
        resize(arr.length / 2);
      }
      size--;
      int result = arr[front];
      arr[front++] = 0;
      front = front == arr.length ? 0 : front;
      return result;
    }
    int size() {
      return size;
    }
    int empty() {
      return size == 0 ? 1 : 0;
    }
    int back() {
      return size == 0 ? -1 : arr[back == 0 ? arr.length - 1 : back - 1];
    }
    int front() {
      return size == 0 ? -1 : arr[front];
    }
    private void resize(int capacity) {
      int[] tmp = new int[capacity];
      if (front < back) {
        for (int i = front; i < back; i++) {
          tmp[i - front] = arr[i];
        }
        back = back - front;
        front = 0;
      } else if (back < front || (back == front && size != 0)) {
        for (int i = front; i < arr.length; i++) {
          tmp[i - front] = arr[i];
        }
        for (int i = 0; i < back; i++) {
          tmp[arr.length - front + i] = arr[i];
        }
        back = arr.length - front + back;
        front = 0;
      } else {
        // size == 0
      }
      arr = tmp;
    }
  }
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    String[] tmp;
    Queue que = new Queue();
    int numTest = Integer.parseInt(br.readLine());
    for (int i = 0; i < numTest; i++) {
      tmp = br.readLine().split(" ");
      if (tmp[0].equals("push")) {
        que.push(Integer.parseInt(tmp[1]));
      } else if (tmp[0].equals("pop")) {
        bw.write(que.pop() + "\n");
      } else if (tmp[0].equals("size")) {
        bw.write(que.size() + "\n");
      } else if (tmp[0].equals("empty")) {
        bw.write(que.empty() + "\n");
      } else if (tmp[0].equals("back")) {
        bw.write(que.back() + "\n");
      } else if (tmp[0].equals("front")) {
        bw.write(que.front() + "\n");
      } else {
        // do nothing
      }
    }
    bw.flush();
  }
}
