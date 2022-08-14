public class CountingSort {
  int[] arr;
  int min, max;
  CountingSort(int[] arr, int min, int max) {
    this.arr = arr;
    this.min = min;
    this.max = max;
  } 
  void sort() {
    int[] count = new int[max-min+1];
    for (int x: arr) {
      count[x-min]++;
    }
    int ptr = 0;
    for (int i = 0; i < count.length; i++) {
      for (int x = 0; x < count[i]; x++) {
        arr[ptr++] = i+min;
      }
    }
  }
  public static void main(String[] args) {
    int[] test = {10, 20, 30, -10, -20, -30};
    CountingSort cs = new CountingSort(test, -30, 30);
    cs.sort();
    for (int x: test) {
      System.out.println(x);
    }
  }
}
