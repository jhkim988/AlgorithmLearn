import java.util.*;

public class DualPivotQuickSort <T extends Comparable<? super T>> implements Sort<T> {
  ArrayList<T> arrList;
  DualPivotQuickSort(ArrayList<T> arrList) {
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
    int left = lo, right = hi;
    
    if (arrList.get(left).compareTo(arrList.get(right)) > 0) swap(left, right);
    T leftPivot = arrList.get(left), rightPivot = arrList.get(right);
    for (int ptr = left+1; ptr <= right-1; ptr++) {
      if (arrList.get(ptr).compareTo(leftPivot) < 0) {
        if (ptr-left != 1) swap(left, left+1);
        swap(left, ptr);
        left++;
      } else if (arrList.get(ptr).compareTo(rightPivot) > 0) {
        if (right-ptr != 1) swap(right-1, ptr);
        swap(right, ptr);
        right--;
      }
    }
    sort(lo, left-1);
    sort(left+1, right-1);
    sort(right+1, hi);
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
    int numData = 10;
    ArrayList<Integer> arrList = new ArrayList<>();
    for (int i = 0; i < numData; i++) {
      arrList.add((int) (Math.random()*1000));
    }
    DualPivotQuickSort<Integer> dualPivot = new DualPivotQuickSort<>(arrList);
    dualPivot.sort();
    dualPivot.print();
  }
}
