import java.util.*;

@SuppressWarnings("unchecked")
public class SortTest {
  Sort<Integer> sort;
  SortTest(Sort<Integer> sort) {
    this.sort = sort;
  }
  void timeTest() {
    long start = System.currentTimeMillis();
    shuffle();
    sort.sort();
    System.out.printf("Run Time - %s: %d ms\n", sort.getClass().toString(), System.currentTimeMillis()-start);
  }
  void shuffle() {
    ArrayList<Integer> arr = (ArrayList<Integer>) sort.get();
    for (int i = arr.size()-1; i > 0; i--) {
      int rIdx = (int) (Math.random()*(i+1));
      swap(arr, i, rIdx);
    }
  }
  void swap(ArrayList<Integer> arr, int a, int b) {
    int tmp = arr.get(a);
    arr.set(a, arr.get(b));
    arr.set(b, tmp);
  }
  boolean accuracy() {
    ArrayList<Integer> ret = (ArrayList<Integer>) sort.get();
    for (int i = 0; i < ret.size()-1; i++) {
      if (ret.get(i) > ret.get(i+1)) return false;
    }
    return true;
  }
  void accuracyTest() {
    System.out.printf("Accuracy Test - %s: %s\n", sort.getClass().toString(), accuracy() ? "PASS" : "FAIL");
  }
  public static void main(String[] args) {
    int numData = 10_000_000;
    // ArrayList<Integer> arrList = makeRandomTestCase(numData, 10000); // Too small range, Too slow quick sort
    ArrayList<Integer> arrList = makeSortedTestCase(numData); // worst case of Quick Sort, must shuffle it.
    SortTest quickSort = new SortTest(new QuickSort<Integer>((ArrayList<Integer>) arrList.clone()));
    SortTest dualPivot = new SortTest(new DualPivotQuickSort<Integer>((ArrayList<Integer>) arrList.clone()));
    SortTest mergeSort = new SortTest(new MergeSort<Integer>((ArrayList<Integer>) arrList.clone()));
    
    quickSort.timeTest();
    dualPivot.timeTest();
    mergeSort.timeTest();

    quickSort.accuracyTest();
    dualPivot.accuracyTest();
    mergeSort.accuracyTest();
  } 
  static ArrayList<Integer> makeRandomTestCase(int numData, int range) {
    ArrayList<Integer> arrList = new ArrayList<>();
    for (int i = 0; i < numData; i++) {
      arrList.add((int) (Math.random() * 1000));
    }
    return arrList;
  }
  static ArrayList<Integer> makeSortedTestCase(int numData) {
    ArrayList<Integer> arrList = new ArrayList<>();
    for (int i = 0; i < numData; i++) {
      arrList.add(i);
    }
    return arrList;
  }
}
