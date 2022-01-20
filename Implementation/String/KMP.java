import java.util.*;

public class KMP {
  // time complexity: O(N + M)
  // N: text length
  // M: pattern length

  // T: A B C D A B C D A B D E
  // P: A B C D A B D
  //    O O O O O O X           get information, P[0...5] same, P[6] different
  //            A B C D A B D   next find, start with pattern[3]
  // preprocess: fail -> jump pos
  public static void main(String[] args) {
    String text = "ILoveAlgorithmILoveJava";
    String pattern = "Love";
    ArrayList<Integer> indices = kmpMatch(text, pattern);
    for (int idx : indices) {
      System.out.println(idx);
    }
  }
  static ArrayList<Integer> kmpMatch(String text, String pattern) {
    ArrayList<Integer> indices = new ArrayList<>();
    int[] skip = skip(pattern);
    char[] textArr = text.toCharArray();
    char[] patternArr = pattern.toCharArray();
    
    // pointer of text, pattern
    int pt = 0;
    int pp = 0;
    while (pt < textArr.length) {
      if (textArr[pt] == patternArr[pp]) {
        pt++;
        pp++;
      }
      if (pp == patternArr.length) {
        indices.add(pt - pp + 1);
        pp = skip[pp - 1];
      } else if (pt < textArr.length && textArr[pt] != patternArr[pp]) {
        if (pp != 0) {
          pp = skip[pp - 1];
        } else {  
          pt++;
        }
      }
    }
    return indices;
  }
  static int[] skip(String pattern) {
    char[] patternArr = pattern.toCharArray();
    int[] memo = new int[patternArr.length];
    int pt = 1;
    int pp = 0;
    while (pt < patternArr.length) {
      if (patternArr[pt] == patternArr[pp]) {
        pp++;
        memo[pt] = pp;
        pt++;
      } else if (pp == 0) {
        memo[pt] = 0;
        pt++;
      } else {
        pp = memo[pp - 1];
      }
    }
    return memo;
  }
}