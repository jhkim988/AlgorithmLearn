import java.util.*;

public class MergeSort <T extends Comparable<? super T>> implements Sort<T> {
  ArrayList<T> arrList;
  MergeSort(ArrayList<T> arrList) {
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
    int mid = (lo+hi) >> 1;
    sort(lo, mid);
    sort(mid+1, hi);

    ArrayList<T> tmp = new ArrayList<>();
    for (int i = lo; i <= mid; i++) {
      tmp.add(arrList.get(i));
    }

    int ptr1 = lo, ptr2 = mid+1;
    for (int i = 0; i < mid-lo+1; i++) {
      while (ptr2 <= hi && tmp.get(i).compareTo(arrList.get(ptr2)) >= 0) {
        arrList.set(ptr1++, arrList.get(ptr2++));
      }
      arrList.set(ptr1++, tmp.get(i));
    }
  }
  void print() {
    for (T v : arrList) System.out.print(v + " ");
  }
  public static void main(String[] args) {
    int numData = 10;
    ArrayList<Integer> arrList = new ArrayList<>();
    for (int i = 0; i < numData; i++) {
      arrList.add((int) (Math.random()*1000));
    }
    MergeSort<Integer> ms = new MergeSort<>(arrList);
    ms.sort();
    ms.print();
  }
}
