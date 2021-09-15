package DP;

import java.util.*;

public class LIS {
  // LIS: Longest Increasing Sequence
  public static void main(String[] args) {
    final int len = 10;
    final int valLim = 30;
    int[] seq = new int[len];
    for (int i = 0; i < len; i++) {
      seq[i] = (int) (Math.random() * valLim);
    }
    System.out.println(Arrays.toString(seq));

    // Get Length of LIS
    // length of LIS: ptr - 1;
    int[] table = new int[len];
    int ptr = 1;
    for (int  i = 0; i < len; i++) {
      if (table[ptr - 1] < seq[i]) {
        table[ptr++] = seq[i];
      } else {
        int find = Arrays.binarySearch(table, 0, ptr, seq[i]);
        if (find < 0) {
          find = -(find + 1);
        }
        table[find] = seq[i];
      }
    } 
    System.out.println(ptr - 1);
  }
}