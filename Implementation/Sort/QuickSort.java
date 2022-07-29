import java.util.*;

public class QuickSort <T extends Comparable<? super T>> implements Sort<T> {
  ArrayList<T> arrList;
  QuickSort(ArrayList<T> arrList) {
    this.arrList = arrList;
  }

  @Override
  public void sort() {
    sort(0, arrList.size()-1);
  }
  @Override
  public ArrayList<T> get() {
    return arrList;
  }

  public void sort(int lo, int hi) {
    if (hi <= lo) return;
    T pivot = arrList.get(hi);
    int i = lo-1; // max{x | arrList[x] < pivot}
    for (int j = lo; j < hi; j++) {
      if (arrList.get(j).compareTo(pivot) >= 0) continue;
      swap(++i, j);      
    }
    swap(++i, hi);
    sort(lo, i-1);
    sort(i+1, hi);
  } 
  private void swap(int a, int b) {
    T tmp = arrList.get(a);
    arrList.set(a, arrList.get(b));
    arrList.set(b, tmp);
  } 
  void print() {
    for (T v : arrList) {
      System.out.print(v + " ");
    }
    System.out.println();
  }
  public static void main(String[] args) {
    ArrayList<Integer> test = new ArrayList<>();
    for (int i = 0; i < 20; i++) {
      test.add((int) (Math.random()*200));
    }
    QuickSort<Integer> qs = new QuickSort<>(test);
    qs.print();
    qs.sort();
    qs.print();
  }
}