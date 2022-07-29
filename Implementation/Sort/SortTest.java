import java.util.*;

public class SortTest {
  Sort sort;
  SortTest(Sort sort) {
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
  public static void main(String[] args) {
    int numData = 1_000_000;
    ArrayList<Integer> arrList = makeRandom(numData);
    SortTest quickSort = new SortTest(new QuickSort<Integer>((ArrayList<Integer>) arrList.clone()));
    SortTest dualPivot = new SortTest(new DualPivotQuickSort<Integer>((ArrayList<Integer>) arrList.clone()));
    quickSort.timeTest();
    dualPivot.timeTest();
  }
}
