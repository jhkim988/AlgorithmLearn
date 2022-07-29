import java.util.*;

@SuppressWarnings("unchecked")
public class SortTest {
  Sort<Integer> sort;
  SortTest(Sort<Integer> sort) {
    this.sort = sort;
  }
  void timeTest() {
    long start = System.currentTimeMillis();
    sort.sort();
    System.out.printf("Run Time - %s: %d ms\n", sort.getClass().toString(), System.currentTimeMillis()-start);
  }
  static ArrayList<Integer> makeRandom(int numData) {
    ArrayList<Integer> arrList = new ArrayList<>();
    for (int i = 0; i < numData; i++) {
      arrList.add((int) (Math.random() * 1000));
    }
    return arrList;
  }
  boolean accuracy() {
    ArrayList<Integer> ret = (ArrayList<Integer>) sort.get();
    for (int i = 0; i < ret.size()-1; i++) {
      if (ret.get(i) > ret.get(i+1)) return false;
    }
    return true;
  }
  void accuracyTest() {
    System.out.printf("Accuracy Test: %s\n", accuracy() ? "PASS" : "FAIL");
  }
  public static void main(String[] args) {
    int numData = 1_000_000;
    ArrayList<Integer> arrList = makeRandom(numData);
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
}
