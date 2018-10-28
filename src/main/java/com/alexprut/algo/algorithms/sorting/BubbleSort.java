package com.alexprut.algo.algorithms.sorting;

import com.alexprut.algo.Utils;

public class BubbleSort {

  /**
   * Time complexity: O(n^2) in the worst case, O(n) in the best case, when the array is sorted
   *
   * <p>Space complexity: O(n) total with O(1) auxiliary
   */
  public static int[] bubbleSort(int[] arr) {
    boolean swapped = true;
    while (swapped) {
      swapped = false;
      for (int i = 1; i < arr.length; i++) {
        if (arr[i - 1] > arr[i]) {
          Utils.swap(arr, i, i - 1);
          swapped = true;
        }
      }
    }

    return arr;
  }
}
