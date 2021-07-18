import java.io.*;

public class BOJ10814 {
  private static class User implements Comparable<User>{
    int age;
    String name;
    // User(int age, String name) {
    //   this.age = age;
    //   this.name = name;
    // }
    User(String info) {
      String[] tmp = info.split(" ");
      this.age = Integer.parseInt(tmp[0]);
      this.name = tmp[1];
    }

    public String toString() {
      return age + " " + name;
    }
    @Override
    public int compareTo(User other) {
      if (this.age > other.age) {
        return 1;
      } else if (this.age < other.age) {
        return -1;
      } else {
        return 0;
      }
    }
  }
  public static void main(String[] args) {
    // stable sorting
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
      
      int numData = Integer.parseInt(br.readLine());
      User[] data = new User[numData];
      for (int i = 0; i < numData; i++) {
        data[i] = new User(br.readLine());
      }
      br.close();
  
      sort(data);

      for (int i = 0; i < numData; i++) {
        bw.write(data[i] + "\n");
      }
      bw.flush();
      bw.close();
    } catch (IOException e) {
      // do nothing
    }
  }
  static void sort(User[] data) {
    User[] buff = new User[data.length];
    mergeSort(data, 0, data.length - 1, buff);
    buff = null;
  }
  static void mergeSort(User[] data, int left, int right, User[] buff) {
    if (left < right){
      int center = (left + right) / 2;
      int p = 0;
      int i;
      int j = 0;
      int k = left;
  
      mergeSort(data, left, center, buff);
      mergeSort(data, center + 1, right, buff);
  
      for (i = left; i <= center; i++) {
        buff[p++] = data[i]; 
      }
      // Merge buff[] and data[center + 1, right]
      while (i <= right && j < p) {
        data[k++] = (buff[j].compareTo(data[i]) <= 0) ? buff[j++] : data[i++];
      }
      while (j < p) {
        data[k++] = buff[j++];
      }
    }
  }
}
