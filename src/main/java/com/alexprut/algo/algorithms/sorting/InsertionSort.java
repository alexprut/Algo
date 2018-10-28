package com.alexprut.algo.algorithms.sorting;

import com.alexprut.algo.Utils;

public class InsertionSort {

  /**
   * Time complexity: O(n^2) in the worst case, when the array is sorted in reverse order O(n) best
   * case, when the array is already sorted
   *
   * <p>Space complexity; O(n) total with O(1) auxiliary In-place algorithm, it rearranges the
   * numbers within the given array, with at most a constant number of them stored outside the array
   * at any time
   */
  public static int[] insertionSort(int[] arr) {
    for (int i = 1; i < arr.length; i++) {
      int j = i;
      while (j > 0 && arr[j - 1] > arr[j]) {
        Utils.swap(arr, j, j - 1);
        j--;
      }
    }
    return arr;
  }
}
