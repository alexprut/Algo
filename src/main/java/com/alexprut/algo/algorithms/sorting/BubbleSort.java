package com.alexprut.algo.algorithms.sorting;

public class BubbleSort {

  /**
   *
   * Time complexity:
   * O(n^2) in the worst case, O(n) in the best case, when the array is sorted
   *
   * Space complexity:
   * O(n) total with O(1) auxiliary
   */
  public static int[] bubbleSort(int[] arr) {
    boolean swapped = true;
    while (swapped) {
      swapped = false;
      for (int i = 1; i < arr.length; i++) {
        if (arr[i - 1] > arr[i]) {
          int tmp = arr[i];
          arr[i] = arr[i - 1];
          arr[i - 1] = tmp;
          swapped = true;
        }
      }
    }

    return arr;
  }
}
