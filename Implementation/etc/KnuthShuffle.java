package etc;

public class KnuthShuffle {
  static Integer[] shuffle(Integer[] input) {
    Integer[] shuffle = input.clone();
    for (int i = input.length - 1; i > 0; i--) {
      int idx = (int) (Math.random() * (i + 1));
      Integer tmp = shuffle[idx];
      shuffle[idx] = shuffle[i];
      shuffle[i] = tmp;
    }
    return shuffle;
  }
  public static void main(String[] args) {
    final int len = 20;
    Integer[] test = new Integer[len];
    for (int i = 0; i < len; i++) {
      test[i] = i;
    }
    Integer[] shuffle = shuffle(test);
    for (int i = 0; i < len; i++) {
      System.out.println(i + ": " + test[i] + " - " + shuffle[i]);
    }
  }
}
