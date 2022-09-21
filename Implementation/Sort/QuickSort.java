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
    int pl = lo;
    int pr = hi;
    T pivot = arrList.get((pl+pr)/2);
    do {
      while (pl <= hi && arrList.get(pl).compareTo(pivot) < 0) pl++;
      while (lo <= pr && arrList.get(pr).compareTo(pivot) > 0) pr--;
      if (pl <= pr) swap(pl++, pr--);
    } while (pl <= pr);
    if (lo < pl) sort(lo, pr);
    if (pr < hi) sort(pl, hi);
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
    int numData = 200;
    ArrayList<Integer> test = new ArrayList<>();
    for (int i = 0; i < numData; i++) {
      test.add((int) (Math.random()*200));
    }
    QuickSort<Integer> qs = new QuickSort<>(test);
    qs.print();
    qs.sort();
    qs.print();
  }
}